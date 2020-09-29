package com.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock 锁的方式使用
 *
 * @Author: bai
 * @DateTime: 2020/7/25 13:45
 */
public class SaleTicketDemo02 {
    public static void main(String[] args) {
        Ticket2 ticket2 = new Ticket2();
        new Thread(() -> { for (int i = 0; i < 40; i++) ticket2.calc(); } ,"A").start();
        new Thread(() -> { for (int i = 0; i < 40; i++) ticket2.calc(); } ,"B").start();
        new Thread(() -> { for (int i = 0; i < 40; i++) ticket2.calc(); } ,"C").start();
    }
}

/**
 * Lock 锁三步骤
 * 1. new ReentrantLock();
 * 2. lock.lock();    // 加锁
 * 3. finally -> lock.unlock();  // 解锁
 */
class Ticket2 {
    /**
     * 票数
     */
    private int num = 30;

    Lock lock = new ReentrantLock();

    /**
     * 买票操作
     */
    public void calc() {
        lock.lock();    // 加锁
        try {
            if (num > 0) {
                System.out.println(Thread.currentThread().getName() + "买到第" + (num--) + "张票，剩余" + num);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();  // 解锁
        }
    }
}
