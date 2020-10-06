package com.bai.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 中文乱码过滤器
 *
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/6 10:55
 */
public class CharacterEncodingFilter implements Filter {
    /**
     * 初始化过滤器
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println(filterConfig.getFilterName());
        System.out.println("初始化 CharacterEncodingFilter 过滤器...");
    }

    /**
     * 过滤器实现
     *
     * @param request  请求
     * @param response 响应
     * @param chain    过滤器链
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("========== CharacterEncodingFilter 过滤器开始 ==========");

        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        chain.doFilter(request, response);

        System.out.println("========== CharacterEncodingFilter 过滤器结束 ==========");
    }

    /**
     * 销毁过滤器
     */
    public void destroy() {
        System.out.println("销毁 CharacterEncodingFilter 过滤器...");
    }
}
