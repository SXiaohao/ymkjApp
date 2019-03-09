package com.ymkj.app.controller.index;


import com.ymkj.app.entity.LoginUser;
import com.ymkj.app.service.LoginService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;


/**
 * 登录控制器
 * @author Xiaohao
 * @date 2019/03/08
 */
@RestController
public class Login {
    @Resource
    LoginService loginService;



    @PostMapping("/login")
    public Map loginValidation(@RequestBody LoginUser user) {

        return loginService.login(user.getPhone(), user.getPassword());
    }
}
