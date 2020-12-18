package com.bai.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/12/16 22:43
 * shiro 配置类
 */
@Configuration
public class ShiroConfig {
    @Bean(name = "userRealm")
    public UserRealm userRealm() {
        return new UserRealm();
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联 userRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 关联 securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /*
         * 添加 shiro 的内置过滤器 =======================
         *  anon:无需认证就可以访问
         *  authc:必须认证才能访问
         *  user:必须拥有记住我功能才能访问
         *  perms:拥有对某个资源的权限才能访问
         *  role:拥有某个角色权限才能访问
         * */
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        // 拦截的请求
        filterChainDefinitionMap.put("/user/add", "perms[user:add]");
        filterChainDefinitionMap.put("/user/update", "perms[user:update]");
        filterChainDefinitionMap.put("/user/*", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        // 未授权则重定向登陆的请求
        shiroFilterFactoryBean.setLoginUrl("/toLogin");
        // 认证失败重定向至认证失败页面请求
        shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");
        return shiroFilterFactoryBean;
    }

    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }
}
