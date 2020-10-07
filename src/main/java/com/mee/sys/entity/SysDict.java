package com.mee.sys.entity;

import com.mee.common.entity.BaseEntity;


/**
 * @author funnyzpc
 * @description 数据字典(菜单)
 */
public class SysDict extends BaseEntity {

    // private String id;

    /** 系列 example: enabled.status **/
    private String series;
    /** 系列描述 example:是否开启 **/
    private String series_desc;
    /** 字典名称 **/
    private String key;
    /** 字典内容 **/
    private String value;
    /** 字典描述 **/
    private String desc;
    /** 删除标记 **/
    private int del_flag;

    // private LocalDateTime create_date;
    // private String create_by;


//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

//    public LocalDateTime getCreate_date() {
//        return create_date;
//    }
//
//    public void setCreate_date(LocalDateTime create_date) {
//        this.create_date = create_date;
//    }
//
//    public String getCreate_by() {
//        return create_by;
//    }
//
//    public void setCreate_by(String create_by) {
//        this.create_by = create_by;
//    }

    public int getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(int del_flag) {
        this.del_flag = del_flag;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }


    public String getSeries_desc() {
        return series_desc;
    }

    public void setSeries_desc(String series_desc) {
        this.series_desc = series_desc;
    }
}
