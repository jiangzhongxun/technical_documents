package com.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 自定义自旋锁
 *
 * @author 南独酌酒 <211425401@126.com>
 * @date 2020/9/9 22:05
 */
public class SpinLockDemo {
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    /**
     * 加锁
     */
    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "==> myLock");

        // 自旋锁
        do {

        } while (!atomicReference.compareAndSet(null, thread));
    }

    /**
     * 解锁
     */
    public void myUnLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "==> myUnLock");
        atomicReference.compareAndSet(thread, null);
    }

    /**
     * 测试自定义的自旋锁
     *
     * @param args
     */
    public static void main(String[] args) {
        SpinLockDemo spinLock = new SpinLockDemo();

        new Thread(() -> {
            spinLock.myLock();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                spinLock.myUnLock();
            }
        }, "A").start();

        new Thread(() -> {
            spinLock.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                spinLock.myUnLock();
            }
        }, "B").start();
    }
}
