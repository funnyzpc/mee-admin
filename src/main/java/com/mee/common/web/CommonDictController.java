package com.mee.common.web;

import com.mee.common.service.CommonDictServiceImpl;
import com.mee.sys.entity.SysDict;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/common/dict")
public class CommonDictController {

    @Resource
    private CommonDictServiceImpl commonDictService;


    @PostMapping("/list")
    public List<SysDict> list(String catalogCode){
        return commonDictService.list();
    }

}
