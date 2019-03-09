package com.ymkj.app.mapper;

import com.ymkj.app.controller.index.Login;
import com.ymkj.app.entity.LoginUser;
import com.ymkj.app.entity.RegisterUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


/**
 * @author Xiaohao
 * @date 2019/03//08
 */
@Component
public interface LoginMapper {
    /**
     * 通过用户姓名判断用户是否存在
     *
     * @param phone 手机号
     * @return 用户
     */
    @Select("select * from ym_user where phone=#{phone}")
    LoginUser findByPhone(@Param("phone") String phone);


}
