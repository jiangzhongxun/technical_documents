package com.bai.model;

import java.io.Serializable;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/12/19 16:19
 */
public class User implements Serializable {
    private String name;
    /**
     * 添加了 transient 关键字后,此字段进行序列化时它的值就不会被进行网络传输
     * User{name='张三', pwd='null', age=3}
     */
    /*transient*/ private String pwd;
    private int age;

    public User() {
    }

    public User(String name, String pwd, int age) {
        this.name = name;
        this.pwd = pwd;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
