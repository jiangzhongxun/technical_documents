package org.javaboy.authserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.InMemoryAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;
import java.util.Arrays;

/**
 * @Author: bai
 * @DateTime: 2020/6/26 10:03
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    TokenStore tokenStore;
    //@Autowired
    //ClientDetailsService clientDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    DataSource dataSource;
    @Autowired
    JwtAccessTokenConverter jwtAccessTokenConverter;
    @Autowired
    MyJWT myJWT;

    @Bean
    ClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices services = new DefaultTokenServices();

        //TODO 配置到内存中的客户端信息
        //services.setClientDetailsService(clientDetailsService);
        //TODO 配置到 DB 中的客户端信息
        services.setClientDetailsService(clientDetailsService());

        services.setSupportRefreshToken(true);  //TODO 支持刷新 token
        services.setTokenStore(tokenStore);
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(jwtAccessTokenConverter, myJWT));
        services.setTokenEnhancer(tokenEnhancerChain);

        //TODO 如果使用的是数据库客户端配置就将这两行注解即可
        //services.setAccessTokenValiditySeconds(60 * 60 * 2);
        //services.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);
        return services;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //TODO 配置到 DB 中的客户端信息
        clients.withClientDetails(clientDetailsService());

        //TODO 配置到内存中的客户端信息
//        clients.inMemory()
//                .withClient("javaboy")
//                .secret(passwordEncoder.encode("123"))
//                .resourceIds("res1")
//                //TODO authorization_code 授权码模式
//                //TODO implicit 简化模式
//                //TODO password 密码模式
//                //TODO client_credentials 客户端模式
//                //TODO refresh_token 作为一种特殊的模式存在 表示刷新token
//                .authorizedGrantTypes("authorization_code", "refresh_token", "implicit", "password", "client_credentials")
//                .scopes("all")
//                .autoApprove(true)
//                .redirectUris("http://localhost:8082/01.html", "http://localhost:8082/02.html");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                //TODO authenticationManager 开启支持密码模式
                .authenticationManager(authenticationManager)
                //TODO  authorizationCodeServices 开启支持授权码模式
                .authorizationCodeServices(authorizationCodeServices())
                .tokenServices(tokenServices());
    }

    @Bean
    AuthorizationCodeServices authorizationCodeServices() {
        return new InMemoryAuthorizationCodeServices();
    }
}
