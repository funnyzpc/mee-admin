package com.mee.sys.service;

import com.mee.common.util.MeeResult;
import com.mee.core.model.Page;
import com.mee.sys.dto.SysUserDTO;
import com.mee.sys.entity.SysRole;
import com.mee.sys.entity.SysUser;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


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
    MeeResult<Page<SysUser>> list(@RequestParam(required = true)Integer page_no, @RequestParam(required = true)Integer page_size, String user_name,String nick_name,String phone, String email,String status,String del_flag);

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
     * @param sysUserDTO(or Map) 系统::用户信息表
     * @return 插入条数
     */
    MeeResult add(SysUserDTO sysUserDTO);
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

    /**
     * 修改用户状态
     * @param user 用户信息
     * @return MeeResult<Void>
     */

    MeeResult<Void> changeStatus(SysUser user);

    /**
     * 修改密码
     * @param id 用户ID
     * @param pwd 密码
     * @return
     */
    MeeResult<Integer> resetPwd(String id, String pwd);

    /**
     * 获取用户角色信息
     * @param userId 用户ID
     * @return
     */
    MeeResult<List<SysRole>> getRoles(String userId);

    /**
     * 导出用户信息
     * @param response
     * @param pageNo
     * @param pageSize
     * @param userName
     * @param nickName
     * @param phone
     * @param email
     * @param status
     * @param delFlag
     */
    void doExport(HttpServletResponse response, Integer pageNo, Integer pageSize, String userName, String nickName, String phone, String email, String status, String delFlag);
}
