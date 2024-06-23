package com.mee.sys.web;

import com.mee.common.util.MeeResult;
import com.mee.core.model.Page;
import com.mee.sys.entity.SysShedlockApp;
import com.mee.sys.service.SysShedlockAppService;
import jakarta.validation.Valid;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;



/**
 * 集群分佈式鎖-应用配置web接口(SysShedlockAppController)
 *
 * @author  ash
 * @version v1.1
 * @date    2024-06-18 16:31:23
 */
@Controller
@RequestMapping("/sys/sys_shedlock_app")
public class SysShedlockAppController{

    /**
    * 业务处理类
    */
    @Autowired
    private SysShedlockAppService sysShedlockAppService;

    /**
     * 集群分佈式鎖-应用配置:页面
     * @return 页面
     */
    @RequiresPermissions("sys:sys_shedlock:list")
    @GetMapping
    public String index(){
        return "sys/sys_shedlock_app";
    }

    /**
     *  集群分佈式鎖-应用配置:列表
     * @param page_no    分页
     * @param page_size  分页大小
     * @param application 当前实例应用
     * @param host_ip 当前实例应用所属IP
     * @param host_name 创建机器
     * @param state 状态 0.关闭 1.开启
     * @return 分頁數據
     */
    @RequiresPermissions("sys:sys_shedlock:list")
    @GetMapping("/list")
    @ResponseBody
    public MeeResult<Page<SysShedlockApp>> list(
        @RequestParam(defaultValue="1")Integer page_no, @RequestParam(defaultValue="10")Integer page_size,String application,String host_ip,String host_name,String state
    ){
        return sysShedlockAppService.list(page_no,page_size,application,host_ip,host_name,state );
    }

    /**
     * 集群分佈式鎖-应用配置:详细信息
     * @param application 主鍵
     * @return 主鍵記錄
    */
    @RequiresPermissions("sys:sys_shedlock:list")
    @GetMapping("/application")
    @ResponseBody
    public MeeResult<SysShedlockApp> findByApplication(@RequestParam(required = true)String application){
        return sysShedlockAppService.findByApplication( application );
    }

    /**
     * 集群分佈式鎖-应用配置:新增
     * @param sysShedlockApp 集群分佈式鎖-应用配置:對象
     * @return 新增結果
    */
    @RequiresPermissions("sys:sys_shedlock:add")
    @PostMapping("add")
    @ResponseBody
    public MeeResult<Integer> add(@RequestBody(required = true) SysShedlockApp sysShedlockApp){
        return sysShedlockAppService.add( sysShedlockApp );
    }

    /**
     * 集群分佈式鎖-应用配置:修改
     * @param sysShedlockApp 集群分佈式鎖-应用配置:對象
     * @return 修改結果
     */
    @RequiresPermissions("sys:sys_shedlock:update")
    @PutMapping("update")
    @ResponseBody
    public MeeResult<Integer> update(@RequestBody(required = true) SysShedlockApp sysShedlockApp ){
        return sysShedlockAppService.update( sysShedlockApp );
    }

    /**
     * 集群分佈式鎖-应用配置:删除
     * @param application 主鍵
     * @return 刪除結果
     */
    @RequiresPermissions("sys:sys_shedlock:delete")
    @DeleteMapping("/delete")
    @ResponseBody
    public MeeResult<Integer> deleteByApplication(@RequestParam(required = true) String application,@RequestParam(required = true) String host_ip){
        return sysShedlockAppService.deleteByApplication(application,host_ip);
    }
    /**
     * 集群分佈式鎖-应用配置:修改状态
     * @param sysShedlockApp 集群分佈式鎖-应用配置:對象
     * @return 修改結果
     */
    @RequiresPermissions("sys:sys_shedlock:update")
    @PutMapping("app_state")
    @ResponseBody
    public MeeResult<Integer> appState(@RequestBody(required = true) @Valid SysShedlockApp sysShedlockApp ){
        return sysShedlockAppService.appState( sysShedlockApp.getApplication(),sysShedlockApp.getHost_ip(),sysShedlockApp.getState() );
    }
    /**
     *  集群分佈式鎖-应用配置:导出
     * @param response   响应体
     * @param page_no    分页
     * @param page_size  分页大小
     * @param application 当前实例应用
     * @param host_ip 当前实例应用所属IP
     * @param host_name 创建机器
     * @param state 状态 0.关闭 1.开启
     * @return 導出excel文件
     */
    @RequiresPermissions("sys:sys_shedlock:list")
    @GetMapping("export")
    public void doExport(HttpServletResponse response,
        @RequestParam(defaultValue="1")Integer page_no,
        @RequestParam(defaultValue="10")Integer page_size,
        String application,
        String host_ip,
        String host_name,
        String state
        )
    {
        sysShedlockAppService.doExport(response,page_no,page_size,application,host_ip,host_name,state );
    }


}
