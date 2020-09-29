package com.lock;

import java.util.concurrent.TimeUnit;

/**
 * 死锁测试
 *
 * @author 南独酌酒 <211425401@126.com>
 * @date 2020/9/9 22:22
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        new Thread(new MyThread("lockA", "lockB"), "T1").start();
        new Thread(new MyThread("lockB", "lockA"), "T2").start();
    }
}

class MyThread implements Runnable {
    private String lockA;
    private String lockB;

    public MyThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "lock:" + lockA + "=>get" + lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "lock:" + lockB + "=>get" + lockA);
            }
        }
    }
}
