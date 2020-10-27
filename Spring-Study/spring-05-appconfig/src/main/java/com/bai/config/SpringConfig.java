package com.bai.config;

import com.bai.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/27 22:19
 */
@Configuration
@ComponentScan("com.bai.pojo")
@Import(SpringConfig2.class)
public class SpringConfig {

    @Bean
    public User getUser() {
        return new User();
    }

}
