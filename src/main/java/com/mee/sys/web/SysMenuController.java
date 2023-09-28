package com.mee.sys.web;

import com.mee.common.util.MeeResult;
import com.mee.sys.entity.SysMenu;
import com.mee.sys.service.SysMenuService;
import com.mee.sys.vo.SysMenuTreeVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统::新菜单表web接口(SysMenuController)
 *
 * @author  shadow
 * @version v1.3
 * @date    2023-05-05 21:48:15
 */
@Controller
@RequestMapping("/sys/sys_menu")
public class SysMenuController {

    /**
    * 业务处理类
    */
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 页面
     * @return 页面
     */
    @RequiresPermissions("sys:sys_menu:list")
    @GetMapping
    public String index(){
        return "sys/sys_menu";
    }

    /** 所有菜单信息 **/
    @RequiresPermissions("sys:sys_menu:list")
    @GetMapping("/list")
    @ResponseBody
    public MeeResult<List<SysMenuTreeVO>> list(String title){
        return sysMenuService.menuAll(title);
    }

    /**
     * 系统::新菜单表::详细信息
     */
    @RequiresPermissions("sys:sys_menu:list")
    @GetMapping("/id")
    @ResponseBody
    public MeeResult<SysMenu> findById(@RequestParam(required = true) String id){
        return sysMenuService.findById( id );
    }

    /**
     * 系统::新菜单表::新增
     */
    @RequiresPermissions("sys:sys_menu:add")
    @PostMapping("add")
    @ResponseBody
    public MeeResult<Void> add(@RequestBody(required = true) SysMenu sysMenu){
        return sysMenuService.add( sysMenu );
    }

    /**
     * 系统::新菜单表::修改
     */
    @RequiresPermissions("sys:sys_menu:update")
    @PutMapping("update")
    @ResponseBody
    public MeeResult<Integer> update(@RequestBody(required = true) SysMenu sysMenu ){
        return sysMenuService.update( sysMenu );
    }

    /**
     * 系统::新菜单表::删除
     */
    @RequiresPermissions("sys:sys_menu:delete")
    @DeleteMapping("/delete")
    @ResponseBody
    public MeeResult<Integer> deleteById(@RequestParam(required = true) String id){
        return sysMenuService.deleteById(id);
    }

//    /**
//     * 系统::新菜单表::批量删除
//     */
//    @RequiresPermissions("sys:sys_menu:delete")
//    @DeleteMapping("/deleteBatch")
//    @ResponseBody
//    public Map deleteBatch(String[] ids){
//        return sysMenu2Service.deleteBatch(ids);
//    }

}
