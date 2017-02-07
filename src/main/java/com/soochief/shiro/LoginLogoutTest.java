package com.soochief.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * Created by sooch on 2017/2/6.
 */
public class LoginLogoutTest {

    @Test
    public void testHelloWorld(){
        // 获取SecurityManager工厂
//        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        // 获取SecurityManager实例，并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        // 获取Subject，取得用户名和密码的身份验证token
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
        try{
            //登录，身份验证
            subject.login(token);
        }catch(AuthenticationException e){
            //身份验证失败
        }
        //断言用户已经登录
        Assert.assertEquals(true, subject.isAuthenticated());
        // 用户退出
        subject.logout();
    }
}
