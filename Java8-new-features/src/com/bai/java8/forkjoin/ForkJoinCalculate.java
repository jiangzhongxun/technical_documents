package com.bai.java8.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/12/3 22:06
 */
public class ForkJoinCalculate extends RecursiveTask<Long> {

    /* RecursiveTask 递归任务 */

    /**
     * 开始值
     */
    private long start;
    /**
     * 结束值
     */
    private long end;
    /**
     * 阈值[多任务至此阈值后停止拆分]
     */
    private static final long THRESHOLD = 10000L;

    public ForkJoinCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long len = end - start;

        // 如果结束值减去开始值等于阈值后就停止任务拆分
        if (len <= THRESHOLD) {
            long sum = 0;
            for (long i = start; i <= end; i++) {
                sum += i;
            }
            return sum;
        }
        // 继续拆分子任务,达到阈值后停止拆分
        else {
            // 获取到中间值
            long middle = (start + end) / 2;
            ForkJoinCalculate leftFork = new ForkJoinCalculate(start, middle);
            leftFork.fork(); // 拆分子任务,同时压入线程队列
            ForkJoinCalculate rightFork = new ForkJoinCalculate(middle + 1, end);
            rightFork.fork();
            // 计算后的任务合并通过 join() 方法来实现
            return leftFork.join() + rightFork.join();
        }
    }
}
