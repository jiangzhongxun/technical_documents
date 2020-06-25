package org.main.mapper;

import org.apache.ibatis.annotations.Param;
import org.main.entity.User;

/**
 * @Author: bai
 * @DateTime: 2020/6/25 9:29
 */
public interface UserMapper {
    User findByUsername(@Param("username") String username);
}
