package com.ymkj.app.service.confessionserver.confessionimpl;

import com.ymkj.app.entity.RegisterUser;
import com.ymkj.app.entity.enumSpecification.statusCode;
import com.ymkj.app.mapper.CommonMapper;
import com.ymkj.app.mapper.confession.LoginMapper;
import com.ymkj.app.service.confessionserver.LoginService;
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
public class LoginServiceImpl implements LoginService {
    @Resource
    LoginMapper loginMapper;
    @Resource
    CommonMapper commonMapper;

    @Override
    public Map login(String phone, String password) {
        Map<String, Object> map = new LinkedHashMap<>();

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(phone, password);
        try {
            subject.login(token);

        } catch (UnknownAccountException e) {
            map.put("status", statusCode.ALREADY_EXISTS.getCode());
            map.put("msg", "用户不存在");
        } catch (IncorrectCredentialsException e) {
            map.put("status", statusCode.FAILURE.getCode());
            map.put("msg", "密码错误");
        }
        RegisterUser user= commonMapper.findByPhone(phone);
        map.put("token","");
        map.put("sex",user.getSex());
        map.put("userName",user.getUserName());
        map.put("schoolId",user.getSchoolId());
        map.put("status", statusCode.SUCCESS.getCode());
        map.put("msg", "登陆成功");
        return map;
    }


}
