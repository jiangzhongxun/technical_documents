package org.javaboy.clientapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @Author: bai
 * @DateTime: 2020/6/26 12:50
 */
@Controller
public class LoginController {
    @Autowired
    RestTemplate restTemplate;

    @PostMapping(value = "/login")
    public String login(String username, String password, Model model) {
        //TODO step1. 根据用户名和密码去授权服务器获取 token
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", username);  //TODO 用户名
        map.add("password", password);  //TODO 密码
        map.add("client_id", "javaboy");    //TODO 授权服务器 id
        map.add("client_secret", "123");    //TODO 授权服务器密码
        map.add("grant_type", "password");  //TODO 授权服务器 密码模式
        Map<String, String> respObject = restTemplate.postForObject("http://localhost:8080/oauth/token", map, Map.class);
        System.out.println("respObject = " + respObject);   //TODO 授权码响应体
        //TODO step2. 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + respObject.get("access_token"));
        HttpEntity<Object> httpParams = new HttpEntity<>(headers);
        //TODO step3. 根据请求头中的 access_token 去访问资源服务器
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8081/hello", HttpMethod.GET, httpParams, String.class);
        //TODO responseEntity.getBody() 则为资源服务器响应内容
        model.addAttribute("msg", responseEntity.getBody());
        return "02";
    }

    @GetMapping(value = "/02.html")
    public String loginPage() {
        return "02";
    }
}
