package com.mee.sys.service;

import com.mee.common.util.MeeResult;
import com.mee.sys.entity.SysRole;


/**
 * 系统::角色表(SysRole2) 业务接口
 * 
 * @author  shadow
 * @version v1.3
 * @date    2023-05-28 16:45:31
 */
public interface SysRoleService {
    /**
     * 查询系统::角色表列表
     *
     * @param SysRole2(or Map) 系统::角色表
     * @return 系统::角色表分页集合
     */
    MeeResult list(Integer page_no,Integer page_size ,String name,String description,Integer status);

    /**
     * 按主键查询系统::角色表
     *
     * @param 系统::角色表主键
     * @return 系统::角色表
     */
    MeeResult findById(String id);

    /**
     * 新增系统::角色表
     *
     * @param SysRole2(or Map) 系统::角色表
     * @return 插入条数
     */
    MeeResult add(SysRole sysRole2);
    /**
     * 修改系统::角色表
     *
     * @param SysRole2(or Map) 系统::角色表
     * @return 更新条数
     */
    MeeResult edit(SysRole sysRole2);

    /**
     * 删除系统::角色表
     *
     * @id 系统::角色表 主键
     * @return 删除条数
     */
    MeeResult deleteById(String id);

    /**
     * 批量删除系统::角色表
     *
     * @ids 系统::角色表 主键集合
     * @return 删除条数
     */
    MeeResult deleteBatch(String[] ids);

}
