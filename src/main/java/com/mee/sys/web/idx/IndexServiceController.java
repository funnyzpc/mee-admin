package com.mee.sys.web.idx;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** 业务中心主页 **/
@Controller
@RequestMapping("/service")
public class IndexServiceController {

    @RequiresPermissions("02")
    @GetMapping
    public String index(){
        return "sys/idx/service_index";
    }
}
