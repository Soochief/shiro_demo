package com.soochief.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 *
 * Created by sooch on 2017/2/6.
 */
public class MyRealm1 implements Realm{

    public String getName() {
        return "myRealm1";
    }

    public boolean supports(AuthenticationToken token) {
        //只支持UsernamePasswordToken
        return token instanceof UsernamePasswordToken;
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        if(!"zhang".equals(username)){
            throw new UnknownAccountException();
        }
        if(!"123".equals(password)){
            throw new IncorrectCredentialsException();
        }
        //身份认证成功，返回AuthenticationInfo实现
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
