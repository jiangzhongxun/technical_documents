package com.bai.demo1;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/31 11:18
 */
public class Client {
    public static void main(String[] args) {
        // 真实角色
        UserServiceImpl userService = new UserServiceImpl();
        //todo 代理角色现在并没有，需要一个模板动态生成代理类
        //todo 而这个模板就是 ProxyInvocationHandler 我们自定义的类
        ProxyInvocationHandler pih = new ProxyInvocationHandler();
        // 设置要代理的真实角色
        pih.setTarget(userService);
        //todo 这行代理就表示通过我们自定义的模板动态生成了 UserService 对象
        UserService proxy = (UserService) pih.getProxy();
        proxy.delete();
    }
}
