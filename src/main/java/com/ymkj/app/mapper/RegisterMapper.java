package com.ymkj.app.mapper;


import com.ymkj.app.entity.RegisterUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
public interface RegisterMapper {

    /**
     * 通过手机号查询用户是否存在
     *
     * @param phone 手机号
     * @return RegisterUser
     */
    @Select("select * from ym_user where phone=#{phone}")
    RegisterUser findByPhone(@Param("phone") String phone);

    /**
     * 注册用户
     *
     * @param phone    手机号
     * @param password 密码
     * @param addDate  添加时间
     * @return “1”为成功
     */
    @Insert("INSERT INTO `ymkj_app`.`ym_user`(`phone`, `password`, `addDate`) VALUES (#{phone}, #{password}, #{addDate})")
    Integer addRegisterUser(String phone, String password, String addDate);

}
