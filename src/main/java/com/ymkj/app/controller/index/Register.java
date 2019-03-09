package com.ymkj.app.controller.index;


import com.ymkj.app.entity.RegisterUser;
import com.ymkj.app.service.RegisterService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 注册控制器
 *
 * @author Xiaohao
 * @date 2019/03/09
 */
@RestController
public class Register {
    @Resource
    RegisterService registerService;

    /**
     * 发送验证码
     *
     * @param phone 手机号
     * @return 发送状态
     */
    @GetMapping("/register/sendSms/{phone}")
    public Map smsVerification(@PathVariable String phone) {
        return registerService.sendStatus(phone);
    }

    /**
     * 用户注册
     *
     * @param user 用户
     * @return 注册状态
     */
    @PostMapping("/register")
    public Map register(@RequestBody RegisterUser user) {
        return registerService.register(user);
    }
}
