package com.bai.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 通过 Response 实现验证码
 *
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/2 17:11
 */
public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置浏览器3秒刷新一次
        resp.setHeader("refresh", "3");
        // 在内存中创建一个图片
        BufferedImage image = new BufferedImage(80, 20, BufferedImage.TYPE_INT_RGB);
        // 得到图片
        Graphics2D graphics = (Graphics2D) image.getGraphics();
        // 设置图片的背景颜色
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, 80, 20);
        // 给图片写数据
        graphics.setColor(Color.green);
        graphics.setFont(new Font(null, Font.ITALIC, 20));
        graphics.drawString(makeNum(), 0, 20);
        // 告诉浏览器,这个请求使用图片的方式打开
        resp.setContentType("image/jpeg");
        // 网站存在缓存,禁止浏览器进行缓存操作
        resp.setDateHeader("expires", -1);
        resp.setHeader("Cache-Control", "no-cache");
        resp.setHeader("Pragma", "no-cache");
        // 将图片写给浏览器
        ImageIO.write(image, "jpg", resp.getOutputStream());
    }

    /**
     * 生成随机数
     *
     * @return 随机数
     */
    private String makeNum() {
        Random random = new Random();
        String num = random.nextInt(9999999) + "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 7 - num.length(); i++) {
            sb.append("0");
        }
        return sb.toString() + num;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
