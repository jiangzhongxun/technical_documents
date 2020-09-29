package com.function;

import java.util.function.Consumer;

/**
 * Consumer 消费性接口
 * 只有参数，没有返回值
 *
 * @Author: bai
 * @DateTime: 2020/7/26 11:05
 */
public class Demo3 {
    public static void main(String[] args) {
//        Consumer<String> consumer = new Consumer<String>() {
//            @Override
//            public void accept(String s) {
//                System.out.println(s);
//            }
//        };
        Consumer<String> consumer = (s) -> {
            System.out.println(s);
        };
        consumer.accept("abc");
    }
}
