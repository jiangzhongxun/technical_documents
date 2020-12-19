package com.bai.controller;

import com.bai.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 异步任务
 *
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/12/19 14:34
 */
@RestController
public class AsyncController {
    @Autowired
    private AsyncService asyncService;

    @GetMapping(value = "/hello")
    public String hello() {
        asyncService.hello();
        return "OK";
    }
}
