package com.mee.sys.service;

import com.mee.common.util.MeeResult;
import com.mee.core.model.Page;
import com.mee.sys.dto.SysRoleUserDTO;
import com.mee.sys.entity.SysRoleUser;
import com.mee.sys.entity.SysUser;
import com.mee.sys.vo.SysRoleUserVO;
import org.springframework.web.bind.annotation.RequestParam;

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
    MeeResult<Page<SysRoleUserVO>> list(@RequestParam(defaultValue="1")Integer page_no, @RequestParam(defaultValue="10")Integer page_size, String user_name, String nick_name, String user_id, String role_id);

    /**
     * 按主键查询系统::角色用户表
     *
     * @param 系统::角色用户表主键
     * @return 系统::角色用户表
     */
    MeeResult<SysRoleUser> findById(String id);

    /**
     * 新增系统::角色用户表
     *
     * @param SysRoleUser(or Map) 系统::角色用户表
     * @return 插入条数
     */
    MeeResult<Void> add(SysRoleUserDTO sysRoleUser2);
    /**
     * 修改系统::角色用户表
     *
     * @param SysRoleUser(or Map) 系统::角色用户表
     * @return 更新条数
     */
    MeeResult<Integer> update(SysRoleUser sysRoleUser2);

    /**
     * 删除系统::角色用户表
     *
     * @id 系统::角色用户表 主键
     * @return 删除条数
     */
    MeeResult<Integer> deleteById(String id);

    /**
     * 批量删除系统::角色用户表
     *
     * @ids 系统::角色用户表 主键集合
     * @return 删除条数
     */
    MeeResult<Integer> deleteBatch(String[] ids);

    /**
     * 获取用户列表
     * @param pageNo
     * @param pageSize
     * @param userName
     * @param phone
     * @param roleId
     * @return
     */
    MeeResult<Page<SysUser>> listUser(Integer pageNo, Integer pageSize, String userName, String phone, String roleId);
}
