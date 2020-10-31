package com.bai.mapper;

import com.bai.pojo.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/31 15:44
 */
public class UserMapperImpl extends SqlSessionDaoSupport implements UserMapper {
    public List<User> getUserList() {
        User user = new User(5, "马小跳", "999");
        addUser(user);
        int i = 1 / 0;
        return getSqlSession().getMapper(UserMapper.class).getUserList();
    }

    public int addUser(User user) {
        return getSqlSession().getMapper(UserMapper.class).addUser(user);
    }

    public int deleteUser(int id) {
        return getSqlSession().getMapper(UserMapper.class).deleteUser(id);
    }
}
