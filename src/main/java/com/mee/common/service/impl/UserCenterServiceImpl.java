package com.mee.common.service.impl;

import com.mee.common.service.UserCenterService;
import com.mee.common.util.ChaosUtil;
import com.mee.common.util.DateUtil;
import com.mee.common.util.MeeResult;
import com.mee.common.util.ResultBuild;
import com.mee.core.configuration.ShiroUtils;
import com.mee.core.dao.DBSQLDao;
import com.mee.sys.dto.SysUserDTO;
import com.mee.sys.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户中心
 *
 * @author shaoow
 * @version 1.0
 * @className UserCenterServiceImpl
 * @date 2023/6/6 13:59
 */
@Service
public class UserCenterServiceImpl implements UserCenterService {
    /**
     * 日志
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserCenterServiceImpl.class);

    @Autowired
    private DBSQLDao dbSQLDao;

    /**
     * 查询系统::用户信息表列表
     *
     * @param SysUser2(or Map) 系统::用户信息表
     * @return 系统::用户信息表分页集合
     */
    @Override
    public MeeResult<SysUser> list(){
        final String user_id = ShiroUtils.getUserId();
        if( null==user_id ){
            throw new RuntimeException("用户不存在，请重新登录!");
        }
        Map<String,String> param = new HashMap<>(2,1);
        param.put("id",user_id);
        // 用户信息
        SysUser sysUser = dbSQLDao.findOne("com.mee.xml.SysUser.findById",param);
        // 角色信息 todo ...

        return ResultBuild.build(sysUser);
    }


    /**
     * 更新用户基本信息
     * @param sysUser 用户信息
     * @return 更新结果
     */
    @Override
    public MeeResult<Integer> updateUserInfo(SysUser sysUser) {
        if( null==sysUser.getId() ){
            return ResultBuild.fail("必要参数不可为空[id]");
        }
        int update_count = dbSQLDao.update("com.mee.xml.SysUser.updateInfoBySelf", sysUser);
        LOG.info("用户自行修改资料{}=>{}条",sysUser,update_count);

        // 更改密码后退出登陆
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        return ResultBuild.build(update_count);
    }

    @Override
    public MeeResult<Integer> updateUserPwd(SysUserDTO sysUserDTO) {
        // 验证并解密
        if( null==sysUserDTO.getOld_pwd() || null==sysUserDTO.getPwd1() || null==sysUserDTO.getPwd2() ){
            return ResultBuild.fail("必要参数为空[原密码，新密码，确认密码]");
        }
        final String dec_old_pwd = ChaosUtil.dec(sysUserDTO.getOld_pwd());
        final String dec_pwd1 = ChaosUtil.dec(sysUserDTO.getPwd1());
        final String dec_pwd2 = ChaosUtil.dec(sysUserDTO.getPwd2());
        if( null==dec_old_pwd || null==dec_pwd1 || null==dec_pwd2 ){
            return ResultBuild.fail("密码异常，请检查[原密码，新密码，确认密码]");
        }
        if( "".equals(dec_pwd1) || !dec_pwd1.equals(dec_pwd2) ){
            return ResultBuild.fail("新密码不一致，请检查[原密码，新密码，确认密码]");
        }

        // 验证原密码
        final String current_user_id = ShiroUtils.getUserId();
        Map<String,Object> param = new HashMap<>(2,1);
        param.put("id",current_user_id);
        SysUser sys_user2 = dbSQLDao.findOne("com.mee.xml.SysUser.findById", param);
        final String dec_pwd = new Sha512Hash(dec_old_pwd,current_user_id,3).toString();
        if( null==sys_user2 || null==dec_pwd || !dec_pwd.equals(sys_user2.getPassword()) ){
            return ResultBuild.fail("老密码不正确！");
        }

        // 更新密码
        final String password = new Sha512Hash(dec_pwd1,current_user_id,3).toString();
        final LocalDateTime now = DateUtil.now();
        param.clear();
        param.put("id",current_user_id);
        param.put("password",password);
        param.put("pwd_reset_time",now);
        param.put("update_time",now);
        param.put("update_by",current_user_id);
        int update_count = dbSQLDao.update("com.mee.xml.SysUser.updatePwd",param);

        // 更改密码后退出登陆
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        return ResultBuild.build(update_count);
    }
}
