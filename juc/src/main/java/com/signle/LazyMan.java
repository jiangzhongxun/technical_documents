package com.signle;

import java.lang.reflect.Constructor;

/**
 * 懒汉式单例
 */
public class LazyMan {
    private LazyMan() {
        // 可以在私有构造器中添加判断来过滤反射
        synchronized (LazyMan.class) {
            if (lazyMan != null) {
                throw new RuntimeException("不要试图使用反射破坏单例模式！");
            }
        }
    }

    // 保证 LazyMan 的原子性操作需要加 volatile 关键字
    private volatile static LazyMan lazyMan;

    /**
     * 双重检测锁模式的懒汉式单例，也称之为 DCL 懒汉式
     * 但是 DCL 懒汉式也存在一个问题，它不是一个原子性操作
     * @return
     */
    public static LazyMan getInstance() {
        if (lazyMan == null) {
            synchronized (LazyMan.class) {
                if (lazyMan == null) {
                    lazyMan = new LazyMan();  // 不是一个原子性操作
                }
            }
        }
        return lazyMan;
    }

    /**
     * 多线程情况下并不能保证单例
     * @return
     */
    /*public static LazyMan getInstance() {
        if (lazyMan == null) {
            lazyMan = new LazyMan();
        }
        return lazyMan;
    }*/

    public static void main(String[] args) throws Exception {
        // 通过反射可破坏 DCL 懒汉式单例
        LazyMan instance = LazyMan.getInstance();
        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true); // 无视空参构造器
        LazyMan instance2 = declaredConstructor.newInstance();
        // 证实了反射是可以破坏 DCl 单例模式的
        System.out.println(instance);  //com.signle.LazyMan@74a14482
        System.out.println(instance2); //com.signle.LazyMan@1540e19d
    }
}
