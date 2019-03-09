package com.ymkj.app.entity;

import com.ymkj.app.utils.PasswordHash;
import lombok.Data;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static com.ymkj.app.utils.PasswordHash.createHash;

/**
 * 用户实体类
 *
 * @author Xiaohao
 * @date 2019/03/08
 */
@Data
public class RegisterUser {

    private String phone;
    private String password;
    private String verificationCode="Xiaohao";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private String addDate= sdf.format(new Date());
    public void setPassword(String password) {
        try {
            this.password = createHash(password);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
}
