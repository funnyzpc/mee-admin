package com.mee.sys.entity;

import com.mee.common.entity.BaseEntity;
/**
 * @author funnyzpc
 * @description 菜单表
 */
public class SysMenu extends BaseEntity {

    private String name;
    private String code;
    private String parent_code;
    private int del_flag;
    private int show_flag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getParent_code() {
        return parent_code;
    }

    public void setParent_code(String parent_code) {
        this.parent_code = parent_code;
    }

    public int getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(int del_flag) {
        this.del_flag = del_flag;
    }

    public int getShow_flag() {
        return show_flag;
    }

    public void setShow_flag(int show_flag) {
        this.show_flag = show_flag;
    }
}
