package com.mee.sys.service;

import com.mee.common.util.MeeResult;
import com.mee.sys.entity.SysRoleMenu;

import java.util.List;
import java.util.Map;

/**
 * 角色菜单关联(SysRoleMenu2) 业务接口
 * 
 * @author  shadow
 * @version v1.3
 * @date    2023-05-28 16:45:29
 */
public interface SysRoleMenuService {
    /**
     * 查询角色菜单关联列表
     *
     * @param SysRoleMenu2(or Map) 角色菜单关联
     * @return 角色菜单关联分页集合
     */
    MeeResult list(Integer page_no,Integer page_size ,String menu_id,String role_id);

    /**
     * 按主键查询角色菜单关联
     *
     * @param 角色菜单关联主键
     * @return 角色菜单关联
     */
    MeeResult findById(String id);

    /**
     * 新增角色菜单关联
     *
     * @param SysRoleMenu2(or Map) 角色菜单关联
     * @return 插入条数
     */
    MeeResult add(Map<String,Object> sysRoleMenu);
    /**
     * 修改角色菜单关联
     *
     * @param SysRoleMenu2(or Map) 角色菜单关联
     * @return 更新条数
     */
    MeeResult update(SysRoleMenu sysRoleMenu);

    /**
     * 删除角色菜单关联
     *
     * @id 角色菜单关联 主键
     * @return 删除条数
     */
    MeeResult deleteById(String id);

    /**
     * 批量删除角色菜单关联
     *
     * @ids 角色菜单关联 主键集合
     * @return 删除条数
     */
    MeeResult deleteBatch(String[] ids);

    /**
     * 列出所有关联角色菜单信息
     * @param roleId 角色ID
     * @return MeeResult<List<SysRoleMenu>>
     */
    MeeResult<List<SysRoleMenu>> listByRoleId(String roleId);
}
