package com.mee.sys.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.mee.sys.entity.SysRoleUser;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 角色用户VO
 *
 * @author shadow
 * @version v1.0
 * @className SysRoleUser2VO
 * @date 2023/5/30 9:13 PM
 */
public class SysRoleUserVO extends SysRoleUser implements Serializable {

    /**
     * 序列化标识
     */
    private static final long serialVersionUID = 1L;


    /**
     * 用户名称
     */
    private String user_name;
    /**
     * 用户昵称
     */
    private String nick_name;
    /**
     * 注册时间
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime register_date;

    private String status;
    private String del_flag;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public LocalDateTime getRegister_date() {
        return register_date;
    }

    public void setRegister_date(LocalDateTime register_date) {
        this.register_date = register_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(String del_flag) {
        this.del_flag = del_flag;
    }

    @Override
    public String toString() {
        return "SysRoleUser2VO{" +
                "user_name='" + user_name + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", register_date=" + register_date +
                ", status='" + status + '\'' +
                ", del_flag='" + del_flag + '\'' +
                '}';
    }
}
