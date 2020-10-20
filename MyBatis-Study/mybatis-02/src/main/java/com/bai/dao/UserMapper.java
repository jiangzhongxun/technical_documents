package com.bai.dao;


import com.bai.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/15 22:07
 */
public interface UserMapper {

    /**
     * 查询全部用户
     *
     * @return 用户信息
     */
    @Select("select * from user")
    List<User> getUsers();

    /**
     * 查询单个用户
     *
     * @param id 查询条件
     * @return 用户信息
     */
    @Select("select * from user where id = #{uid}")
    User getUserById(@Param("uid") int id);

    /**
     * 新增用户
     *
     * @param user 新增数据
     */
    @Insert("insert into user(id, name, pwd) values(#{id}, #{name}, #{pwd})")
    void addUser(User user);

    /**
     * 更新用户信息
     *
     * @param user 更新数据
     */
    @Update("update user set name = #{name},pwd = #{pwd} where id = #{id}")
    void updateUser(User user);

    /**
     * 删除用户
     *
     * @param id 删除条件
     */
    @Delete("delete from user where id = #{uid}")
    void deleteById(@Param("uid") int id);
}
