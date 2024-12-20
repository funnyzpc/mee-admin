package com.mee.common.web;

import com.mee.common.service.impl.IndexServiceImpl;
import com.mee.common.util.MeeResult;
import com.mee.sys.vo.SysMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
* home主页
* @className    IndexController
* @author       shadow
* @date         2023/7/3 14:20
* @version      1.0
*/
@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    public IndexServiceImpl indexService;

    /**
     * 主页
     * @return 主页页面
     */
    @RequestMapping
    public String index(){
        return "index";
    }

    /**
     * 获取菜单树
     *
     * @return MeeResult<List<SysMenuVO>>
     */
    @GetMapping(value = "menu",produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MeeResult<List<SysMenuVO>> menu(){
        return indexService.buildMenu();
    }

}
