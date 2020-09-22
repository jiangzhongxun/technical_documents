package com.jdbc.test.util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * JDBC 工具类
 *
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/9/22 20:31
 */
public class JDBCUtils {
    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;

    // 静态代码块,项目一启动就会执行
    static {
        try {
            // 数据库连接创建一次即可
            InputStream in = JDBCUtils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(in);  // 加载 db.properties 文件
            // 从 db.properties 文件中读取属性连接数据库的信息
            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }

    /**
     * 提供统一的用于创建数据库连接的方法
     *
     * @return 数据库连接对象  Connection
     * @throws SQLException 可能抛出的异常
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    /**
     * 提供统一的用于释放数据库连接的方法
     *
     * @param conn 数据库连接对象
     * @param st   执行 sql 语句的对象
     * @param rs   获取 sql 返回结果的对象
     */
    public static void release(Connection conn, Statement st, ResultSet rs) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }
}
