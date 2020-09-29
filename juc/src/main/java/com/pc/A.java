package com.pc;

/**
 * 模拟生产者消费者问题
 *
 * @Author: bai
 * @DateTime: 2020/7/25 14:31
 */
public class A {
    public static void main(String[] args) {
        Data data = new Data();
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
class Data {
    private int num = 0;

    // +1
    public synchronized void increment() throws InterruptedException {
        // thia.wait() 可能会出现虚假唤醒等情况
        // 所以官方推荐 this.wait() 方法在 while 块中使用
        while (num != 0) {
            //等待
            this.wait();
        }
        // 干活
        this.num++;
        System.out.println(Thread.currentThread().getName() + "->" + num);
        // 通知其他线程
        this.notifyAll();
    }

    // -1
    public synchronized void decrement() throws InterruptedException {
        // thia.wait() 可能会出现虚假唤醒等情况
        // 所以官方推荐 this.wait() 方法在 while 块中使用
        while (num == 0) {
            // 等待
            this.wait();
        }
        // 干活
        this.num--;
        System.out.println(Thread.currentThread().getName() + "->" + num);
        // 通知其他线程
        this.notifyAll();
    }
}
