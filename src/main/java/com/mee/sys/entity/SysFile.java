package com.mee.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
* 上传文件
* @className    SysFile
* @author       shadow
* @date         2023/7/3 10:49
* @version      1.0
*/
public class SysFile  {

    private String id;
    private String original_name; // 上传文件名(原始文件名)
    private String name;//保存文件名
    private String file_path;//文件目录地址
    private String file_type;//文件类型 .jpg、.pdf、xls、xlsx
    private String category;// 文件分类

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime create_time;
    private String create_by;

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public String getFile_type() {
        return file_type;
    }

    public void setFile_type(String file_type) {
        this.file_type = file_type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    @Override
    public String toString() {
        return "SysFile{" +
                "id='" + id + '\'' +
                ", original_name='" + original_name + '\'' +
                ", name='" + name + '\'' +
                ", file_path='" + file_path + '\'' +
                ", file_type='" + file_type + '\'' +
                ", category='" + category + '\'' +
                ", create_time='" + create_time + '\'' +
                ", create_by='" + create_by + '\'' +
                '}';
    }
}
