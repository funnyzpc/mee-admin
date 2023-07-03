package com.mee.sys.service;

import java.util.Map;
import com.mee.sys.entity.SysRoleUser;

import java.lang.String;
import java.lang.Integer;

/**
 * 系统::角色用户表(SysRoleUser2) 业务接口
 * 
 * @author  shadow
 * @version v1.3
 * @date    2023-05-28 16:45:32
 */
public interface SysRoleUser2Service{
    /**
     * 查询系统::角色用户表列表
     *
     * @param SysRoleUser(or Map) 系统::角色用户表
     * @return 系统::角色用户表分页集合
     */
    Map list(Integer page_no,Integer page_size ,String user_id,String role_id);

    /**
     * 按主键查询系统::角色用户表
     *
     * @param 系统::角色用户表主键
     * @return 系统::角色用户表
     */
    Map findById(String id);

    /**
     * 新增系统::角色用户表
     *
     * @param SysRoleUser(or Map) 系统::角色用户表
     * @return 插入条数
     */
    Map add(SysRoleUser sysRoleUser2);
    /**
     * 修改系统::角色用户表
     *
     * @param SysRoleUser(or Map) 系统::角色用户表
     * @return 更新条数
     */
    Map update(SysRoleUser sysRoleUser2);

    /**
     * 删除系统::角色用户表
     *
     * @id 系统::角色用户表 主键
     * @return 删除条数
     */
    Map deleteById(String id);

    /**
     * 批量删除系统::角色用户表
     *
     * @ids 系统::角色用户表 主键集合
     * @return 删除条数
     */
    Map deleteBatch(String[] ids);

}
