package com.ymkj.app.mapper;

import com.ymkj.app.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author Xiaohao
 * @date 2019/03/14
 */
@Component
public interface CommonMapper {


    /**
     * 通过用户姓名判断用户是否存在
     *
     * @param phone 手机号
     * @return 用户
     */
    @Select("Select * FROM ym_user WHERE phone=#{phone}")
    User findByPhone(@Param("phone") String phone);
}
