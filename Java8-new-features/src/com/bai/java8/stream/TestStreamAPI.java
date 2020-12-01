package com.bai.java8.stream;

import com.bai.java8.lambda.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/11/30 21:07
 */
public class TestStreamAPI {
    private static final List<Student> students = Arrays.asList(
            new Student("张三", 20, 1111.11),
            new Student("李四", 30, 2222.22),
            new Student("王五", 35, 3333.33),
            new Student("赵六", 50, 6666.66),
            new Student("田七", 18, 8888.88),
            new Student("田七", 18, 8888.88),
            new Student("田七", 18, 8888.88)
    );

    public static void main(String[] args) {
        test5();
    }

    /**
     * 排序
     */
    private static void test5() {
        List<String> list = Arrays.asList("cc", "aa", "ee", "dd");
        list.stream().sorted().forEach(System.out::println);

        System.out.println("----------------");

        students.stream()
                .sorted((a, b) -> {
                    if (a.getAge().equals(b.getAge())) {
                        return a.getName().compareTo(b.getName());
                    } else {
                        return a.getAge().compareTo(b.getAge());
                    }
                }).forEach(System.out::println);
    }

    private static void test4() {
        List<String> list = Arrays.asList("java", "SpringBoot", "c#");

        List<Object> objList = new ArrayList<>();
        objList.add(11);
        objList.add(22);
        objList.addAll(list);

        System.out.println(objList);
    }

    /**
     * 映射
     */
    private static void test3() {
        List<String> list = Arrays.asList("java", "SpringBoot", "c#");
        list.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        System.out.println("----------------------------------");

        students.stream()
                .map(Student::getName)
                .distinct()
                .forEach(System.out::println);

        System.out.println("----------------------------------");

        list.stream()
                .map(String::toUpperCase)
                .map(TestStreamAPI::filterCharacter)
                .forEach((characterStream -> {
                    characterStream.forEach(System.out::println);
                }));

        System.out.println("----------------------------------");

        list.stream()
                .map(String::toUpperCase)
                .flatMap(TestStreamAPI::filterCharacter)
                .forEach(System.out::println);
    }

    private static Stream<Character> filterCharacter(String str) {
        List<Character> strList = new ArrayList<>();
        for (Character character : str.toCharArray()) {
            strList.add(character);
        }
        return strList.stream();
    }

    /**
     * 筛选与切片
     */
    private static void test2() {
        students.stream()
                .distinct()
                .forEach(System.out::println);
    }

    /**
     * 创建 Stream 流的四种方式
     */
    private static void test1() {
        // 1.可以通过 Collection 系列集合创建 stream() 或 parallelStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        // 2.可以通过 Arrays 中的静态方法 stream() 获取数组流
        Student[] students = new Student[10];
        Stream<Student> stream2 = Arrays.stream(students);

        // 3.可以通过 Stream 类中的静态方法 of() 获取流
        Stream<String> stream3 = Stream.of("java", "python", "javascript");

        // 4.创建无限流
        // 可以通过 iterate() 迭代方式获取流
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
        stream4.limit(5).forEach(System.out::println);

        // 可以通过 generate() 生成方法获取流
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }
}
