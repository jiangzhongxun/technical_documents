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
    public void getUserList() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        // 1.获取到 SqlSession 对象那个
        // 2.通过 getMapper 方法加载对应的 UserMapper 接口
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 3.执行接口方法
        List<User> userList = mapper.getUserList();
        for (User user : userList) {
            System.out.println(user);
        }
        // 4.关闭 sqlSession 对象
        sqlSession.close();
    }

    @Test
    public void getUserById() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserById(1);
        System.out.println(user);
        sqlSession.close();
    }

    @Test
    public void addUser() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.addUser(new User(4, "哈哈", "135463"));
        sqlSession.commit();    // 一定要记住提交事务
        sqlSession.close();
    }

    @Test
    public void updateUser() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.updateUser(new User(4, "呵呵", "66666"));
        sqlSession.commit();    // 一定要记住提交事务
        sqlSession.close();
    }

    @Test
    public void deleteUser() {
        SqlSession sqlSession = MyBatisUtil.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.deleteUser(4);
        sqlSession.commit();
        sqlSession.close();
    }
}
