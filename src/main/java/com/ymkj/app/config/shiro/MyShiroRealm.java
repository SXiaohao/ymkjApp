package com.ymkj.app.config.shiro;

import com.ymkj.app.entity.User;
import com.ymkj.app.mapper.LoginMapper;
import com.ymkj.app.service.LoginService;
import com.ymkj.app.utils.PasswordHash;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static com.ymkj.app.utils.PasswordHash.validatePassword;

/**
 * @author Administrator
 */
public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    LoginMapper loginMapper;
    /**
     * 角色权限和对应权限添加
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
       return null;
    }

    /**
     * 认证逻辑
     * @param authenticationToken ''
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
        User user= loginMapper.findByName(token.getUsername());
        System.out.println(user);
        String[] passwordString= new String[2];
        if(user!=null){
            System.out.println(user);
            try {
                passwordString = validatePassword(token.getPassword(),user.getPassword());
                System.out.println(passwordString[1]);
                token.setPassword(passwordString[1].toCharArray());
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                e.printStackTrace();
            }
            return new SimpleAuthenticationInfo("",passwordString[0],"");
        }
        System.out.println(411);
        return null;
    }
}
