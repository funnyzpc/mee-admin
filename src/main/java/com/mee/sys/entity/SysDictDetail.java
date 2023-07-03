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
 * 数据字典详情对象 sys_dict_detail2
 * 
 * @author  shadow
 * @version v1.0
 * @date    2023-05-15 10:27:31
 */
public class SysDictDetail implements Serializable{

  /**
  * 序列化标识
   */
  private static final long serialVersionUID = 1L;

  /**
  * 主键(字典项id)
  */
  private String id;

  /**
  * 字典id
  */
  private String dict_id;

  /**
  * 字典标签
  */
  private String label;

  /**
  * 字典值
  */
  private String value;

  /**
  * 排序
  */
  private Integer sort;

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

  public SysDictDetail setId(String id) {
    this.id=id;
    return this;
  }
  public String getDict_id() {
    return dict_id;
  }

  public SysDictDetail setDict_id(String dict_id) {
    this.dict_id=dict_id;
    return this;
  }
  public String getLabel() {
    return label;
  }

  public SysDictDetail setLabel(String label) {
    this.label=label;
    return this;
  }
  public String getValue() {
    return value;
  }

  public SysDictDetail setValue(String value) {
    this.value=value;
    return this;
  }

  public Integer getSort() {
    return sort;
  }

  public void setSort(Integer sort) {
    this.sort = sort;
  }

  public Long getCreate_by() {
    return create_by;
  }

  public SysDictDetail setCreate_by(Long create_by) {
    this.create_by=create_by;
    return this;
  }
  public Long getUpdate_by() {
    return update_by;
  }

  public SysDictDetail setUpdate_by(Long update_by) {
    this.update_by=update_by;
    return this;
  }
  public LocalDateTime getCreate_time() {
    return create_time;
  }

  public SysDictDetail setCreate_time(LocalDateTime create_time) {
    this.create_time=create_time;
    return this;
  }
  public LocalDateTime getUpdate_time() {
    return update_time;
  }

  public SysDictDetail setUpdate_time(LocalDateTime update_time) {
    this.update_time=update_time;
    return this;
  }

  @Override
  public String toString() {
      return "SysDictDetail2::{"+
      "id:"+this.id+
      ", dict_id:"+this.dict_id+
      ", label:"+this.label+
      ", value:"+this.value+
      ", sort:"+this.sort+
      ", create_by:"+this.create_by+
      ", update_by:"+this.update_by+
      ", create_time:"+this.create_time+
      ", update_time:"+this.update_time+
      "}";
  }
}
