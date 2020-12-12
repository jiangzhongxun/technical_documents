package org.never;

import org.never.anno.EnableConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableConfig
public class SpringbootDemo3Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDemo3Application.class, args);
    }

}
