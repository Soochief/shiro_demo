package com.soochief.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * Created by sooch on 2017/2/7.
 */
public class AuthenticationTest {

    private void login(String configFile){
        //获取SecurityManager工厂
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);
        //获取SecurityManager实例,绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //获取Subject
        Subject subject = SecurityUtils.getSubject();
        //创建用户名/密码验证token
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");

        subject.login(token);
    }

    /**
     * 测试AllSuccessfulStrategy成功
     */
    @Test
    public void testAllSuccessfulStrategyWithSuccess(){
        login("classpath:shiro-authentication-all-success.ini");
        Subject subject = SecurityUtils.getSubject();

        //获取身份集合，其中包含了Realm验证成功的身份信息
        PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(2,principalCollection.asList().size());
    }

    /**
     * 测试AllSuccessfulStrategy失败
     * 若抛出UnknownAccountException则测试通过
     */
    @Test(expected = UnknownAccountException.class)
    public void testAllSuccessfulStragegyWithFail(){
        login("classpath:shiro-authentication-all-success.ini");
        Subject subject = SecurityUtils.getSubject();
    }
}
