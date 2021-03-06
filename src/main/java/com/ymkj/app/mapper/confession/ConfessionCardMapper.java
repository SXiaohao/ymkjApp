package com.ymkj.app.mapper.confession;

import com.ymkj.app.entity.ConfessionCard;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 表白墙文章卡片 Dao层
 *
 * @author Xiaohao
 * @date 2019/02/28
 */
@Component
public interface ConfessionCardMapper {
    /**
     * 查询ym_confession表和ym_user所有数据
     *
     * @param page  '起始
     * @param limit '结束'
     * @return List<confession>
     */
    @Select("SELECT ym_confession.*, ym_user.userName,avatar,GROUP_CONCAT(imagePath)AS imagesList " +
            "FROM ym_user,ym_confession,ym_confession_image " +
            "WHERE ym_confession.userId=ym_user.userId " +
            "AND ym_confession.articleId=ym_confession_image.articleId  " +
            "GROUP by ym_confession.articleId " +
            "ORDER BY `ym_confession`.`releaseTime` DESC limit #{page},#{limit}")
    List<ConfessionCard> findOfAll(@Param("page") int page, @Param("limit") int limit);

    /**
     * 查询m_confession数据数量
     *
     * @return '返回ym_confession数据数量'
     */
    @Select("SELECT COUNT(*) FROM ym_confession")
    int cardsCount();
}
