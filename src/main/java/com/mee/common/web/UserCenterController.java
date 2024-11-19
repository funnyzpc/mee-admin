package com.mee.common.web;

import com.mee.common.service.impl.UserCenterServiceImpl;
import com.mee.common.util.MeeResult;
import com.mee.sys.dto.SysUserDTO;
import com.mee.sys.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 用户中心相关
 *
 * @author shaoow
 * @version 1.0
 * @className UserCenterController
 * @date 2023/6/6 13:40
 */
@Controller
@RequestMapping("/common/user_center")
public class UserCenterController {


    @Autowired
    private UserCenterServiceImpl userCenterService;

    /**
     * 页面
     * @return 页面
     */
    @GetMapping
    public String index(){
        return "common/user_center";
    }

    /**
     * 用户信息查询
     */
    @GetMapping("/list")
    @ResponseBody
    public MeeResult<SysUser> list( ){
        return userCenterService.list();
    }

    /**
     * 更新用户信息
     */
    @PutMapping("update_user_info")
    @ResponseBody
    public MeeResult<Integer> updateUserInfo(@RequestBody(required = true) SysUser sysUser){
        return userCenterService.updateUserInfo(sysUser);
    }

    /**
     * 更新密码
     */
    @PutMapping("update_user_pwd")
    @ResponseBody
    public MeeResult<Integer> updateUserPwd(@RequestBody(required = true) SysUserDTO sysUserDTO){
        return userCenterService.updateUserPwd(sysUserDTO);
    }

}
