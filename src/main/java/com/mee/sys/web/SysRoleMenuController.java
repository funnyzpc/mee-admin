package com.mee.sys.web;

import com.mee.common.util.MeeResult;
import com.mee.core.model.Page;
import com.mee.sys.entity.SysRoleMenu;
import com.mee.sys.service.impl.SysRoleMenuServiceImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 角色菜单关联web接口(SysRoleMenu2Controller)
 *
 * @author  shadow
 * @version v1.3
 * @date    2023-05-28 16:45:29
 */
@Controller
@RequestMapping("/sys/sys_role_menu")
public class SysRoleMenuController {

    /**
    * 业务处理类
    */
    @Autowired
    private SysRoleMenuServiceImpl sysRoleMenuService;

    /**
     * 页面
     * @return 页面
     */
    @RequiresPermissions("sys:sys_role_menu:list")
    @GetMapping
    public String index(){
        return "sys/sys_role_menu";
    }

    /**
     * 查询 角色菜单关联 列表
     */
    @RequiresPermissions("sys:sys_role_menu:list")
    @GetMapping("/list")
    @ResponseBody
    public MeeResult<Page<SysRoleMenu>> list(
            @RequestParam(defaultValue="1",required = true)Integer page_no,
            @RequestParam(defaultValue="10",required = true) Integer page_size,
            String menu_id, String role_id
    ){
        return sysRoleMenuService.list(page_no,page_size,menu_id,role_id
        );
    }

    /**
     * 查询 角色菜单关联 列表
     */
    @RequiresPermissions("sys:sys_role_menu:list")
    @GetMapping("/listByRoleId")
    @ResponseBody
    public MeeResult<List<SysRoleMenu>> listByRoleId(@RequestParam(required = true) String role_id  ){
        return sysRoleMenuService.listByRoleId( role_id );
    }

    /**
     * 角色菜单关联::详细信息
     */
    @RequiresPermissions("sys:sys_role_menu:list")
    @GetMapping("/id")
    @ResponseBody
    public MeeResult<SysRoleMenu> findById(String id){
        return sysRoleMenuService.findById( id );
    }

    /**
     * 角色菜单关联::新增
     */
    @RequiresPermissions("sys:sys_role_menu:add")
    @PostMapping("add")
    @ResponseBody
    public MeeResult add(@RequestBody Map<String,Object> sysRoleMenu2){
        return sysRoleMenuService.add( sysRoleMenu2 );
    }

//    /**
//     * 角色菜单关联::修改
//     */
//    @RequiresPermissions("sys:sys_role_menu:update")
//    @PutMapping("update")
//    @ResponseBody
//    public Map update(@RequestBody SysRoleMenu2 sysRoleMenu2 ){
//        return sysRoleMenu2Service.update( sysRoleMenu2 );
//    }

    /**
     * 角色菜单关联::删除
     */
    @RequiresPermissions("sys:sys_role_menu:delete")
    @DeleteMapping("/delete")
    @ResponseBody
    public MeeResult deleteById(String id){
        return sysRoleMenuService.deleteById(id);
    }

    /**
     * 角色菜单关联::批量删除
     */
    @RequiresPermissions("sys:sys_role_menu:delete")
    @DeleteMapping("/deleteBatch")
    @ResponseBody
    public MeeResult deleteBatch(String[] ids){
        return sysRoleMenuService.deleteBatch(ids);
    }

}
