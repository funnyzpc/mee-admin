package com.mee.module.sv1.entity;

import java.lang.String;
import java.lang.Integer;
import java.io.Serializable;
import java.time.LocalDate;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.lang.Long;
import java.time.LocalDateTime;

/**
 * 测试::线下店铺对象 t_offline_store
 * 
 * @author  shadow
 * @version v1.0
 * @date    2023-06-16 11:25:46
 */
public class TOfflineStore implements Serializable{

  /**
  * 序列化标识
   */
  private static final long serialVersionUID = 1L;

  /**
  * 主键
  */
  private String id;

  /**
  * 开店时间
  */
  @JsonSerialize(using = LocalDateSerializer.class)
  @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
  @DateTimeFormat(pattern="yyyy-MM-dd")
  private LocalDate open_date;

  /**
  * 编号
  */
  private String code;

  /**
  * 名称
  */
  private String name;

  /**
  * 店铺简称或昵称
  */
  private String nick_name;

  /**
  * 店铺地址
  */
  private String addr;

  /**
  * 品牌(从字典获取)
  */
  private Integer brand;

  /**
  * 状态 0.关闭 1.开启
  */
  private Integer status;

  /**
  * 备注
  */
  private String description;

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

  public TOfflineStore setId(String id) {
    this.id=id;
    return this;
  }
  public LocalDate getOpen_date() {
    return open_date;
  }

  public TOfflineStore setOpen_date(LocalDate open_date) {
    this.open_date=open_date;
    return this;
  }
  public String getCode() {
    return code;
  }

  public TOfflineStore setCode(String code) {
    this.code=code;
    return this;
  }
  public String getName() {
    return name;
  }

  public TOfflineStore setName(String name) {
    this.name=name;
    return this;
  }
  public String getNick_name() {
    return nick_name;
  }

  public TOfflineStore setNick_name(String nick_name) {
    this.nick_name=nick_name;
    return this;
  }
  public String getAddr() {
    return addr;
  }

  public TOfflineStore setAddr(String addr) {
    this.addr=addr;
    return this;
  }
  public Integer getBrand() {
    return brand;
  }

  public TOfflineStore setBrand(Integer brand) {
    this.brand=brand;
    return this;
  }
  public Integer getStatus() {
    return status;
  }

  public TOfflineStore setStatus(Integer status) {
    this.status=status;
    return this;
  }
  public String getDescription() {
    return description;
  }

  public TOfflineStore setDescription(String description) {
    this.description=description;
    return this;
  }
  public Long getCreate_by() {
    return create_by;
  }

  public TOfflineStore setCreate_by(Long create_by) {
    this.create_by=create_by;
    return this;
  }
  public LocalDateTime getCreate_time() {
    return create_time;
  }

  public TOfflineStore setCreate_time(LocalDateTime create_time) {
    this.create_time=create_time;
    return this;
  }
  public Long getUpdate_by() {
    return update_by;
  }

  public TOfflineStore setUpdate_by(Long update_by) {
    this.update_by=update_by;
    return this;
  }
  public LocalDateTime getUpdate_time() {
    return update_time;
  }

  public TOfflineStore setUpdate_time(LocalDateTime update_time) {
    this.update_time=update_time;
    return this;
  }

  @Override
  public String toString() {
      return "TOfflineStore::{"+
      "id:"+this.id+
      ", open_date:"+this.open_date+
      ", code:"+this.code+
      ", name:"+this.name+
      ", nick_name:"+this.nick_name+
      ", addr:"+this.addr+
      ", brand:"+this.brand+
      ", status:"+this.status+
      ", description:"+this.description+
      ", create_by:"+this.create_by+
      ", create_time:"+this.create_time+
      ", update_by:"+this.update_by+
      ", update_time:"+this.update_time+
      "}";
  }
}
