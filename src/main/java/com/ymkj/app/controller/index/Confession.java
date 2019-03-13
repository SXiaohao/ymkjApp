package com.ymkj.app.controller.index;

import com.ymkj.app.service.confession.confessionimpl.ConfessionServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 表白墙控制器
 *
 * @author Xiaohao
 * @date 2019/02/28
 */
@RestController
public class Confession {
    @Resource
    ConfessionServiceImpl confessionService;


    @GetMapping(value = "/confession/index/{page}")
    public Map confessionIndex(@PathVariable int page) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("totalPages", confessionService.totalPages());
        map.put("cardsList", confessionService.getConfessionCardList(page));
        return map;
    }
}
