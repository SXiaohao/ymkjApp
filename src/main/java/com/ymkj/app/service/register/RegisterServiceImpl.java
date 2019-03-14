package com.ymkj.app.service.register;

import com.aliyuncs.exceptions.ClientException;
import com.ymkj.app.entity.User;
import com.ymkj.app.entity.enumSpecification.statusCode;
import com.ymkj.app.mapper.CommonMapper;
import com.ymkj.app.mapper.register.RegisterMapper;
import com.ymkj.app.service.common.VerificationCode;
import com.ymkj.app.service.login.LoginService;
import com.ymkj.app.utils.FileUtil;
import com.ymkj.app.utils.JwtUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

import static com.ymkj.app.utils.PasswordHash.createHash;
import static com.ymkj.app.utils.SmsUtils.sendSms;
import static java.util.concurrent.Executors.*;

/**
 * @author Xiaohao
 * @date 2019/03/10
 */
@Service
public class RegisterServiceImpl implements RegisterService, VerificationCode {

    @Resource
    CommonMapper commonMapper;

    @Resource
    RegisterMapper registerMapper;
    private static String fileName = "201903011105473949.png";
    private static final String IMAGE_PATH = "http://localhost/static/images/";

    @Override
    public Map sendStatus(String phone) {
        Map<String, Object> map = new LinkedHashMap<>();
        User user = commonMapper.findByPhone(phone);
        if (user != null) {
            map.put("status", statusCode.FAILURE.getCode());
            map.put("msg", "此手机号已注册，请直接登陆！");
        } else {
            //生成短信验证码
            String random = String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
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
    public Map register(User user) {
        Map<String, Object> map = new LinkedHashMap<>();
        try {
            user.setPassword(createHash(user.getPassword()));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        user.setToken(JwtUtil.createTokenWithClaim());
        if (registerMapper.addRegisterUser(user.getPhone(),
                user.getPassword(), user.getUserName(), IMAGE_PATH + fileName,
                user.getAddDate(), user.getSchool().getId(), user.getSex(), user.getToken()) == 1) {
            FileUtil.moveTotherFolders(fileName);
            map.put("token", user.getToken());
            map.put("phone", user.getPhone());
            map.put("sex", user.getSex());
            map.put("userName", user.getUserName());
            map.put("status", statusCode.SUCCESS.getCode());
            map.put("msg", "注册成功");
        } else {
            map.put("status", statusCode.EXCEPTION.getCode());
            map.put("msg", "注册失败");
        }

        return map;
    }


    @Override
    public Map upLoad(MultipartFile file) {
        Map<String, Object> map = new LinkedHashMap<>();
        if (!file.isEmpty()) {
            // 获取文件名称,包含后缀
            String fileTyps = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            // String fileName="demo"+fileTyps;
            fileName = UUID.randomUUID().toString() + fileTyps;

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
        map.put("status", statusCode.FAILURE.getCode());
        map.put("msg", "上传失败！");

        return map;
    }


}

