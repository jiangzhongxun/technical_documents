package com.bai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/12/16 22:15
 */
@Controller
public class IndexController {

    /**
     * skip index.html page
     *
     * @return index.html
     */
    @GetMapping(value = "/")
    public String toIndexPage(Model model) {
        model.addAttribute("msg", "Hello,Shiro");
        return "index";
    }

    /**
     * skip add.html page
     *
     * @return add.html
     */
    @GetMapping(value = "/add")
    public String toAddPage() {
        return "add";
    }

    /**
     * skip update.html page
     *
     * @return update.html
     */
    @GetMapping(value = "/update")
    public String toUpdatePage() {
        return "update";
    }
}
