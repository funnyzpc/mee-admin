package com.mee.sys.web;

import com.mee.common.util.MeeResult;
import com.mee.core.model.Page;
import com.mee.sys.entity.SysFile;
import com.mee.sys.service.impl.SysFileServiceImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
* 上传文件管理
* @className    SysFileController
* @author       shadow
* @date         2023/7/3 14:18
* @version      1.0
*/
@Controller
@RequestMapping("/sys/sys_file")
public class SysFileController  {

    @Autowired
    private SysFileServiceImpl sysFileService;

    /**
     * 页面
     * @return
     */
    @RequiresPermissions("sys:sys_file:list")
    @GetMapping
    public String index(){
        return "sys/sys_file";
    }

    /**
     * 分页查询
     * @param original_name original_name
     * @param name  name
     * @param dts   dts
     * @param dte   dte
     * @param page_no   page_no
     * @param page_size page_size
     * @return 分页数据
     */
    @RequiresPermissions("sys:sys_file:list")
    @GetMapping("list")
    @ResponseBody
    public MeeResult<Page<SysFile>> list(String original_name, String name, String dts, String dte, int page_no, int page_size){
        return sysFileService.list(original_name, name, dts, dte, page_no, page_size);
    }

    /**
     * 系统::文件管理::删除
     */
    @RequiresPermissions("sys:sys_file:delete")
    @DeleteMapping("/delete")
    @ResponseBody
    public MeeResult<Integer> deleteById(@RequestParam(required = true) String id){
        return sysFileService.deleteById(id);
    }


}
