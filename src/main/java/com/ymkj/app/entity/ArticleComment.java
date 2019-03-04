package com.ymkj.app.entity;

import lombok.Data;

import java.util.List;

@Data
public class ArticleComment {
    private List<ArticleReply> replyList;
}
