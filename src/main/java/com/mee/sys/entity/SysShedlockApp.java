package com.mee.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 集群分佈式鎖-应用配置对象 sys_shedlock_app
 * 
 * @author  ash
 * @version v1.1
 * @date    2024-06-18 16:20:15
 */
public class SysShedlockApp implements Serializable{

  /**
  * 序列化标识
   */
  private static final long serialVersionUID = 1L;


  /**
  * 当前实例应用
  */
  @NotNull(message = "application不可为空")
  private String application;

  /**
  * 当前实例应用所属IP
  */
  @NotNull(message = "host_ip不可为空")
  private String host_ip;

  /**
  * 创建机器
  */
  private String host_name;

  /**
  * 状态 0.关闭 1.开启
  */
  private String state;
  /**
   * 备注信息
   */
  private String label;

  /**
  * 创建及更新时间
  */
  @JsonSerialize(using = LocalDateTimeSerializer.class)
  @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss")
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
  private LocalDateTime update_time;

  public String getApplication() {
    return application;
  }

  public SysShedlockApp setApplication(String application) {
    this.application=application;
    return this;
  }
  public String getHost_ip() {
    return host_ip;
  }

  public SysShedlockApp setHost_ip(String host_ip) {
    this.host_ip=host_ip;
    return this;
  }
  public String getHost_name() {
    return host_name;
  }

  public SysShedlockApp setHost_name(String host_name) {
    this.host_name=host_name;
    return this;
  }
  public String getState() {
    return state;
  }

  public SysShedlockApp setState(String state) {
    this.state=state;
    return this;
  }
  public LocalDateTime getUpdate_time() {
    return update_time;
  }

  public SysShedlockApp setUpdate_time(LocalDateTime update_time) {
    this.update_time=update_time;
    return this;
  }

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  @Override
  public String toString() {
    return "SysShedlockApp{" +
            "application='" + application + '\'' +
            ", host_ip='" + host_ip + '\'' +
            ", host_name='" + host_name + '\'' +
            ", state='" + state + '\'' +
            ", label='" + label + '\'' +
            ", update_time=" + update_time +
            '}';
  }

}
