package com.callcable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author: bai
 * @DateTime: 2020/7/25 16:57
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        MyThread thread = new MyThread();
        FutureTask futureTask = new FutureTask(thread);
        new Thread(futureTask).start();     // 有缓存
        Integer result = (Integer) futureTask.get();    // get 方法获取返回值时可能会堵塞
        System.out.println(result);
    }
}

class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        // 产生耗时操作
        return 1024;
    }
}
