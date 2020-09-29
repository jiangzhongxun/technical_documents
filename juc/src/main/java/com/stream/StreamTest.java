package com.stream;

import java.util.Arrays;
import java.util.List;

/**
 * 题目要求：只能用一行代码实现！
 * 现在有5个用户!筛选：
 * 1. ID必须是偶数
 * 2. 年龄必须大于23岁
 * 3. 用户名转换为大写字母
 * 4. 用户名字母倒着排序
 * 5. 只输出一个用户
 *
 * @Author: bai
 * @DateTime: 2020/7/26 11:18
 */
public class StreamTest {
    public static void main(String[] args) {
        User u1 = new User(1, "a", 21);
        User u2 = new User(2, "b", 22);
        User u3 = new User(3, "c", 23);
        User u4 = new User(4, "d", 24);
        User u5 = new User(6, "e", 25);
        // 存入集合中
        List<User> list = Arrays.asList(u1, u2, u3, u4, u5);

        list.stream()
                .filter((u) -> { return u.getId() % 2 == 0; })  // 运用了断定型接口：有一个参数，返回值只能是布尔值
                .filter((u) -> { return u.getAge() > 23; }) // 运用了断定型接口：有一个参数，返回值只能是布尔值
                .map((u) -> {return u.getName().toUpperCase(); })   // 运用了函数式接口
                .sorted((e1, e2) -> { return e2.compareTo(e1); })   // 运用了消费性接口，只有参数，没有返回值
                .limit(1)   // 分页操作，提取一个输出
                .forEach(System.out::println);
    }
}
