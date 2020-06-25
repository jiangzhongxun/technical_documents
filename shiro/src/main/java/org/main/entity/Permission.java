package org.main.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色权限类
 * @Author: bai
 * @DateTime: 2020/6/25 9:21
 */
@Data
public class Permission implements Serializable {
    /**
     * 权限id
     */
    private Integer pid;
    /**
     * 权限组名称
     */
    private String name;
    /**
     * 可访问的 url
     */
    private String url;
}
