package com.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 异步回调 CompletableFuture
 *
 * @Author: bai
 * @DateTime: 2020/9/7 21:44
 */
public class Demo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*// 没有返回值的 runAsync 异步回调
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "runAsync->Void");
        });
        System.out.println("123");
        completableFuture.get(); // 获取异步阻塞结果*/

        // 有返回值的 supplyAsync 异步回调
        // error/success
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "supplyAsync->Void");
            int i = 10 / 0;
            return 1024;
        });

        System.out.println(completableFuture.whenComplete((t, u) -> { // 正常回调
            System.out.println("t->" + t); // 正常的结果
            System.out.println("u->" + u); // 错误的结果  java.lang.ArithmeticException: / by zero
        }).exceptionally((e) -> { // 错误回调
            System.out.println(e.getMessage());
            return 404; // 返回错误信息
        }).get());

    }
}
