package com.jdbc.test;

import com.jdbc.test.util.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/9/22 20:54
 */
public class JDBCTestSelect {
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            st = conn.createStatement();
            String sql = "select username from jdbctest WHERE id = 4";
            rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.println("查询的名称为:" + rs.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } finally {
            JDBCUtils.release(conn, st, rs);
        }
    }
}
