package com.mee.sys.entity;

import java.lang.String;
import java.io.Serializable;
import java.lang.Long;
import java.time.LocalDateTime;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * 系统::角色用户表对象 sys_role_user2
 * 
 * @author  shadow
 * @version v1.3
 * @date    2023-05-28 16:45:32
 */
public class SysRoleUser implements Serializable{

  /**
   * 序列化标识
   */
  private static final long serialVersionUID = 1L;

  /**
   * id
   */
  private String id;

  /**
   * 用户ID
   */
  private String user_id;

  /**
   * 角色ID
   */
  private String role_id;

  /**
   * 创建人
   */
  private Long create_by;

  /**
   * 创建时间
   */
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime create_time;


  public String getId() {
    return id;
  }

  public SysRoleUser setId(String id) {
    this.id=id;
    return this;
  }
  public String getUser_id() {
    return user_id;
  }

  public SysRoleUser setUser_id(String user_id) {
    this.user_id=user_id;
    return this;
  }
  public String getRole_id() {
    return role_id;
  }

  public SysRoleUser setRole_id(String role_id) {
    this.role_id=role_id;
    return this;
  }
  public Long getCreate_by() {
    return create_by;
  }

  public SysRoleUser setCreate_by(Long create_by) {
    this.create_by=create_by;
    return this;
  }
  public LocalDateTime getCreate_time() {
    return create_time;
  }

  public SysRoleUser setCreate_time(LocalDateTime create_time) {
    this.create_time=create_time;
    return this;
  }

  @Override
  public String toString() {
    return "SysRoleUser::{"+
            "id:"+this.id+
            ", user_id:"+this.user_id+
            ", role_id:"+this.role_id+
            ", create_by:"+this.create_by+
            ", create_time:"+this.create_time+
            "}";
  }

}
