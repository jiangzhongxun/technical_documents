package com.forkjoin;

import java.util.stream.LongStream;

/**
 * ForkJoin 测试
 * ForkJoin 特点: 任务分支、任务窃取、对于大数据量时效率比较好
 *
 * @Author: bai
 * @DateTime: 2020/8/8 13:47
 */
public class ForkJoinTest {
    public static void main(String[] args) {
        // Stream 并行流计算 0 到 十亿的合
        long begin = System.currentTimeMillis();
        long sum = LongStream.rangeClosed(0L, 10_0000_0000L).parallel().reduce(0, Long::sum);
        long end = System.currentTimeMillis();
        // sum=500000000500000000, 耗时:169
        System.err.println("sum=" + sum + ", 耗时:" + (end - begin));
    }
}
