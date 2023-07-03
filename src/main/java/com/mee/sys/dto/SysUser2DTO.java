package com.mee.sys.dto;

import com.mee.sys.entity.SysUser;

import java.io.Serializable;

/**
 * 用户新增对象
 *
 * @author shadow
 * @version v1.0
 * @className SysUser2DTO
 * @date 2023/5/31 10:04 PM
 */
public class SysUser2DTO extends SysUser implements Serializable {


    /**
     * 序列化标识
     */
    private static final long serialVersionUID = 1L;

    /**
     * 老密码
     */
    private String old_pwd;

    /**
     * 用户密码
     */
    private String pwd1;
    /**
     * 确认密码
     */
    private String pwd2;


    public String getPwd1() {
        return pwd1;
    }

    public void setPwd1(String pwd1) {
        this.pwd1 = pwd1;
    }

    public String getPwd2() {
        return pwd2;
    }

    public void setPwd2(String pwd2) {
        this.pwd2 = pwd2;
    }

    public String getOld_pwd() {
        return old_pwd;
    }

    public void setOld_pwd(String old_pwd) {
        this.old_pwd = old_pwd;
    }

    @Override
    public String toString() {
        return "SysUser2DTO{" +
                "old_pwd='" + old_pwd + '\'' +
                ", pwd1='" + pwd1 + '\'' +
                ", pwd2='" + pwd2 + '\'' +
                "} " + super.toString();
    }
}
