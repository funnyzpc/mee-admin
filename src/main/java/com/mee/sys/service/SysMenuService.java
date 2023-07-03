package com.mee.sys.service;

import com.mee.common.util.MeeResult;
import com.mee.sys.entity.SysMenu;

import java.util.Map;


/**
 * 系统::新菜单表(SysMenu2) 业务接口
 * 
 * @author  shadow
 * @version v1.3
 * @date    2023-05-05 21:48:15
 */
public interface SysMenuService {
    /**
     * 查询系统::新菜单表列表
     *
     * @param ... 系统::新菜单表
     * @return 系统::新菜单表分页集合
     */
    MeeResult list(Integer page_no,Integer page_size ,Integer type,String title,String path,String target,String permission,Integer show);

    /**
     * 按主键查询系统::新菜单表
     *
     * @param id 系统::新菜单表主键
     * @return 系统::新菜单表
     */
    MeeResult findById(String id);

    /**
     * 新增系统::新菜单表
     *
     * @param sysMenu2(or Map) 系统::新菜单表
     * @return 插入条数
     */
    MeeResult add(SysMenu sysMenu2);
    /**
     * 修改系统::新菜单表
     *
     * @param sysMenu2(or Map) 系统::新菜单表
     * @return 更新条数
     */
    MeeResult edit(SysMenu sysMenu2);

    /**
     * 删除系统::新菜单表
     *
     * @id 系统::新菜单表 主键
     * @return 删除条数
     */
    MeeResult deleteById(String id);

    /**
     * 批量删除系统::新菜单表
     *
     * @ids 系统::新菜单表 主键集合
     * @return 删除条数
     */
    MeeResult deleteBatch(String[] ids);

    Map<String, Object> menuAll(String title);
}
