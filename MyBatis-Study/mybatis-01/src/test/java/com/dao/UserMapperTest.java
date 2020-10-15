package com.dao;

import com.bai.dao.UserMapper;
import com.bai.pojo.User;
import com.bai.util.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/15 22:11
 */
public class UserMapperTest {

    @Test
    public void test01() {
        SqlSession sqlSession = null;
        try {
            // 1.获取到 SqlSession 对象那个
            sqlSession = MyBatisUtil.getSqlSession();
            // 2.通过 getMapper 方法加载对应的 UserMapper 接口
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            // 3.执行接口方法
            List<User> userList = mapper.getUserList();
            for (User user : userList) {
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 4.关闭 sqlSession 对象
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

}
