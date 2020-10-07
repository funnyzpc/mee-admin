package com.mee.sys.entity;

import com.mee.common.entity.BaseEntity;

/**
 * @author funnyzpc
 * @description 上传文件管理
 */
public class SysFile extends BaseEntity {

    //private String id;
    private String original_name; // 上传文件名(原始文件名)
    private String name;//保存文件名
    private String file_path;//文件目录地址
    private String file_type;//文件类型 .jpg、.pdf、xls、xlsx
    private String category;// 文件分类
    //private String create_date;
    //private String create_by;

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
}
