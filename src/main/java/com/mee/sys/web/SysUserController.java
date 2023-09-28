package com.mee.sys.web;


import com.mee.common.service.impl.ShiroAccountLockedServiceImpl;
import com.mee.common.util.MeeResult;
import com.mee.common.util.ResultBuild;
import com.mee.core.model.Page;
import com.mee.sys.dto.SysUserDTO;
import com.mee.sys.entity.SysRole;
import com.mee.sys.entity.SysUser;
import com.mee.sys.service.SysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 系统::用户信息表web接口(SysUser2Controller)
 *
 * @author  shadow
 * @version v1.3
 * @date    2023-05-30 20:59:40
 */
@Controller
@RequestMapping("/sys/sys_user")
public class SysUserController {

    /**
    * 业务处理类
    */
    @Autowired
    private SysUserService sysUserService;
    /**
     * 登录用户锁定业务
     */
    @Autowired
    private ShiroAccountLockedServiceImpl shiroAccountLockedService;

    /**
     * 页面
     * @return 页面
     */
    @RequiresPermissions("sys:sys_user:list")
    @GetMapping
    public String index(){
        return "sys/sys_user";
    }

    /**
     * 查询 系统::用户信息表 列表
     */
    @RequiresPermissions("sys:sys_user:list")
    @GetMapping("/list")
    @ResponseBody
    public MeeResult<Page<SysUser>> list(
            @RequestParam(required = true)Integer page_no,
            @RequestParam(required = true)Integer page_size,
            String user_name,String nick_name,String phone,
            String email,String status,String del_flag
    ){
        return sysUserService.list(page_no,page_size,user_name,nick_name,phone,email,status,del_flag);
    }

    /**
     * 系统::用户信息表::详细信息
     */
    @RequiresPermissions("sys:sys_user:list")
    @GetMapping("/id")
    @ResponseBody
    public MeeResult<SysUser> findById(@RequestParam(required = true) String id){
        return sysUserService.findById( id );
    }

    /**
     * 系统::用户信息表::新增
     */
    @RequiresPermissions("sys:sys_user:add")
    @PostMapping("add")
    @ResponseBody
    public MeeResult<Void> add(@RequestBody(required = true) SysUserDTO sysUser2DTO){
        return sysUserService.add( sysUser2DTO );
    }

    /**
     * 系统::用户信息表::修改
     */
    @RequiresPermissions("sys:sys_user:update")
    @PutMapping("update")
    @ResponseBody
    public MeeResult<Integer> update(@RequestBody(required = true) SysUser sysUser ){
        return sysUserService.update( sysUser );
    }

    /**
     * 系统::用户信息表::删除
     */
    @RequiresPermissions("sys:sys_user:delete")
    @DeleteMapping("/delete")
    @ResponseBody
    public MeeResult<Integer> deleteById(@RequestParam(required = true) String id){
        return sysUserService.deleteById(id);
    }

//    /**
//     * 系统::用户信息表::批量删除
//     */
//    //@RequiresPermissions("sys:sys_user:delete")
//    @DeleteMapping("/deleteBatch")
//    @ResponseBody
//    public Map deleteBatch(String[] ids){
//        return sysUser2Service.deleteBatch(ids);
//    }


    /** 状态切换 **/
    @RequiresPermissions("sys:sys_user:change_status")
    @PutMapping("/change_status")
    @ResponseBody
    public MeeResult<Void> changeStatus(@RequestBody(required = true) SysUser user){
        return sysUserService.changeStatus(user);
    }

    /** 解除锁定 **/
    @RequiresPermissions("sys:sys_user:unlock")
    @PutMapping("/unlock")
    @ResponseBody
    public MeeResult<Void> unlock(@RequestParam(value = "user_name",required = true) String user_name){
        shiroAccountLockedService.clearCounter(user_name);
        return ResultBuild.SUCCESS();
    }

    /** 重置密码 **/
    @RequiresPermissions("sys:sys_user:reset_pwd")
    @PutMapping("reset_pwd")
    @ResponseBody
    public MeeResult<Integer> resetPwd(@RequestParam(value = "id",required = true) String id,
                                       @RequestParam(value = "pwd",required = true) String pwd){
        return sysUserService.resetPwd(id,pwd);
    }

    /**
     * 获取角色
     * @return 用户角色信息
     */
    @RequiresPermissions("sys:sys_user:get_roles")
    @GetMapping("getRoles")
    @ResponseBody
    public MeeResult<List<SysRole>> getRoles(@RequestParam(value = "id",required = true) String user_id ){
        return sysUserService.getRoles(user_id);
    }

//    /**
//     * 导入用户信息
//     * @param file 文件
//     * @param name 名称
//     * @return
//     */
//    @RequiresPermissions("dev")
//    @PostMapping("import")
//    @ResponseBody
//    public MeeResult<Integer> doImport(@RequestParam(value = "file",required = true) MultipartFile file,
//                        @RequestParam(value = "name",required = false) String name){
//        return sysUserService.doImport(file,name);
//    }

    /**
     * 导出文件信息
     * @param response  response
     * @param page_no   page_no
     * @param page_size page_size
     * @param user_name user_name
     * @param nick_name nick_name
     * @param phone phone
     * @param email email
     * @param status    status
     * @param del_flag  del_flag
     */
    @RequiresPermissions("sys:sys_user:export")
    @GetMapping("export")
    public void doExport(HttpServletResponse response,
                         @RequestParam(required = true)Integer page_no,
                         @RequestParam(required = true)Integer page_size,
                         String user_name,String nick_name,String phone,
                         String email,String status,String del_flag){
        sysUserService.doExport( response,page_no,page_size,user_name,nick_name,phone,email,status,del_flag );
    }


}
