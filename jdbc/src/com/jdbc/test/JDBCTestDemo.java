package com.jdbc.test;

import java.sql.*;

/**
 * 第一个 jdbc 测试案例
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/9/21 21:29
 */
public class JDBCTestDemo {
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        try {
            //1. 加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2. 提供数据库连接的信息和账号密码
            /**
             * useUnicode=true 支持中文编码
             * characterEncoding=utf8 设置字符集的编码格式
             * useSSL=true 使用安全的连接
             */
            String url = "jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf8&useSSL=true";
            String username = "root";
            String password = "123456";
            //3. 连接数据库
            connection = DriverManager.getConnection(url, username, password);
            //4. 创建执行 sql 的对象
            statement = connection.createStatement();
            //5. 执行 SQL、获取返回结果并查看
            String sql = "SELECT `id`,`username`,`password`,`email`,`birthday` FROM jdbcTest";
            result = statement.executeQuery(sql);
            while (result.next()) {
                System.err.println("id=" + result.getInt("id"));
                System.err.println("username=" + result.getString("username"));
                System.err.println("password=" + result.getString("password"));
                System.err.println("email=" + result.getString("email"));
                System.err.println("birthday=" + result.getDate("birthday"));
                System.err.println("======================");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            //6. 释放连接
            try {
                result.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
