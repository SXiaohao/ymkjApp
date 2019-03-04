package com.ymkj.app.service;


import com.ymkj.app.entity.ArticleContent;
import com.ymkj.app.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    public ArticleContent getArticleContent(int articleId) {

        return articleMapper.getArticleContent(articleId);
    }
}
