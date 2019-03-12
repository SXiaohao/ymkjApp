package com.ymkj.app.controller.index;


import com.ymkj.app.entity.RegisterUser;
import com.ymkj.app.service.confessionserver.confessionimpl.LoginServiceImpl;
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
    LoginServiceImpl loginService;



    @PostMapping("/login")
    public Map loginValidation(@RequestBody RegisterUser user) {
        System.out.println(user);
        return loginService.login(user.getPhone(), user.getPassword());
    }
}
