package com.mee.sys.service;

import com.mee.common.util.MeeResult;
import com.mee.sys.entity.SysUser;


/**
 * 系统::用户信息表(SysUser2) 业务接口
 * 
 * @author  shadow
 * @version v1.3
 * @date    2023-05-30 20:59:40
 */
public interface SysUserService {
    /**
     * 查询系统::用户信息表列表
     *
     * @param SysUser2(or Map) 系统::用户信息表
     * @return 系统::用户信息表分页集合
     */
    MeeResult list(Integer page_no,Integer page_size ,String user_name,String phone,String email,String status);

    /**
     * 按主键查询系统::用户信息表
     *
     * @param 系统::用户信息表主键
     * @return 系统::用户信息表
     */
    MeeResult findById(String id);

    /**
     * 新增系统::用户信息表
     *
     * @param SysUser2(or Map) 系统::用户信息表
     * @return 插入条数
     */
    MeeResult add(SysUser sysUser2);
    /**
     * 修改系统::用户信息表
     *
     * @param SysUser2(or Map) 系统::用户信息表
     * @return 更新条数
     */
    MeeResult update(SysUser sysUser2);

    /**
     * 删除系统::用户信息表
     *
     * @id 系统::用户信息表 主键
     * @return 删除条数
     */
    MeeResult deleteById(String id);

    /**
     * 批量删除系统::用户信息表
     *
     * @ids 系统::用户信息表 主键集合
     * @return 删除条数
     */
    MeeResult deleteBatch(String[] ids);

}