package org.main;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: bai
 * @DateTime: 2020/6/25 9:30
 */
@SpringBootApplication
@ComponentScan
@MapperScan(basePackages = {"org.main.mapper"})
public class ShrioApplication {
    public static void main(String[] args) {
        SpringApplication.run(ShrioApplication.class, args);
    }
}
