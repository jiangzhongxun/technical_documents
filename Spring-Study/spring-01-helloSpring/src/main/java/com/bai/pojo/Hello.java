package com.bai.pojo;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/25 11:21
 */
public class Hello {
    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return "Hello{" +
                "str='" + str + '\'' +
                '}';
    }
}
