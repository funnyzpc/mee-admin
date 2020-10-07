package com.mee.sys.web.idx;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/** 报表中心主页 **/
@Controller
@RequestMapping("/report")
public class IndexReportController {

    @RequiresPermissions("03")
    @GetMapping
    public String index(){
        return "sys/idx/report_index";
    }

}
