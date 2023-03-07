package ThreadLocal;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;


/**
 * 类比  RequestContextHolder-如何使用ThreadLocal
 */
public class ThreadLocalTest {

    /**
     * 使用线程池工具创建只有5个线程的线程池
     */
    static ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

    /**
     * 使用guava的ThreadFactoryBuilder来创建线程池
     */
    private static ThreadFactory nameFactory = new ThreadFactoryBuilder()
            .setNameFormat("demo-pool-%d").build();

    private static ExecutorService pool = new ThreadPoolExecutor(5, 10,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024),
            nameFactory,
            new ThreadPoolExecutor.AbortPolicy());


    /**
     * -Xms125 -Xmx125
     */
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            pool.execute(() -> {
                BizData bizData = new BizData();
                // 模拟在在每个线程中创建 HashMap存放10M大小的信息
                /*Map<Thread, BizData> map = new HashMap<>();
                map.put(Thread.currentThread(),bizData);
                // 业务操作
                map.get(Thread.currentThread());*/

                // ----------------------------------------
                // 模拟在threadLocal中存放10M大小的信息
                ThreadLocal<Object> local = new ThreadLocal<>();
                local.set(bizData);
                // 业务操作
                local.get(); // java.lang.OutOfMemoryError: Java heap space
                // 用完remove
//                local.remove();
                System.out.println(Thread.currentThread().getName() + "执行完成");
            });
        }
    }

    /**
     * 模拟业务数据
     */
    static class BizData {
        // 10M
        private byte[] data = new byte[1024 * 1024 * 10];
    }
}
