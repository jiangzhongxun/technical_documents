package org.main.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.main.entity.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Author: bai
 * @DateTime: 2020/6/25 10:10
 */
@RestController
public class UserController {

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    /**
     * 两个case
     * 第一个是只有登陆后才能访问相关的接口,没有登陆是不允许访问相关的接口,例如admin接口
     * 第二个是某些接口只能被某些角色访问
     * @return
     */
    @GetMapping(value = "/admin")
    public String admin() {
        return "admin success";
    }

    @GetMapping(value = "/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (null != subject) {
            subject.logout();
        }
        return "login";
    }

    @GetMapping(value = "/index")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/edit")
    public String edit() {
        return "edit success";
    }

    @GetMapping(value = "/unauthorized")
    public String unauthorized() {
        return "unauthorized";
    }

    @PostMapping(value = "/loginUser")
    public String loginUser(@RequestParam(value = "username") String username,
                            @RequestParam(value = "password") String password,
                            HttpSession session) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            User user = (User) subject.getPrincipal();
            session.setAttribute("user", user);
            return "index";
        } catch (Exception e) {
            e.printStackTrace();
            return "login";
        }
    }
}
