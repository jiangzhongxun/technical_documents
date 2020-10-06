package com.bai.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 统计网站在线认数的监听器
 *
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/6 11:20
 */
public class OnLineListener implements HttpSessionListener {
    /**
     * 监听 session 创建后的操作
     *
     * @param event session监听对象
     */
    public void sessionCreated(HttpSessionEvent event) {
        System.out.println("========== 监听 session 创建 ==========");
        ServletContext ctx = event.getSession().getServletContext();    // 得到 Servlet 上下文对象
        Integer onlineCount = (Integer) ctx.getAttribute("online_count");  // 查看有几个在线人数
        if (onlineCount == null || onlineCount == 0) {
            onlineCount = 1;     // 如果当前在线人数为 0 的情况下,那就设置为 1 个人在线即可
        } else {
            onlineCount += 1;    // 如果已经有访问人数,那就再原基础上加 1 个数量即可
        }
        ctx.setAttribute("online_count", onlineCount);
    }

    /**
     * 监听 session 销毁后的操作
     *
     * @param event session监听对象
     */
    public void sessionDestroyed(HttpSessionEvent event) {
        System.out.println("========== 监听 session 销毁 ==========");
        event.getSession().invalidate();    // 手动执行销毁 session
        ServletContext ctx = event.getSession().getServletContext();    // 得到 Servlet 上下文对象
        Integer onlineCount = (Integer) ctx.getAttribute("online_count");  // 查看有几个在线人数
        if (onlineCount == null || onlineCount == 0) {
            onlineCount = 0;     // 如果当前在线人数为 0 的情况下,那就设置为 0 个人在线即可
        } else {
            onlineCount -= 1;    // 如果已经有访问人数,那就再原基础上减去 1 个数量即可
        }
        ctx.setAttribute("online_count", onlineCount);
    }
}
