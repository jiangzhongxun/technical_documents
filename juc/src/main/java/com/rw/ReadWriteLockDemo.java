package com.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 可重入读写锁：读取可以多条线程一起执行,写入时只能一条线程执行
 *
 * @Author: bai
 * @DateTime: 2020/7/26 8:35
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCacheLock myCache = new MyCacheLock();

        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                // 5条线程执行写入操作
                myCache.put(temp + "", temp + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(() -> {
                // 5条线程执行读取操作
                myCache.get(temp + "");
            }, String.valueOf(i)).start();
        }
    }
}

/**
 * 加锁缓存
 */
class MyCacheLock {
    private volatile Map<String, Object> map = new HashMap<>();
    /**
     * ReadWriteLock 提供了更加细粒度的读写锁
     * 写锁：同一时刻只允许一个线程操作
     * 读锁：可以同时允许多个线程操作
     */
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /**
     * 添加
     *
     * @param key   键
     * @param value 值
     */
    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();// 写入时加锁
        try {
            System.out.println(Thread.currentThread().getName() + "-> 写入" + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "-> 写入OK");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();//写入完成解锁
        }
    }

    /**
     * 获取
     *
     * @param key 键
     */
    public void get(String key) {
        readWriteLock.readLock().lock();// 读取时加锁
        try {
            System.out.println(Thread.currentThread().getName() + "-> 读取" + key);
            map.get(key);
            System.out.println(Thread.currentThread().getName() + "-> 读取OK");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();// 读取完成解锁
        }
    }
}


/**
 * 无锁缓存
 */
class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();

    /**
     * 添加
     *
     * @param key   键
     * @param value 值
     */
    public void put(String key, Object value) {
        System.out.println(Thread.currentThread().getName() + "-> 写入" + key);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + "-> 写入OK");
    }

    /**
     * 获取
     *
     * @param key 键
     */
    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + "-> 读取" + key);
        map.get(key);
        System.out.println(Thread.currentThread().getName() + "-> 读取OK");
    }
}
