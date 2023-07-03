package com.mee.sys.web;

import com.mee.common.util.MeeResult;
import com.mee.core.model.Page;
import com.mee.sys.entity.SysLog;
import com.mee.sys.service.impl.SysLogServiceImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


/**
*   日志管理
*   @className  SysLogController
*   @author     shadow
*   @date       2023/6/18 10:38 PM
*   @version    v1.0
*/
@Controller
@RequestMapping("/sys/sys_log")
public class SysLogController {

    @Autowired
    private SysLogServiceImpl sysLogService;

    /**
     * 页面
     * @return 页面
     */
    @RequiresPermissions("sys:sys_log:list")
    @GetMapping
    public String index(){
        return "sys/sys_log";
    }

    /**
     * 分页查询
     * @param log_type
     * @param log_title
     * @param log_date
     * @param page_no
     * @param page_size
     * @return .
     */
    @RequiresPermissions("sys:sys_log:list")
    @GetMapping("list")
    @ResponseBody
    public MeeResult<Page<SysLog>> list(String log_type, String log_title, String log_date, int page_no, int page_size){
        return sysLogService.list(log_type,log_title,log_date,page_no,page_size);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @RequiresPermissions("sys:sys_log:delete")
    @DeleteMapping("delete")
    @ResponseBody
    public MeeResult delete(@RequestParam(required = true) String id){
        return sysLogService.delete(id);
    }

    /**
     * 导出
     * @param response 响应对象
     * @param page_no   页
     * @param page_size 分页大小
     */
    @RequiresPermissions("sys:sys_log:export")
    @GetMapping("/export")
    public void export(HttpServletResponse response,
                       @RequestParam(required = true) Integer page_no,
                       @RequestParam(required = true) Integer page_size){
        sysLogService.export(response,page_no,page_size);
    }
}