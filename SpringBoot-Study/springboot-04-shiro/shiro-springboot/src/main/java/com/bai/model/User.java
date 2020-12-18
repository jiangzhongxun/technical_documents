package com.bai.model;

import java.io.Serializable;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/12/17 22:34
 */
public class User implements Serializable {
    private int id;
    private String name;
    /**
     * 添加了 transient 关键字后,此字段将不会被序列化
     */
    transient private String pwd;
    private String perms;

    public User() {
    }

    public User(int id, String name, String pwd, String perms) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.perms = perms;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", perms='" + perms + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }
}
