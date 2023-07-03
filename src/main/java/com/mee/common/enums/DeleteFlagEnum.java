package com.mee.common.enums;


/**
*   删除标记枚举
*   @className  DeleteFlagEnum
*   @author     shadow
*   @date       2023/5/31 10:17 PM
*   @version    v1.0
*/
public enum DeleteFlagEnum {

    /** 正常 **/
    NORMAL(1, "正常"),
    /** 已删除 **/
    DELETED(0, "已删除"),
    ;

    public Integer code;
    public String name;

    DeleteFlagEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

}
