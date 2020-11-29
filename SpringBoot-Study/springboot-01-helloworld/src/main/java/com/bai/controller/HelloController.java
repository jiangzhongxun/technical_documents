package com.bai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/11/17 20:50
 */
@Controller
@RequestMapping(value = "/hello")
public class HelloController {
    @GetMapping(value = "/h1")
    @ResponseBody
    public String hello() {
        return "hello,SpringBoot";
    }
}
