package com.function;

import java.util.function.Function;

/**
 * 函数式接口，有一个输入参数，有一个输出参数
 * 只要是函数式接口，都可以使用 lambda 表达式简化代码
 * @Author: bai
 * @DateTime: 2020/7/26 10:51
 */
public class Demo1 {
    public static void main(String[] args) {
        Function<String, String> function = (str) -> {return str;};
        System.out.println(function.apply("abc"));
    }
}
