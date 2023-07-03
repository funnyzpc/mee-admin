package com.mee.sys.web;

import com.mee.common.util.MeeResult;
import com.mee.sys.entity.SysMenu;
import com.mee.sys.service.impl.SysMenuServiceImpl;
import com.mee.sys.vo.SysMenu2TreeVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 系统::新菜单表web接口(SysMenu2Controller)
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
    private SysMenuServiceImpl sysMenu2Service;

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
    public MeeResult<List<SysMenu2TreeVO>> list(String title){
        return sysMenu2Service.menuAll(title);
    }

    /**
     * 系统::新菜单表::详细信息
     */
    @RequiresPermissions("sys:sys_menu:list")
    @GetMapping("/id")
    @ResponseBody
    public MeeResult<SysMenu> findById(String id){
        return sysMenu2Service.findById( id );
    }

    /**
     * 系统::新菜单表::新增
     */
    @RequiresPermissions("sys:sys_menu:add")
    @PostMapping("add")
    @ResponseBody
    public MeeResult add(@RequestBody SysMenu sysMenu2){
        return sysMenu2Service.add( sysMenu2 );
    }

    /**
     * 系统::新菜单表::修改
     */
    @RequiresPermissions("sys:sys_menu:update")
    @PutMapping("update")
    @ResponseBody
    public MeeResult update(@RequestBody SysMenu sysMenu2 ){
        return sysMenu2Service.update( sysMenu2 );
    }

    /**
     * 系统::新菜单表::删除 TODO ...
     */
    @RequiresPermissions("sys:sys_menu:delete")
    @DeleteMapping("/delete")
    @ResponseBody
    public MeeResult deleteById(String id){
        return sysMenu2Service.deleteById(id);
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
