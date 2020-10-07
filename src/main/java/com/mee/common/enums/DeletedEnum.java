package com.mee.common.enums;


/****
 * @author funnyzpc
 * @description 删除标记表示
 */
public enum DeletedEnum {

    /** 正常 **/
    NORMAL(0, "正常"),
    /** 已删除 **/
    DELETED(1, "已删除"),
    ;

    public Integer code;
    public String name;

    DeletedEnum(Integer code, String name) {
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
