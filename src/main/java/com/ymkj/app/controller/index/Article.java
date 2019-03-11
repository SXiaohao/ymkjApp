package com.ymkj.app.controller.index;

import com.ymkj.app.service.confessionserver.confessionimpl.ArticleServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    ArticleServiceImpl articleService;

    @GetMapping(value = "/confession/article/{articleId}")
    public Map articleIndex(@PathVariable int articleId) {
        return articleService.getArticleContent(articleId);
    }

    @GetMapping(value = "/confession/comment/{commentId}")
    public Map articleCommentAndReply(@PathVariable int commentId){
        return articleService.getCommentAndReply(commentId);
    }
}
