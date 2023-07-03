package com.mee.common.enums;

/**
 * 状态枚举
 *
 * @author shaoow
 * @version 1.0
 * @className StatusEnum
 * @date 2023/6/2 14:55
 */
public enum StatusEnum {

    /** 正常 **/
    VALID("1", "有效"),
    /** 已删除 **/
    INVALID("0", "无效"),
    ;

    public final String code;
    public final String name;

    StatusEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
