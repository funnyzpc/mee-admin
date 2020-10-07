package com.mee.common.util.excel;

/**
 * @author funnyzpc
 */
public class CellBaseType {
    /**
     * EXCEL 类型一栏
     常规
     数值
     货币
     会计专用
     日期
     时间
     百分比
     分数
     科学计数
     文本
     特殊
     自定义
     */

    /**
     *  常规
     */
    public static final Integer GENERAL = 0;
    /**
     * 数值
     */
    public static final Integer NUMERIC= 2;
    /**
     * 货币
     */
    public static final Integer CURRENCY= 3;
    /**
     * 会计专用
     */
    public static final Integer ACCOUNTING= 4;
    /**
     * 日期
     */
    public static final Integer DATE= 5;
    /**
     * 时间
     */
    public static final Integer TIME= 6;
    /**
     * 百分比
     */
    public static final Integer PERCENTAGE= 7;
    /**
     * 分数
     */
    public static final Integer FRACTION= 8;
    /**
     * 科学计数
     */
    public static final Integer SCIENTIFIC_NUMERIC= 9;
    /**
     * 文本
     */
    public static final Integer TEXT= 10;
    /**
     * 特殊
     */
    public static final Integer SPECIAL= 11;
    /**
     * 自定义
     */
    public static final Integer CUSTOM= 12;

}