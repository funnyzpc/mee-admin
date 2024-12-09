package com.mee.sys.service;

import com.mee.common.util.MeeResult;
import com.mee.core.model.Page;
import org.quartz.impl.QrtzJob;

import java.lang.String;
import java.lang.Integer;
import java.util.Map;

/**
 * 定时任务::任务配置表(QrtzJob) 业务接口
 * 
 * @author  ash
 * @version v1.1
 * @date    2024-09-14 16:11:41
 */
public interface QrtzJobService{

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
    MeeResult<Page<QrtzJob>> list(Integer page_no, Integer page_size , String application, String state, String job_class, String job_data, String job_description);

//    /**
//     * 定时任务::任务配置表:详细信息
//     * @param id 主鍵
//     * @return 主鍵記錄
//     */
//    MeeResult<QrtzJob> findById(String id);

    /**
     * 定时任务::任务配置表:新增
     * @param qrtzJob 定时任务::任务配置表:對象
     * @return 新增結果
    */
    MeeResult<Integer> add(QrtzJob qrtzJob);

    /**
     * 定时任务::任务配置表:修改
     * @param qrtzJob 定时任务::任务配置表:對象
     * @return 修改結果
    */
    MeeResult<Integer> update(QrtzJob qrtzJob);

    /**
     * 定时任务::任务配置表:删除
     * @param id 主鍵
     * @return 刪除結果
    */
    MeeResult<Integer> deleteById(String id);

    /**
     *  定时任务::任务配置表:获取所有应用节点
     *
     *  **/
    MeeResult<Map> listApp();

    MeeResult<Integer> updateJobState(String job_id,String state);
}
