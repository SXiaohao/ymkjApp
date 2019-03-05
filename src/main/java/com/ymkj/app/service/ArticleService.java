package com.ymkj.app.service;


import com.ymkj.app.entity.ArticleComment;
import com.ymkj.app.entity.ArticleContent;
import com.ymkj.app.entity.ArticleReply;
import com.ymkj.app.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * 文章逻辑层
 * @author Xiaohao
 * @date 2019/03/04
 */
@Service
public class ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    /**
     * @param articleId 文章id
     * @return 文章内容
     */
    public ArticleContent getArticleContent(int articleId) {

        return articleMapper.getArticleContent(articleId);
    }

    public Map getCommentAndReply(int articleId){
        List<ArticleComment> commentAndReplyList=articleMapper.getArticleComment(articleId);
        Map<String,Object> map =new LinkedHashMap<String, Object>(){};
        if (commentAndReplyList.size()!=0) {
            for (ArticleComment comment : commentAndReplyList
            ) {
                comment.setReplyList(articleMapper.getArticleReply(comment.getCommentId()));
            }
            map.put("commentAndReplyList",commentAndReplyList);
        }else {
           map.put("status",300);
           map.put("msg","暂无评论");
        }

        return map;
    }
}
