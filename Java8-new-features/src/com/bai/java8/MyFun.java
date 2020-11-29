package com.bai.java8;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/11/29 18:38
 */
@FunctionalInterface
public interface MyFun<T> {
    /**
     * 数字运算函数式编程
     *
     * @param t 参数
     * @return 返回值
     */
    T getValue(T t);
}
