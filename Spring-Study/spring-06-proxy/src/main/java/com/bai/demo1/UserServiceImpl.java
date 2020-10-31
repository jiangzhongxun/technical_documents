package com.bai.demo1;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/31 11:13
 */
public class UserServiceImpl implements UserService{
    public void insert() {
        System.out.println("添加了一个用户");
    }

    public void delete() {
        System.out.println("删除了一个用户");
    }

    public void update() {
        System.out.println("修改了一个用户");
    }

    public void query() {
        System.out.println("查询了一个用户");
    }
}
