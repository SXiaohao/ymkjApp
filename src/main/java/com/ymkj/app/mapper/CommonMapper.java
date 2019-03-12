package com.ymkj.app.mapper;

import com.ymkj.app.controller.index.Register;
import com.ymkj.app.entity.RegisterUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface CommonMapper {


    /**
     * 通过用户姓名判断用户是否存在
     *
     * @param phone 手机号
     * @return 用户
     */
    @Select("select * from ym_user where phone=#{phone}")
    RegisterUser findByPhone(@Param("phone") String phone);
}
