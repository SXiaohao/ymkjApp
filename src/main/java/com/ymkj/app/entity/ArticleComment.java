package com.ymkj.app.entity;

import lombok.Data;

import java.util.List;

/**
 * @author SXiaohao
 * @date 2019/03/05
 */
@Data
public class ArticleComment {
    private  int commentId;
    private String commentatorName;
    private String avatar;
    private int articleId;
    private String commentContent;
    private String commentTime;
    private List<ArticleReply> replyList;
}
