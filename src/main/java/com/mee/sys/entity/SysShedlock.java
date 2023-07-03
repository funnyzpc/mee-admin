package com.mee.sys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author funnyzpc
 * @description 分佈式&集群 定時任務鎖表
 */
public class SysShedlock  {

    private String name;
    private String label;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime locked_at;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lock_until;
    private String locked_by;

    private String status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public LocalDateTime getLocked_at() {
        return locked_at;
    }

    public void setLocked_at(LocalDateTime locked_at) {
        this.locked_at = locked_at;
    }

    public LocalDateTime getLock_until() {
        return lock_until;
    }

    public void setLock_until(LocalDateTime lock_until) {
        this.lock_until = lock_until;
    }

    public String getLocked_by() {
        return locked_by;
    }

    public void setLocked_by(String locked_by) {
        this.locked_by = locked_by;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SysShedlock{" +
                "name='" + name + '\'' +
                ", label='" + label + '\'' +
                ", locked_at=" + locked_at +
                ", lock_until=" + lock_until +
                ", locked_by='" + locked_by + '\'' +
                ", status='" + status + '\'' +
                "} ";
    }
}
