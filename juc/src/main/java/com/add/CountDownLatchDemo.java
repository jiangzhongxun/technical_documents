package com.add;

import java.util.concurrent.CountDownLatch;

/**
 * 计数器
 *
 * @Author: bai
 * @DateTime: 2020/7/25 17:14
 */
public class CountDownLatchDemo {
    public static void main(String[] args) {
        // 总数是6,必须在执行任务的时候在使用
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "-> GO out");
                /**
                 * countDown() 减少锁存器的计数，如果计数达到零，释放所有等待的线程。
                 * 如果当前计数大于零，则它将递减。 如果新计数为零，则所有等待的线程都将被重新启用以进行线程调度。
                 * 如果当前计数等于零，那么没有任何反应。
                 */
                countDownLatch.countDown(); // 数量 -1
            }, String.valueOf(i)).start();
        }
        try {
            // 如果计数达到零，则方法返回值为 true
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Close Door");
    }
}
