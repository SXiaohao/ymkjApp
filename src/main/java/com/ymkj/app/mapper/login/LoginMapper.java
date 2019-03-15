package com.ymkj.app.mapper.login;


import com.ymkj.app.entity.School;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;


/**
 * 登录Dao层
 *
 * @author Xiaohao
 * @date 2019/03//08
 */
@Component
public interface LoginMapper {
    /**
     * 查找学校
     *
     * @param id 学校Id
     * @return 学校信息
     */
    @Select("SELECT * FROM ym_school WHERE id=#{id}")
    School findOfSchool(String id);

    /**
     * 更新token
     *
     * @param phone 手机号
     * @param token token
     * @return 更新状态
     */
    @Update("UPDATE `ym_user` SET `token` = #{token} WHERE `phone` =#{phone}")
    Integer updateToken(String phone, String token);

    /**
     * 忘记密码
     *
     * @param phone    手机号
     * @param password 密码
     * @return 更新状态
     */
    @Update("UPDATE `ym_user` SET `password` = #{password}, WHERE `phone` =#{phone}")
    Integer updatePassword(String phone, String password);
}
