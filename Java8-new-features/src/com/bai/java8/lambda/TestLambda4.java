package com.bai.java8.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/11/30 15:47
 */
public class TestLambda4 {
    public static void main(String[] args) {
        test4();
    }

    // Predicate<T> 断言型接口案例 ->

    public static void test4() {
        List<String> list = Arrays.asList("Android", "IOS", "MacOS", "Linux", "windows");
        filterStr(list, (str) -> str.length() < 5).forEach(System.out::println);
    }

    public static List<String> filterStr(List<String> list, Predicate<String> predicate) {
        List<String> strList = new ArrayList<>();
        for (String s : list) {
            if (predicate.test(s)) {
                strList.add(s);
            }
        }
        return strList;
    }

    // Function<T, R> 函数型接口案例 ->

    public static void test3() {
        System.out.println(strHandler("我是小刚蛋", str -> str.substring(2, 5)));
        System.out.println(strHandler("abcdefg", String::toUpperCase));
    }

    public static String strHandler(String str, Function<String, String> function) {
        return function.apply(str);
    }

    // Supplier<T> 供给型接口案例 ->

    public static void test2() {
        getNumList(10, () -> (int) (Math.random() * 100)).forEach(System.out::println);
    }

    public static List<Integer> getNumList(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(supplier.get());
        }
        return list;
    }

    // Consumer<T> 消费性接口案例 ->

    public static void test1() {
        happy(1000.00, m -> System.out.println("我每天都需要消费" + m + "元"));
    }

    public static void happy(Double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }
}
