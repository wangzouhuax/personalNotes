package com.wzh.create;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description ThreadPoolExecutor创建线程，创建过程源码分析
 * @author wangzouhuax
 * @create 2023-02-03 12:01
 */
public class ThreadPoolExecutorCreate {

    public static void main(String[] args) {
        /*
         * 核心线程数
         * 最大线程数
         * 超出核心线程数之外的线程的空闲存活时间
         * 存放待执行的任务：必须是一个阻塞队列。
         *          LinkedBlockingQueue  容量没有上限的队列
         * 线程工厂
         * 任务拒绝策略
         */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                10, 20,
                0L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy()
        );


        // 允许核心线程数线程超时  默认为false   表示如果任务队列中任务没了，核心线程数永远不会消失
        // 修改后，如果任务队列中任务没了，核心线程数也有可能会消失（ 会调用 workQueue.poll(keepAliveTIme,TimeUnit.SECONDS) 阻塞定长时间）
        executor.allowCoreThreadTimeOut(true);

        // 线程池在执行任务时才会去创建线程
        // 创建除的线程  每个线程不停的执行
        // while (task != null || (task = getTask()) != null) {
        //
        // }
        // getTask() -> 核心代码：
        //      try {
        //            // workQueue：阻塞队列  poll()/take() 都会阻塞，如果队列有内容，就会拿出任务，如果没有则阻塞
        //            // 区别：poll()  可设置超时时间    超过核心线程数时标记  boolean timed = allowCoreThreadTimeOut || wc > corePoolSize;
        //            // take() 无限阻塞  知道队列中有任务过来，再继续拿
        //            Runnable r = timed ? workQueue.poll(keepAliveTime, TimeUnit.NANOSECONDS) : workQueue.take();
        //            if (r != null)
        //                return r;
        //            timedOut = true;
        //        } catch (InterruptedException retry) {
        //            timedOut = false;
        //        }
        // 没有任务  阻塞获取不到任务  跳出循环 boolean completedAbruptly = false;
        // finally {  processWorkerExit(w, completedAbruptly);}
        // processWorkerExit -> 当前线程池中线程个数 >= 核心线程数 线程直接结束
        //                      没有则在消失之前重新创建一个线程，替代者
        executor.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("业务代码执行");
                throw new NullPointerException();
            }
        });

        executor.shutdown(); // 将剩余任务执行完并且工作线程清零
        executor.shutdownNow();// 不执行剩余任务，直接将线程池清掉。
        // 先中断线程池中的线程（getTask()获取任务为空，跳出循环，执行processWorkerExit(),且线程池状态是stop时，直接退出。 ），
    }


    /*
     * 源码分析：executor.execute()
     *      private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
     *      ctl 控制 记录线程池中的状态
     */
}
