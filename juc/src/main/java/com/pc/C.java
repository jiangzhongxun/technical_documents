package com.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过 Condition 来精准唤醒指定线程 A -> B -> C
 * @Author: bai
 * @DateTime: 2020/7/25 15:23
 */
public class C {
    public static void main(String[] args) {
        Data3 data  = new Data3();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printA();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printB();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.printC();
            }
        }, "C").start();
    }
}

// 资源类
class Data3 {
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();
    private int num = 1;    // 1A 2B 3C

    public void printA() {
        lock.lock();    // 加锁
        try {
            while (num != 1) {
                condition1.await(); // 等待
            }
            System.out.println(Thread.currentThread().getName() + "-> AAAAA");
            // 唤醒指定线程 B
            this.num = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();  // 解锁
        }
    }

    public void printB() {
        lock.lock();    // 加锁
        try {
            while (num != 2) {
                condition2.await(); // 等待
            }
            System.out.println(Thread.currentThread().getName() + "-> BBBBB");
            // 唤醒指定线程 C
            this.num = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();  // 解锁
        }
    }

    public void printC() {
        lock.lock();    // 加锁
        try {
            while (num != 3) {
                condition3.await(); // 等待
            }
            System.out.println(Thread.currentThread().getName() + "-> CCCCC");
            // 唤醒指定线程 A
            this.num = 1;
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();  // 解锁
        }
    }
}
