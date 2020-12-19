package com.bai.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/12/19 16:04
 */
@Service
public class ScheduledService {
    /**
     * 执行定时任务
     */
//    @Scheduled(cron = "* * * * * ?")
    public void executeScheduledTask() {
        System.err.println("每两秒执行一次此任务！！！ ->>>>>>>>>>>>>>");
    }
}
