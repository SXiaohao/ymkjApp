package com.ymkj.app.service;


import com.ymkj.app.entity.ArticleComment;
import com.ymkj.app.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * 文章逻辑层
 *
 * @author Xiaohao
 * @date 2019/03/04
 */
@Service
public class ArticleService {

    @Resource
    ArticleMapper articleMapper;

    /**
     * 文章内容 附带 5条评论
     *
     * @param articleId 文章id
     * @return map
     */
    public Map getArticleContent(int articleId) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("articleContent", articleMapper.getArticleContent(articleId));
        List<ArticleComment> commentAndReplyList = articleMapper.getArticleComment(articleId);
        if (commentAndReplyList.size() != 0) {
            foreachReply(commentAndReplyList);
            map.put("commentAndReplyList", commentAndReplyList);
            map.put("other", "查看全部评论 ");

        } else {
            map.put("commentAndReplyList", commentAndReplyList);
            map.put("other", "暂无评论");
        }
        return map;
    }

    /**
     * 评论详情页
     *
     * @param articleId 文章id
     * @return 文章的全部的评论
     */
    public Map getCommentAndReply(int articleId) {
        List<ArticleComment> commentAndReplyList = articleMapper.getAllArticleComment(articleId);
        Map<String, Object> map = new LinkedHashMap<String, Object>() {
        };
        if (commentAndReplyList.size() != 0) {
            foreachReply(commentAndReplyList);
            map.put("commentAndReplyList", commentAndReplyList);
            map.put("length", commentAndReplyList.size());
        }
        return map;
    }


    /**
     * 将回复填充进评论链表
     *
     * @param commentAndReplyList 空回复的评论链表
     */
    private void foreachReply(List<ArticleComment> commentAndReplyList) {
        for (ArticleComment comment : commentAndReplyList
        ) {
            comment.setReplyList(articleMapper.getArticleReply(comment.getCommentId()));
        }
    }
}
