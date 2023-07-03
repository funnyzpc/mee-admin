package com.mee.sys.web;

import com.mee.common.util.MeeResult;
import com.mee.core.model.Page;
import com.mee.sys.entity.SysDictDetail;
import com.mee.sys.service.impl.SysDictDetailServiceImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 数据字典详情web接口(SysDictDetail2Controller)
 *
 * @author  shadow
 * @version v1.0
 * @date    2023-05-15 10:27:31
 */
@Controller
@RequestMapping("/sys/sys_dict_detail")
public class SysDictDetailController {

    /**
    * 业务处理类
    */
    @Autowired
    private SysDictDetailServiceImpl sysDictDetail2Service;

    /**
     * 查询 数据字典详情 列表
     */
    @RequiresPermissions("sys:sys_dict:list")
    @GetMapping("/list")
    @ResponseBody
    public MeeResult<Page<SysDictDetail>> list(
            @RequestParam(defaultValue="1")Integer page_no,
            @RequestParam(defaultValue="10")Integer page_size,
            String dict_id, String label, String value
    ){
        return sysDictDetail2Service.list(page_no,page_size,dict_id,label,value);
    }

    /**
     * 数据字典详情::详细信息
     */
    @RequiresPermissions("sys:sys_dict:list")
    @GetMapping("/id")
    @ResponseBody
    public MeeResult<SysDictDetail> findById(String id){
        return sysDictDetail2Service.findById( id );
    }

    /**
     * 数据字典详情::新增
     */
    @RequiresPermissions("sys:sys_dict:add")
    @PostMapping("add")
    @ResponseBody
    public MeeResult add(@RequestBody SysDictDetail sysDictDetail2){
        return sysDictDetail2Service.add( sysDictDetail2 );
    }

    /**
     * 数据字典详情::修改
     */
    @RequiresPermissions("sys:sys_dict:update")
    @PutMapping("update")
    @ResponseBody
    public MeeResult update(@RequestBody SysDictDetail sysDictDetail2 ){
        return sysDictDetail2Service.update( sysDictDetail2 );
    }

    /**
     * 数据字典详情::删除
     */
    @RequiresPermissions("sys:sys_dict:delete")
    @DeleteMapping("/delete")
    @ResponseBody
    public MeeResult deleteById(String id){
        return sysDictDetail2Service.deleteById(id);
    }

    /**
     * 数据字典详情::批量删除
     */
    @RequiresPermissions("sys:sys_dict:delete")
    @DeleteMapping("/deleteBatch")
    @ResponseBody
    public MeeResult<Integer> deleteBatch(String[] ids){
        return sysDictDetail2Service.deleteBatch(ids);
    }

}
