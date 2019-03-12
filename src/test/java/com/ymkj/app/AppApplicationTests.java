package com.ymkj.app;


import com.ymkj.app.utils.JwtUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import static com.ymkj.app.utils.PasswordHash.validatePassword;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AppApplicationTests {

    @Test
    public void contextLoads()  {
        JwtUtil.createToken();

        //String createToken = demo.createToken();
        String createTokenWithClaim = JwtUtil.createTokenWithClaim("18841725546");
        System.out.println(createTokenWithClaim);
        JwtUtil.verifyToken(createTokenWithClaim);


    }
}
