package com.mee.common.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/***
 * @author  funnyzpc
 * @description 主页(首屏)
 */
@Controller
public class CommonIndexController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonIndexController.class);

    @GetMapping("/index")
    public String index(){
        LOGGER.info("进入首屏");
        return "index";
    }
}
