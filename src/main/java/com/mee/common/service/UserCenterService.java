package com.mee.common.service;

import com.mee.common.util.MeeResult;
import com.mee.sys.dto.SysUserDTO;
import com.mee.sys.entity.SysUser;

/**
 * 用户中心interface
 *
 * @author shaoow
 * @version 1.0
 * @className UserCenterService
 * @date 2023/6/6 13:59
 */
public interface UserCenterService {

    MeeResult<SysUser> list();

    MeeResult<Integer> updateUserInfo(SysUser sysUser);

    MeeResult<Integer> updateUserPwd(SysUserDTO sysUserDTO);


}
