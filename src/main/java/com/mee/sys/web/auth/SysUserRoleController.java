package com.mee.sys.web.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author funnyzpc
 * @description 用户角色关联
 */
@Controller
@RequestMapping("/userRole")
public class SysUserRoleController {

    @GetMapping
    public String index(){
        return "sys/relation_user_role";
    }

}
