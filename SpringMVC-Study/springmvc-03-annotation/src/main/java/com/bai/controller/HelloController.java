package com.bai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/11/2 21:48
 */
@Controller
@RequestMapping(value = "/hello")
public class HelloController {
    @RequestMapping(value = "/h1")
    public String test1(Model model) {
        model.addAttribute("msg", "HelloSpringMVC-Annotation");
        return "hello";
    }

    @GetMapping(value = "/t2/{a}/{b}")
    public String test2(@PathVariable int a, @PathVariable int b, Model model) {
        int res = a + b;
        model.addAttribute("msg", "结果为：" + res);
        return "hello";
    }
}
