package com.mee.core.configuration;


import com.mee.sys.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


/**
 * shiro 工具类
 *
 * @author shadow
 */
public class ShiroUtils {
    private static final Logger log = LoggerFactory.getLogger(ShiroUtils.class);

    private ShiroUtils(){}

    /**
     * 获取shiro subject
     * @return
     * @author shadow
     * @Date 2019年11月21日 上午10:00:55
     */
    public synchronized static Subject getSubjct(){
        return SecurityUtils.getSubject();
    }

    /**
     * 获取登录session
     * @return
     * @author shadow
     * @Date 2019年11月21日 上午10:00:41
     */
    public static Session getSession(){
        return SecurityUtils.getSubject().getSession();
    }

    /**
     * 退出登录
     * @author shadow
     * @Date 2019年11月21日 上午10:00:24
     */
    public static void logout(){
        getSubjct().logout();
    }

    /**
     * 获取登录用户
     * @return
     * @author shadow
     * @Date 2019年11月21日 上午10:00:10
     */
    public synchronized static SysUser getUser(){
        SysUser user = null;
        Object obj = getSubjct().getPrincipal();
        if (null != obj){
            user = new SysUser();
            // TODO BeanUtils.copyBeanProp(user, obj);
            BeanUtils.copyProperties(obj,user);
        }
        return user;
    }

    /**
     * set用户
     * @param user
     * @author shadow
     * @Date 2019年11月21日 上午9:59:52
     */
    @Deprecated
    public static void setUser(SysUser user){
        Subject subject = getSubjct();
        PrincipalCollection principalCollection = subject.getPrincipals();
        String realmName = principalCollection.getRealmNames().iterator().next();
        PrincipalCollection newPrincipalCollection = new SimplePrincipalCollection(user, realmName);
        // 重新加载Principal
        subject.runAs(newPrincipalCollection);
    }

    /**
     * 清除授权信息
     * @author shadow
     * @Date 2019年11月21日 上午9:59:37
     */
    public static void clearCachedAuthorizationInfo(){
        RealmSecurityManager rsm = (RealmSecurityManager) SecurityUtils.getSecurityManager();
        ShiroRealm realm = (ShiroRealm) rsm.getRealms().iterator().next();
        realm.clearCachedAuthorizationInfo();
    }

    /**
     * 获取登录用户id
     * @return
     * @author shadow
     * @Date 2019年11月21日 上午9:58:55
     */
    public static String getUserId(){
        SysUser sysUser = getUser();
        if (sysUser == null || sysUser.getId() == null){
            log.error("用户不存在！");
            throw new RuntimeException("用户不存在！");
        }
        return sysUser.getId().trim();
    }

    /**
     * 获取登录用户name
     * @return
     * @author shadow
     * @Date 2019年11月21日 上午9:58:48
     */
    public static String getLoginName(){
        SysUser sysUser = getUser();
        if (sysUser == null){
            throw new RuntimeException("用户不存在！");
        }
        return sysUser.getUser_name();
    }

    /**
     * 获取登录用户ip
     * @return
     * @author shadow
     * @Date 2019年11月21日 上午9:58:26
     */
    public static String getIp(){
        return getSubjct().getSession().getHost();
    }

    /**
     * 获取登录用户sessionid
     * @return
     * @author shadow
     */
    public static String getSessionId(){
        return String.valueOf(getSubjct().getSession().getId());
    }

    /** get request**/
    public static HttpServletRequest getRequest(){
        try{
            return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        }catch(Exception e){
            log.error("获取Request是异常 ",e);
            return null;
        }
    }
}