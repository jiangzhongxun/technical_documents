package com.bai.java8;


import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/11/29 18:03
 */
public class TestLambda2 {
    public static void main(String[] args) {
        test5();
    }

    private static void test1() {
        Runnable thread = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!");
            }
        };
        thread.run();
        System.out.println("--------------------------------");
        Runnable thread2 = () -> System.out.println("Hello Lambda!");
        thread2.run();
    }

    private static void test2() {
        Consumer<String> consumer = System.out::println;
        consumer.accept("爷笑了");
    }

    private static void test3() {
        Comparator<Integer> comparator = (o1, o2) -> {
            System.out.println("函数式编程");
            return Integer.compare(o1, o2);
        };
        System.out.println(comparator);
    }

    private static void test4() {
        Comparator<Integer> comparator = (o1, o2) -> Integer.compare(o1, o2);
//        Comparator<Integer> comparator = Integer::compare;
        System.out.println(comparator);
    }

    private static void test5() {
        Integer num = operation(100, (x) -> x + 2);
        System.out.println(num);

        System.out.println(operation(100, (x) -> x * x));
    }

    private static Integer operation(Integer num, MyFun<Integer> myFun) {
        return myFun.getValue(num);
    }
}
