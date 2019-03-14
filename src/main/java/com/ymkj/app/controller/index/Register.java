package com.ymkj.app.controller.index;


import com.ymkj.app.entity.User;
import com.ymkj.app.entity.enumSpecification.statusCode;
import com.ymkj.app.service.register.RegisterServiceImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.*;

/**
 * 注册控制器
 *
 * @author Xiaohao
 * @date 2019/03/09
 */
@RestController
public class Register {
    @Resource
    RegisterServiceImpl registerService;

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
    public Map register(@RequestBody User user) {
        return registerService.register(user);
    }


    /**
     * 上传图片
     *
     * @param file 图片文件
     * @return 图片
     * @throws IllegalStateException
     */
    @PostMapping("/upload/avatar")
    public Map upLoadImage(@RequestParam("avatar") MultipartFile file) throws IllegalStateException {
        return registerService.upLoad(file);
    }


}

