package com.mee.sys.service.impl;

import com.mee.common.util.MeeResult;
import com.mee.common.util.ResultBuild;
import com.mee.common.util.excel.ExcelWriteUtil;
import com.mee.core.dao.DBSQLDao;
import com.mee.core.model.Page;
import com.mee.sys.entity.SysLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登陆日志
 *
 * @author shadow
 * @version v1.0
 * @className SysLogServiceImpl
 * @date 2023/6/4 1:01 AM
 */
@Service
public class SysLogServiceImpl {

    private static final Logger LOG = LoggerFactory.getLogger(SysLogServiceImpl.class);

    @Autowired
    private DBSQLDao dbSQLDao;

    public MeeResult<Page<SysLog>> list(String log_type, String log_title, String log_date, int pageIdx, int pageSize){
        LOG.info("开始查询数据 pageIdx:{}, pageSize:{}",pageIdx,pageSize);
        Map<String,Object> queryParam = new HashMap<String,Object>(2,1);
        if(!StringUtils.isEmpty(log_type)){queryParam.put("log_type",log_type);}
        if(!StringUtils.isEmpty(log_date)){queryParam.put("log_date",log_date);}
        if(!StringUtils.isEmpty(log_title)){queryParam.put("log_title",log_title+"%");}
        Page<SysLog> list = dbSQLDao.list("com.mee.xml.SysLog.findList", queryParam, pageIdx, pageSize);
        return ResultBuild.build(list);
    }

    public MeeResult<Integer> delete(String id){
        Map<String,Object> params = new HashMap<String,Object>(2,1);
        params.put("id",id);
        int deleteCount = dbSQLDao.delete("com.mee.xml.SysLog.delete",params);
        if(deleteCount>0){
            return ResultBuild.build(deleteCount);
        }
        LOG.error("文件删除失败 id:{}",id);
        return ResultBuild.fail("删除失败");
    }

    /** 导出 **/
    public static final String[] DATA_FIELD = {"log_type_desc","log_title","log_date","remote_address","log_content"};
    public static final String[] HEADER_FIELD = {"日志类型","日志标题","日志时间","远程地址","日志内容"};

    public void export(HttpServletResponse response, int pageIdx, int pageSize){
        LOG.info("开始查询数据 pageIdx:{}, pageSize:{}",pageIdx,pageSize);
//        List<SysLog> dataList = dbSQLDao.list("com.mee.xml.SysLog.findList",new HashMap<String,Object>(),pageIdx,pageSize).getData();
        Page<SysLog> list = dbSQLDao.list("com.mee.xml.SysLog.findList", pageIdx, pageSize);
        List<SysLog> dataList = list.getData();
        dataList.parallelStream().forEach(item->{
            item.setLog_type_desc(1==item.getLog_type()?"登录":"其它");
        });
        File file = ExcelWriteUtil.toXlsxByObj(dataList, HEADER_FIELD, DATA_FIELD);
        ResultBuild.toResponse(response,file);
    }


}
