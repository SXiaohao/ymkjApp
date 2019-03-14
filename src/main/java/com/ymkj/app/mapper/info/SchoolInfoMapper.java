package com.ymkj.app.mapper.info;


import com.ymkj.app.entity.School;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * 学校信息Dao层
 *
 * @author Xiaohao
 * @date 2019/03/12
 */
@Component
public interface SchoolInfoMapper {
    /**
     * 获取学校信息
     *
     * @param id 学校Id
     * @return 学校信息
     */
    @Select("select * from ym_school where id=#{id}")
    School getSchoolInfo(String id);

    /**
     * 上传学校信息
     *
     * @param id   学校Id
     * @param title 学校名字
     * @param addr       学校地址
     * @return 插入状态
     */
    @Insert("INSERT INTO `ymkj_app`.`ym_school`" +
            "(`id`, `title`, `addr`) VALUES " +
            "(#{id},#{title},#{addr} )")
    Integer insertSchoolInfo(String id, String title, String addr);
}
