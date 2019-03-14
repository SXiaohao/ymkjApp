package com.ymkj.app.mapper.register;


import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Component;

/**
 * 注册Dao层
 *
 * @author Xiaohao
 * @date 2019/03/11
 */
@Component
public interface RegisterMapper {


    /**
     * 注册用户
     *
     * @param phone    手机号
     * @param password 密码
     * @param userName 昵称
     * @param avatar 头像
     * @param addDate  添加时间
     * @param id 学校id
     * @param sex 性别
     * @param token 'token'
     * @return 插入状态
     */
    @Insert("INSERT INTO `ymkj_app`.`ym_user`" +
            "(`phone`, `password`, `userName`, `avatar`, `addDate`, `id`, `sex`, `token`)" +
            " VALUES " +
            "(#{phone}, #{password}, #{userName}, #{avatar}, #{addDate}, #{id}, #{sex}, #{token})")
    Integer addRegisterUser(String phone, String password, String userName, String avatar, String addDate, String id, int sex,String token);


}
