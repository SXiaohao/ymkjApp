package com.ymkj.app.entity;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;


@Data
public class ArticleContent {

    private int articleId;
    private int userId;
    private String userName;
    private String avatar;
    private int readingVolume;
    private int thumbsUp;
    private String releaseTime;
    private String content;
    private String[] imagesList;

    public void setReleaseTime(Date releaseTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd ");
        this.releaseTime = format.format(releaseTime);
    }

    public void setImagesList(String imagesList) {
        String[] params;
        params = imagesList.split(",");
        this.imagesList = params;
    }
}
