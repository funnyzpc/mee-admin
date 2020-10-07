package com.mee.sys.web.idx;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** 系统管理主页 **/
@Controller
@RequestMapping("/sys")
public class IndexSysController {

    @RequiresPermissions("04")
    @GetMapping
    public String index(){
        return "sys/idx/sys_index";
    }

}
