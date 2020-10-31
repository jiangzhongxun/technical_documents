package com.bai.log;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/31 11:55
 */
public class BeforeLog implements MethodBeforeAdvice {
    /**
     * 方法前置增强
     *
     * @param method 要执行的目标对象的方法
     * @param args   参数
     * @param target 目标对象
     */
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.err.println(target.getClass().getName() + "执行了" + method.getName() + "方法");
    }
}
