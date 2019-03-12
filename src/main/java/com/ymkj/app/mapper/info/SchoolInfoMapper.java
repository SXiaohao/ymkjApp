package com.ymkj.app.mapper.info;


import com.ymkj.app.entity.School;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * @author Xiaohao
 * @date 2019/03/12
 */
@Component
public interface SchoolInfoMapper {
    /**
     * 获取学校信息
     *
     * @param schoolId 学校Id
     * @return 学校信息
     */
    @Select("select * from ym_school where schoolId=#{schoolId}")
    School getSchoolInfo(String schoolId);

    /**
     * 上传学校信息
     *
     * @param schoolId   学校Id
     * @param schoolName 学校名字
     * @param addr       学校地址
     * @return 插入状态
     */
    @Insert("INSERT INTO `ymkj_app`.`ym_school`(`schoolId`, `schoolName`, `addr`) VALUES (#{schoolId},#{schoolName} ,#{addr} )")
    Integer insertSchoolInfo(String schoolId, String schoolName, String addr);
}
