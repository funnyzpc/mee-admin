package com.mee.sys.web;

import com.mee.common.util.ResultBuild;
import com.mee.core.dao.DBSQLDao;
import com.mee.sys.entity.SysDict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author funnyzpc
 * @description 字典管理
 */
@Controller
@RequestMapping("/sys/dict")
public class SysDictController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysDictController.class);

    @Autowired
    private DBSQLDao dbsqlDao;

    @GetMapping
    public String index(){
        return "sys/dict";
    }

    @PostMapping
    @ResponseBody
    public Map<String,Object> list(int pageIdx, int pageSize){
        return new HashMap<String,Object>(1,1){{
            put("data",dbsqlDao.list("com.mee.xml.SysDict.findList",new HashMap<String,Object>(),pageIdx,pageSize));
        }};
    }

    @RequestMapping("/save")
    @ResponseBody
    public Map save(SysDict sysDict){
       // 参数校验
       if(null == sysDict || null==sysDict.getSeries() || null==sysDict.getSeries_desc() || null == sysDict.getKey() || null == sysDict.getValue()){
            return ResultBuild.FAIL;
       }
       if(null == sysDict.getId() || "".equals(sysDict.getId().trim())){
           // 插入数据
           String recordId = dbsqlDao.create("com.mee.xml.SysDict.insert",sysDict);
           LOGGER.info("创建sys_dict结果:{}",recordId);
           return ResultBuild.SUCCESS;
       }
        // 更新数据
       int updateCount = dbsqlDao.update("com.mee.xml.SysDict.update",sysDict);
       LOGGER.info("更新sys_dict结果:{}",updateCount);
       return ResultBuild.SUCCESS;
    }


    @RequestMapping("/delete")
    @ResponseBody
    public Map delete(String id){
        // 参数校验
        if(null == id || "".equals(id.trim())){
            return ResultBuild.FAIL;
        }
        Map<String,Object> params = new HashMap<String,Object>(1,1){{
           put("id",id.trim());
        }};
        // 删除数据(标记为删除)
        if(id.contains(",")){
            Arrays.stream(id.split(",")).forEach(item->{
                params.put("id",item);
                int recordCount = dbsqlDao.delete("com.mee.xml.SysDict.delete",params);
                LOGGER.info("删除sys_dict结果:{}",recordCount);
            });
            return ResultBuild.SUCCESS;
        }
        int recordCount = dbsqlDao.delete("com.mee.xml.SysDict.delete",params);
        LOGGER.info("删除sys_dict结果:{}",recordCount);
        return ResultBuild.SUCCESS;
    }

}
