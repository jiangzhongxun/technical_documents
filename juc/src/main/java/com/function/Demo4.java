package com.function;

import java.util.function.Supplier;

/**
 * Supplier 供给行接口
 * 没有参数，只有返回值
 *
 * @Author: bai
 * @DateTime: 2020/7/26 11:07
 */
public class Demo4 {
    public static void main(String[] args) {
//        Supplier<Integer> supplier = new Supplier<Integer>() {
//            @Override
//            public Integer get() {
//                return 1024;
//            }
//        };
        Supplier<Integer> supplier = () -> {
            return 1024;
        };
        System.out.println(supplier.get());
    }
}
