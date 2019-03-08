package com.ymkj.app.mapper;

import com.ymkj.app.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;


/**
 * @author Xiaohao
 * @date 2019/03//08
 */
public interface LoginMapper {
    /**
     * 判断用户是否存在
     * @param userName 用户姓名
     * @return 用户
     */
    @Select("select * from ym_user where userName=#{userName}")
    User findByName(@Param("userName") String userName);
}
