package com.ymkj.app.controller.index;

import com.ymkj.app.entity.ArticleContent;
import com.ymkj.app.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Map;

/**
 * 文章详情页控制器
 *
 * @author Xiaohao
 * @date 2019/03/02
 */
@RestController
public class Article {
    @Resource
    ArticleService articleService;

    @GetMapping(value = "/confession/article/{articleId}")
    public ArticleContent articleIndex(@PathVariable int articleId) {

        return articleService.getArticleContent(articleId);
    }

    @GetMapping(value = "/confession/comment/{commentId}")
    public Map articleCommentAndReply(@PathVariable int commentId){
        return articleService.getCommentAndReply(commentId);
    }
}
