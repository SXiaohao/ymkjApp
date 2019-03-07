package com.ymkj.app.entity.shiro;

import lombok.Data;

/**
 * @author Administrator
 */
@Data
public class Permission {
    private Long id;
    private String permission;
    private Role role;
}
