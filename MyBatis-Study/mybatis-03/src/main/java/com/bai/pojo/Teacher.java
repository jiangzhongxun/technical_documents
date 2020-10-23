package com.bai.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/23 22:06
 */
@Data
public class Teacher {
    private Integer id;
    private String name;
    private List<Student> studentList;
}
