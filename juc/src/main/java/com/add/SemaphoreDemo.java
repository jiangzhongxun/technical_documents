package com.add;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Author: bai
 * @DateTime: 2020/7/25 17:36
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        // 模拟抢车位 3个车位
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();// 获取
                    System.out.println(Thread.currentThread().getName() + "-> 抢到了车位");
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "-> 离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();// 释放
                }
            },String.valueOf(i)).start();
        }
    }
}
