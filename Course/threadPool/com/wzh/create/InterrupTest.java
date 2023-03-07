package com.wzh.create;

/**
 * @author wangzouhuax
 * @Description 中断线程
 * @create 2023-02-03 14:57
 */
public class InterrupTest {

    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
//                while (true) {
                while (Thread.interrupted()) {  // 判断当前线程有没有接受到中断信号
                    System.out.println("thread正在执行");
                }
            }
        });
        thread.start();

        Thread.sleep(300);

        // 中断这个线程。
        thread.interrupt();

        System.out.println("中断线程thread ======================");
    }
}
