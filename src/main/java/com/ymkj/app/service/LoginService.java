package com.ymkj.app.service;

import com.ymkj.app.entity.shiro.User;
import com.ymkj.app.mapper.LoginMapper;
import org.apache.catalina.security.SecurityUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.RememberMeManager;
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
    public User findByName(String userName,String password){
        Map<String,String> map= new LinkedHashMap<>();
        Subject subject= SecurityUtils.getSubject();

        UsernamePasswordToken token =new UsernamePasswordToken(userName,password);
        try{
            subject.login(token);
            token.setRememberMe(true);
        }catch (UnknownAccountException e){
            map.put("msg","用户不存在");
        }
        return loginMapper.findByName(userName);
    }
}
