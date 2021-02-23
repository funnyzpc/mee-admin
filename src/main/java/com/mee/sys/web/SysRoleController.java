package com.mee.sys.web;

import com.mee.common.util.ResultBuild;
import com.mee.core.dao.DBSQLDao;
import com.mee.sys.entity.SysRole;
import com.mee.sys.entity.SysRoleMenu;
import com.mee.sys.entity.SysRoleUser;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author funnyzpc
 * @description 角色管理
 */
@Controller
@RequestMapping("/sys/role")
public class SysRoleController {
    private static final Logger log = LoggerFactory.getLogger(SysRoleController.class);

    @Autowired
    private DBSQLDao dbSQLDao;

    @RequiresPermissions("040102")
    @GetMapping
    public String index(){
        return "sys/role";
    }

    /**
     * 角色列表查询
     * @param role_name
     * @param role_desc
     * @param status
     * @return
     */
    @RequiresPermissions("040102")
    @PostMapping
    @ResponseBody
    public Map<String,Object> list(String role_name, String role_desc,Integer status/*, int pageIdx, int pageSize*/){
        Map<String,Object> params = new HashMap<String,Object>(4,1);
        if(null != role_name && !"".equals(role_name)){ params.put("role_name","%"+role_name.trim()+"%"); }
        if(null != role_desc && !"".equals(role_desc)){ params.put("role_desc",role_desc.trim()+"%"); }
        if(null != status){ params.put("status",status); }
        return new HashMap<String,Object>(1,1){{
            put("data", dbSQLDao.query("com.mee.xml.SysRole.findList",params/*,pageIdx,pageSize*/));
        }};
    }

    /** 所有菜单 **/
    @RequiresPermissions("040102")
    @PostMapping("/menus")
    @ResponseBody
    public Map<String,Object> menus(){
        return new HashMap<String,Object>(1,1){{
            put("data", dbSQLDao.query("com.mee.xml.SysMenu.findMenus"));
        }};
    }

    /** 角色已有菜单权限 **/
    @RequiresPermissions("040102")
    @PostMapping("/roleMenus")
    @ResponseBody
    public Map<String,Object> roleMenus(String id){
        if(StringUtils.isEmpty(id)){
            return new HashMap<>();
        }
        Map<String,Object> params = new HashMap<String,Object>(1,1){{
            put("role_id",id);
        }};
        return new HashMap<String,Object>(1,1){{
            put("data", dbSQLDao.query("com.mee.xml.SysRoleMenu.findList0",params));
        }};
    }

    /** 所有用户 **/
    @RequiresPermissions("040102")
    @PostMapping("/users")
    @ResponseBody
    public Map<String,Object> users(){
        return new HashMap<String,Object>(1,1){{
            put("users", dbSQLDao.query("com.mee.xml.SysUser.findUsers"));
        }};
    }

    /** 角色已有用户权限 **/
    @RequiresPermissions("040102")
    @PostMapping("/roleUsers")
    @ResponseBody
    public Map<String,Object> roleUsers(String role_id){
        if(StringUtils.isEmpty(role_id)){
            return new HashMap<>();
        }
        Map<String,Object> params = new HashMap<String,Object>(1,1){{
            put("role_id",role_id);
        }};
        return new HashMap<String,Object>(1,1){{
            put("roleUsers", dbSQLDao.query("com.mee.xml.SysRoleUser.findList",params));
        }};
    }

    /** 保存(用户关系、菜单关系) NEW **/
    @RequiresPermissions("040102")
    @PostMapping("/saveAll")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class,readOnly = false)
    public Map<String,Object> saveAll(SysRole role, String[] menus, String[] users/* String[] depts*/){
        // validate
        if(null == role){
            log.error("传入菜单和用户为空 menus:{},users:{}",menus,users);
            return ResultBuild.fail("传入参数为空 role");
        }

        /** 先删除 再 新增 **/
        if(!StringUtils.isEmpty(role.getId())){
            // this.delete(role.getId(),role.getRole_name());
            Map<String,Object> params = new HashMap<String,Object>(1,1){{
                put("role_id",role.getId());
            }};
            // 这里必须删除,否则无法更新保存
            int deleteRoleCount = dbSQLDao.delete("com.mee.xml.SysRole.delete",params);
            int deleteRoleUserCount = dbSQLDao.delete("com.mee.xml.SysRoleMenu.delete",params);
            int deleteRoleMenuCount = dbSQLDao.delete("com.mee.xml.SysRoleUser.delete",params);
            log.info("已删除 {} - {} 角色用户关系{}条,删除角色菜单关系{}条.",deleteRoleCount,role.getRole_name(),deleteRoleUserCount,deleteRoleMenuCount);
        }

        // 默认开启状态
        if(null== role.getStatus()){ role.setStatus(1); }

        // 创建角色
        //if(StringUtils.isEmpty(role.getId())) {
        role.setId(null);
        dbSQLDao.create("com.mee.xml.SysRole.insert", role);
        //}
        // 新增角色用户关系
        if(null!=users && users.length>0) {
            for (String userId : users) {
                SysRoleUser roleUser = new SysRoleUser();
                roleUser.setRole_id(role.getId());
                roleUser.setUser_id(userId);
                dbSQLDao.create("com.mee.xml.SysRoleUser.insert", roleUser);
            }
        }
        // 新增角色菜单关系
        if(null!=menus && menus.length>0) {
            for (String menuCode : menus) {
                SysRoleMenu roleMenu = new SysRoleMenu();
                roleMenu.setRole_id(role.getId());
                roleMenu.setMenu_code(menuCode);
                dbSQLDao.create("com.mee.xml.SysRoleMenu.insert", roleMenu);
            }
        }
        return ResultBuild.SUCCESS;
    }


    /** 移除角色 **/
    @RequiresPermissions("040102")
    @PostMapping("/delete")
    @ResponseBody
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public Map<String,Object> delete(String role_id,String role_name){
        if(StringUtils.isEmpty(role_id) || StringUtils.isEmpty(role_name)){
            return ResultBuild.fail("失败参数role_id or role_name为空！");
        }
        // 超管无法删除
        if("202009141147480080100000".equals(role_id) || "ROLE_admin".toUpperCase().equals(role_name.toUpperCase())){
            return ResultBuild.fail("超管无法删除");
        }
        Map<String,Object> params = new HashMap<String,Object>(1,1){{
            put("role_id",role_id);
        }};
        int deleteRoleCount = dbSQLDao.delete("com.mee.xml.SysRole.delete",params);
        int deleteRoleUserCount = dbSQLDao.delete("com.mee.xml.SysRoleMenu.delete",params);
        int deleteRoleMenuCount = dbSQLDao.delete("com.mee.xml.SysRoleUser.delete",params);
        log.info("already remove RoleCount:{},RoleUserCount:{},deleteRoleMenuCount:{}",deleteRoleCount,deleteRoleUserCount,deleteRoleMenuCount);
        return ResultBuild.SUCCESS;
    }
}
