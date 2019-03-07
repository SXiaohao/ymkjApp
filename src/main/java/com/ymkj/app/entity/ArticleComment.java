package com.ymkj.app.entity;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.ymkj.app.utils.RelativeDateFormat.formatToString;

/**
 * @author SXiaohao
 * @date 2019/03/05
 */
@Data
public class ArticleComment {
    private int commentId;
    private int commentatorId;
    private String commentatorName;
    private String avatar;
    private int articleId;
    private int thumbsUp;
    private String commentContent;
    private String commentTime;
    private List<ArticleReply> replyList;

    public void setCommentTime(String commentTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            this.commentTime = formatToString(format.parse(commentTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
