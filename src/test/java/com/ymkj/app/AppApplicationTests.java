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
import static com.ymkj.app.utils.PasswordHash.validatePassword;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AppApplicationTests {

    @Test
    public void contextLoads()  {


        try {
            String [] strings= PasswordHash.validatePassword("18841725546","1000:0397e5f820ca8a99fe2560c77b5031051345a1a904b4de16:5bf032470a3f989b447b92a2b4f775228a864dfaa21eca58");
            System.out.println(strings[0]);
            System.out.println(strings[1]);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

    }
}
