package com.bai.java8.forkjoin;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/12/3 22:06
 */
public class TestForkJoin {
    public static void main(String[] args) {
        test3();
    }

    /**
     * 单线程计算
     */
    private static void test3() {
        Instant start = Instant.now();

        long sum = 0;
        for (long i = 0; i < 100_0000_0000L; i++) {
            sum += i;
        }
        System.out.println(sum);

        Instant end = Instant.now();
        // 10亿耗时毫秒数为：262 - 258
        // 100亿耗时毫秒数为：2581
        System.out.println("耗时：" + Duration.between(start, end).toMillis());
    }

    /**
     * 任务拆分
     */
    private static void test2() {
        Instant start = Instant.now();

        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinCalculate(0, 100_0000_0000L);
        Long sum = pool.invoke(task);
        System.out.println(sum);

        Instant end = Instant.now();
        // 10亿耗时毫秒数为：234 - 289
        // 100亿耗时毫秒数为：1970
        System.out.println("耗时：" + Duration.between(start, end).toMillis());
    }

    /**
     * java8 并行流
     */
    private static void test1() {
        Instant start = Instant.now();

        LongStream.rangeClosed(0, 100_0000_0000L)
                .parallel()
                .reduce(0, Long::sum);

        Instant end = Instant.now();
        // 耗时毫秒数为：1121 - 1159
        System.out.println("耗时：" + Duration.between(start, end).toMillis());
    }
}
