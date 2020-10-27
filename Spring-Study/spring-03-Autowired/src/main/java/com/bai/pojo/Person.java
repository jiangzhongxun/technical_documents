package com.bai.pojo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Resource;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/26 22:00
 */
public class Person {
    private String name;
    @Resource
    private Cat cat;
    @Resource
    private Dog dog;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cat getCat() {
        return cat;
    }

    public void setCat(Cat cat) {
        this.cat = cat;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", cat=" + cat +
                ", dog=" + dog +
                '}';
    }
}
