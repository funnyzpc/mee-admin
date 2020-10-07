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
}
