package com.bai.java8;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/11/30 15:11
 */
public class TestLambda3 {
    private static final List<Student> students = Arrays.asList(
            new Student("张三", 20, 1111.11),
            new Student("李四", 30, 2222.22),
            new Student("王五", 35, 3333.33),
            new Student("赵六", 50, 6666.66),
            new Student("田七", 18, 8888.88)
    );

    public static void main(String[] args) {
        test3();
    }

    private static void test1() {
        Collections.sort(students, (x, y) -> {
            if (x.getAge() == y.getAge()) {
                return x.getName().compareTo(y.getName());
            } else {
                return Integer.compare(x.getAge(), y.getAge());
            }
        });

        for (Student student : students) {
            System.out.println(student);
        }
    }

    private static void test2() {
        /*对字符串进行大写转换操作*/
        String upperStr = strHandler("abcdefg", String::toUpperCase);
        System.out.println(upperStr);

        /*对字符串进行截取操作*/
        String newStr = strHandler("我是钢铁侠", str -> str.substring(2, 5));
        System.out.println(newStr);
    }

    private static void test3() {
        // 通过 Long::sum 求两数之和
        System.out.println(numHandler(100L, 200L, Long::sum));
        // 通过 Long::max 求出最大数
        System.out.println(numHandler(100L, 200L, Long::max));
        // 通过 Long::min 请求最小数
        System.out.println(numHandler(100L, 200L, Long::min));
        // 求出 x * y 的结果
        System.out.println(numHandler(100L, 200L, (x, y) -> x * y));
    }

    /**
     * 字符串处理方法
     *
     * @param str    字符串
     * @param myFun2 lambda 函数式接口
     */
    private static String strHandler(String str, MyFun2 myFun2) {
        return myFun2.getValue(str);
    }

    /**
     * 数字运算处理方法
     *
     * @param x      数字1
     * @param y      数字2
     * @param myFun3 函数式接口
     * @return 运算后的结果
     */
    private static Long numHandler(Long x, Long y, MyFun3<Long, Long> myFun3) {
        return myFun3.getValue(x, y);
    }
}
