package com.bai.demo1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * todo 自定义用于动态生成代理类的模板
 *
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/31 11:14
 */
public class ProxyInvocationHandler implements InvocationHandler {
    /**
     * todo 被代理的接口
     */
    private Object target;

    public void setTarget(Object target) {
        this.target = target;
    }

    /**
     * todo 生成得到代理类
     */
    public Object getProxy() {
        return Proxy.newProxyInstance(this.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);
    }

    /**
     * todo 处理代理实例，并返回结果
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.log(method.getName());
        return method.invoke(target, args);
    }

    private void log(String msg) {
        System.out.println("[DEBUG] 执行了(" + msg + ")方法");
    }
}
