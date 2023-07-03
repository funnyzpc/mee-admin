package com.mee.sys.service;

import com.mee.common.util.MeeResult;
import com.mee.sys.entity.SysRoleUser;

/**
 * 系统::角色用户表(SysRoleUser2) 业务接口
 * 
 * @author  shadow
 * @version v1.3
 * @date    2023-05-28 16:45:32
 */
public interface SysRoleUserService {
    /**
     * 查询系统::角色用户表列表
     *
     * @param SysRoleUser(or Map) 系统::角色用户表
     * @return 系统::角色用户表分页集合
     */
    MeeResult list(Integer page_no,Integer page_size ,String user_id,String role_id);

    /**
     * 按主键查询系统::角色用户表
     *
     * @param 系统::角色用户表主键
     * @return 系统::角色用户表
     */
    MeeResult findById(String id);

    /**
     * 新增系统::角色用户表
     *
     * @param SysRoleUser(or Map) 系统::角色用户表
     * @return 插入条数
     */
    MeeResult add(SysRoleUser sysRoleUser2);
    /**
     * 修改系统::角色用户表
     *
     * @param SysRoleUser(or Map) 系统::角色用户表
     * @return 更新条数
     */
    MeeResult update(SysRoleUser sysRoleUser2);

    /**
     * 删除系统::角色用户表
     *
     * @id 系统::角色用户表 主键
     * @return 删除条数
     */
    MeeResult deleteById(String id);

    /**
     * 批量删除系统::角色用户表
     *
     * @ids 系统::角色用户表 主键集合
     * @return 删除条数
     */
    MeeResult deleteBatch(String[] ids);

}
