package com.mee.common.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;

/**
* 公共文件下载
* @className    CommonFileController
* @author       shadow
* @date         2023/7/3 14:19
* @version      1.0
*/
@Controller
@RequestMapping("/common/file")
public class CommonFileController {
    private static final Logger log = LoggerFactory.getLogger(CommonFileController.class);

    @Value("${mee.file.upload-dir}")
    private String file_base_dir;

    /**
     * 文件下载
     * @param response 响应体
     * @param filePath 文件路径
     * @param fileName 文件名称
     */
    @GetMapping
    public void download(HttpServletResponse response,String filePath,String fileName) {
        File file = new File(file_base_dir+File.separator + filePath);
        if (file.exists()) {
            try(InputStream inputStream = new BufferedInputStream(new FileInputStream(file))){
                String downloadFileName = URLEncoder.encode(StringUtils.isEmpty(fileName)?file.getName():fileName,"UTF-8");
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition","attachment;filename=\"" +downloadFileName+ "\"");
                response.setContentLength((int) file.length());
                FileCopyUtils.copy(inputStream, response.getOutputStream());
            }catch (Exception e){
                log.error("文件读取失败:");
            }
        }else{
            // file not found
            // response.setStatus(HttpStatus.NOT_FOUND.value());
            log.error("文件不存在 filePath:{},fileName:{}",filePath,fileName);
            response.setStatus(HttpStatus.NOT_FOUND.value());
            // response.setIntHeader("文件不存在:"+filePath,HttpStatus.NOT_FOUND.value());
        }
    }
}
