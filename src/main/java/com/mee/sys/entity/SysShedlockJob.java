package com.mee.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 集群分佈式鎖-任务配置对象 sys_shedlock_job
 * 
 * @author  ash
 * @version v1.1
 * @date    2024-06-18 16:20:27
 */
public class SysShedlockJob implements Serializable{

  /**
  * 序列化标识
   */
  private static final long serialVersionUID = 1L;


  /**
  * 当前实例应用
  */
  private String application;

  /**
  * 任務名稱(即ID)
  */
  private String name;

  /**
  * 当前实例应用所属IP
  */
  private String host_ip;

  /**
  * 任務開始鎖定
  */
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime locked_at;

  /**
  * 任務鎖定至
  */
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime lock_until;

  /**
  * 任務執行人
  */
  private String locked_by;

  /**
  * 0.close关闭 1.open开启(默认) 局部限制标志位(只限制所有相同实例中的此任务)
  */
  private String state;

  /**
  * job传入数据
  */
  private String data;

  /**
  * 任務標識
  */
  private String label;
  /**
   * 任务执行时间类型
   */
  private String call_type;
  /**
   * 任务执行时间value
   */
  private String call_value;
  /**
   * 下一次执行时间
   */
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime call_next;

  /**
  * 最后更新时间(也即最近一次执行时间/获取锁时间)
  */
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime update_time;

  public String getApplication() {
    return application;
  }

  public SysShedlockJob setApplication(String application) {
    this.application=application;
    return this;
  }
  public String getName() {
    return name;
  }

  public SysShedlockJob setName(String name) {
    this.name=name;
    return this;
  }
  public String getHost_ip() {
    return host_ip;
  }

  public SysShedlockJob setHost_ip(String host_ip) {
    this.host_ip=host_ip;
    return this;
  }
  public LocalDateTime getLocked_at() {
    return locked_at;
  }

  public SysShedlockJob setLocked_at(LocalDateTime locked_at) {
    this.locked_at=locked_at;
    return this;
  }
  public LocalDateTime getLock_until() {
    return lock_until;
  }

  public SysShedlockJob setLock_until(LocalDateTime lock_until) {
    this.lock_until=lock_until;
    return this;
  }
  public String getLocked_by() {
    return locked_by;
  }

  public SysShedlockJob setLocked_by(String locked_by) {
    this.locked_by=locked_by;
    return this;
  }
  public String getState() {
    return state;
  }

  public SysShedlockJob setState(String state) {
    this.state=state;
    return this;
  }
  public String getData() {
    return data;
  }

  public SysShedlockJob setData(String data) {
    this.data=data;
    return this;
  }
  public String getLabel() {
    return label;
  }

  public SysShedlockJob setLabel(String label) {
    this.label=label;
    return this;
  }
  public LocalDateTime getUpdate_time() {
    return update_time;
  }

  public SysShedlockJob setUpdate_time(LocalDateTime update_time) {
    this.update_time=update_time;
    return this;
  }

  public String getCall_type() {
    return call_type;
  }

  public void setCall_type(String call_type) {
    this.call_type = call_type;
  }

  public String getCall_value() {
    return call_value;
  }

  public void setCall_value(String call_value) {
    this.call_value = call_value;
  }

  public LocalDateTime getCall_next() {
    return call_next;
  }

  public void setCall_next(LocalDateTime call_next) {
    this.call_next = call_next;
  }

  @Override
  public String toString() {
    return "SysShedlockJob{" +
            "application='" + application + '\'' +
            ", name='" + name + '\'' +
            ", host_ip='" + host_ip + '\'' +
            ", locked_at=" + locked_at +
            ", lock_until=" + lock_until +
            ", locked_by='" + locked_by + '\'' +
            ", state='" + state + '\'' +
            ", data='" + data + '\'' +
            ", label='" + label + '\'' +
            ", call_type='" + call_type + '\'' +
            ", call_value='" + call_value + '\'' +
            ", call_next=" + call_next +
            ", update_time=" + update_time +
            '}';
  }


}
