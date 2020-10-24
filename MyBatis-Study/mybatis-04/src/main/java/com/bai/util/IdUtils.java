package com.bai.util;

import org.junit.Test;

import java.util.UUID;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/24 10:27
 */
@SuppressWarnings("all")    // 镇压警告
public class IdUtils {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Test
    public void test() {
        System.out.println(IdUtils.getUUID());
    }
}
