package com.bai.dao;

import com.bai.pojo.User;

import java.util.List;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/15 22:07
 */
public interface UserMapper {

    /**
     * 查询所有用户信息
     *
     * @return 用户信息
     */
    public List<User> getUserList();

    /**
     * 跟据 id 查询用户信息
     *
     * @param id 查询条件
     * @return 用户信息
     */
    public User getUserById(int id);

    /**
     * 添加用户
     *
     * @param user 添加信息
     * @return 影响行数
     */
    public int addUser(User user);

    /**
     * 更新用户
     *
     * @param user 修改信心
     * @return 影响行数
     */
    public int updateUser(User user);

    /**
     * 删除用户
     *
     * @param id 删除条件
     * @return 影响行数
     */
    public int deleteUser(int id);

}
