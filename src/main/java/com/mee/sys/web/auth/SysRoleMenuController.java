package com.mee.sys.web.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author funnyzpc
 * @description 角色财产关联
 */
@Controller
@RequestMapping("/roleMenu")
public class SysRoleMenuController {

    @GetMapping
    public String index(){
        return "sys/relation_role_menu";
    }

}
