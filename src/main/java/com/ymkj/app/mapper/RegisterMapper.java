package com.ymkj.app.mapper;



import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;

@Component
public interface RegisterMapper {

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
