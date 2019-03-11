package com.ymkj.app.service.confessionserver.confessionimpl;

import com.aliyuncs.exceptions.ClientException;
import com.ymkj.app.entity.RegisterUser;
import com.ymkj.app.entity.enumSpecification.statusCode;
import com.ymkj.app.mapper.CommonMapper;
import com.ymkj.app.mapper.confession.RegisterMapper;
import com.ymkj.app.service.confessionserver.RegisterService;
import com.ymkj.app.utils.FileUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import static com.ymkj.app.utils.SmsUtils.sendSms;

/**
 * @author Xiaohao
 * @date 2019/03/10
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Resource
    CommonMapper commonMapper;

    @Resource
    RegisterMapper registerMapper;

    private static String random;

    @Override
    public Map sendStatus(String phone) {
        Map<String, Object> map = new LinkedHashMap<>();
        RegisterUser user = (RegisterUser) commonMapper.findByPhone(phone);
        if (user != null) {
            map.put("status", statusCode.NOT_LOGIN.getCode());
            map.put("msg", "此手机号已注册，请直接登陆！");

        } else {
            //生成短信验证码
            random = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
            try {
                //发送验证码
                sendSms(phone, random);
            } catch (ClientException e) {
                e.printStackTrace();
            }
            map.put("status", statusCode.SUCCESS.getCode());
            map.put("msg", "验证码发送成功，请注意查收！");
            map.put("verificationCode", random);

        }
        return map;
    }

    @Override
    public Map register(RegisterUser user) {
        Map<String, Object> map = new LinkedHashMap<>();
        if (!user.getVerificationCode().equals(random)) {
            map.put("status", statusCode.NOT_LOGIN.getCode());
            map.put("msg", "验证码错误");
        } else {
            if (registerMapper.addRegisterUser(user.getPhone(), user.getPassword(), user.getAddDate()) == 1) {
                map.put("status", statusCode.SUCCESS.getCode());
                map.put("msg", "注册成功");
            } else {
                map.put("status", statusCode.EXCEPTION.getCode());
                map.put("msg", statusCode.EXCEPTION.getMessage());
            }

        }
        return map;
    }

    @Override
    public Map upLoad(MultipartFile file) {
        Map<String, Object> map = new LinkedHashMap<>();
        if (!file.isEmpty()) {
            // 获取文件名称,包含后缀
            String fileTyps = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            // String tempName="demo"+fileTyps;
            String fileName = UUID.randomUUID().toString() + fileTyps;

            // 存放在这个路径下：该路径是该工程目录下的static文件下：(注：该文件可能需要自己创建)
            // 放在static下的原因是，存放的是静态文件资源，即通过浏览器输入本地服务器地址，加文件名时是可以访问到的
            String path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/temp/";

            try {
                // 该方法是对文件写入的封装，在util类中，导入该包即可使用，后面会给出方法
                FileUtil.fileupload(file.getBytes(), path, fileName);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            map.put("status", statusCode.SUCCESS.getCode());
            map.put("msg", "上传成功！");
            map.put("imgName", fileName);

            return map;
        }
        map.put("status", statusCode.NOT_LOGIN.getCode());
        map.put("msg", "上传失败！");

        return map;
    }


}

