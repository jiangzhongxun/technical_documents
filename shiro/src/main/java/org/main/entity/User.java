package org.main.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户类
 * @Author: bai
 * @DateTime: 2020/6/25 9:23
 */
@Data
public class User implements Serializable {
    /**
     * 用户id
     */
    private Integer uid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户的角色[单用户可能具备多种角色]
     */
    private Set<Role> roles = new HashSet<Role>();
}
