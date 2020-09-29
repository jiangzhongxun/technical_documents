package com.bq;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Author: bai
 * @DateTime: 2020/7/26 9:10
 */
public class Test {
    public static void main(String[] args) {
        test04();
    }

    /**
     * 抛出异常
     */
    public static void test01() {
        ArrayBlockingQueue queue = new ArrayBlockingQueue(3);

        System.out.println(queue.add("a"));
        System.out.println(queue.add("b"));
        System.out.println(queue.add("c"));

        System.out.println(queue.element());    // 检测队首元素

        // java.lang.IllegalStateException: Queue full 队列已满
        // System.out.println(queue.add("d"));

        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());

        // java.util.NoSuchElementException 没有元素异常
        // System.out.println(queue.remove());
    }

    /**
     * 不会抛出异常,有返回值
     */
    public static void test02() {
        ArrayBlockingQueue queue = new ArrayBlockingQueue(3);

        System.out.println(queue.offer("a"));
        System.out.println(queue.offer("b"));
        System.out.println(queue.offer("c"));
        // System.out.println(queue.offer("d")); 返回 false 不会抛出异常

        System.out.println(queue.peek());   // 检测队首元素方法

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        // System.out.println(queue.poll());   返回 null 不会抛出异常
    }

    /**
     * 等待,阻塞(一直堵塞)
     */
    public static void test03() {
        ArrayBlockingQueue queue = new ArrayBlockingQueue(3);

        try {
            // put 堵塞式存储
            queue.put("a");
            queue.put("b");
            queue.put("c");
//            queue.put("d");   存不进去会一直堵塞等待

            // take 堵塞式获取
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
//            System.out.println(queue.take()); 取不出也会一直阻塞等待
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 等待,堵塞(超时等待)
     */
    public static void test04() {
        ArrayBlockingQueue queue = new ArrayBlockingQueue(3);

        try {
            System.out.println(queue.offer("a"));
            System.out.println(queue.offer("b"));
            System.out.println(queue.offer("c"));
            System.out.println(queue.offer("d", 2, TimeUnit.SECONDS));// 超过2秒插入不进去就不等待了

            System.out.println(queue.poll());
            System.out.println(queue.poll());
            System.out.println(queue.poll());
            System.out.println(queue.poll(2,TimeUnit.SECONDS));// 超过2秒取不出来就不等待了
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
