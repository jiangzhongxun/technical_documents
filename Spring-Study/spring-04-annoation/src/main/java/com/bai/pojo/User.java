package com.bai.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/27 21:44
 */
// 此行配置等价于 <bean id="user222" class="com.bai.pojo.User"/>
@Component(value = "user222")
// 作用域注解 singleton 单例或 prototype
@Scope(value = "singleton")
public class User {
    // 此行配置等价于 <property name="name" value="小白龙"/>
    @Value("小白龙")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
