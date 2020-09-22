package com.jdbc.test;

import com.jdbc.test.util.JDBCUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/9/22 20:53
 */
public class JDBCTestDelete {
    public static void main(String[] args) {
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtils.getConnection();
            st = conn.createStatement();
            String sql = "DELETE FROM jdbctest WHERE id = 1";
            int result = st.executeUpdate(sql);
            if (result > 0) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        } finally {
            JDBCUtils.release(conn, st, rs);
        }
    }
}
