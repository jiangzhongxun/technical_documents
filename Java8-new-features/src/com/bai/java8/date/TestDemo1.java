package com.bai.java8.date;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/12/5 18:37
 */
public class TestDemo1 {
    public static void main(String[] args) throws Exception {
        test7();
    }

    /**
     * ZonedDate\ZonedTime\ZonedDateTime
     */
    private static void test7() {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Pyongyang"));
        System.out.println(now);  // 2020-12-05T21:26:00.824

        LocalDateTime now1 = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        ZonedDateTime ztd = now1.atZone(ZoneId.of("Asia/Shanghai"));
        System.out.println(ztd);  // 2020-12-05T20:27:03.954+08:00[Asia/Shanghai]
    }

    private static void test6() {
        // 打印支持的所有时区
        Set<String> set = ZoneId.getAvailableZoneIds();
        set.forEach(System.out::println);
    }

    /**
     * DateTimeFormatter 格式化时间/日期
     */
    private static void test5() {
        // 使用 DateTimeFormatter 中定义好的格式化规则
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
        LocalDateTime ldt = LocalDateTime.now();
        // 通过 LocalDateTime 对象的 format 方法指定日期格式化规则
        System.out.println(ldt.format(dtf)); // 2020-12-05

        System.out.println("-----------------------------");

        // 自定义日期格式化规则
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH分:mm分:ss秒");
        String strDate = dtf2.format(ldt);
        System.out.println(strDate);  // 2020年12月05日 20分:19分:06秒

        // 将格式化后的日期反转回去
        LocalDateTime newDate = LocalDateTime.parse(strDate, dtf2);
        System.out.println(newDate);  // 2020-12-05T20:19:06
    }

    /**
     * Duration:计算两个时间之间的间隔
     * Period:计算两个日期之间的间隔
     */
    private static void test4() {
        Instant beginTime = Instant.now();
        try {
            // 睡眠 1 秒钟
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant endTime = Instant.now();

        // 通过 between(Temporal startInclusive, Temporal endExclusive) 方法获取到比较后的 Duration 实例
        Duration duration = Duration.between(beginTime, endTime);
        System.out.println(duration.toMillis());    // 通过 toMillis() 方法获取两个时间之间毫秒间隔差比为： 1002

        System.out.println("------------------------------------");

        LocalDate beginDate = LocalDate.of(2018, 3, 28);
        LocalDate endDate = LocalDate.now();
        System.out.println(beginDate);  // 2018-03-28
        System.out.println(endDate);    // 2020-12-05

        Period period = Period.between(beginDate, endDate);
        System.out.println(period);
        System.out.println(period.getYears());  // 相差 2 年
        System.out.println(period.getMonths()); // 相差 8 个月
        System.out.println(period.getDays());   // 相差 7 天
    }

    /**
     * Instant 时间戳
     * 以 Unix 元年：1970年1月1日 00:00:00 到某个时间之间的毫秒数
     */
    private static void test3() {
        Instant now = Instant.now();
        System.out.println(now); // 2020-12-05T11:04:15.017Z

        // 通过 atOffset() 指定时区偏差为 8小时
        OffsetDateTime offsetDateTime = now.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime); // 2020-12-05T19:04:15.017+08:00

        // 打印出当前日期毫秒数
        System.out.println(now.toEpochMilli()); // 1607166255017

        // ofEpochSecond() 方法从 1970年开始为基础偏移日期得到一个新实例 Instant
        Instant instant = Instant.ofEpochSecond(60);
        System.out.println(instant);    // 1970-01-01T00:01:00Z
    }

    /**
     * 日期 API 测试 LocalDate    LocalTime   LocalDateTime
     * 无论什么操作都会生成一个新的 LocalDateTime 实例
     * 多线程下操作日期不会出现线程安全问题
     */
    private static void test2() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);  // 当前时间 2020-12-05T18:49:47.379

        LocalDateTime localDateTime1 = LocalDateTime.of(2021, 1, 1, 0, 0, 0);
        System.out.println(localDateTime1); // 通过 of方法 创建时间   2021-01-01T00:00

        // plusYears(long years) 在原日期基础上添加指定年份
        LocalDateTime localDateTime2 = localDateTime.plusYears(2);
        System.out.println(localDateTime2); // 2022-12-05T18:51:36.076

        // minusMonths(long months) 在原日期基础上减去指定月份
        LocalDateTime localDateTime3 = localDateTime.minusMonths(2);
        System.out.println(localDateTime3); // 2020-10-05T18:52:39.626

        System.out.println(localDateTime.getYear());        // 年
        System.out.println(localDateTime.getMonthValue());  // 月
        System.out.println(localDateTime.getDayOfMonth());  // 日
        System.out.println(localDateTime.getHour());        // 时
        System.out.println(localDateTime.getMinute());      // 分
        System.out.println(localDateTime.getSecond());      // 秒
    }

    private static void test1() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        Callable<LocalDate> task = () -> LocalDate.parse("20201205", formatter);
        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future<LocalDate>> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list.add(pool.submit(task));
        }

        for (Future<LocalDate> future : list) {
            System.out.println(future.get());
        }

        pool.shutdown();
    }
}
