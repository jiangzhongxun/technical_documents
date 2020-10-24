package com.bai.dao;

import com.bai.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/24 12:43
 */
public interface UserMapper {
    /**
     * 跟据id查询用户
     *
     * @param id 查询条件
     * @return 用户信息
     */
    public User getById(@Param("id") int id);
}
