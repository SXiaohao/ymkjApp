package com.ymkj.app.service.login;

import com.aliyuncs.exceptions.ClientException;
import com.ymkj.app.entity.User;
import com.ymkj.app.entity.enumSpecification.statusCode;
import com.ymkj.app.mapper.CommonMapper;
import com.ymkj.app.mapper.login.LoginMapper;
import com.ymkj.app.service.common.VerificationCode;
import com.ymkj.app.utils.JwtUtil;
import com.ymkj.app.utils.PasswordHash;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.ymkj.app.utils.SmsUtils.sendSms;

/**
 * @author Xiaohao
 * @date 2019/03/15
 */
@Service
public class LoginServiceImpl implements LoginService, VerificationCode {
    @Resource
    LoginMapper loginMapper;
    @Resource
    CommonMapper commonMapper;
    private static String random;

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
        User user = commonMapper.findByPhone(phone);
        String newToken = JwtUtil.createTokenWithClaim();
        loginMapper.updateToken(phone, newToken);
        map.put("token", newToken);
        map.put("sex", user.getSex());
        map.put("phone", phone);
        map.put("userName", user.getUserName());
        map.put("school", loginMapper.findOfSchool(user.getId()));
        map.put("status", statusCode.SUCCESS.getCode());
        map.put("msg", "登陆成功");

        return map;
    }

    @Override
    public Map forgotPassword(User user) {
        Map<String, Object> map = new LinkedHashMap<>();
        if (user.getCode().equals(random)){
            try {
                String password = PasswordHash.createHash(user.getPhone());
                if (loginMapper.updatePassword(user.getPhone(), password) == 1) {
                    map.put("status", statusCode.SUCCESS.getCode());
                    map.put("msg", "密码修改成功！！");
                    return map;
                }
                map.put("status", statusCode.FAILURE.getCode());
                map.put("msg", "密码修改失败！！");
                return map;
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                e.printStackTrace();
            }
            map.put("status", statusCode.EXCEPTION.getCode());
            map.put("msg", "发生异常！！");
            return map;
        }
       return map;
    }

    @Override
    public Map sendStatus(String phone) {
        Map<String, Object> map = new LinkedHashMap<>();
        User user = commonMapper.findByPhone(phone);
        if (user != null) {
            random = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
            try {
                //发送验证码
                sendSms(phone, random);
            } catch (ClientException e) {
                e.printStackTrace();
            }
            map.put("status", statusCode.SUCCESS.getCode());
            map.put("msg", "验证码发送成功，请注意查收！");
            map.put("verificationCode", random);

        } else {
            map.put("status", statusCode.FAILURE.getCode());
            map.put("msg", "此手机号不存在，请注册！");
        }
        return map;
    }
}
