package com.bai.service;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/31 11:53
 */
public class UserServiceImpl implements UserService {
    @Override
    public void add() {
        System.out.println("添加了一个用户");
    }

    @Override
    public void delete() {
        System.out.println("删除了一个用户");
    }

    @Override
    public void update() {
        System.out.println("更新了一个用户");
    }

    @Override
    public void select() {
        System.out.println("查询了一个用户");
    }

    @Override
    public String console() {
        return "大胡子";
    }
}
