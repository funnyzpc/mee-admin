package com.mee.sys.entity;

import java.lang.String;
import java.lang.Integer;
import java.io.Serializable;
import java.time.LocalDateTime;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * 系统::新菜单表对象 sys_menu2
 * 
 * @author  shadow
 * @version v1.3
 * @date    2023-05-05 21:27:15
 */
public class SysMenu implements Serializable{
  private static final long serialVersionUID = 1L;
  /**
  * 菜单
  */
  private String id;

  /**
  * 父ID
  */
  private String pid;

  /**
  * 菜单类型(1.目录 2.菜单 3.按钮
  */
  private Integer type;

  /**
  * 菜单名称
  */
  private String title;

  /**
  * 菜单图标
  */
  private String icon;

  /**
  * 路径(不含context-path 且于/开始)
  */
  private String path;

  /**
  * _blank.在新窗口中打开 _self.当前框架中打开 _parent.在父框架中打开 _top.在整个窗口中打开 _content.在右侧框架中打开
  */
  private String target;

  /**
  * 权限标识(菜单或按钮，具体用于权限管控的对象)
  */
  private String permission;

  /**
  * 子级个数(含隐藏的 show=1)
  */
  private Integer sub_count;

  /**
  * 是否显示(0:否 1:是)
  */
  private Integer show;

  /**
  * 排序
  */
  private Integer sort;

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

  public SysMenu setId(String id) {
    this.id=id;
    return this;
  }
  public String getPid() {
    return pid;
  }

  public SysMenu setPid(String pid) {
    this.pid=pid;
    return this;
  }
  public Integer getType() {
    return type;
  }

  public SysMenu setType(Integer type) {
    this.type=type;
    return this;
  }
  public String getTitle() {
    return title;
  }

  public SysMenu setTitle(String title) {
    this.title=title;
    return this;
  }
  public String getIcon() {
    return icon;
  }

  public SysMenu setIcon(String icon) {
    this.icon=icon;
    return this;
  }
  public String getPath() {
    return path;
  }

  public SysMenu setPath(String path) {
    this.path=path;
    return this;
  }
  public String getTarget() {
    return target;
  }

  public SysMenu setTarget(String target) {
    this.target=target;
    return this;
  }
  public String getPermission() {
    return permission;
  }

  public SysMenu setPermission(String permission) {
    this.permission=permission;
    return this;
  }
  public Integer getSub_count() {
    return sub_count;
  }

  public SysMenu setSub_count(Integer sub_count) {
    this.sub_count=sub_count;
    return this;
  }
  public Integer getShow() {
    return show;
  }

  public SysMenu setShow(Integer show) {
    this.show=show;
    return this;
  }
  public Integer getSort() {
    return sort;
  }

  public SysMenu setSort(Integer sort) {
    this.sort=sort;
    return this;
  }
  public LocalDateTime getCreate_time() {
    return create_time;
  }

  public SysMenu setCreate_time(LocalDateTime create_time) {
    this.create_time=create_time;
    return this;
  }
  public String getCreate_by() {
    return create_by;
  }

  public SysMenu setCreate_by(String create_by) {
    this.create_by=create_by;
    return this;
  }
  public LocalDateTime getUpdate_time() {
    return update_time;
  }

  public SysMenu setUpdate_time(LocalDateTime update_time) {
    this.update_time=update_time;
    return this;
  }
  public String getUpdate_by() {
    return update_by;
  }

  public SysMenu setUpdate_by(String update_by) {
    this.update_by=update_by;
    return this;
  }

  @Override
  public String toString() {
    return "SysMenu::{"+
            "id:"+this.id+
            ", pid:"+this.pid+
            ", type:"+this.type+
            ", title:"+this.title+
            ", icon:"+this.icon+
            ", path:"+this.path+
            ", target:"+this.target+
            ", permission:"+this.permission+
            ", sub_count:"+this.sub_count+
            ", show:"+this.show+
            ", sort:"+this.sort+
            ", create_time:"+this.create_time+
            ", create_by:"+this.create_by+
            ", update_time:"+this.update_time+
            ", update_by:"+this.update_by+
            "}";
  }

}
