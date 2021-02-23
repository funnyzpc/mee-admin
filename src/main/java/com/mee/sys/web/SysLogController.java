package com.mee.sys.web;

import com.mee.common.util.ResultBuild;
import com.mee.common.util.excel.ExcelWriteUtil;
import com.mee.core.dao.DBSQLDao;
import com.mee.sys.entity.SysLog;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author funnyzpc
 * @description 日志管理
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController {
    private static final Logger log = LoggerFactory.getLogger(SysLogController.class);

    @Autowired
    private DBSQLDao dbSQLDao;

    @RequiresPermissions("040202")
    @GetMapping
    public String index(){
        return "sys/log";
    }

    @RequiresPermissions("040202")
    @PostMapping
    @ResponseBody
    public Map<String,Object> list(String log_type,String log_title,String log_date,int pageIdx, int pageSize){
        log.info("开始查询数据 pageIdx:{}, pageSize:{}",pageIdx,pageSize);
        Map<String,Object> queryParam = new HashMap<String,Object>(2,1);
        if(!StringUtils.isEmpty(log_type)){queryParam.put("log_type",log_type);}
        if(!StringUtils.isEmpty(log_date)){queryParam.put("log_date",log_date);}
        if(!StringUtils.isEmpty(log_title)){queryParam.put("log_title",log_title+"%");}
        return new HashMap<String,Object>(1,1){{
            put("data",dbSQLDao.list("com.mee.xml.SysLog.findList",queryParam,pageIdx,pageSize));
        }};
    }

    @RequiresPermissions("040202")
    @PostMapping("/delete")
    @ResponseBody
    public Map<String,Object> delete(String id){
        Map<String,Object> params = new HashMap<String,Object>(1,1){{
            put("id",id);
        }};
        int deleteCount = dbSQLDao.delete("com.mee.xml.SysLog.delete",params);
        if(deleteCount>0){
            return ResultBuild.SUCCESS;
        }
        log.error("文件删除失败 id:{}",id);
        return ResultBuild.fail("删除失败");
    }

    /** 导出 **/
    public static final String[] data_field = {"log_type_desc","log_title","log_date","remote_address","log_content"};
    public static final String[] header_field= {"日志类型","日志标题","日志时间","远程地址","日志内容"};
    // public static final CellFmt[] field_format = {null,null,null,CellFmt.CUSTOM_02,CellFmt.CUSTOM_02,null,null,CellFmt.NUMERIC_02};

    @RequiresPermissions("040202")
    @GetMapping("/export")
    public void export(HttpServletResponse response,int pageIdx, int pageSize){
        log.info("开始查询数据 pageIdx:{}, pageSize:{}",pageIdx,pageSize);
        List<SysLog> dataList = dbSQLDao.list("com.mee.xml.SysLog.findList",new HashMap<String,Object>(),pageIdx,pageSize).getData();
        dataList.parallelStream().forEach(item->{
            item.setLog_type_desc(1==item.getLog_type()?"登录":"其它");
        });
        File file = ExcelWriteUtil.toXlsxByObj(dataList,header_field,data_field);
        ResultBuild.toResponse(response,file);
    }
}