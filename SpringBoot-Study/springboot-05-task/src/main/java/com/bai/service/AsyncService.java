package com.bai.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/12/19 14:40
 */
@Service
public class AsyncService {
    @Async  // 支持异步任务
    public void hello() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("异步任务处理完成！");
    }
}
