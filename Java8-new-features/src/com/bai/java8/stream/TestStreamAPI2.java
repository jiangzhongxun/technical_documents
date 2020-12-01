package com.bai.java8.stream;

import com.bai.java8.lambda.Student;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
            new Student("田七", 18, 8888.88, Student.Status.FREE)
    );

    public static void main(String[] args) {
        test2();
    }

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
