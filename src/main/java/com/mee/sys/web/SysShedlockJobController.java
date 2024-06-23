package com.mee.sys.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.mee.common.util.MeeResult;
import com.mee.core.model.Page;
import com.mee.sys.entity.SysShedlockJob;
import com.mee.sys.service.SysShedlockJobService;
import jakarta.validation.Valid;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import java.time.LocalDateTime;


/**
 * 集群分佈式鎖-任务配置web接口(SysShedlockJobController)
 *
 * @author  ash
 * @version v1.1
 * @date    2024-06-18 16:31:16
 */
@Controller
@RequestMapping("/sys/sys_shedlock_job")
public class SysShedlockJobController{

    /**
    * 业务处理类
    */
    @Autowired
    private SysShedlockJobService sysShedlockJobService;

    /**
     * 集群分佈式鎖-任务配置:页面
     * @return 页面
     */
    @RequiresPermissions("sys:sys_shedlock:list")
    @GetMapping
    public String index(){
        return "sys/sys_shedlock_job";
    }

    /**
     *  集群分佈式鎖-任务配置:列表
     * @param page_no    分页
     * @param page_size  分页大小
     * @param application 当前实例应用
     * @param name 任務名稱(即ID)
     * @param host_ip 当前实例应用所属IP
     * @param locked_at 任務開始鎖定
     * @param state 0.close关闭 1.open开启(默认) 局部限制标志位(只限制所有相同实例中的此任务)
     * @param label 任務標識
     * @return 分頁數據
     */
    @RequiresPermissions("sys:sys_shedlock:list")
    @GetMapping("/list")
    @ResponseBody
    public MeeResult<Page<SysShedlockJob>> list(
        @RequestParam(defaultValue="1")Integer page_no,
        @RequestParam(defaultValue="10")Integer page_size,
        String application,String name,String host_ip,
//        @JsonSerialize(using =LocalDateTimeSerializer.class)
//        @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss")
//        @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
//        LocalDateTime locked_at,
        String state,String label
    ){
        return sysShedlockJobService.list(page_no,page_size,application,name,host_ip,state,label );
    }

    /**
     * 集群分佈式鎖-任务配置:详细信息
     * @param application 主鍵
     * @return 主鍵記錄
    */
    @RequiresPermissions("sys:sys_shedlock:list")
    @GetMapping("/application")
    @ResponseBody
    public MeeResult<SysShedlockJob> findByApplication(@RequestParam(required = true)String application){
        return sysShedlockJobService.findByApplication( application );
    }

    /**
     * 集群分佈式鎖-任务配置:新增
     * @param sysShedlockJob 集群分佈式鎖-任务配置:對象
     * @return 新增結果
    */
    @RequiresPermissions("sys:sys_shedlock:add")
    @PostMapping("add")
    @ResponseBody
    public MeeResult<Integer> add(@RequestBody(required = true) SysShedlockJob sysShedlockJob){
        return sysShedlockJobService.add( sysShedlockJob );
    }

    /**
     * 集群分佈式鎖-任务配置:修改
     * @param sysShedlockJob 集群分佈式鎖-任务配置:對象
     * @return 修改結果
     */
    @RequiresPermissions("sys:sys_shedlock:update")
    @PutMapping("update")
    @ResponseBody
    public MeeResult<Integer> update(@RequestBody(required = true) SysShedlockJob sysShedlockJob ){
        return sysShedlockJobService.update( sysShedlockJob );
    }

    /**
     * 集群分佈式鎖-任务配置:删除
     * @param application 主鍵
     * @return 刪除結果
     */
    @RequiresPermissions("sys:sys_shedlock:delete")
    @DeleteMapping("/delete")
    @ResponseBody
    public MeeResult<Integer> deleteByApplication(@RequestParam(required = true) String application,@RequestParam(required = true) String name){
        return sysShedlockJobService.deleteByApplication(application,name);
    }

    /**
     * 集群分佈式鎖-应用配置:修改状态
     * @param sysShedlockJob 集群分佈式鎖-应用配置:對象
     * @return 修改結果
     */
    @RequiresPermissions("sys:sys_shedlock:update")
    @PutMapping("job_state")
    @ResponseBody
    public MeeResult<Integer> jobState(@RequestBody(required = true) @Valid SysShedlockJob sysShedlockJob ){
        return sysShedlockJobService.jobState( sysShedlockJob.getApplication(),sysShedlockJob.getName(),sysShedlockJob.getState() );
    }

    /**
     *  集群分佈式鎖-任务配置:导出
     * @param response   响应体
     * @param page_no    分页
     * @param page_size  分页大小
     * @param application 当前实例应用
     * @param name 任務名稱(即ID)
     * @param host_ip 当前实例应用所属IP
     * @param locked_at 任務開始鎖定
     * @param state 0.close关闭 1.open开启(默认) 局部限制标志位(只限制所有相同实例中的此任务)
     * @param label 任務標識
     * @return 導出excel文件
     */
    @RequiresPermissions("sys:sys_shedlock:list")
    @GetMapping("export")
    public void doExport(HttpServletResponse response,
        @RequestParam(defaultValue="1")Integer page_no, @RequestParam(defaultValue="10")Integer page_size,
        String application,
        String name,
        String host_ip,
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss")
        @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
        LocalDateTime locked_at,
        String state,
        String label
        )
    {
        sysShedlockJobService.doExport(response,page_no,page_size,application,name,host_ip,locked_at,state,label );
    }


}
