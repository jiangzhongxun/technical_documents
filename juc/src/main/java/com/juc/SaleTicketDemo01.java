package com.juc;

/**
 * 传统方式加锁
 *
 * @Author: bai
 * @DateTime: 2020/7/25 13:45
 */
public class SaleTicketDemo01 {
    public static void main(String[] args) {
        // 并发编程,多条线程操作统一资源
        // 首先准备好要操作的资源类 Ticket
        Ticket ticket = new Ticket();
        // 准备好多条线程去操作资源
        // Runnable 的本质是 @FunctionalInterface 函数式接口,jdk8 开始就支持了 lamda 表达式： () -> {代码}
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.calc();
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.calc();
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.calc();
            }
        }, "C").start();

    }
}

/**
 * 企业级开发最注重解耦
 * 正常的资源类中只应该包含两样东西 属性、方法
 */
class Ticket {
    /**
     * 票数
     */
    private int num = 30;

    /**
     * 买票操作
     * synchronized 的本质就是排队，加锁
     */
    public synchronized void calc() {
        if (num > 0) {
            System.out.println(Thread.currentThread().getName() + "买到第" + (num--) + "张票，剩余" + num);
        }
    }
}
