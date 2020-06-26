package org.javaboy.clientapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@SpringBootTest
class ClientAppApplicationTests {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * TODO 测试 OAuth 的客户端模式
     */
    @Test
    public void test1() {
        //TODO step1. 根据用户名和密码去授权服务器获取 token
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", "javaboy");    //TODO 授权服务器 id
        map.add("client_secret", "123");    //TODO 授权服务器密码
        map.add("grant_type", "client_credentials");  //TODO 授权服务器 客户端模式
        Map<String, String> respObject = restTemplate.postForObject("http://localhost:8080/oauth/token", map, Map.class);
        System.out.println("respObject = " + respObject);   //TODO 授权码响应体
        //TODO step2. 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + respObject.get("access_token"));
        HttpEntity<Object> httpParams = new HttpEntity<>(headers);
        //TODO step3. 根据请求头中的 access_token 去访问资源服务器
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:8081/hello", HttpMethod.GET, httpParams, String.class);
        System.out.println("responseEntity = " + responseEntity.getBody());
    }

}
