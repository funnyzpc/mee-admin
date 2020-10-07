package com.mee.sys.service;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MyShiroAuthService {


    public HashMap<String,Object> userLoginAction (String userName, String passWord){

        HashMap<String,Object> resultMap = new HashMap<>();

        //初始化SecurityManager对象
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        //通过SecurityManager工厂对象,获取SecurityManager实例对象.
        SecurityManager securityManager =  factory.getInstance();

        // 把 securityManager 实例 绑定到 SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);

        //组建Subject主体.
        Subject subject = SecurityUtils.getSubject();

        //创建 token 令牌
        UsernamePasswordToken token = new UsernamePasswordToken(userName,passWord);

        //用户登录操作.
        try{
            subject.login(token);
            resultMap.put("code","200");
            resultMap.put("msg","用户登录成功");
        }catch (AuthenticationException e){
            //登录失败原因 1 用户不存在 2 用户密码不正确
            resultMap.put("code","-1");
            resultMap.put("msg","用户登录失败");
        }
        return resultMap;

    }
}
