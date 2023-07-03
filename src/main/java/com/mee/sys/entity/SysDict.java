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
 * 数据字典对象 sys_dict2
 * 
 * @author  shadow
 * @version v1.0
 * @date    2023-05-15 10:27:36
 */
public class SysDict implements Serializable{

  /**
  * 序列化标识
   */
  private static final long serialVersionUID = 1L;

  /**
  * 主键(字典ID)
  */
  private String id;

  /**
  * 字典名称
  */
  private String name;

  /**
  * 描述
  */
  private String description;

  /**
  * 创建者
  */
  private Long create_by;

  /**
  * 更新者
  */
  private Long update_by;

  /**
  * 创建日期
  */
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime create_time;

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

  public SysDict setId(String id) {
    this.id=id;
    return this;
  }
  public String getName() {
    return name;
  }

  public SysDict setName(String name) {
    this.name=name;
    return this;
  }
  public String getDescription() {
    return description;
  }

  public SysDict setDescription(String description) {
    this.description=description;
    return this;
  }
  public Long getCreate_by() {
    return create_by;
  }

  public SysDict setCreate_by(Long create_by) {
    this.create_by=create_by;
    return this;
  }
  public Long getUpdate_by() {
    return update_by;
  }

  public SysDict setUpdate_by(Long update_by) {
    this.update_by=update_by;
    return this;
  }
  public LocalDateTime getCreate_time() {
    return create_time;
  }

  public SysDict setCreate_time(LocalDateTime create_time) {
    this.create_time=create_time;
    return this;
  }
  public LocalDateTime getUpdate_time() {
    return update_time;
  }

  public SysDict setUpdate_time(LocalDateTime update_time) {
    this.update_time=update_time;
    return this;
  }

  @Override
  public String toString() {
      return "SysDict2::{"+
      "id:"+this.id+
      ", name:"+this.name+
      ", description:"+this.description+
      ", create_by:"+this.create_by+
      ", update_by:"+this.update_by+
      ", create_time:"+this.create_time+
      ", update_time:"+this.update_time+
      "}";
  }
}
