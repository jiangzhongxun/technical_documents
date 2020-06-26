package org.javaboy.userserver.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: bai
 * @DateTime: 2020/6/26 10:26
 */
@RestController
@CrossOrigin(value = "*")
public class HelloController {
    @GetMapping(value = "/hello")
    public String hello() {
        return "hello?小李子";
    }

    @GetMapping(value = "/admin/hello")
    public String admin() {
        return "admin";
    }
}
