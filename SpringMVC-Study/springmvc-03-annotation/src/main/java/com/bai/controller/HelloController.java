package com.bai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
}
