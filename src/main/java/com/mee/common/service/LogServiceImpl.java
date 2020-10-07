package com.mee.common.service;


import com.mee.common.util.DateUtil;
import com.mee.core.dao.DBSQLDao;
import com.mee.sys.entity.SysLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author funnyzpc
 * @description 文件上传
 */
@Service
public class LogServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(LogServiceImpl.class);

    @Autowired
    private DBSQLDao dbsqlDao;

    /** 日志记录 **/
    @Async
    public void log(String user_id,int log_type,String log_title,String remote_address,String log_content){
        /*
        id
        log_type
        log_title
        log_date
        remote_address
        log_content
         */
        SysLog sysLog = new SysLog();
        sysLog.setLog_type(log_type);
        sysLog.setLog_title(log_title);
        sysLog.setLog_date(DateUtil.now());
        sysLog.setRemote_address(remote_address);
        sysLog.setLog_content(log_content);
        sysLog.setCreate_by(user_id);

        String id = dbsqlDao.create("com.mee.xml.SysLog.insert",sysLog);
        log.info("已记录 title:{},id:{}",log_title,id);
    }


}
