package com.ymkj.app;


import com.ymkj.app.utils.PasswordHash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;



import static com.ymkj.app.utils.PasswordHash.createHash;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AppApplicationTests {

    @Test
    public void contextLoads() throws InvalidKeySpecException, NoSuchAlgorithmException {

           if (PasswordHash.validatePassword("18841725546",PasswordHash.createHash("18841725546"))){
               System.out.println("true"+PasswordHash.createHash("18841725546"));
           }
           else {
               System.out.println("false");
           }

    }
}
