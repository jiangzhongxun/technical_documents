package com.bai.java8;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/11/30 15:29
 */
@FunctionalInterface
public interface MyFun3<T, R> {
    public R getValue(T t1, T t2);
}
