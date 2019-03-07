package com.ymkj.app.mapper;

import com.ymkj.app.entity.shiro.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface LoginMapper {
    @Select("select * from ym_user where userName=#{userName}")
    User findByName(@Param("userName") String userName);
}
