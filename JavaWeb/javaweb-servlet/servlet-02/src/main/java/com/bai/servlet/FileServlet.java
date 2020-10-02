package com.bai.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/2 17:11
 */
public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //todo 1.要下载的文件路径
        String realPath = "F:\\git仓库\\technical_documents\\JavaWeb\\javaweb-servlet\\servlet-02\\src\\main\\resources\\1.jpg";
        //todo 2.获取要下载的文件名
        //todo 通过 lastIndexOf 方法截取 \ 路径第一个位置到最后一个位置就是文件的名称
        String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
        //todo 3.设置响应头,让浏览器支持下载文件的配置信息
        resp.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        //todo 4.获取文件下载的输入流
        FileInputStream in = new FileInputStream(realPath);
        //todo 5.创建缓冲区
        int len = 0;
        byte[] buffer = new byte[1024];
        //todo 6.获取 OutPutStream 对象
        ServletOutputStream os = resp.getOutputStream();
        //todo 7.将 FileOutputStream 流写入到 buffer 缓冲区,使用 OutputStream 将缓冲区的数据输出到客户端
        while ((len = in.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        //todo 8.释放流
        os.close();
        in.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
