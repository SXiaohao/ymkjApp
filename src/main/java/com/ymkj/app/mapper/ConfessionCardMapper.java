package com.ymkj.app.mapper;

import com.ymkj.app.entity.ConfessionCard;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 表白墙文章卡片 Dao层
 * @author Xiaohao
 * @date 2019/02/28
 */
@Component
public interface ConfessionCardMapper {
    /**
     * 查询ym_confession表和ym_user所有数据
     * @param page '起始
     * @param limit '结束'
     * @return List<Confession>
     */
    @Select("select ym_confession.*, ym_user.userName,avatar ,GROUP_CONCAT(imagePath)AS imagesList from ym_user,ym_confession,ym_confession_image where ym_confession.userId=ym_user.userId AND ym_confession.articleId=ym_confession_image.articleId  group by ym_confession.articleId ORDER BY `ym_confession`.`releaseTime` DESC limit #{page},#{limit}")
    List<ConfessionCard> findOfAll(@Param("page") int page,@Param("limit") int limit);

    /**
     * 查找文章对应的图片
     * @param id '目标文章id'
     * @return 图片链接
     */
    @Select("SELECT imagePath From ym_confession_image where articleId = #{id}")
    List<String> findOfConfessionImage(@Param("id")int id);

    /**
     * 查询m_confession数据数量
     * @return '返回ym_confession数据数量'
     */
    @Select("SELECT COUNT(*) FROM ym_confession")
    int cardsCount ();
}
