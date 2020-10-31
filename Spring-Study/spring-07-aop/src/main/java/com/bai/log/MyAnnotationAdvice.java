package com.bai.log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/31 13:03
 */
@Aspect // 标注此类为一个切面
public class MyAnnotationAdvice {
    @Before("execution(* com.bai.service.UserServiceImpl.*(..))")
    public void before() {
        System.out.println("方法执行前======");
    }

    @Around("execution(* com.bai.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("方法环绕前======");
        // 方法签名
        System.out.println(joinPoint.getSignature());
        // 执行方法
        joinPoint.proceed();
        System.out.println("方法环绕后======");
    }

    @After("execution(* com.bai.service.UserServiceImpl.*(..))")
    public void after() {
        System.out.println("方法执行后======");
    }
}
