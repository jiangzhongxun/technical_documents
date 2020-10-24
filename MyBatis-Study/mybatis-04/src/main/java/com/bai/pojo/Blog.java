package com.bai.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/24 10:20
 */
@Data
public class Blog {
    private String id;
    private String title;
    private String author;
    //todo 由于这里配置的属性和数据库明显不一致,可以通过配置驼峰命名转换来达到我们想要的结果
    private Date createTime;
    private Integer views;
}
