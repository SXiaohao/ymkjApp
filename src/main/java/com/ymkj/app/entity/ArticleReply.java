package com.ymkj.app.entity;

import lombok.Data;

/**
 * @author Xiaohao
 * @date 2019/03/05
 */
@Data
public class ArticleReply {
    private int replyId;
    private int commentId;
    private int replierId;
    private String replierName;
    private int toReplierId;
    private String toReplierName;
    private String replyContent;
    private String replyTime;

}
