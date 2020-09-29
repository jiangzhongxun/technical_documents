package com.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 关于锁的8个问题
 * 1.标准情况下，两个线程先打印发短信还是打电话？ 结果是先打印发短信
 * 2.sendMsg 延迟4秒，两个线程先打印发短信还是打电话？ 结果是先打印发短信
 *
 * @Author: bai
 * @DateTime: 2020/7/25 15:37
 */
public class TestLock1 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            phone.sendMsg();
        }, "A").start();
        // 延迟1秒
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            phone.call();
        }, "B").start();
    }
}

class Phone {
    public synchronized void sendMsg() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public synchronized void call() {
        System.out.println("打电话");
    }
}