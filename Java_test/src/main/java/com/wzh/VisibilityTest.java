package com.wzh;

/**
 * @author Fox
 *
 * -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -Xcomp
 * hsdis-amd64.dll
 *  可见性案例
 */
public class VisibilityTest {
    //  storeLoad  JVM内存屏障  ---->  (汇编层面指令)  lock; addl $0,0(%%rsp)
    // lock前缀指令不是内存屏障的指令，但是有内存屏障的效果   缓存失效
    // Java 关键字，Openjdk源码，
    private volatile boolean flag = true;

    // ⑤ 可以跳出循环
    //private volatile Integer count = 0;
    // ⑥ 可以跳出循环
    //private Integer count = 0;
    private int count = 0;

    public void refresh() {
        flag = false;
        System.out.println(Thread.currentThread().getName() + "修改flag:"+flag);
    }

    public void load() {
        System.out.println(Thread.currentThread().getName() + "开始执行.....");
        while (flag) {
            //TODO  业务逻辑
            count++;
            //JMM模型    内存模型： 线程间通信有关   共享内存模型
            //没有跳出循环   可见性的问题  ---》 threadA 对 threadB 的操作不可见
            //能够跳出循环   ①内存屏障   --> 调用内存屏障，调用JVM接口storeFence()，保证可见性 （不安全）
            //UnsafeFactory.getUnsafe().storeFence();
            //能够跳出循环    ?   ②释放时间片，上下文切换 -->还原线程  去主程序中重新加载上下文最新的值：flag=true
            //Thread.yield();  // 暂停当前正在执行的线程对象（及放弃当前拥有的cup资源），并执行其他线程。 （程序计数器PC、还原现场。加载上下文）
            // 线程上下文切换时间大概在5-10ms左右

            //能够跳出循环    ③println()底层使用synchronized 锁机制能够保证可见性 --> 底层还是调用JVM接口storeFence() -->内存屏障
            //System.out.println(count);

            // ④当前线程发放一个许可
            //LockSupport.unpark(Thread.currentThread());

            // 缓存淘汰 可见性保证
            //shortWait(1000000); //1ms
            //shortWait(1000); // 1ns

//            try {
//                Thread.sleep(1);   //内存屏障
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            //总结：  Java中可见性如何保证？ 方式归类有两种：
            //1.  jvm层面 storeLoad内存屏障    ===>  x86   lock替代了mfence
            // 2.  上下文切换   Thread.yield();


        }
        System.out.println(Thread.currentThread().getName() + "跳出循环: count=" + count);
    }

    public static void main(String[] args) throws InterruptedException {
        VisibilityTest test = new VisibilityTest();

        // 线程threadA模拟数据加载场景
        Thread threadA = new Thread(() -> test.load(), "threadA");
        threadA.start();

        // 让threadA执行一会儿
        Thread.sleep(1000);
        // 线程threadB通过flag控制threadA的执行时间
        Thread threadB = new Thread(() -> test.refresh(), "threadB");
        threadB.start();

    }


    public static void shortWait(long interval) {
        long start = System.nanoTime();
        long end;
        do {
            end = System.nanoTime();
        } while (start + interval >= end);
    }
}
