package com.ymkj.app.service;

import com.ymkj.app.entity.User;
import com.ymkj.app.mapper.LoginMapper;
import org.apache.shiro.SecurityUtils;
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

    public Map findByName(String userName, String password) {
        Map<String, String> map = new LinkedHashMap<>();

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        try {
            System.out.println(1);
            subject.login(token);
        } catch (UnknownAccountException e) {
            map.put("msg", "用户不存在");
        }

        return map;
    }
}
