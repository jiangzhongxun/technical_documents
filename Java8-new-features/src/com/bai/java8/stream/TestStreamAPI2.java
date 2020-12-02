package com.bai.java8.stream;

import com.bai.java8.lambda.Student;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/12/1 21:34
 */
public class TestStreamAPI2 {
    private static final List<Student> students = Arrays.asList(
            new Student("张三", 20, 1111.11, Student.Status.BUSY),
            new Student("李四", 30, 2222.22, Student.Status.FREE),
            new Student("王五", 35, 3333.33, Student.Status.VOCATION),
            new Student("赵六", 50, 6666.66, Student.Status.BUSY),
            new Student("田七", 18, 8888.88, Student.Status.FREE),
            new Student("田七", 18, 8888.88, Student.Status.FREE)
    );

    public static void main(String[] args) {
        test10();
    }

    private static void test10() {
        String str = students.stream()
                .map(Student::getName)
                .collect(Collectors.joining(" ~ ", "[", "]"));
        System.out.println(str);
    }

    private static void test9() {
        DoubleSummaryStatistics ds = students.stream()
                .collect(Collectors.summarizingDouble(Student::getSalary));
        System.out.println(ds.getCount());
        System.out.println(ds.getMax());
        System.out.println(ds.getAverage());
    }

    /**
     * 分区
     */
    private static void test8() {
        Map<Boolean, List<Student>> map = students.stream()
                .collect(Collectors.partitioningBy(e -> e.getSalary() > 8000));
        System.out.println(map);
    }

    /**
     * 多级分组
     */
    private static void test7() {
        Map<Student.Status, Map<String, List<Student>>> map = students.stream()
                .collect(Collectors.groupingBy(Student::getStatus, Collectors.groupingBy((e) -> {
                    if (e.getAge() <= 35) return "青年";
                    else if (e.getAge() <= 50) return "中年";
                    else return "老年";
                })));
        System.out.println(map);
    }

    /**
     * 分组
     */
    private static void test6() {
        Map<Student.Status, List<Student>> map = students.stream()
                .collect(Collectors.groupingBy(Student::getStatus));
        System.out.println(map);
    }

    /**
     * 收集
     */
    private static void test5() {
        // 总数
        Long count = students.stream()
                .collect(Collectors.counting());
        System.out.println(count);

        // 平均值
        Double avg = students.stream()
                .collect(Collectors.averagingDouble(Student::getSalary));
        System.out.println(avg);

        // 总和
        Double sum = students.stream()
                .collect(Collectors.summingDouble(Student::getSalary));
        System.out.println(sum);

        // 最大值
        Optional<Double> max = students.stream()
                .map(Student::getSalary)
                .collect(Collectors.maxBy(Double::compare));
        System.out.println(max.get());

        // 最小值
        Optional<Double> min = students.stream()
                .map(Student::getSalary)
                .collect(Collectors.minBy(Double::compareTo));
        System.out.println(min.get());
    }

    /**
     * 收集
     */
    private static void test4() {
        /* 将所有名字收集到 List 集合中 */
        students.stream()
                .map(Student::getName)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("-----------------------");

        /* 将所有名字收集到 Set 集合中 */
        students.stream()
                .map(Student::getName)
                .collect(Collectors.toSet())
                .forEach(System.out::println);

        System.out.println("-----------------------");

        /* 将所有名字收集到自定义集合中 */
        students.stream()
                .map(Student::getName)
                .collect(Collectors.toCollection(HashSet::new))
                .forEach(System.out::println);
    }

    /**
     * 规约
     */
    private static void test3() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(list.stream().reduce(0, Integer::sum));


        System.out.println("------------------------");

        Optional<Double> op = students.stream()
                .map(Student::getSalary)
                .reduce(Double::sum);
        System.out.println(op.get());
    }

    /**
     * 查找与匹配
     */
    private static void test2() {
        long count = students.stream()
                .count();
        System.out.println(count);

        Optional<Integer> op1 = students.stream()
                .map(Student::getAge)
                .max(Integer::compareTo);
        System.out.println(op1.get());

        Optional<Integer> op2 = students.stream()
                .map(Student::getAge)
                .min(Integer::compareTo);
        System.out.println(op2.get());
    }

    /**
     * 查找与匹配
     */
    private static void test1() {
        boolean b1 = students.stream()
                .allMatch((student -> student.getStatus().equals(Student.Status.BUSY)));
        System.out.println(b1);

        boolean b2 = students.stream()
                .anyMatch((student -> student.getStatus().equals(Student.Status.BUSY)));
        System.out.println(b2);

        boolean b3 = students.stream()
                .noneMatch((student -> student.getStatus().equals(Student.Status.BUSY)));
        System.out.println(b3);

        Optional<Student> op1 = students.stream()
                .filter(s -> s.getStatus().equals(Student.Status.BUSY))
                .findFirst();
        System.out.println(op1.get());

        Optional<Student> op2 = students.parallelStream()
                .findAny();
        System.out.println(op2.get());

    }


}
