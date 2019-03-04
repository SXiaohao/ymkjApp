package com.ymkj.app.entity;


import lombok.Data;

import java.util.Date;

/**
 * 用户实体类
 *
 * @author Xiaohao
 * @date 2019/2/27
 */
@Data
public class User {
    private int userId;
    private String phone;
    private String password;
    private String userName;
    private String avatar;
    private String schoolName;
    private Date addDate;
    private int status;
}
