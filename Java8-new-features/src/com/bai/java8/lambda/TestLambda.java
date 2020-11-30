package com.bai.java8.lambda;

import java.util.*;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/11/29 17:31
 */
public class TestLambda {
    private static final List<Student> students = Arrays.asList(
            new Student("张三", 20, 1111.11),
            new Student("李四", 30, 2222.22),
            new Student("王五", 35, 3333.33),
            new Student("赵六", 50, 6666.66),
            new Student("田七", 18, 8888.88)
    );

    public static void main(String[] args) {
        test3();
        test4();
    }

    public static void test3() {
        students.stream()
                .filter((s) -> s.getAge() > 20)
                .forEach(System.out::println);
    }

    public static void test4() {
        students.stream()
                .map(Student::getName)
                .forEach(System.out::println);
    }

    public static void test1() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o1, o2);
            }
        };

        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }

    public static void test2() {
        Comparator<Integer> comparator = Integer::compare;
        TreeSet<Integer> treeSet = new TreeSet<>(comparator);
    }
}
