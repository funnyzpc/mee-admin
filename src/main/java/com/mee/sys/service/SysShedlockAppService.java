package com.mee.sys.service;

import com.mee.common.util.MeeResult;
import com.mee.core.model.Page;
import com.mee.sys.entity.SysShedlockApp;
import jakarta.validation.constraints.NotNull;

import javax.servlet.http.HttpServletResponse;

/**
 * 集群分佈式鎖-应用配置(SysShedlockApp) 业务接口
 * 
 * @author  ash
 * @version v1.1
 * @date    2024-06-18 16:31:23
 */
public interface SysShedlockAppService{

    /**
     *  集群分佈式鎖-应用配置:列表
     * @param page_no    分页
     * @param page_size  分页大小
     * @param application 当前实例应用
     * @param host_ip 当前实例应用所属IP
     * @param host_name 创建机器
     * @param state 状态 0.关闭 1.开启
     * @return 分頁數據
    */
    MeeResult<Page<SysShedlockApp>> list(Integer page_no, Integer page_size , String application, String host_ip, String host_name, String state);

    /**
     * 集群分佈式鎖-应用配置:详细信息
     * @param application 主鍵
     * @return 主鍵記錄
     */
    MeeResult<SysShedlockApp> findByApplication(String application);

    /**
     * 集群分佈式鎖-应用配置:新增
     * @param sysShedlockApp 集群分佈式鎖-应用配置:對象
     * @return 新增結果
    */
    MeeResult<Integer> add(SysShedlockApp sysShedlockApp);

    /**
     * 集群分佈式鎖-应用配置:修改
     * @param sysShedlockApp 集群分佈式鎖-应用配置:對象
     * @return 修改結果
    */
    MeeResult<Integer> update(SysShedlockApp sysShedlockApp);

    /**
     * 集群分佈式鎖-应用配置:删除
     * @param application 主鍵
     * @return 刪除結果
    */
    MeeResult<Integer> deleteByApplication(String application,String hostIP);

    /**
     * 修改状态(开启/关闭)
     * @param application   application
     * @param hostIP    hostIP
     * @param state state
     * @return MeeResult
     */
    MeeResult<Integer> appState(String application,String hostIP,String state);


    /**
     *  集群分佈式鎖-应用配置:导出
     * @param response   响应体
     * @param page_no    分页
     * @param page_size  分页大小
     * @param application 当前实例应用
     * @param host_ip 当前实例应用所属IP
     * @param host_name 创建机器
     * @param state 状态 0.关闭 1.开启
     * @return 導出excel文件
     */
    void doExport(HttpServletResponse response, @NotNull Integer page_no, @NotNull Integer page_size, String application, String host_ip, String host_name, String state);


}
