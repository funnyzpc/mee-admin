package com.mee.sys.service;

import com.mee.common.util.MeeResult;
import com.mee.core.model.Page;
import org.quartz.impl.QrtzNode;

/**
 * 定时任务::节点实例表(QrtzNode) 业务接口
 * 
 * @author  ash
 * @version v1.1
 * @date    2024-09-18 10:17:53
 */
public interface QrtzNodeService{

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
    MeeResult<Page<QrtzNode>> list(Integer page_no, Integer page_size , String application, String host_ip, String host_name, String state);

//    /**
//     * 定时任务::节点实例表:详细信息
//     * @param application 主鍵
//     * @return 主鍵記錄
//     */
//    MeeResult<QrtzNode> findByApplication(String application);

    /**
     * 定时任务::节点实例表:新增
     * @param qrtzNode 定时任务::节点实例表:對象
     * @return 新增結果
    */
    MeeResult<Integer> add(QrtzNode qrtzNode);

    /**
     * 定时任务::节点实例表:修改
     * @param qrtzNode 定时任务::节点实例表:對象
     * @return 修改結果
    */
    MeeResult<Integer> update(QrtzNode qrtzNode);

    /**
     * 定时任务::节点实例表:删除
     * @param application 主鍵
     * @return 刪除結果
    */
    MeeResult<Integer> deleteByApplication(String application,String host_ip);

//    /**
//     * 定时任务::节点实例表:批量删除
//     * @param applications 逐漸列表
//     * @return 批量刪除結果
//    */
//    MeeResult<Integer> deleteBatch(String[] applications);
    /**
     * 定时任务::节点:状态调整
     * @param qrtzNode :: application 主鍵  host_ip 主机IP  state 状态
     * @return 调整结果
     */
    MeeResult<Integer> nodeState(QrtzNode qrtzNode);

}
