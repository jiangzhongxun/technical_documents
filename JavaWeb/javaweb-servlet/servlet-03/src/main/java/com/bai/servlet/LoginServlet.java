package com.bai.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Request案例
 *
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/2 21:28
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //todo 设置请求和相应的字符编码格式
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        //todo 通过 request 获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String[] hobbys = req.getParameterValues("hobbys");
        System.out.println("======================================");
        System.err.println(username);
        System.err.println(password);
        System.err.println(Arrays.toString(hobbys));
        System.out.println("======================================");
        //todo 请求转发至 success.jsp 页面
        req.getRequestDispatcher("/success.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
