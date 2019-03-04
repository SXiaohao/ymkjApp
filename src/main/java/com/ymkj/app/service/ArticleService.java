package com.ymkj.app.service;


import com.ymkj.app.entity.ArticleContent;
import com.ymkj.app.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * 文章逻辑层
 * @author Xiaohao
 * @date 2019/03/04
 */
@Service
public class ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    public ArticleContent getArticleContent(int articleId) {

        return articleMapper.getArticleContent(articleId);
    }
}
