package com.bai.mapper;

import com.bai.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/12/17 22:36
 */
@Repository
@Mapper
public interface UserMapper {
    public User queryUser(@Param("username") String username);
}
