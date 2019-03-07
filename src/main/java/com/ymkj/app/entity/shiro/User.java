package com.ymkj.app.entity.shiro;

import lombok.Data;

import java.util.List;

/**
 * @author Administrator
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer password;
    private List<Role> roles;

}
