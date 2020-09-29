package com.function;

import java.util.function.Predicate;

/**
 * 断定型接口 Predicate
 * 有一个参数，返回值只能是布尔值
 *
 * @Author: bai
 * @DateTime: 2020/7/26 10:56
 */
public class Demo2 {
    public static void main(String[] args) {
//        Predicate<String> predicate = new Predicate<String>() {
//            @Override
//            public boolean test(String value) {
//                return value.isEmpty();
//            }
//        };
        Predicate<String> predicate = (value) -> {
            return value.isEmpty();
        };
        System.out.println(predicate.test("123"));
    }
}
