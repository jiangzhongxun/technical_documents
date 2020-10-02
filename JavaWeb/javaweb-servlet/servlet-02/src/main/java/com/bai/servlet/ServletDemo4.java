package com.bai.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/2 17:11
 */
public class ServletDemo4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // ServletContext 的请求转发案例
        ServletContext context = this.getServletContext();
        context.getRequestDispatcher("/sd3").forward(req, resp);    // 请求转发至 /sb3 路径去
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
