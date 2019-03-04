package com.ymkj.app.mapper;

import com.ymkj.app.entity.ArticleComment;
import com.ymkj.app.entity.ArticleContent;
import com.ymkj.app.entity.ArticleReply;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ArticleMapper {


    /**
     * 获取文章详情页的内容
     * @param articleId 文章id
     * @return  ArticleContent实体类
     */
    @Select("select ym_confession.userId, ym_user.userName,avatar,ym_confession.articleId,content,releaseTime,readingVolume,thumbsUp ,GROUP_CONCAT(imagePath)AS imagesList from ym_user,ym_confession,ym_confession_image where  ym_confession.articleId=ym_confession_image.articleId  AND ym_confession.userId=ym_user.userId AND ym_confession_image.articleId=#{articleId} group by ym_confession.articleId ")
    ArticleContent getArticleContent(@Param("articleId")int articleId);

    List<ArticleComment> getArticleComment(@Param("articleId")int id);

    List<ArticleReply> getArticleReply(@Param("commentId")int id);
}
