package com.mee.common.web;

import com.mee.common.service.impl.CommonDictServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
* 公共字典接口
* @className    CommonDictController
* @author       shadow
* @date         2023/5/15 10:03
* @version      1.0
*/
@RestController
@RequestMapping("/common/dict")
public class CommonDictController {

    @Resource
    private CommonDictServiceImpl commonDictService;

    /**
     * 获取指定字典数据
     * @param names 字典名称
     * @return 字典map
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Map getDicts(String[] names){
        //return commonDictService.getDicts(names);
        return commonDictService.getDicts2(names);
    }

}
