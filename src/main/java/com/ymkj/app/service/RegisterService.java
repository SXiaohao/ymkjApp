package com.ymkj.app.service;

import com.aliyuncs.exceptions.ClientException;
import com.ymkj.app.entity.RegisterUser;
import com.ymkj.app.entity.enumSpecification.statusCode;
import com.ymkj.app.mapper.CommonMapper;
import com.ymkj.app.mapper.RegisterMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.ymkj.app.utils.SmsUtils.sendSms;

/**
 * @author Xiaohao
 * @date 2019/03/10
 */
@Service
public class RegisterService {

    @Resource
    CommonMapper commonMapper;

    @Resource
    RegisterMapper registerMapper;

    private static String random;

    public Map sendStatus(String phone) {
        Map<String, Object> map = new LinkedHashMap<>();
        RegisterUser user = (RegisterUser) commonMapper.findByPhone(phone);
        if (user != null) {
            map.put("status", statusCode.NOT_LOGIN.getCode());
            map.put("msg", "此手机号已注册，请直接登陆！");

        } else {
            //生成短信验证码
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

        }
        return map;
    }

    public Map register(RegisterUser user) {
        Map<String, Object> map = new LinkedHashMap<>();
        if (!user.getVerificationCode().equals(random)) {
            map.put("status", statusCode.NOT_LOGIN.getCode());
            map.put("msg", "验证码错误");
        } else {
            if (registerMapper.addRegisterUser(user.getPhone(), user.getPassword(), user.getAddDate()) == 1) {
                map.put("status", statusCode.SUCCESS.getCode());
                map.put("msg", "注册成功");
            } else {
                map.put("status", statusCode.EXCEPTION.getCode());
                map.put("msg", statusCode.EXCEPTION.getMessage());
            }

        }
        return map;
    }
}
