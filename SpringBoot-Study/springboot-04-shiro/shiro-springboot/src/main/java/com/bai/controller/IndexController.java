package com.bai.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/12/16 22:15
 */
@Controller
public class IndexController {

    /**
     * skip index.html page
     *
     * @return index.html
     */
    @GetMapping(value = {"/", "/index"})
    public String toIndexPage(Model model) {
        model.addAttribute("msg", "Hello,Shiro");
        return "index";
    }

    /**
     * skip add.html page
     *
     * @return add.html
     */
    @GetMapping(value = "/user/add")
    public String toAddPage() {
        return "user/add";
    }

    /**
     * skip update.html page
     *
     * @return update.html
     */
    @GetMapping(value = "/user/update")
    public String toUpdatePage() {
        return "user/update";
    }

    @GetMapping(value = "/toLogin")
    public String toLoginPage() {
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(HttpServletRequest request, Model model) {
        // 接收请求参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password)) {
            model.addAttribute("msg", "用户名或密码不能为空");
            return "login";
        }

        // 获取当前登陆用户
        Subject subject = SecurityUtils.getSubject();
        // 封装用户的登陆数据到 token 中
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            // 调用 login 方法进行登陆操作 shiro 自己帮我们做登陆操作~
            subject.login(token);
            return "index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户名错误");
            return "login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误");
            return "login";
        }
    }
}
