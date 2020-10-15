package com.bai.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/15 21:54
 */
@Data
public class User implements Serializable {
    private Integer id;
    private String name;
    private String pwd;
}
