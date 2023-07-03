package com.mee.sys.web;

import com.mee.common.util.MeeResult;
import com.mee.core.model.Page;
import com.mee.sys.dto.SysRoleUser2DTO;
import com.mee.sys.entity.SysRoleUser;
import com.mee.sys.entity.SysUser;
import com.mee.sys.service.impl.SysRoleUserServiceImpl;
import com.mee.sys.vo.SysRoleUser2VO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * 系统::角色用户表web接口(SysRoleUser2Controller)
 *
 * @author  shadow
 * @version v1.3
 * @date    2023-05-28 16:45:32
 */
@Controller
@RequestMapping("/sys/sys_role_user")
public class SysRoleUserController {

    /**
    * 业务处理类
    */
    @Autowired
    private SysRoleUserServiceImpl sysRoleUserService;

    /**
     * 页面
     * @return 页面
     */
    @RequiresPermissions("sys:sys_role_user:list")
    @GetMapping
    public String index(){
        return "sys/sys_role_user";
    }

    /**
     * 查询 系统::角色用户表 列表
     */
    @RequiresPermissions("sys:sys_role_user:list")
    @GetMapping("/list")
    @ResponseBody
    public MeeResult<Page<SysRoleUser2VO>> list(
            @RequestParam(defaultValue="1")Integer page_no,
            @RequestParam(defaultValue="10")Integer page_size,
            String user_name,
            String nick_name,
            String user_id,
            String role_id
    ){
        return sysRoleUserService.list(page_no,page_size,user_name,nick_name,user_id,role_id);
    }

    /**
     * 根据角色查询角色用户
     * @param page_no
     * @param page_size
     * @param user_name
     * @param phone
     * @param role_id
     * @return
     */
    @RequiresPermissions("sys:sys_role_user:list")
    @GetMapping("/list_user")
    @ResponseBody
    public MeeResult<Page<SysUser>> listUser(
            @RequestParam(defaultValue="1")Integer page_no,
            @RequestParam(defaultValue="10")Integer page_size,
            String user_name,
            String phone,
            @RequestParam(required = true) String role_id
    ){
        return sysRoleUserService.listUser(page_no,page_size,user_name,phone,role_id);
    }

    /**
     * 系统::角色用户表::详细信息
     */
    @RequiresPermissions("sys:sys_role_user:list")
    @GetMapping("/id")
    @ResponseBody
    public MeeResult<SysRoleUser> findById(String id){
        return sysRoleUserService.findById( id );
    }

    /**
     * 系统::角色用户表::新增
     */
    @RequiresPermissions("sys:sys_role_user:add")
    @PostMapping("add")
    @ResponseBody
    public MeeResult add(@RequestBody(required = true) SysRoleUser2DTO sysRoleUser2DTO){
        return sysRoleUserService.add( sysRoleUser2DTO );
    }

//    /**
//     * 系统::角色用户表::修改
//     */
//    @RequiresPermissions("sys:sys_role_user:edit")
//    @PutMapping
//    @ResponseBody
//    public Map edit(@RequestBody SysRoleUser2 sysRoleUser2 ){
//        return sysRoleUser2Service.update( sysRoleUser2 );
//    }

    /**
     * 系统::角色用户表::删除
     */
    @RequiresPermissions("sys:sys_role_user:delete")
    @DeleteMapping("/delete")
    @ResponseBody
    public MeeResult deleteById(String id){
        return sysRoleUserService.deleteById(id);
    }

//    /**
//     * 系统::角色用户表::批量删除
//     */
//    @RequiresPermissions("sys:sys_role_user:delete")
//    @DeleteMapping("/deleteBatch")
//    @ResponseBody
//    public Map deleteBatch(String[] ids){
//        return sysRoleUser2Service.deleteBatch(ids);
//    }

}
