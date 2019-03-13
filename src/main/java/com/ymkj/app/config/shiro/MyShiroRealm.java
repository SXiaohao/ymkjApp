package com.ymkj.app.config.shiro;

import com.ymkj.app.entity.User;
import com.ymkj.app.mapper.CommonMapper;
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
    CommonMapper commonMapper;

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
     *
     * @param authenticationToken
     * @return AuthenticationInfo
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = commonMapper.findByPhone(token.getUsername());
        if (user != null) {
            try {
                String[] passwordString = validatePassword(token.getPassword(), user.getPassword());
                token.setPassword(passwordString[1].toCharArray());
                return new SimpleAuthenticationInfo("", passwordString[0], "");
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
