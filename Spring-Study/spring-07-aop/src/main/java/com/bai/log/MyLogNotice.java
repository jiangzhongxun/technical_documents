package com.bai.log;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/31 12:44
 */
public class MyLogNotice {
    public void before() {
        System.err.println("方法执行前 >>>>>>");
    }

    public void after() {
        System.err.println("方法执行后 >>>>>>");
    }
}
