package com.mee.common.util;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/***
 * @author funnyzpc
 * @description 静态结构体(用于返回固定参数)
 */
public class ResultBuild {
    private static final Logger log = LoggerFactory.getLogger(ResultBuild.class);
    /**
     * 状态值定义(status)：
     *      0:  失败
     *      1:  成功
     *      2:  padding
     */

    /** 保存成功 **/
    public static final Map<String,Object> SUCCESS =  new HashMap<String,Object>(2,1){{
        put("status",1);
        put("msg","成功");
    }};

    /** 保存失败 **/
    public static final HashMap<String,Object> FAIL =  new HashMap<String,Object>(2,1){{
        put("status",0);
        put("msg","失败");
    }};

    /** 自定义失败内容 **/
    public static Map<String,Object> fail(String msg){
        return new HashMap<String,Object>(3,1){{
            put("status",0);
            put("msg", StringUtils.isEmpty(msg)?"失败":msg);
            put("timestamp",System.currentTimeMillis());
        }};
    }


    /** 保存成功 **/
    public static final HashMap<String,Object> PADDING = new HashMap<String,Object>(2,1){{
        put("status",2);
        put("msg","处理中");
    }};

    public static void toResponse(HttpServletResponse response, File file) {
        toResponse(response,file,null);
    }
        /**
         * 文件写入至Response
         * @param response 响应
         * @param file  文件
         * @param fileName 下载文件名
         */
    public static void toResponse(HttpServletResponse response, File file, String fileName){
        if(null == fileName){
            fileName = file.getName();
        }
        try( OutputStream outputStream=response.getOutputStream()) {
            /**
             * 设置attachment ，防止下载文件出现未知文件类型/文件名
             */
            response.reset();
            response.setContentType("application/octet-stream");
            response.addHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName,"UTF-8"));
            outputStream.write(FileUtils.readFileToByteArray(file));
        }catch (Exception iOException){
            log.error("文件 下载失败! ",iOException);
        }finally {
            //文件删除
            file.delete();
        }
    }

}
