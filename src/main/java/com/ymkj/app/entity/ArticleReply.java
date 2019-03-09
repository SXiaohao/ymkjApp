package com.ymkj.app.entity;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.ymkj.app.utils.RelativeDateFormat.formatToString;

/**
 * 评论回复实体类
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

    public void setReplyTime(String replyTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            this.replyTime = formatToString(format.parse(replyTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
