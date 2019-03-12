package com.ymkj.app.service.schoolservice.schoolimpl;

import com.ymkj.app.entity.School;
import com.ymkj.app.mapper.info.SchoolInfoMapper;
import com.ymkj.app.service.schoolservice.SchoolInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Xiaohao
 * @date 2019/03/12
 */
@Service
public class SchoolInfoServiceImpl implements SchoolInfoService {
    @Resource
    SchoolInfoMapper schoolInfoMapper;

    @Override
    public void getSchoolInfo(School school) {
        if (schoolInfoMapper.getSchoolInfo(school.getSchoolId()) == null) {
            schoolInfoMapper.insertSchoolInfo(school.getSchoolId(), school.getSchoolName(), school.getAddr());
        }
    }
}
