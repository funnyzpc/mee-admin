package com.mee.sys.web;

import com.mee.common.util.MeeResult;
import com.mee.core.model.Page;
import com.mee.sys.service.QrtzAppService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.impl.QrtzApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.lang.String;
import java.lang.Integer;



/**
 * 定时任务::应用表web接口(QrtzAppController)
 *
 * @author  ash
 * @version v1.1
 * @date    2024-09-14 10:23:41
 */
@Controller
@RequestMapping("/sys/qrtz_app")
public class QrtzAppController{

    /**
    * 业务处理类
    */
    @Autowired
    private QrtzAppService qrtzAppService;

    /**
     * 定时任务::应用表:页面
     * @return 页面
     */
    @RequiresPermissions("sys:qrtz_app:list")
    @GetMapping
    public String index(){
        return "sys/qrtz_app";
    }

    /**
     *  定时任务::应用表:列表
     * @param page_no    分页
     * @param page_size  分页大小
     * @param application 调度名称/应用名称
     * @param state 状态 N.停止/不可用  Y.开启/可用
     * @return 分頁數據
     */
    @RequiresPermissions("sys:qrtz_app:list")
    @GetMapping("/list")
    @ResponseBody
    public MeeResult<Page<QrtzApp>> list(
        @RequestParam(defaultValue="1")Integer page_no,
        @RequestParam(defaultValue="10")Integer page_size
        ,String application
        ,String state
    ){
        return qrtzAppService.list(page_no,page_size,application,state );
    }

//    /**
//     * 定时任务::应用表:详细信息
//     * @param application 主鍵
//     * @return 主鍵記錄
//    */
//    @RequiresPermissions("sys:qrtz_app:list")
//    @GetMapping("/application")
//    @ResponseBody
//    public MeeResult<QrtzApp> findByApplication(@RequestParam(required = true)String application){
//        return qrtzAppService.findByApplication( application );
//    }

    /**
     * 定时任务::应用表:新增
     * @param qrtzApp 定时任务::应用表:對象
     * @return 新增結果
    */
    @RequiresPermissions("sys:qrtz_app:add")
    @PostMapping("add")
    @ResponseBody
    public MeeResult<Integer> add(@RequestBody(required = true) QrtzApp qrtzApp){
        return qrtzAppService.add( qrtzApp );
    }

//    /**
//     * 定时任务::应用表:修改
//     * @param qrtzApp 定时任务::应用表:對象
//     * @return 修改結果
//     */
//    @RequiresPermissions("sys:qrtz_app:update")
//    @PutMapping("update")
//    @ResponseBody
//    public MeeResult<Integer> update(@RequestBody(required = true) QrtzApp qrtzApp ){
//        return qrtzAppService.update( qrtzApp );
//    }

    /**
     * 定时任务::应用表:删除
     * @param application 主鍵
     * @return 刪除結果
     */
    @RequiresPermissions("sys:qrtz_app:delete")
    @DeleteMapping("/delete")
    @ResponseBody
    public MeeResult<Integer> deleteByApplication(@RequestParam(required = true) String application){
        return qrtzAppService.deleteByApplication(application);
    }

    /**
     * 定时任务::应用表:状态调整
     * @param application 主鍵
     * @param state 状态
     * @return 调整结果
     */
    @RequiresPermissions("sys:qrtz_app:update")
    @PutMapping("/app_state")
    @ResponseBody
    public MeeResult<Integer> appState(@RequestBody(required = true) QrtzApp qrtzApp){
        return qrtzAppService.appState(qrtzApp);
    }


}
