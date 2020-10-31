package com.bai.mapper;

import com.bai.pojo.User;

import java.util.List;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/31 15:06
 */
public interface UserMapper {
    public List<User> getUserList();

    public int addUser(User user);

    public int deleteUser(int id);
}
