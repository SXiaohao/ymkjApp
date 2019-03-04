package com.ymkj.app.controller.index;

import com.ymkj.app.service.ConfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 表白墙控制器
 *
 * @author Xiaohao
 * @date 2019/02/28 System.out.println
 */
@RestController
public class Confession {
    @Autowired
    ConfessionService confessionService;


    @GetMapping(value = "/confession/index/{page}")
    public Map confessionIndex(@PathVariable int page) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("totalPages", confessionService.totalPages());
        map.put("cardsList", confessionService.getConfessionCardList(page));
        return map;
    }
}
