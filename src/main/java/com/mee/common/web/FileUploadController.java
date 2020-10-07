package com.mee.common.web;

import com.mee.common.util.DateUtil;
import com.mee.common.util.ResultBuild;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import java.io.File;
import java.util.Map;

/**
 * @author  funnyzpc
 * @description 上传文件测试
 */
//@RestController
public class FileUploadController {
    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);
    private static final String FILE_SYSTEM_SEPARATOR_KEY = "file.separator";

    private static final String file_base_path;
    static {
        if(System.getProperty(FILE_SYSTEM_SEPARATOR_KEY).equals("/")){
            file_base_path = "/tmp/";
        }else{
            file_base_path = "D:/tmp/";
        }
    }

    //@PostMapping("/fileUpload")
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
            File baseDirFile = new File(file_base_path+File.separator+today);
            if(!baseDirFile.exists()){
                baseDirFile.mkdirs();
            }
            // 上传文件名
            File file = new File(baseDirFile.getPath()+File.separator+System.currentTimeMillis()+fileSuffix);
            uploadFile.transferTo(file);
            log.info("已上传文件:{}",file.getName());
            // 返回信息
            return ResultBuild.SUCCESS;
        }catch(Exception ex){
            log.error("上传文件发生错误:",ex);
            return ResultBuild.FAIL;
        }
    }
}
