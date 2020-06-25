package org.main.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 角色类
 * @Author: bai
 * @DateTime: 2020/6/25 9:22
 */
@Data
public class Role implements Serializable {
    /**
     * 角色id
     */
    private Integer rid;
    /**
     * 角色名称
     */
    private String rname;
    /**
     * 角色权限[单角色可能具备多种权限]
     */
    private Set<Permission> permissions = new HashSet<Permission>();
    /**
     * 角色属于哪个用户[单角色可能属于多个用户],[同时有多人具备同一种角色]
     */
    private Set<User> users = new HashSet<User>();
}
