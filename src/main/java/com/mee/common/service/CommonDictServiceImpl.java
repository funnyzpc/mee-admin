package com.mee.common.service;

import com.mee.core.dao.DBSQLDao;
import com.mee.sys.entity.SysDict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 字典数据缓存及刷新处理，解决页面刷新字段不显示问题
 */
@Service
public class CommonDictServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(CommonDictServiceImpl.class);

    @Autowired
    private DBSQLDao dbSQLDao;

    private static volatile List<SysDict> DATA_LIST = null;

    // 缓存数据
    @PostConstruct
    public List<SysDict> list(){
        if(null==DATA_LIST){
            log.info("启动自动缓存字典...");
            return DATA_LIST= dbSQLDao.query("com.mee.xml.SysDict.findList");
        }
        return DATA_LIST;
    }

    // 刷新缓存
    @Async
    public void refreshDict(){
        DATA_LIST= dbSQLDao.query("com.mee.xml.SysDict.findList");
    }

}
