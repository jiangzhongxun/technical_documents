package com.bai.pojo;

import lombok.Data;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/23 22:05
 */
@Data
public class Student {
    private Integer id;
    private String name;
    private Teacher teacher;
}
