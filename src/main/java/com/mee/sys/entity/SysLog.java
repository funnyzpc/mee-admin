package com.mee.sys.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author funnyzpc
 * @description 登录日志
 */
public class SysLog {

    private String id;
    private int log_type; // 日志类型 1.登录 2.异常 3.其它
    private String log_title;// 日志标题
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime log_date; // 日志发生时间
    private String remote_address; //用户IP
    private String log_content; // 日志内容
    // ext field
    private String log_type_desc;

/*    *//**
     * 创建人
     *//*
    private String create_by;*/

    public String getLog_type_desc() {
        return log_type_desc;
    }

    public void setLog_type_desc(String log_type_desc) {
        this.log_type_desc = log_type_desc;
    }

    public int getLog_type() {
        return log_type;
    }

    public void setLog_type(int log_type) {
        this.log_type = log_type;
    }

    public String getLog_title() {
        return log_title;
    }

    public void setLog_title(String log_title) {
        this.log_title = log_title;
    }

    public LocalDateTime getLog_date() {
        return log_date;
    }

    public void setLog_date(LocalDateTime log_date) {
        this.log_date = log_date;
    }

    public String getRemote_address() {
        return remote_address;
    }

    public void setRemote_address(String remote_address) {
        this.remote_address = remote_address;
    }

    public String getLog_content() {
        return log_content;
    }

    public void setLog_content(String log_content) {
        this.log_content = log_content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SysLog{" +
                "id='" + id + '\'' +
                ", log_type=" + log_type +
                ", log_title='" + log_title + '\'' +
                ", log_date=" + log_date +
                ", remote_address='" + remote_address + '\'' +
                ", log_content='" + log_content + '\'' +
                ", log_type_desc='" + log_type_desc + '\'' +
//                ", create_by='" + create_by + '\'' +
                '}';
    }


}
