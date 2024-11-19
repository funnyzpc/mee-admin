package com.mee.common.util;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;


/**
*   响应体
*   @className  ResultBuild
*   @author     shadow
*   @date       2023/6/21 9:05 PM
*   @version    v1.0
*/
public class ResultBuild {

    /**
     * 日志
     */
    private static final Logger LOG = LoggerFactory.getLogger(ResultBuild.class);

    /*********************
     * 状态值定义(status)：
     *      0:  失败
     *      1:  成功
     *      2:  padding
     * ********************
     */

    /**
     * 成功
     */
    private static final Integer SUCCESS = 1;
    /**
     * 失败
     */
    private static final Integer FAIL = 0;
    /**
     * 处理中
     */
    private static final Integer PADDING = 2;


    /**
     * 默认成功
     * @return
     */
    public static final MeeResult SUCCESS(){
        return new MeeResult<Void>();
    }

    /**
     * 成功
     * @param msg
     * @return
     */
    public static MeeResult success(String msg){
        return new MeeResult<Void>().setMsg( msg );
    }

    /**
     * 默认成功
     * @param data 数据
     * @return
     * @param <D> 返回结果范型
     */
    public static <D extends Object> MeeResult<D> build(D data){
        return new MeeResult<D>().setData(data);
    }

    /**
     * 自定义构造可成功可失败可padding
     * @param status 状态
     * @param msg   消息
     * @param data  数据
     * @return
     * @param <D>
     */
    public static <D extends Object> MeeResult<D> build(Integer status,String msg,D data){
        return new MeeResult<D>().setStatus(status).setMsg(msg).setData(data);
    }

    /** 保存失败 **/
    public static MeeResult FAIL() {
        return new MeeResult<Void>().setStatus(FAIL).setMsg("fail").setTimestamp(System.currentTimeMillis());
    }

    /** 自定义失败内容 **/
    public static MeeResult fail(String msg){
        return new MeeResult<Void>().setStatus(FAIL).setMsg(msg).setTimestamp(System.currentTimeMillis());
    }

    /**
     * 处理中/进行中
     *
     * **/
    public static MeeResult<Void> PADDING(){
        return new MeeResult<Void>().setStatus(PADDING).setMsg("process").setTimestamp(System.currentTimeMillis());
    }

    /**
     * 处理中/进行中
     *
     * **/
    public static MeeResult<Void> padding(String msg){
        return new MeeResult<Void>().setStatus(PADDING).setMsg(msg).setTimestamp(System.currentTimeMillis());
    }

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
            String file_name = new String(fileName.getBytes(StandardCharsets.UTF_8),"ISO-8859-1");
            /**
             * 设置attachment ，防止下载文件出现未知文件类型/文件名
             */
            response.reset();
            response.setHeader("content-type","application/octet-stream;charset=utf-8");
            //response.setContentType("application/octet-stream");
            //response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName,"UTF-8"));
            // 只有此种方式是成功的其他方式均失败！
            response.setHeader("Content-Disposition", "attachment;filename=\"" + file_name + "\"");
            outputStream.write(FileUtils.readFileToByteArray(file));
        }catch (Exception iOException){
            LOG.error("文件 下载失败! ",iOException);
        }finally {
            //文件删除
            file.delete();
        }
    }

    /**
     * 文件写入至Response
     * @param response 响应
     * @param fileName 下载文件名
     */
    public static void toResponse(HttpServletResponse response,String fileName){
        try {
            String file_name = new String(fileName.getBytes(StandardCharsets.UTF_8),"ISO-8859-1");
            /**
             * 设置attachment ，防止下载文件出现未知文件类型/文件名
             */
            response.reset();
            response.setHeader("content-type","application/octet-stream;charset=utf-8");
            //response.setContentType("application/octet-stream");
            //response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName,"UTF-8"));
            // 只有此种方式是成功的其他方式均失败！
            response.setHeader("Content-Disposition", "attachment;filename=\"" + file_name + "\"");
        }catch (Exception iOException){
            LOG.error("文件 下载失败! ",iOException);
        }
    }

}
