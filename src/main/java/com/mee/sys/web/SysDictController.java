package com.mee.sys.web;

import com.mee.common.util.MeeResult;
import com.mee.core.model.Page;
import com.mee.sys.entity.SysDict;
import com.mee.sys.service.SysDictService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * 数据字典web接口(SysDict2Controller)
 *
 * @author  shadow
 * @version v1.0
 * @date    2023-05-15 10:27:36
 */
@Controller
@RequestMapping("/sys/sys_dict")
public class SysDictController {

    /**
    * 业务处理类
    */
    @Autowired
    private SysDictService sysDictService;

    /**
     * 页面
     * @return page
     */
    @RequiresPermissions("sys:sys_dict:list")
    @GetMapping
    public String index(){
        return "sys/sys_dict";
    }

    /**
     * 查询 数据字典 列表
     */
    @RequiresPermissions("sys:sys_dict:list")
    @GetMapping("/list")
    @ResponseBody
    public MeeResult<Page<SysDict>> list(@RequestParam(defaultValue="1")Integer page_no, @RequestParam(defaultValue="10")Integer page_size, String name, String description){
        return sysDictService.list(page_no,page_size,name,description);
    }

    /**
     * 数据字典::详细信息
     */
    @RequiresPermissions("sys:sys_dict:list")
    @GetMapping("/id")
    @ResponseBody
    public MeeResult<SysDict> findById(@RequestParam(required = true) String id){
        return sysDictService.findById( id );
    }

    /**
     * 数据字典::新增
     */
    @RequiresPermissions("sys:sys_dict:add")
    @PostMapping("add")
    @ResponseBody
    public MeeResult<Integer> add(@RequestBody(required = true) SysDict sysDict){
        return sysDictService.add( sysDict );
    }

    /**
     * 数据字典::修改
     */
    @RequiresPermissions("sys:sys_dict:update")
    @PutMapping("update")
    @ResponseBody
    public MeeResult<Integer> update(@RequestBody(required = true) SysDict sysDict ){
        return sysDictService.update( sysDict );
    }

    /**
     * 数据字典::删除
     */
    @RequiresPermissions("sys:sys_dict:delete")
    @DeleteMapping("/delete")
    @ResponseBody
    public MeeResult<Integer> deleteById(@RequestParam(required = true) String id){
        return sysDictService.deleteById(id);
    }

//    /**
//     * 数据字典::批量删除
//     */
//    @RequiresPermissions("sys:sys_dict:delete")
//    @DeleteMapping("/deleteBatch")
//    @ResponseBody
//    public Map deleteBatch(String[] ids){
//        return sysDict2Service.deleteBatch(ids);
//    }

}
