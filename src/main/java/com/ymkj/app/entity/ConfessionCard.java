package com.ymkj.app.entity;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.ymkj.app.utils.RelativeDateFormat.formatToString;

/**
 * 表白墙文章卡片实体类
 *
 * @author Xiaohao
 * @date 2019/02/28
 */
@Data
public class ConfessionCard {
    private final static int ARTICLE_STRING_SUB = 80;
    private int userId;
    private String userName;
    private String avatar;
    private int articleId;
    private String content;
    private String releaseTime;
    private int thumbsUp;
    private int readingVolume;
    private String[] imagesList;

    public void setContent(String content) {
        if (content.length() >= ARTICLE_STRING_SUB) {
            this.content = content.substring(0, 80) + "...";
        } else {
            this.content = content;
        }
    }

    public void setReleaseTime(String releaseTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            this.releaseTime = formatToString(format.parse(releaseTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    public void setImagesList(String imagesList) {
        String[] params;
        params = imagesList.split(",");
        this.imagesList = params;
    }

}
