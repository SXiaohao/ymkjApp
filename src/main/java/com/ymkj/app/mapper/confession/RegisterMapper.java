package com.ymkj.app.mapper.confession;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
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
     * @param addDate  添加时间
     * @return 插入状态
     */
    @Insert("INSERT INTO `ymkj_app`.`ym_user`" +
            "(`phone`, `password`, `addDate`) " +
            "VALUES (#{phone}, #{password}, #{addDate})")
    Integer addRegisterUser(String phone, String password, String addDate);

    /**
     * 更新用户表注册信息
     *
     * @param avatar   头像路径
     * @param userName 昵称
     * @param sex      性别
     * @param schoolId 学校Id
     * @param phone    手机号
     * @return 更新状态
     */
    @Update("UPDATE `ymkj_app`.`ym_user` " +
            "SET " +
            "`userName` = #{userName}, `avatar` = #{avatar}, `schoolId` = #{schoolId} " +
            "WHERE `phone` = #{phone}")
    Integer updateUser(String avatar, String userName, int sex, int schoolId, String phone);

}
