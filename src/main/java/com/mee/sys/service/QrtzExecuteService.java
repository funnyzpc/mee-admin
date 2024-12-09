package com.mee.sys.service;

import com.mee.common.util.MeeResult;
import com.mee.core.model.Page;
import org.quartz.impl.QrtzExecute;

import java.lang.String;
import java.lang.Integer;
import java.lang.Long;

/**
 * 定时任务::执行配置表(QrtzExecute) 业务接口
 * 
 * @author  ash
 * @version v1.1
 * @date    2024-09-14 16:11:46
 */
public interface QrtzExecuteService{

    /**
     *  定时任务::执行配置表:列表
     * @param page_no    分页
     * @param page_size  分页大小
     * @param pid 关联任务(QRTZ_JOB::ID)
     * @param job_type 任务类型
     * @param state 任务状态 传入_默认INIT(EXECUTING.执行中 PAUSED.暂停 COMPLETE.完成 ERROR.异常 INIT.初始化/未启动）
     * @return 分頁數據
    */
    MeeResult<Page<QrtzExecute>> list(Integer page_no, Integer page_size , Long pid, String job_type, String state);

//    /**
//     * 定时任务::执行配置表:详细信息
//     * @param id 主鍵
//     * @return 主鍵記錄
//     */
//    MeeResult<QrtzExecute> findById(String id);

    /**
     * 定时任务::执行配置表:新增
     * @param qrtzExecute 定时任务::执行配置表:對象
     * @return 新增結果
    */
    MeeResult<Integer> add(QrtzExecute qrtzExecute);

    /**
     * 定时任务::执行配置表:修改
     * @param qrtzExecute 定时任务::执行配置表:對象
     * @return 修改結果
    */
    MeeResult<Integer> update(QrtzExecute qrtzExecute);

    /**
     * 定时任务::执行配置表:删除
     * @param id 主鍵
     * @return 刪除結果
    */
    MeeResult<Integer> deleteById(String id);

    MeeResult<Integer> updateExecuteState(String executeId, String state);

//    /**
//     * 定时任务::执行配置表:批量删除
//     * @param ids 逐漸列表
//     * @return 批量刪除結果
//    */
//    MeeResult<Integer> deleteBatch(String[] ids);


}
