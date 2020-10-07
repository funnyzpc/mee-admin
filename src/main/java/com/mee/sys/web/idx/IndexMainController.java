package com.mee.sys.web.idx;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author funnyzpc
 * @description home主页
 */
@Controller
@RequestMapping({"/main","/*/main"})
public class IndexMainController {

    //@RequiresPermissions("01")
    @RequestMapping
    public String main(){
        return "sys/idx/main_index";
    }

}
