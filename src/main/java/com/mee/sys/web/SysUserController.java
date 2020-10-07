package com.mee.sys.web;

import com.mee.common.service.SeqGenServiceImpl;
import com.mee.common.util.MD5Util;
import com.mee.common.util.ResultBuild;
import com.mee.core.dao.DBSQLDao;
import com.mee.sys.entity.SysUser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/sys/user")
public class SysUserController {
    private static final Logger log = LoggerFactory.getLogger(SysMenuController.class);

    @Autowired
    private DBSQLDao dbsqlDao;
    @Autowired
    private SeqGenServiceImpl seqGenService;

    @RequiresPermissions("040101")
    @GetMapping
    public String index(){
        return "sys/user";
    }

    @RequiresPermissions("040101")
    @PostMapping
    @ResponseBody
    public Map<String,Object> list(String nick_name, String user_name,String email,Integer status, int pageIdx, int pageSize){
        Map<String,Object> params = new HashMap<String,Object>(4,1);
        if(null != nick_name && !"".equals(nick_name)){ params.put("nick_name","%"+nick_name.trim()+"%"); }
        if(null != user_name && !"".equals(user_name)){ params.put("user_name",user_name.trim()+"%"); }
        if(null != email && !"".equals(email)){ params.put("email",email.trim()+"%"); }
        if(null != status){ params.put("status",status); }
        return new HashMap<String,Object>(1,1){{
            put("data",dbsqlDao.list("com.mee.xml.SysUser.findList",params,pageIdx,pageSize));
        }};
    }

    @RequiresPermissions("040101")
    @RequestMapping("/save")
    @ResponseBody
    public Map save(SysUser sysUser){
        // 参数校验
        if(null == sysUser
                || null==sysUser.getUser_name()
                || null==sysUser.getEmail()
                || null == sysUser.getStatus()){
            return ResultBuild.FAIL;
        }
        if(null == sysUser.getId() || "".equals(sysUser.getId().trim())){
            sysUser.setUser_id(seqGenService.genShortPrimaryKey());
            sysUser.setRegister_date(LocalDateTime.now());
            sysUser.setPassword(MD5Util.encode(sysUser.getPassword()));
            String recordId = dbsqlDao.create("com.mee.xml.SysUser.insert",sysUser);
            log.info("创建sys_dict结果:{}",recordId);
            return ResultBuild.SUCCESS;
        }
        Map<String,Object> queryParam = new HashMap<String,Object>(1,1){{
            put("id",sysUser.getId());
        }};
        if(!StringUtils.isEmpty(sysUser.getPassword())) {
            SysUser oldUser = dbsqlDao.queryOne("com.kans.xml.SysUser.findList", queryParam);
            if (null == oldUser) {
                return ResultBuild.fail("用户不存在:" + sysUser.getUser_name());
            }
            if (!sysUser.getPassword().equals(oldUser.getPassword())) {
                sysUser.setPassword(MD5Util.encode(sysUser.getPassword()));
            }
        }
        // 更新数据
        int updateCount = dbsqlDao.update("com.mee.xml.SysUser.update",sysUser);
        log.info("更新sys_dict结果:{}",updateCount);
        return ResultBuild.SUCCESS;
    }

    @RequiresPermissions("040101")
    @RequestMapping("/disabled")
    @ResponseBody
    public Map delete(String id,Integer status){
        // 参数校验
        if(null == id || "".equals(id.trim()) || null == status){
            return ResultBuild.FAIL;
        }
        if("1".equals(id) || "2".equals(id)){
            return ResultBuild.fail("指定用户不可操作 [1.超级管理员、2.测试用户]");
        }

        // 更新数据
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setStatus(status);
        int updateCount = dbsqlDao.update("com.mee.xml.SysUser.update",sysUser);


        log.info("更新sys_dict结果:{},删除用户角色关系:{}",updateCount);
        return ResultBuild.SUCCESS;
    }

    /** 删除用户 **/
    @RequiresPermissions("040101")
    @RequestMapping("/delete")
    @ResponseBody
    public Map delete(String id){
        // 参数校验
        if(null == id || "".equals(id.trim())){
            return ResultBuild.FAIL;
        }
        if("1".equals(id) || "2".equals(id)){
            return ResultBuild.fail("指定用户不可删除 [1.超级管理员、2.测试用户]");
        }

        // query user info
        Map<String,Object> queryParam = new HashMap<String,Object>(1,1){{
            put("id",id);
        }};
        SysUser user = dbsqlDao.queryOne("com.mee.xml.SysUser.findList",queryParam);
        if(null == user){
            return ResultBuild.fail("用户信息不存在!");
        }
        // 删除用户角色关系
        Map<String,Object> params = new HashMap<String,Object>(1,1){{
            put("user_id",user.getUser_id());
        }};
        int deleteRoleUserCount = dbsqlDao.delete("com.mee.xml.SysRoleUser.delete",params);

        Map<String,Object> delParams = new HashMap<String,Object>(1,1){{
            put("id",id);
        }};
        // 更新数据
        int updateCount = dbsqlDao.update("com.mee.xml.SysUser.delete",delParams);
        log.info("删除sys_dict结果:{},id:{},deleteRoleUserCount:{}",updateCount,id,deleteRoleUserCount);
        return ResultBuild.SUCCESS;
    }

}
