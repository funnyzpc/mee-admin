package com.mee.sys.web;


import com.mee.common.util.MeeResult;
import com.mee.core.model.Page;
import com.mee.sys.service.QrtzNodeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.impl.QrtzNode;
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
 * 定时任务::节点实例表web接口(QrtzNodeController)
 *
 * @author  ash
 * @version v1.1
 * @date    2024-09-18 10:17:53
 */
@RestController
@RequestMapping("/sys/qrtz_node")
public class QrtzNodeController{

    /**
    * 业务处理类
    */
    @Autowired
    private QrtzNodeService qrtzNodeService;

    /**
     *  定时任务::节点实例表:列表
     * @param page_no    分页
     * @param page_size  分页大小
     * @param application 调度名称/应用名称
     * @param host_ip 实例机器IP
     * @param host_name 实例机器名称
     * @param state 状态 N.停止/不可用  Y.开启/可用
     * @return 分頁數據
     */
    @RequiresPermissions("sys:qrtz_app:list")
    @GetMapping("/list")
    @ResponseBody
    public MeeResult<Page<QrtzNode>> list(
        @RequestParam(defaultValue="1")Integer page_no, @RequestParam(defaultValue="10")Integer page_size
        ,String application
        ,String hostIP
        ,String hostName
        ,String state
    ){
        return qrtzNodeService.list(page_no,page_size,application,hostIP,hostName,state );
    }

//    /**
//     * 定时任务::节点实例表:详细信息
//     * @param application 主鍵
//     * @return 主鍵記錄
//    */
//    @RequiresPermissions("sys:qrtz_app:list")
//    @GetMapping("/application")
//    @ResponseBody
//    public MeeResult<QrtzNode> findByApplication(@RequestParam(required = true)String application){
//        return qrtzNodeService.findByApplication( application );
//    }

    /**
     * 定时任务::节点实例表:新增
     * @param qrtzNode 定时任务::节点实例表:對象
     * @return 新增結果
    */
    @RequiresPermissions("sys:qrtz_app:add")
    @PostMapping("add")
    @ResponseBody
    public MeeResult<Integer> add(@RequestBody(required = true) QrtzNode qrtzNode){
        return qrtzNodeService.add( qrtzNode );
    }

    /**
     * 定时任务::节点实例表:修改
     * @param qrtzNode 定时任务::节点实例表:對象
     * @return 修改結果
     */
    @RequiresPermissions("sys:qrtz_app:update")
    @PutMapping("update")
    @ResponseBody
    public MeeResult<Integer> update(@RequestBody(required = true) QrtzNode qrtzNode ){
        return qrtzNodeService.update( qrtzNode );
    }

    /**
     * 定时任务::节点实例表:删除
     * @param application 主鍵
     * @return 刪除結果
     */
    @RequiresPermissions("sys:qrtz_app:delete")
    @DeleteMapping("/delete")
    @ResponseBody
    public MeeResult<Integer> deleteByApplication(@RequestParam(required = true) String application,@RequestParam(required = true) String hostIp){
        return qrtzNodeService.deleteByApplication(application,hostIp);
    }

//    /**
//     * 定时任务::节点实例表:批量删除
//     * @param applications 逐漸列表
//     * @return 批量刪除結果
//     */
//    @RequiresPermissions("sys:qrtz_app:delete")
//    @DeleteMapping("/delete_batch")
//    @ResponseBody
//    public MeeResult<Integer> deleteBatch(@RequestBody(required = true) String[] applications){
//        return qrtzNodeService.deleteBatch(applications);
//    }

    /**
     * 定时任务::节点:状态调整
     * @param qrtzNode :: application 主鍵  host_ip 主机IP  state 状态
     * @return 调整结果
     */
    @RequiresPermissions("sys:qrtz_app:update")
    @PutMapping("/node_state")
    @ResponseBody
    public MeeResult<Integer> nodeState(@RequestBody(required = true) QrtzNode qrtzNode ){
        return qrtzNodeService.nodeState(qrtzNode);
    }

}
