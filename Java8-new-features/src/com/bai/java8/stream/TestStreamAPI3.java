package com.bai.java8.stream;

import com.bai.java8.lambda.Student;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/12/3 21:15
 */
public class TestStreamAPI3 {
    private static final List<Student> students = Arrays.asList(
            new Student("张三", 20, 1111.11, Student.Status.BUSY),
            new Student("李四", 30, 2222.22, Student.Status.FREE),
            new Student("王五", 35, 3333.33, Student.Status.VOCATION),
            new Student("赵六", 50, 6666.66, Student.Status.BUSY),
            new Student("田七", 18, 8888.88, Student.Status.FREE),
            new Student("田七", 18, 8888.88, Student.Status.FREE)
    );

    public static void main(String[] args) {
        test2();
    }

    /**
     * 通过 map 和 reduce 获取到 Student 中的总数量
     */
    private static void test2() {
        Optional<Integer> count = students.stream()
                .map(s -> 1)
                .reduce(Integer::sum);
        System.out.println(count.get());
    }

    /**
     * 给定一个数字列表，如何返回一个由每个数字的平方构成的列表呢？
     * 例如：给定 [1,2,3,4,5] 返回 [1,4,9,16,25]
     */
    private static void test1() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        nums.stream()
                .map((num) -> { return num * num;})
                .forEach(System.out::println);
    }
}
