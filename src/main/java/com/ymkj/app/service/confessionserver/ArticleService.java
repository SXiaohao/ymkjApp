package com.ymkj.app.service.confessionserver;

import java.util.Map;

/**
 * @author Xiaohao
 * @date 2019/03/12
 */
public interface ArticleService {
    /**
     * 文章内容 附带 5条评论
     *
     * @param articleId 文章id
     * @return map
     */
     Map getArticleContent(int articleId) ;

    /**
     * 评论详情页
     *
     * @param articleId '文章id'
     * @return 文章的全部的评论
     */
     Map getCommentAndReply(int articleId) ;


}
