package com.mee.core.configuration;

import com.mee.common.enums.StatusEnum;
import com.mee.common.service.LogServiceImpl;
import com.mee.common.service.impl.ShiroAccountLockedServiceImpl;
import com.mee.common.util.ChaosUtil;
import com.mee.common.util.DateUtil;
import com.mee.common.util.HttpUtil;
import com.mee.core.dao.DBSQLDao;
import com.mee.sys.entity.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description 身份校验核心类
 * @author shadow
 *
 */
// @Service
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    private DBSQLDao dbSQLDao;
    @Value("${spring.profiles.active}")
    private String env;
    @Resource
    private LogServiceImpl logService;
    @Resource
    private ShiroAccountLockedServiceImpl shiroAccountLockedService;
    /**
     * 认证登陆
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //加这一步的目的是在Post请求的时候会先进认证，然后在到请求
        if (token.getPrincipal() == null) {
            return null;
        }
        String username = (String) token.getPrincipal();
        String enc_password = new String((char[]) token.getCredentials());
        // 解密账户密码
        if(!StringUtils.hasText(enc_password)){
            throw new IncorrectCredentialsException("账户密码有误:"+username);
        }

        String dec_password = ChaosUtil.dec(enc_password);
        if("".equals(dec_password)){
            throw new IncorrectCredentialsException("账户密码有误:"+username);
        }else{
            UsernamePasswordToken tk = (UsernamePasswordToken)token;
            tk.setPassword(dec_password.toCharArray());
        }

        // check locked 账户是否锁定
        if(shiroAccountLockedService.isLocked(username)){
            throw new LockedAccountException("账户已锁定:"+username);
        }
        Map<String,Object> param = new HashMap<String,Object>(2,1);
        param.put("user_name",username);
        param.put("del_flag",1);
        SysUser sysUser = dbSQLDao.findOne("com.mee.xml.SysUser.findList",param);
        if( null==sysUser ){
            throw new UnknownAccountException("用户不存在:"+username);
        }
        if ( !StatusEnum.VALID.code.equals(sysUser.getStatus()) ) {
            throw new DisabledAccountException("账户未启用，请联系管理员"+username);
        }else{
            //ByteSource salt = ByteSource.Util.bytes(username+(env.contains("prod")?env:"test"));//盐
            ByteSource salt = ByteSource.Util.bytes( sysUser.getId() );//盐
            sysUser.setLast_login_date(DateUtil.now());
            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                    sysUser, // 用户对象
                    sysUser.getPassword(), // 密码
                    salt, // 盐
                    getName() // realm name
            );
            // record last_login_date
            dbSQLDao.update("com.mee.xml.SysUser.updateLastLoginDate",sysUser);
            // record log
            logService.log(sysUser.getId(),1,sysUser.getNick_name()+"|"+username, HttpUtil.getRemoteAddr(ShiroUtils.getRequest()),"user login record");
            // check single
            // this.kickOutAccount(username);
            return authenticationInfo;
        }

    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if(principals == null){
            throw new AuthorizationException("principals should not be null");
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser userinfo  = (SysUser)principals.getPrimaryPrincipal();

        // 通过中间表查询用户所属角色
        Map<String,Object> params = new HashMap<String,Object>(1,1);
        params.put("user_id",userinfo.getId());

        List<SysRole> sysRoles= dbSQLDao.find("com.mee.xml.SysRole.findByUserId",params);
        for(SysRole sys_role:sysRoles){
            String role_id=sys_role.getId();//角色id
            authorizationInfo.addRole(sys_role.getName());//添加角色名字
            // 查询菜单权限
            Map<String,Object> queryParams = new HashMap<String,Object>(2,1);
            queryParams.put("role_id",role_id);
            List<SysMenu> sysMenus= dbSQLDao.find("com.mee.xml.SysMenu.findByRoleId",queryParams);
            for(SysMenu p:sysMenus){
                if(!StringUtils.isEmpty(p.getPermission())){
                    authorizationInfo.addStringPermission(p.getPermission());
                }
            }
        }
        return authorizationInfo;
    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo(){
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }

    /**
     * 踢出当前账户已登录的
     * @param user_name
     */
    public void kickOutAccount(String user_name){
        // 获取当前用户sessionId
        String currentUserSessionId = SecurityUtils.getSubject().getSession().getId().toString();

        // 获取shiro的sessionManager
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        DefaultWebSessionManager sessionManager = (DefaultWebSessionManager)securityManager.getSessionManager();

        // 获取所有已登录用户的session列表
        Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();
        for(Session onlineSession:sessions){
            SimplePrincipalCollection onlineSessionKey = (SimplePrincipalCollection)onlineSession.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if(null == onlineSessionKey){
                continue;
            }
            SysUser rst = (SysUser)onlineSessionKey.getPrimaryPrincipal();
            if(user_name.equals(rst.getUser_name()) && !onlineSession.getId().equals(currentUserSessionId)){
                sessionManager.getSessionDAO().delete(onlineSession);
            }
        }
    }

}