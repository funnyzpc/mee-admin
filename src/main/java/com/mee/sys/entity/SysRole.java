package com.mee.sys.entity;

import java.lang.String;
import java.lang.Integer;
import java.io.Serializable;
import java.lang.Long;
import java.time.LocalDateTime;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * 系统::角色表对象 sys_role2
 * 
 * @author  shadow
 * @version v1.3
 * @date    2023-05-28 16:45:31
 */
public class SysRole implements Serializable{

  /**
  * 序列化标识
   */
  private static final long serialVersionUID = 1L;

  /**
  * 角色id
  */
  private String id;

  /**
  * 角色名称(en)
  */
  private String name;

  /**
  * 角色描述
  */
  private String description;

  /**
  * 角色状态 0.关闭 1.开启
  */
  private Integer status;

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

  /**
  * 更新人
  */
  private Long update_by;

  /**
  * 更新时间
  */
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime update_time;


  public String getId() {
    return id;
  }

  public SysRole setId(String id) {
    this.id=id;
    return this;
  }
  public String getName() {
    return name;
  }

  public SysRole setName(String name) {
    this.name=name;
    return this;
  }
  public String getDescription() {
    return description;
  }

  public SysRole setDescription(String description) {
    this.description=description;
    return this;
  }
  public Integer getStatus() {
    return status;
  }

  public SysRole setStatus(Integer status) {
    this.status=status;
    return this;
  }
  public Long getCreate_by() {
    return create_by;
  }

  public SysRole setCreate_by(Long create_by) {
    this.create_by=create_by;
    return this;
  }
  public LocalDateTime getCreate_time() {
    return create_time;
  }

  public SysRole setCreate_time(LocalDateTime create_time) {
    this.create_time=create_time;
    return this;
  }
  public Long getUpdate_by() {
    return update_by;
  }

  public SysRole setUpdate_by(Long update_by) {
    this.update_by=update_by;
    return this;
  }
  public LocalDateTime getUpdate_time() {
    return update_time;
  }

  public SysRole setUpdate_time(LocalDateTime update_time) {
    this.update_time=update_time;
    return this;
  }

  @Override
  public String toString() {
      return "SysRole2::{"+
      "id:"+this.id+
      ", name:"+this.name+
      ", description:"+this.description+
      ", status:"+this.status+
      ", create_by:"+this.create_by+
      ", create_time:"+this.create_time+
      ", update_by:"+this.update_by+
      ", update_time:"+this.update_time+
      "}";
  }
}
