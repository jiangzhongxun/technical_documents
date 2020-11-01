package com.bai.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/11/1 12:02
 */
public class HelloController implements Controller {

    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        // ModelAndView 模式和视图
        ModelAndView mv = new ModelAndView();

        // 封装对象,放在 ModelAndView 中
        mv.addObject("msg", "HelloSpringMVC");

        // 封装要跳转的视图，放在 ModelAndView 中
        mv.setViewName("hello");    // /WEB-INF/jsp/hello.jsp
        return mv;
    }
}
