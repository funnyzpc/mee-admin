package com.mee.sys.web;


import com.mee.common.util.MeeResult;
import com.mee.core.model.Page;
import com.mee.sys.service.QrtzExecuteService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.impl.QrtzExecute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



/**
 * 定时任务::执行配置表web接口(QrtzExecuteController)
 *
 * @author  ash
 * @version v1.1
 * @date    2024-09-14 16:11:46
 */
@RestController
@RequestMapping("/sys/qrtz_execute")
public class QrtzExecuteController{

    /**
    * 业务处理类
    */
    @Autowired
    private QrtzExecuteService qrtzExecuteService;

    /**
     *  定时任务::执行配置表:列表
     * @param page_no    分页
     * @param page_size  分页大小
     * @param pid 关联任务(QRTZ_JOB::ID)
     * @param job_type 任务类型
     * @param state 任务状态 传入_默认INIT(EXECUTING.执行中 PAUSED.暂停 COMPLETE.完成 ERROR.异常 INIT.初始化/未启动）
     * @return 分頁數據
     */
    @RequiresPermissions("sys:qrtz_job:list")
    @GetMapping("/list")
    @ResponseBody
    public MeeResult<Page<QrtzExecute>> list(
        @RequestParam(defaultValue="1")Integer page_no, @RequestParam(defaultValue="10")Integer page_size
        ,Long pid
        ,String jobType
        ,String state
    ){
        return qrtzExecuteService.list(page_no,page_size,pid,jobType,state );
    }

//    /**
//     * 定时任务::执行配置表:详细信息
//     * @param id 主鍵
//     * @return 主鍵記錄
//    */
//    @RequiresPermissions("sys:qrtz_job:list")
//    @GetMapping("/id")
//    @ResponseBody
//    public MeeResult<QrtzExecute> findById(@RequestParam(required = true)String id){
//        return qrtzExecuteService.findById( id );
//    }

    /**
     * 定时任务::执行配置表:新增
     * @param qrtzExecute 定时任务::执行配置表:對象
     * @return 新增結果
    */
    @RequiresPermissions("sys:qrtz_job:add")
    @PostMapping("add")
    @ResponseBody
    public MeeResult<Integer> add(@RequestBody(required = true) QrtzExecute qrtzExecute){
        return qrtzExecuteService.add( qrtzExecute );
    }

    /**
     * 定时任务::执行配置表:修改
     * @param qrtzExecute 定时任务::执行配置表:對象
     * @return 修改結果
     */
    @RequiresPermissions("sys:qrtz_job:update")
    @PutMapping("update")
    @ResponseBody
    public MeeResult<Integer> update(@RequestBody(required = true) QrtzExecute qrtzExecute ){
        return qrtzExecuteService.update( qrtzExecute );
    }

    /**
     * 定时任务::执行配置表:删除
     * @param id 主鍵
     * @return 刪除結果
     */
    @RequiresPermissions("sys:qrtz_job:delete")
    @DeleteMapping("/delete")
    @ResponseBody
    public MeeResult<Integer> deleteById(@RequestParam(required = true) String id){
        return qrtzExecuteService.deleteById(id);
    }
//
//    /**
//     * 定时任务::执行配置表:批量删除
//     * @param ids 逐漸列表
//     * @return 批量刪除結果
//     */
//    @RequiresPermissions("sys:qrtz_job:delete")
//    @DeleteMapping("/delete_batch")
//    @ResponseBody
//    public MeeResult<Integer> deleteBatch(@RequestBody(required = true) String[] ids){
//        return qrtzExecuteService.deleteBatch(ids);
//    }

//    /**
//     *  定时任务::执行配置表:导出
//     * @param response   响应体
//     * @param page_no    分页
//     * @param page_size  分页大小
//     * @param pid 关联任务(QRTZ_JOB::ID)
//     * @param job_type 任务类型
//     * @param state 任务状态 传入_默认INIT(EXECUTING.执行中 PAUSED.暂停 COMPLETE.完成 ERROR.异常 INIT.初始化/未启动）
//     * @return 導出excel文件
//     */
//    @RequiresPermissions("sys:qrtz_job:export")
//    @GetMapping("export")
//    public void doExport(HttpServletResponse response,
//        @RequestParam(defaultValue="1")Integer page_no, @RequestParam(defaultValue="10")Integer page_size
//        ,Long pid
//        ,String job_type
//        ,String state
//        )
//    {
//        qrtzExecuteService.doExport(response,page_no,page_size,pid,job_type,state );
//    }
//
//    /**
//     * 定时任务::执行配置表:导入
//     * @param file 文件
//     * @return 導入結果
//     */
//    @RequiresPermissions("sys:qrtz_job:import")
//    @PostMapping("import")
//    @ResponseBody
//    public MeeResult<String> doImport(@RequestParam(value = "file",required = true) MultipartFile file
//    /*@RequestParam(value = "name",required = false) String name*/)throws Exception{
//        return qrtzExecuteService.doImport(file);
//    }

    /**
     * 定时任务::任务配置表:修改状态
     * @param qrtzJob 定时任务::任务配置表:修改状态
     * @return 修改結果
     */
    @RequiresPermissions("sys:qrtz_job:update")
    @PutMapping("state")
    @ResponseBody
    public MeeResult<Integer> updateExecuteState(String execute_id,String state ){
        return qrtzExecuteService.updateExecuteState(execute_id,state);
    }

}
