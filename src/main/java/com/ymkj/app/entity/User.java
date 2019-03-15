package com.ymkj.app.entity;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 用户实体类
 *
 * @author Xiaohao
 * @date 2019/03/08
 */
@Data
public class User {
    private final static int MAX_LENGTH = 20;
    private final static int MIN_LENGTH = 3;

    private String phone;
    private String password;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private String addDate = sdf.format(new Date());
    private String avatar;
    private int sex;
    private String id;
    private School school;
    private String userName;
    private String token;
    private String code;


}