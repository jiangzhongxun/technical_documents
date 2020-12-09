package com.bai.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/12/9 22:16
 */
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final JdbcTemplate jdbcTemplate;

    @GetMapping(value = "/getUserList")
    public List<Map<String, Object>> getUserList() {
        String sql = "select * from user";
        return jdbcTemplate.queryForList(sql);
    }

    @GetMapping(value = "/addUser")
    public String addUser() {
        String sql = "insert into user(name,pwd) values(?,?)";
        Object[] objs = new Object[]{"小飞", "777"};
        return "增加受影响行数为：" + jdbcTemplate.update(sql, objs);
    }

    @GetMapping(value = "/updateUser/{id}")
    public String updateUser(@PathVariable(value = "id", required = true) int id) {
        String sql = "update user set name = ?,pwd = ? where id = ?";
        Object[] objs = new Object[]{"小飞侠", "777333", id};
        return "修改受影响行数为：" + jdbcTemplate.update(sql, objs);
    }

    @GetMapping(value = "/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id", required = true) int id) {
        String sql = "delete from user where id = ?";
        return "删除受影响行数为：" + jdbcTemplate.update(sql, id);
    }
}
