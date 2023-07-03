package com.mee.common.web;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页
 *
 * @author shadow
 * @version v1.0
 * @className HomeController
 * @date 2023/4/20 10:20 PM
 */
@Deprecated
//@Controller
//@RequestMapping("/home")
public class HomeController {

    /**
     * 主页跳转
     * @return
     */
    @RequestMapping
    public String index(){
        return "home";
    }

    /**
     * 主页接口 TODO...
     */
}
