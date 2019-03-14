package com.ymkj.app.service.login;

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

    Map forgotpassword();
}
