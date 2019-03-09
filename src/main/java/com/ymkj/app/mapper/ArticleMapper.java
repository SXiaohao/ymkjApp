package com.ymkj.app.mapper;

import com.ymkj.app.entity.ArticleComment;
import com.ymkj.app.entity.ArticleContent;
import com.ymkj.app.entity.ArticleReply;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 文章Dao层
 *
 * @author Xiaohao
 * @date 2019/03/04
 */
@Component
public interface ArticleMapper {

    /**
     * 获取文章详情页的内容
     *
     * @param articleId 文章id
     * @return ArticleContent实体类
     */
    @Select("select ym_confession.userId, ym_user.userName,avatar,ym_confession.articleId,content,releaseTime,readingVolume,thumbsUp ,GROUP_CONCAT(imagePath)AS imagesList from ym_user,ym_confession,ym_confession_image where  ym_confession.articleId=ym_confession_image.articleId  AND ym_confession.userId=ym_user.userId AND ym_confession_image.articleId=#{articleId} group by ym_confession.articleId ")
    ArticleContent getArticleContent(@Param("articleId") int articleId);

    /**
     * 查询前五条评论
     *
     * @param articleId 文章id
     * @return List<ArticleComment>
     */
    @Select("SELECT * ,ym_user.userName AS commentatorName,ym_user.avatar AS avatar From ym_confession_comment,ym_user where ym_user.userId=ym_confession_comment.commentatorId AND articleId = #{articleId} LIMIT 0,5")
    List<ArticleComment> getArticleComment(@Param("articleId") int articleId);

    /**
     * 查询所有评论
     *
     * @param articleId 文章id
     * @return List<ArticleComment>
     */
    @Select("SELECT * ,ym_user.userName AS commentatorName,ym_user.avatar AS avatar From ym_confession_comment,ym_user where ym_user.userId=ym_confession_comment.commentatorId AND articleId = #{articleId}")
    List<ArticleComment> getAllArticleComment(@Param("articleId") int articleId);

    /**
     * 评论下的所有回复
     *
     * @param commentId 评论id
     * @return List<ArticleReply>
     */
    @Select("SELECT t1.*, t2.userName AS replierName,t3.userName AS toReplierName From ym_confession_reply AS t1 INNER JOIN ym_user AS t2 ON t1.replierId = t2.userId AND commentId=#{commentId} INNER JOIN ym_user AS t3 ON t1.toReplierId = t3.userId AND commentId=#{commentId}")
    List<ArticleReply> getArticleReply(@Param("commentId") int commentId);
}
