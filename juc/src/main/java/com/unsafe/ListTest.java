package com.unsafe;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * java.util.ConcurrentModificationException 并发修改异常
 *
 * @Author: bai
 * @DateTime: 2020/7/25 16:01
 */
public class ListTest {
    public static void main(String[] args) {
        /**
         * 解决方案
         * 1.List<String> list = new Vector<>();
         * 2.List<String> list = Collections.synchronizedList(new ArrayList<>());
         * 3.List<String> list = new CopyOnWriteArrayList<>();
         */

        List<String> list = new CopyOnWriteArrayList<>();
        Map<String, Object> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
