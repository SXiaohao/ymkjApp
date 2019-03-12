package com.ymkj.app.service.confessionserver;


import java.util.List;

/**
 * @author Xiaohao
 * @date 2019/03/12
 */
public interface ConfessionService {


    /**
     * 表白墙文章列表
     *
     * @param page '页码'
     * @return List
     */
    List getConfessionCardList(int page);

    /**
     * 表白墙总页数
     * @return 页数
     */
    int totalPages();
}
