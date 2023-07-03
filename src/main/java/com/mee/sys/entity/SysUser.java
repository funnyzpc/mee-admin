package com.mee.sys.entity;

import java.lang.String;
import java.lang.Integer;
import java.io.Serializable;
import java.lang.Long;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * 系统::用户信息表对象 sys_user2
 * 
 * @author  shadow
 * @version v1.3
 * @date    2023-05-30 20:59:40
 */
public class SysUser implements Serializable{

  /**
  * 序列化标识
   */
  private static final long serialVersionUID = 1L;

  /**
  * 表ID/用户ID
  */
  private String id;

  /**
  * 部门ID(保留字段暂不使用)
  */
  private Long dept_id;

  /**
  * 用户名称
  */
  private String user_name;

  /**
  * 用户昵称
  */
  private String nick_name;

  /**
  * M.男 F.女
  */
  private String gender;

  /**
  * 手机号码
  */
  private String phone;

  /**
  * 用户email(可用于登陆)
  */
  private String email;

  /**
  * 用户密码 序列化需要被忽略
  */
  @JsonIgnore
  private transient String password;

  /**
  * 用户注册时间
  */
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime register_date;

  /**
  * 最后登录时间
  */
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime last_login_date;

  /**
  * 密码最后重置时间
  */
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime pwd_reset_time;

  /**
  * 状态1.启用 0.禁用
  */
  private String status;

  /**
  * 删除标记1.正常 0.删除
  */
  private Integer del_flag;

  /**
  * 创建时间
  */
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime create_time;

  /**
  * 创建人
  */
  private String create_by;

  /**
  * 创建时间
  */
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime update_time;

  /**
  * 创建人
  */
  private String update_by;


  public String getId() {
    return id;
  }

  public SysUser setId(String id) {
    this.id=id;
    return this;
  }
  public Long getDept_id() {
    return dept_id;
  }

  public SysUser setDept_id(Long dept_id) {
    this.dept_id=dept_id;
    return this;
  }
  public String getUser_name() {

    return (null==this.user_name || "".equals(this.user_name.trim()))?null:this.user_name.trim();
  }

  public SysUser setUser_name(String user_name) {
    this.user_name=user_name;
    return this;
  }
  public String getNick_name() {
    return nick_name;
  }

  public SysUser setNick_name(String nick_name) {
    this.nick_name=nick_name;
    return this;
  }
  public String getGender() {
    return gender;
  }

  public SysUser setGender(String gender) {
    this.gender=gender;
    return this;
  }
  public String getPhone() {
    return phone;
  }

  public SysUser setPhone(String phone) {
    this.phone=phone;
    return this;
  }
  public String getEmail() {
    return email;
  }

  public SysUser setEmail(String email) {
    this.email=email;
    return this;
  }
  public String getPassword() {
    return password;
  }

  public SysUser setPassword(String password) {
    this.password=password;
    return this;
  }
  public LocalDateTime getRegister_date() {
    return register_date;
  }

  public SysUser setRegister_date(LocalDateTime register_date) {
    this.register_date=register_date;
    return this;
  }
  public LocalDateTime getLast_login_date() {
    return last_login_date;
  }

  public SysUser setLast_login_date(LocalDateTime last_login_date) {
    this.last_login_date=last_login_date;
    return this;
  }
  public LocalDateTime getPwd_reset_time() {
    return pwd_reset_time;
  }

  public SysUser setPwd_reset_time(LocalDateTime pwd_reset_time) {
    this.pwd_reset_time=pwd_reset_time;
    return this;
  }
  public String getStatus() {
    return status;
  }

  public SysUser setStatus(String status) {
    this.status=status;
    return this;
  }
  public Integer getDel_flag() {
    return del_flag;
  }

  public SysUser setDel_flag(Integer del_flag) {
    this.del_flag=del_flag;
    return this;
  }
  public LocalDateTime getCreate_time() {
    return create_time;
  }

  public SysUser setCreate_time(LocalDateTime create_time) {
    this.create_time=create_time;
    return this;
  }
  public String getCreate_by() {
    return create_by;
  }

  public SysUser setCreate_by(String create_by) {
    this.create_by=create_by;
    return this;
  }
  public LocalDateTime getUpdate_time() {
    return update_time;
  }

  public SysUser setUpdate_time(LocalDateTime update_time) {
    this.update_time=update_time;
    return this;
  }
  public String getUpdate_by() {
    return update_by;
  }

  public SysUser setUpdate_by(String update_by) {
    this.update_by=update_by;
    return this;
  }

  @Override
  public String toString() {
      return "SysUser2::{"+
      "id:"+this.id+
      ", dept_id:"+this.dept_id+
      ", user_name:"+this.user_name+
      ", nick_name:"+this.nick_name+
      ", gender:"+this.gender+
      ", phone:"+this.phone+
      ", email:"+this.email+
      ", password:"+this.password+
      ", register_date:"+this.register_date+
      ", last_login_date:"+this.last_login_date+
      ", pwd_reset_time:"+this.pwd_reset_time+
      ", status:"+this.status+
      ", del_flag:"+this.del_flag+
      ", create_time:"+this.create_time+
      ", create_by:"+this.create_by+
      ", update_time:"+this.update_time+
      ", update_by:"+this.update_by+
      "}";
  }
}
