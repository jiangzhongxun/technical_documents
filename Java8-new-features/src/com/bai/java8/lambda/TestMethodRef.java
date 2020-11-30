package com.bai.java8.lambda;

import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/11/30 17:45
 */
public class TestMethodRef {
    public static void main(String[] args) {
        test6();
    }

    // 数组引用
    public static void test6() {
        Function<Integer, String[]> function = (x) -> new String[x];
        String[] apply = function.apply(10);
        System.out.println(apply.length);

        Function<Integer, String[]> function1 = String[]::new;
        String[] apply1 = function1.apply(10);
        System.out.println(apply1.length);
    }

    // 构造器引用
    public static void test5() {
        Supplier<Student> studentSupplier = () -> new Student();
        System.out.println(studentSupplier.get());
        // 构造器引用方式
        Supplier<Student> studentSupplier1 = Student::new;
        System.out.println(studentSupplier1.get());
    }

    // 类::实例方法名 ->
    public static void test4() {
        BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y);
        BiPredicate<String, String> biPredicate1 = String::equals;
        System.out.println(biPredicate1.test("hello", "hello"));
    }

    // 类::静态方法名 ->
    public static void test3() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        Comparator<Integer> comparator1 = Integer::compare;
    }

    // 对象::实例方法名 ->
    public static void test1() {
        /*
         * 这两句代码表达意思相同，都是输出语句
         * 使用方法引用的前提是保持和 Consumer 方法一致
         *   todo Consumer 中的 accept 方法参数类型和无返回值
         *   todo 而 System.out 类中的 println 方法参数类型也是 String 和 无返回值
         * 在二者方法的参数类型和返回值类型一致的情况下，可以使用方法引用的方式来替换 Lambda 表达式
         * */
        Consumer<String> consumer = str -> System.out.println(str);
        Consumer<String> consumer1 = System.out::println;
        consumer1.accept("abc");
    }

    public static void test2() {
        Student student = new Student("bai", 18, 3333.33);
        Supplier<String> supplier = () -> student.getName();
        System.out.println(supplier.get());
        // ==========方法引用改造上面的代码================
        Supplier<Integer> supplier1 = student::getAge;
        System.out.println(supplier1.get());
    }

}
