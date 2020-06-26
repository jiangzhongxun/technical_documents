package org.javaboy.userserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @Author: bai
 * @DateTime: 2020/6/26 10:02
 */
@Configuration
public class AccessTokenConfig {
//    @Autowired
//    RedisConnectionFactory redisConnectionFactory;

    @Bean
    TokenStore tokenStore() {
        //TODO OAuth 令牌持久化改为 redis 缓存模式
        //return new RedisTokenStore(redisConnectionFactory);

        //TODO 使用 JWT 模式
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("javaboy");
        return converter;
    }
}
