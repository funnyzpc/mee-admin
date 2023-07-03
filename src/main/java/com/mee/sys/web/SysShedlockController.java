package com.mee.sys.web;

import com.mee.common.util.MeeResult;
import com.mee.core.model.Page;
import com.mee.sys.entity.SysShedlock;
import com.mee.sys.service.impl.SysShedlockServiceImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;


/**
*   分佈式&集群 定時任務管理
*   @className  SysShedlockController
*   @author     shadow
*   @date       2023/6/18 10:41 PM
*   @version    v1.0
*/
@Controller
@RequestMapping("/sys/sys_shedlock")
public class SysShedlockController {

    @Autowired
    private SysShedlockServiceImpl sysShedlockService;

    /**
     * 页面
     * @return
     */
    @RequiresPermissions("sys:sys_shedlock:list")
    @GetMapping
    public String index(){
        return "sys/sys_shedlock";
    }

    /**
     * 分页查询
     * @param name
     * @param label
     * @param locked_at_start
     * @param locked_at_end
     * @param page_no
     * @param page_size
     * @return
     */
    @RequiresPermissions("sys:sys_shedlock:list")
    @GetMapping("list")
    @ResponseBody
    public MeeResult<Page<SysShedlock>> list(String name, String label,
                                             @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss") LocalDateTime locked_at_start,
                                             @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")LocalDateTime locked_at_end,
                                             @RequestParam(required = true) int page_no, @RequestParam(required = true) int page_size){
        return sysShedlockService.list(name,label,locked_at_start,locked_at_end,page_no,page_size);
    }

    /**
     * 更新
     * @param sysShedlock
     * @return
     */
    @RequiresPermissions("sys:sys_shedlock:update")
    @PutMapping("update")
    @ResponseBody
    public MeeResult update(@RequestBody(required = true) SysShedlock sysShedlock){
       return sysShedlockService.update(sysShedlock);
    }

    /**
     * 删除
     * @param name
     * @return
     */
    @RequiresPermissions("sys:sys_shedlock:delete")
    @DeleteMapping("delete")
    @ResponseBody
    public MeeResult delete(@RequestParam(required = true) String name){
        return sysShedlockService.delete(name);
    }
}