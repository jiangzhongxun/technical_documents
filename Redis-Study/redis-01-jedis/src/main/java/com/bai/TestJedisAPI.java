package com.bai;

import redis.clients.jedis.Jedis;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/12/26 13:53
 */
public class TestJedisAPI {
    public static void main(String[] args) {
        // 连接远程连接不上 8.133.174.58
        Jedis jedis = new Jedis("8.133.174.58", 6379);
        System.out.println(jedis.ping());
        jedis.flushDB();    // 清空当前数据库
        jedis.select(2);    // 切换到2号数据库
        jedis.set("username", "xiaoming");  // 设置一个值
        System.out.println(jedis.get("username"));  // 获取一个值的内容
    }
}
