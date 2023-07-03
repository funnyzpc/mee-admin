package com.mee.sys.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 角色用户DTO
 *
 * @author shadow
 * @version v1.0
 * @className SysRoleUser2DTO
 * @date 2023/6/5 10:00 PM
 */
public class SysRoleUserDTO implements Serializable {

    /**
     * 序列化标识
     */
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private String role_id;

    /**
     * 用户ID列表
     */
    private List<String> user_ids;

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public List<String> getUser_ids() {
        return user_ids;
    }

    public void setUser_ids(List<String> user_ids) {
        this.user_ids = user_ids;
    }

    @Override
    public String toString() {
        return "SysRoleUser2DTO{" +
                "role_id='" + role_id + '\'' +
                ", user_ids=" + user_ids +
                '}';
    }
}
