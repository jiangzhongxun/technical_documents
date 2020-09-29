package com.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: bai
 * @DateTime: 2020/7/25 14:55
 */
public class B {
    public static void main(String[] args) {
        Data2 data = new Data2();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    data.increment();// +1
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    data.decrement();// -1
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    data.increment();// +1
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    data.decrement();// -1
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "D").start();
    }
}

/**
 * 资源类
 * 生产者消费者六字真言：等待、干活、通知
 */
class Data2 {
    private int num = 0;

    Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    //condition.await();  // 等待
    //condition.signalAll();  // 唤醒全部

    // +1
    public void increment() throws InterruptedException {
        lock.lock();    // 加锁
        try {
            while (num != 0) {
                condition.await();//等待
            }
            this.num++;
            System.out.println(Thread.currentThread().getName() + "->" + num);
            condition.signalAll();// 通知其他线程
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();  //解锁
        }
    }

    // -1
    public void decrement() throws InterruptedException {
        lock.lock();;   //加锁
        try {
            while (num == 0) {
                condition.await();// 等待
            }
            this.num--;
            System.out.println(Thread.currentThread().getName() + "->" + num);
            condition.signalAll(); // 通知其他线程
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();  //解锁
        }
    }
}
