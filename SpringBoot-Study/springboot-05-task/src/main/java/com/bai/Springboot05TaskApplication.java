package com.bai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync // 开启支持异步任务
@EnableScheduling // 开启支持定时任务
public class Springboot05TaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot05TaskApplication.class, args);
    }

}
