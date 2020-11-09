package com.mee.sys.entity;


import com.mee.common.entity.BaseEntity;

/**
 * @author funnyzpc
 * @description 菜单表
 */
public class SysRoleMenu extends BaseEntity {

    // private String id;
    private String role_id;
    private String menu_code;

    // 扩展字段
    private String role_name;
    private String role_desc;
    private String name;
    private String code;

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getMenu_code() {
        return menu_code;
    }

    public void setMenu_code(String menu_code) {
        this.menu_code = menu_code;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getRole_desc() {
        return role_desc;
    }

    public void setRole_desc(String role_desc) {
        this.role_desc = role_desc;
    }

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
}
