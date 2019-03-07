package com.ymkj.app;


import com.ymkj.app.utils.PasswordHash;
import org.apache.shiro.codec.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;



import static com.ymkj.app.utils.PasswordHash.createHash;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AppApplicationTests {

    @Test
    public void contextLoads() throws InvalidKeySpecException, NoSuchAlgorithmException {

        KeyGenerator keygen = KeyGenerator.getInstance("AES");
        SecretKey deskey = keygen.generateKey();
        System.out.println(Base64.encodeToString(deskey.getEncoded()));


    }
}
