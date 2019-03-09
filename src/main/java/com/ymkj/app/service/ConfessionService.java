package com.ymkj.app.service;

import com.ymkj.app.entity.ConfessionCard;
import com.ymkj.app.mapper.ConfessionCardMapper;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.*;


/**
 * 表白墙逻辑层
 *
 * @author Xiaohao
 * @date 2019/02/27
 */
@Service
public class ConfessionService {
    @Resource
    ConfessionCardMapper confessionCardMapper;

    /**
     * 表白墙文章列表
     *
     * @param page '页码'
     * @return List
     */
    public List getConfessionCardList(int page) {

        List<ConfessionCard> list = confessionCardMapper.findOfAll((page - 1) * 20, page * 20);

        return list;
    }

    /**
     * @return 总页数
     */
    public int totalPages() {
        int totalPages = confessionCardMapper.cardsCount() / 20;
        if (confessionCardMapper.cardsCount() / 20 != 0) {
            totalPages++;
        }
        return totalPages;
    }
}
