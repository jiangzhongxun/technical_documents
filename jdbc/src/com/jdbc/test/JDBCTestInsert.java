package com.jdbc.test;

import com.jdbc.test.util.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 测试 JDBC 的插入操作
 *
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/9/22 20:45
 */
public class JDBCTestInsert {
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            st = conn.createStatement();
            String sql = "INSERT INTO jdbctest(`id`,`username`,`password`,`email`,`birthday`)" +
                    "VALUES(4, '小龙', '123456', 'xllzs@qq.com', '2020-09-22')";
            int result = st.executeUpdate(sql);
            if (result > 0) {
                System.out.println("新增成功");
            } else {
                System.out.println("新增失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } finally {
            JDBCUtils.release(conn, st, rs);
        }
    }
}
