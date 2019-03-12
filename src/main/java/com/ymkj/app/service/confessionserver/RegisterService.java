package com.ymkj.app.service.confessionserver;


import com.ymkj.app.entity.RegisterUser;
import org.apache.catalina.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author Xiaohao
 */
public interface RegisterService {

    /**
     * 发送验证码
     *
     * @param phone 手机号
     * @return 发送状态
     */
    Map sendStatus(String phone);

    /**
     * 用户注册
     *
     * @param user 用户填写的注册信息
     * @return 注册状态
     */
    Map register(RegisterUser user);

    /**
     * 用户上传的临时图片
     *
     * @param file 图片文件
     * @return 临时图片地址
     */
    Map upLoad(MultipartFile file);


    /**
     * 完善用户信息
     * @param user
     * @return
     */
     Map updateInformation(RegisterUser user);
}
