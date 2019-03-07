package com.ymkj.app.entity.shiro;

import lombok.Data;

import java.util.List;

/**
 * @author Administrator
 */
@Data
public class Role {
    private Long id;
    private String roleName;
    private User user;
    private List<Permission> permissions;
}
