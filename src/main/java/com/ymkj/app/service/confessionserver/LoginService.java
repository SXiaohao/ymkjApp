package com.ymkj.app.service.confessionserver;

import com.ymkj.app.entity.enumSpecification.statusCode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import java.util.LinkedHashMap;
import java.util.Map;

public interface LoginService {


    /**
     * 用户登录
     * @param phone 手机号
     * @param password 密码
     * @return 登录状态
     */
    Map login(String phone, String password) ;
}
