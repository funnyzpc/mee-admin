package com.mee.sys.service.impl;

import com.mee.common.util.DateUtil;
import com.mee.common.util.MeeResult;
import com.mee.common.util.ResultBuild;
import com.mee.core.dao.DBSQLDao;
import com.mee.core.model.Page;
import com.mee.sys.entity.SysFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件也无泪
 *
 * @author shadow
 * @version v1.0
 * @className SysFileServiceImpl
 * @date 2023/6/4 12:57 AM
 */
@Service
public class SysFileServiceImpl {
    private static final Logger LOG = LoggerFactory.getLogger(SysFileServiceImpl.class);

    /**
     * 默认文件目录
     */
    @Value("${mee.file.upload-dir}")
    private String file_base_dir;

    @Autowired
    private DBSQLDao dbSQLDao;

    public MeeResult list(String original_name, String name, String dts, String dte, int pageIdx, int pageSize){
        LOG.info("开始查询数据 pageIdx:{}, pageSize:{}",pageIdx,pageSize);
        Map<String,Object> queryParams = new HashMap<String,Object>(3,1);
        if(!StringUtils.isEmpty(original_name)){queryParams.put("original_name",original_name+"%");}
        if(!StringUtils.isEmpty(name)){queryParams.put("name",name+"%");}
        if(!StringUtils.isEmpty(dts)){queryParams.put("dts", LocalDateTime.parse(dts, DateUtil.FORMAT_DAY_TIME));}
        if(!StringUtils.isEmpty(dte)){queryParams.put("dte",LocalDateTime.parse(dte, DateUtil.FORMAT_DAY_TIME));}
        Page list = dbSQLDao.list("com.mee.xml.SysFile.findList", queryParams, pageIdx, pageSize);
        return ResultBuild.build(list);
    }


    public MeeResult deleteById(String id) {
        LOG.info("开始查询:{}",id);
        if(null==id || "".equals(id)){
            LOG.error("必要参数为空:{}",id);
            return ResultBuild.fail("必要参数为空[id]");
        }

        Map<String,Object> param = new HashMap<>(4);
        param.put("id",id);
        SysFile sys_file = dbSQLDao.findOne("com.mee.xml.SysFile.findList", param);
        if( null==sys_file ){
            return ResultBuild.fail("未找到记录");
        }
        // 删除文件
        File file = new File(file_base_dir+File.separator + sys_file.getFile_path());
        file.delete();

        // 删除用户
        param.clear();
        param.put("id",id);
        int delete_count = dbSQLDao.delete("com.mee.xml.SysFile.delete", param);

        LOG.info("已删除用户{}=>{}条",id,delete_count);
        return ResultBuild.build( delete_count );
    }

}
