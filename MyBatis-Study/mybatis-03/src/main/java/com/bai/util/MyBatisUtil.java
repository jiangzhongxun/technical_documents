package com.bai.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/15 22:04
 */
public class MyBatisUtil {

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            // 初始化构建 SqlSessionFactory
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过 SqlSessionFactory 创建一个 SqlSession 对象
     *
     * @return SqlSession 对象
     */
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession(true);
    }
}
