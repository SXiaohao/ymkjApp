package com.ymkj.app.service.confessionserver;

import com.ymkj.app.entity.ConfessionCard;

import java.util.List;

public interface ConfessionService {


    /**
     * 表白墙文章列表
     *
     * @param page '页码'
     * @return List
     */
    List getConfessionCardList(int page);

    /**
     * @return 表白墙总页数
     */
    int totalPages();
}
