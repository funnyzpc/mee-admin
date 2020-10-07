package com.mee.sys.entity;


import com.mee.common.entity.BaseEntity;

/**
 * @author funnyzpc
 * @description 菜单表
 */
public class SysRoleUser extends BaseEntity {

    // private String id;
    private String user_id;
    private String role_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }
}
