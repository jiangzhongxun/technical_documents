package com.java.snake;

import javax.swing.*;

/**
 * 游戏启动类
 * @Author: bai
 * @DateTime: 2020/6/27 13:21
 */
public class StartGamesApplication {
    public static void main(String[] args) {
        // step1. 绘制一个静态窗口
        JFrame frame = new JFrame("Java贪吃蛇小游戏");
        frame.setBounds(10, 10, 900, 720);  // 设置界面大小及坐标
        frame.setResizable(false);  // 设置窗口大小固定
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   // 设置关闭事件,游戏关闭程序停止
        // step2. 添加自定义游戏的画布背景
        frame.add(new GamePanel());
        frame.setVisible(true); // 设置窗口展示
    }
}

