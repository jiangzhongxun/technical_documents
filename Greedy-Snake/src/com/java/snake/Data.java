package com.java.snake;

import javax.swing.*;
import java.net.URL;

/**
 * 小游戏所需数据
 * @Author: bai
 * @DateTime: 2020/6/27 13:37
 */
public class Data {
    //TODO 广告栏图片
    public static URL headerUrl = Data.class.getResource("/statics/header.png");
    public static ImageIcon headerImg = new ImageIcon(headerUrl);

    //TODO 贪吃蛇头部图片
    public static URL upUrl = Data.class.getResource("/statics/up.png");
    public static ImageIcon upImg = new ImageIcon(upUrl);   // 头部向上方向
    public static URL downUrl = Data.class.getResource("/statics/down.png");
    public static ImageIcon downImg = new ImageIcon(downUrl);   // 头部向下方向
    public static URL leftUrl = Data.class.getResource("/statics/left.png");
    public static ImageIcon leftImg = new ImageIcon(leftUrl);   // 头部向左方向
    public static URL rightUrl = Data.class.getResource("/statics/right.png");
    public static ImageIcon rightImg = new ImageIcon(rightUrl);   // 头部向右方向

    //TODO 贪吃蛇身体图片
    public static URL bodyUrl = Data.class.getResource("/statics/body.png");
    public static ImageIcon bodyImg = new ImageIcon(bodyUrl);

    //TODO 食物图片
    public static URL foodUrl = Data.class.getResource("/statics/food.png");
    public static ImageIcon foodImg = new ImageIcon(foodUrl);
}
