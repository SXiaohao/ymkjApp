package com.ymkj.app.service;

import com.ymkj.app.entity.enumSpecification.statusCode;
import com.ymkj.app.mapper.LoginMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Xiaohao
 */
@Service
public class LoginService {
    @Resource
    LoginMapper loginMapper;

    public Map login(String phone, String password) {
        Map<String, Object> map = new LinkedHashMap<>();

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(phone, password);
        try {

            subject.login(token);
            map.put("status", statusCode.SUCCESS.getCode());
            map.put("msg", "登陆成功");
        } catch (UnknownAccountException e) {
            map.put("status", statusCode.ALREADY_EXISTS.getCode());
            map.put("msg", "用户不存在");
        } catch (IncorrectCredentialsException e) {
            map.put("status", statusCode.NOT_LOGIN.getCode());
            map.put("msg", "密码错误");
        }
        return map;
    }


}
