package com.bai.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/27 22:18
 */
@Component
public class User {
    @Value("哪吒")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
