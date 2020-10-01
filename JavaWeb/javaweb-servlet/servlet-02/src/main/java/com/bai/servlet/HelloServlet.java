package com.bai.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ServletContext 的应用案例
 *
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/1 11:30
 */
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Hello");
        ServletContext context = this.getServletContext();
        context.setAttribute("username", "小娜扎");
    }
}
