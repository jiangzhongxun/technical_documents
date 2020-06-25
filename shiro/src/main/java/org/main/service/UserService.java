package org.main.service;

import org.main.entity.User;
import org.main.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: bai
 * @DateTime: 2020/6/25 9:42
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }
}
