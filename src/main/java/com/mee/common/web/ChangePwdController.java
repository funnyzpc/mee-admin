package com.mee.common.web;


import com.mee.common.util.MD5Util;
import com.mee.common.util.MeeResult;
import com.mee.common.util.ResultBuild;
import com.mee.core.configuration.ShiroUtils;
import com.mee.core.dao.DBSQLDao;
import com.mee.sys.entity.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Deprecated
//@Controller
//@RequestMapping("/changePwd")
public class ChangePwdController {
    private static final Logger log = LoggerFactory.getLogger(ChangePwdController.class);

    @Autowired
    private DBSQLDao dbsqlDao;

    @GetMapping
    public String index(){
        return "changePwd";
    }

    /**
     * @param pwd_old 原密码
     * @param pwd1 新密码
     * @param pwd2 确认新密码
     * @return
     */
    @PostMapping
    @ResponseBody
    public MeeResult changePwd(String pwd_old, String pwd1, String pwd2){
        if(StringUtils.isEmpty(pwd_old) || StringUtils.isEmpty(pwd1) || StringUtils.isEmpty(pwd2)){
            return ResultBuild.fail("密码输入均不可为空!");
        }
        //  判断密码
        if (!pwd1.equals(pwd2)){
            return ResultBuild.fail("新密码不匹配,请确认后输入");
        }
        SysUser user = ShiroUtils.getUser();
        if(null == user){
            return ResultBuild.fail("当前登录用户不存在");
        }
        if(!MD5Util.encode(pwd_old).equals(user.getPassword())){
            return ResultBuild.fail("原密码错误,请检查");
        }

        // 修改密码及重置有效期(TODO)
        SysUser updateUser = new SysUser();
        updateUser.setPassword(MD5Util.encode(pwd1));
        updateUser.setId(user.getId());
        int updateCount = dbsqlDao.update("com.mee.xml.SysUser.update",updateUser);
        log.info("用户{}登录密码已修改,结果:{}",user.getUser_name(),updateCount);
        ShiroUtils.logout();
        return ResultBuild.SUCCESS();
    }

}
