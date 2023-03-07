package com.wzh.create;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @Description Executor方式创建线程
 * @author wangzouhuax
 * @create 2023-02-03 10:24
 */
public class ExecutorCreate {

    public static void main(String[] args) {
        /*
         * 生成线程数量固定的线程池
         *  return new ThreadPoolExecutor(nThreads 核心线程数, nThreads（传入的参数）最大线程数,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
         *  LinkedBlockingQueue  容量没有上限的队列
         */
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(1);

        /*
         * 其他等价的是newFixedThreadPool(1)不同，返回的执行器保证不可重新配置以使用额外的线程。
         * return new FinalizableDelegatedExecutorService(new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()));
         */
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        /*
         *  根据需要创建新的线程，但会在以前构建的线程可用时重用它们
         *  return new ThreadPoolExecutor(0 核心线程数, Integer.MAX_VALUE （2^31-1）最大线程数（无限制）, 60L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());
         *  Integer.MAX_VALUE 超过内存上线  内存不足
         *  SynchronousQueue 本身不存储任务，只是将任务直接转发
         */
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        // (corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS, new DelayedWorkQueue())
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        //
        ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor();

    }
}
