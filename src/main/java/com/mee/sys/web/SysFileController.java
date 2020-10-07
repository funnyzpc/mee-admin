package com.mee.sys.web;


import com.mee.common.util.DateUtil;
import com.mee.common.util.ResultBuild;
import com.mee.core.dao.DBSQLDao;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.File;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author funnyzpc
 * @description 上传文件管理
 */
@Controller
@RequestMapping("/sys/file")
public class SysFileController  {
    private static final Logger log = LoggerFactory.getLogger(SysFileController.class);

    @Value("${mee.file.upload-dir}")
    private String upload_dir;

    @Autowired
    private DBSQLDao dbsqlDao;

    @RequiresPermissions("040204")
    @GetMapping
    public String index(){
        return "sys/file";
    }

    @RequiresPermissions("040204")
    @PostMapping
    @ResponseBody
    public Map<String,Object> list(String original_name, String name,String dts,String dte, int pageIdx, int pageSize){
        log.info("开始查询数据 pageIdx:{}, pageSize:{}",pageIdx,pageSize);
        Map<String,Object> queryParams = new HashMap<String,Object>(3,1);
        if(!StringUtils.isEmpty(original_name)){queryParams.put("original_name",original_name+"%");}
        if(!StringUtils.isEmpty(name)){queryParams.put("name",name+"%");}
        if(!StringUtils.isEmpty(dts)){queryParams.put("dts", LocalDateTime.parse(dts, DateUtil.FORMAT_DAY_TIME));}
        if(!StringUtils.isEmpty(dte)){queryParams.put("dte",LocalDateTime.parse(dte, DateUtil.FORMAT_DAY_TIME));}
        return new HashMap<String,Object>(1,1){{
            put("data",dbsqlDao.list("com.mee.xml.SysFile.findList",queryParams,pageIdx,pageSize));
        }};
    }

    @RequiresPermissions("040204")
    @PostMapping("/import")
    private Map<String,Object> uploadFile(MultipartRequest request) {
        try{
            MultipartFile uploadFile =request.getFile("file");
            // 是否存在
            if(uploadFile.isEmpty()){
                return ResultBuild.fail("上传文件为空");
            }
            // 构建目录
            String today = DateUtil.now().format(DateUtil.FORMAT_DAY);
            String originalFilename = uploadFile.getOriginalFilename();
            String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 上传目录
            File baseDirFile = new File(upload_dir+File.separator+today);
            if(!baseDirFile.exists()){
                baseDirFile.mkdirs();
            }
            // 上传文件名
            File file = new File(baseDirFile.getPath()+File.separator+System.currentTimeMillis()+fileSuffix);
            uploadFile.transferTo(file);

            // TODO 上传文件归档信息写入DB
            log.info("已上传文件:{}",file.getName());
            // 返回信息
            return ResultBuild.SUCCESS;
        }catch(Exception ex){
            log.error("上传文件发生错误:",ex);
            return ResultBuild.FAIL;
        }
    }

}
