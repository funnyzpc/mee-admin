package com.mee.sys.web;

import com.mee.common.util.MeeResult;
import com.mee.core.model.Page;
import com.mee.sys.service.QrtzJobService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.impl.QrtzJob;
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

import java.util.Map;


/**
 * 定时任务::任务配置表web接口(QrtzJobController)
 *
 * @author  ash
 * @version v1.1
 * @date    2024-09-14 16:11:41
 */
@Controller
@RequestMapping("/sys/qrtz_job")
public class QrtzJobController{

    /**
    * 业务处理类
    */
    @Autowired
    private QrtzJobService qrtzJobService;
    /**
     * 定时任务::应用表:页面
     * @return 页面
     */
    @RequiresPermissions("sys:qrtz_job:list")
    @GetMapping
    public String index(){
        return "sys/qrtz_job";
    }

    /**
     *  定时任务::任务配置表:列表
     * @param page_no    分页
     * @param page_size  分页大小
     * @param application 调度名称/应用名称
     * @param state 任务状态 传入_默认INIT(EXECUTING.执行中 PAUSED.暂停 COMPLETE.完成 ERROR.异常 INIT.初始化/未启动）
     * @param job_class 任务全类名
     * @param job_data 任务数据
     * @param job_description 任务描述
     * @return 分頁數據
     */
    @RequiresPermissions("sys:qrtz_job:list")
    @GetMapping("/list")
    @ResponseBody
    public MeeResult<Page<QrtzJob>> list(
        @RequestParam(defaultValue="1")Integer page_no, @RequestParam(defaultValue="10")Integer page_size
        ,String application
        ,String state
        ,String jobClass
        ,String jobData
        ,String jobDescription
    ){
        return qrtzJobService.list(page_no,page_size,application,state,jobClass,jobData,jobDescription );
    }

    /**
     *  定时任务::任务配置表:获取所有应用节点
     *
     *  **/
    @RequiresPermissions("sys:qrtz_job:list")
    @GetMapping("/listApp")
    @ResponseBody
    public MeeResult<Map> listApp(){
        return qrtzJobService.listApp();
    }
//
//    /**
//     * 定时任务::任务配置表:详细信息
//     * @param id 主鍵
//     * @return 主鍵記錄
//    */
//    @RequiresPermissions("sys:qrtz_job:list")
//    @GetMapping("/id")
//    @ResponseBody
//    public MeeResult<QrtzJob> findById(@RequestParam(required = true)String id){
//        return qrtzJobService.findById( id );
//    }

    /**
     * 定时任务::任务配置表:新增
     * @param qrtzJob 定时任务::任务配置表:對象
     * @return 新增結果
    */
    @RequiresPermissions("sys:qrtz_job:add")
    @PostMapping("add")
    @ResponseBody
    public MeeResult<Integer> add(@RequestBody(required = true) QrtzJob qrtzJob){
        return qrtzJobService.add( qrtzJob );
    }

    /**
     * 定时任务::任务配置表:修改
     * @param qrtzJob 定时任务::任务配置表:對象
     * @return 修改結果
     */
    @RequiresPermissions("sys:qrtz_job:update")
    @PutMapping("update")
    @ResponseBody
    public MeeResult<Integer> update(@RequestBody(required = true) QrtzJob qrtzJob ){
        return qrtzJobService.update( qrtzJob );
    }

    /**
     * 定时任务::任务配置表:删除
     * @param id 主鍵
     * @return 刪除結果
     */
    @RequiresPermissions("sys:qrtz_job:delete")
    @DeleteMapping("/delete")
    @ResponseBody
    public MeeResult<Integer> deleteById(@RequestParam(required = true) String id){
        return qrtzJobService.deleteById(id);
    }

    /**
     * 定时任务::任务配置表:修改状态
     * @param qrtzJob 定时任务::任务配置表:修改状态
     * @return 修改結果
     */
    @RequiresPermissions("sys:qrtz_job:update")
    @PutMapping("state")
    @ResponseBody
    public MeeResult<Integer> updateJobState(String job_id,String state ){
        return qrtzJobService.updateJobState(job_id,state);
    }

}
