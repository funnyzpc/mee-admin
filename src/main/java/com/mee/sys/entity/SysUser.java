package com.mee.sys.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.mee.common.entity.BaseEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;


/**
 * @author funnyzpc
 * @description 菜单表
 */
public class SysUser extends BaseEntity {

    // private String id;
    // 用户编号
    private String user_id;
    // 昵称
    private String nick_name;
    // 登陆用户名 user_name
    private String user_name;
    private String username;
    // email(可用于登陆用户名)
    private String email;
    // 登陆密码
    private String password;
    // 权限编号
    private String role_id;
    // 用户状态 0:无效 1:有效 2:未激活(绑定邮箱)
    private Integer status;
    // 注册日期时间
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime register_date;
    // 最后登陆时间
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime last_login_date;

    /** 扩展:一个用户具有多个角色 **/
    private List<SysRole> role_list;

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getRegister_date() {
        return register_date;
    }

    public void setRegister_date(LocalDateTime register_date) {
        this.register_date = register_date;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<SysRole> getRole_list() {
        return role_list;
    }

    public void setRole_list(List<SysRole> role_list) {
        this.role_list = role_list;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getLast_login_date() {
        return last_login_date;
    }

    public void setLast_login_date(LocalDateTime last_login_date) {
        this.last_login_date = last_login_date;
    }
}
