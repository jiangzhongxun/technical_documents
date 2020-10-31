package com.bai.mapper;

import com.bai.pojo.User;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/31 16:01
 */
public class UserMapperImpl2 extends SqlSessionDaoSupport implements UserMapper {
    public List<User> getUserList() {
        return getSqlSession().getMapper(UserMapper.class).getUserList();
    }
}
