package com.mee.sys.service;

import com.mee.common.util.MeeResult;
import com.mee.core.model.Page;
import com.mee.sys.entity.SysShedlockJob;
import jakarta.validation.constraints.NotNull;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

/**
 * 集群分佈式鎖-任务配置(SysShedlockJob) 业务接口
 * 
 * @author  ash
 * @version v1.1
 * @date    2024-06-18 16:31:16
 */
public interface SysShedlockJobService{

    /**
     *  集群分佈式鎖-任务配置:列表
     * @param page_no    分页
     * @param page_size  分页大小
     * @param application 当前实例应用
     * @param name 任務名稱(即ID)
     * @param host_ip 当前实例应用所属IP
     * @param state 0.close关闭 1.open开启(默认) 局部限制标志位(只限制所有相同实例中的此任务)
     * @param label 任務標識
     * @return 分頁數據
    */
    MeeResult<Page<SysShedlockJob>> list(Integer page_no, Integer page_size , String application, String name, String host_ip ,String state, String label);

    /**
     * 集群分佈式鎖-任务配置:详细信息
     * @param application 主鍵
     * @return 主鍵記錄
     */
    MeeResult<SysShedlockJob> findByApplication(String application);

    /**
     * 集群分佈式鎖-任务配置:新增
     * @param sysShedlockJob 集群分佈式鎖-任务配置:對象
     * @return 新增結果
    */
    MeeResult<Integer> add(SysShedlockJob sysShedlockJob);

    /**
     * 集群分佈式鎖-任务配置:修改
     * @param sysShedlockJob 集群分佈式鎖-任务配置:對象
     * @return 修改結果
    */
    MeeResult<Integer> update(SysShedlockJob sysShedlockJob);

    /**
     * 集群分佈式鎖-任务配置:删除
     * @param application 主鍵
     * @param String name
     * @return 刪除結果
    */
    MeeResult<Integer> deleteByApplication(String application,String name);

    /**
     * 修改状态(开启/关闭)
     * @param application   application
     * @param name    name
     * @param state state
     * @return MeeResult
     */
    MeeResult<Integer> jobState(String application,String name,String state);

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
    void doExport(HttpServletResponse response, @NotNull Integer page_no, @NotNull Integer page_size, String application, String name, String host_ip, LocalDateTime locked_at, String state, String label);


}
