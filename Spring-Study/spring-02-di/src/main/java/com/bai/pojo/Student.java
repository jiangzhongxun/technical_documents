package com.bai.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author: 南独酌酒 <211425401@126.com>
 * @date: 2020/10/26 20:49
 */
@Data
public class Student implements Serializable {
    /**
     * 基本类型
     */
    private String name;
    /**
     * 引用类型
     */
    private Address address;
    /**
     * 空 null 类型
     */
    private String wife;
    /**
     * List 类型
     */
    private List<String> hobbys;
    /**
     * Set 类型
     */
    private Set<String> games;
    /**
     * Map 类型
     */
    private Map<String, String> card;
    /**
     * 数组类型
     */
    private String[] books;
    /**
     * 配置文件类型 Properties
     */
    private Properties info;
}
