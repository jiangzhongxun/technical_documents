package com.signle;

/**
 * 饿汉式单例
 */
public class Hungry {
    private Hungry() {
    }

    private static Hungry HUNGRY = new Hungry();

    // 饿汉式一开始就加载大量数据，可能有些数据并不会被使用到特别多，这就造成了一些资源浪费情况
    private byte[] data1 = new byte[1024 * 1024];
    private byte[] data2 = new byte[1024 * 1024];
    private byte[] data3 = new byte[1024 * 1024];
    private byte[] data4 = new byte[1024 * 1024];

    /**
     * 提供对外获取单例模式的公共方法
     *
     * @return 类实例
     */
    public static Hungry getInstance() {
        return HUNGRY;
    }
}
