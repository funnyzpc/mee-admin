package com.mee.sys.web;

import com.mee.common.util.MeeResult;
import com.mee.core.model.Page;
import com.mee.sys.entity.SysRole;
import com.mee.sys.service.impl.SysRoleServiceImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 系统::角色表web接口(SysRole2Controller)
 *
 * @author  shadow
 * @version v1.3
 * @date    2023-05-28 16:45:31
 */
@Controller
@RequestMapping("/sys/sys_role")
public class SysRoleController {

    /**
    * 业务处理类
    */
    @Autowired
    private SysRoleServiceImpl sysRoleService;

    /**
     * 页面
     * @return 页面
     */
    @RequiresPermissions("sys:sys_role:list")
    @GetMapping
    public String index(){
        return "sys/sys_role";
    }

    /**
     * 查询 系统::角色表 列表
     */
    @RequiresPermissions("sys:sys_role:list")
    @GetMapping("/list")
    @ResponseBody
    public MeeResult<Page<SysRole>> list(
            @RequestParam(defaultValue="1")Integer page_no,
            @RequestParam(defaultValue="10")Integer page_size,
            String name, String description, Integer status
    ){
        return sysRoleService.list(page_no,page_size,name,description,status);
    }

    /**
     * 系统::角色表::详细信息
     */
    @RequiresPermissions("sys:sys_role:list")
    @GetMapping("/id")
    @ResponseBody
    public MeeResult<SysRole> findById(String id){
        return sysRoleService.findById( id );
    }

    /**
     * 系统::角色表::新增
     */
    @RequiresPermissions("sys:sys_role:add")
    @PostMapping("add")
    @ResponseBody
    public MeeResult add(@RequestBody SysRole sysRole2){
        return sysRoleService.add( sysRole2 );
    }

    /**
     * 系统::角色表::修改
     */
    @RequiresPermissions("sys:sys_role:update")
    @PutMapping("update")
    @ResponseBody
    public MeeResult update(@RequestBody SysRole sysRole2 ){
        return sysRoleService.update( sysRole2 );
    }

    /**
     * 系统::角色表::删除
     */
    @RequiresPermissions("sys:sys_role:delete")
    @DeleteMapping("/delete")
    @ResponseBody
    public MeeResult deleteById(String id){
        return sysRoleService.deleteById(id);
    }

//    /**
//     * 系统::角色表::批量删除
//     */
//    @RequiresPermissions("sys:sys_role:delete")
//    @DeleteMapping("/deleteBatch")
//    @ResponseBody
//    public Map deleteBatch(String[] ids){
//        return sysRole2Service.deleteBatch(ids);
//    }

}
