package com.mee.sys.service;


import com.mee.common.util.MeeResult;
import com.mee.core.model.Page;
import org.quartz.impl.QrtzApp;

/**
 * 定时任务::应用表(QrtzApp) 业务接口
 * 
 * @author  ash
 * @version v1.1
 * @date    2024-09-14 10:23:41
 */
public interface QrtzAppService{

    /**
     *  定时任务::应用表:列表
     * @param page_no    分页
     * @param page_size  分页大小
     * @param application 调度名称/应用名称
     * @param state 状态 N.停止/不可用  Y.开启/可用
     * @return 分頁數據
    */
    MeeResult<Page<QrtzApp>> list(Integer page_no, Integer page_size , String application, String state);

//    /**
//     * 定时任务::应用表:详细信息
//     * @param application 主鍵
//     * @return 主鍵記錄
//     */
//    MeeResult<QrtzApp> findByApplication(String application);

    /**
     * 定时任务::应用表:新增
     * @param qrtzApp 定时任务::应用表:對象
     * @return 新增結果
    */
    MeeResult<Integer> add(QrtzApp qrtzApp);

//    /**
//     * 定时任务::应用表:修改
//     * @param qrtzApp 定时任务::应用表:對象
//     * @return 修改結果
//    */
//    MeeResult<Integer> update(QrtzApp qrtzApp);

    /**
     * 定时任务::应用表:删除
     * @param application 主鍵
     * @return 刪除結果
    */
    MeeResult<Integer> deleteByApplication(String application);

    /**
     * 定时任务::应用表:状态调整
     * @param application 主鍵
     * @param state 状态
     * @return 调整结果
     */
    MeeResult<Integer> appState(QrtzApp qrtzApp);

}
