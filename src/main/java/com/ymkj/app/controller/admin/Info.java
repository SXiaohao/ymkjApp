package com.ymkj.app.controller.admin;


import com.ymkj.app.entity.School;
import com.ymkj.app.service.schoolservice.SchoolInfoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Xiaohao
 * @date 2019/03/12
 */
@RestController
public class Info {
    @Resource
    SchoolInfoService schoolInfoService;

    @PostMapping("/school/getInfo")
    public void schoolInfo(@RequestBody School school) {
        schoolInfoService.getSchoolInfo(school);
    }
}
