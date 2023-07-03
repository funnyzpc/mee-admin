package com.mee.sys.vo;

import com.mee.sys.entity.SysMenu;

import java.io.Serializable;

/**
 * 菜单树对象
 *
 * @author shadow
 * @version v1.0
 * @className SysMenu2TreeVO
 * @date 2023/5/7 10:34 PM
 */
public class SysMenu2TreeVO extends SysMenu  implements Serializable {

    /**
     * 日志对象
     */
    private static final long serialVersionUID = 1L;


    /**
     * 层级
     */
    private Integer level;

    /**
     * 层级缩进
     */
    private Integer level_locking;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLevel_locking() {
        return level_locking;
    }

    public void setLevel_locking(Integer level_locking) {
        this.level_locking = level_locking;
    }

    @Override
    public String toString() {
        return "SysMenu2::{"+
                "id:"+super.getId()+
                ", pid:"+super.getPid()+
                ", type:"+super.getType()+
                ", title:"+super.getTitle()+
                ", icon:"+super.getIcon()+
                ", path:"+super.getPath()+
                ", target:"+super.getTarget()+
                ", permission:"+super.getPermission()+
                ", sub_count:"+super.getSub_count()+
                ", show:"+super.getShow()+
                ", sort:"+super.getSort()+
                ", create_time:"+super.getCreate_time()+
                ", create_by:"+super.getCreate_by()+
                ", update_time:"+super.getUpdate_time()+
                ", update_by:"+super.getUpdate_by()+
                ", level:"+this.level+
                ", level_locking:"+this.level_locking+
                "}";
    }

}
