package com.pool;

import java.util.concurrent.*;

/**
 * 线程池 Executors 工具类
 * 3大方法、7大参数、四种策略
 *
 * @Author: bai
 * @DateTime: 2020/7/26 9:58
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        // 线程池3大方法
        // ExecutorService threadPool = Executors.newSingleThreadExecutor();//单个线程
        // ExecutorService threadPool = Executors.newFixedThreadPool(5);//创建一个固定的线程池大小
        // ExecutorService threadPool = Executors.newCachedThreadPool();// 创建一个弹性的线程池,可扩展

        // 自定义线程池:工作中非常推荐使用自定义线程池
        // 最大线程池的定义,不同系统不同 CPU
        System.out.println("本电脑最大 CPU 核数：" + Runtime.getRuntime().availableProcessors());
        ExecutorService threadPool = new ThreadPoolExecutor(
                2, Runtime.getRuntime().availableProcessors(), 3, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());

        // 四种拒绝策略
        // new ThreadPoolExecutor.AbortPolicy()  多余的线程不处理,会抛出异常 java.util.concurrent.RejectedExecutionException
        // new ThreadPoolExecutor.CallerRunsPolicy() 哪里来的回哪去,多余的线程不处理,会返回给 main 线程处理
        // new ThreadPoolExecutor.DiscardPolicy() 多余的线程会丢弃
        // new ThreadPoolExecutor.DiscardOldestPolicy() 多余的线程会尝试与最早的线程竞争,竞争成功了就会执行,失败了就会丢弃

        try {
            // 最高承载 = queue + manPoolSize
            for (int i = 1; i <= 15; i++) {
                // 使用了线程池，就是用线程池提供的 execute 方法创建线程
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "-> OK");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();//关闭线程池
        }
    }
}
