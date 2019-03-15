package com.ymkj.app.service.login;

import com.ymkj.app.entity.User;

import java.util.Map;

public interface LoginService {


    /**
     * 用户登录
     *
     * @param phone    手机号
     * @param password 密码
     * @return 登录状态
     */
    Map login(String phone, String password);

    /**
     * 修改密码
     * @param user 用户信息
     * @return 修改状态
     */
    Map forgotPassword(User user);
}
