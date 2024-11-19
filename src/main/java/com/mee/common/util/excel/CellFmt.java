package com.mee.common.util.excel;


import org.apache.poi.ss.usermodel.CellType;

/**
 * @author funnyzpc
 */
public enum CellFmt {
     /*
        ======> Excel 类型定义一览 <=====
     常规 GENERAL = 0
     数值 NUMERIC= 2
     货币 CURRENCY= 3
     会计专用   ACCOUNTING= 4
     日期 DATE= 5
     时间 TIME= 6
     百分比    PERCENTAGE= 7
     分数 FRACTION= 8
     科学计数   SCIENTIFIC_NUMERIC= 9
     文本 TEXT= 10
     特殊 SPECIAL= 11
     自定义    CUSTOM= 12
     */
    /**
     * 以下单元格定义类型枚举均用于POI Excel类型
     * 编号顺序为： “大类别_顺序号"
     *  一般国内所使用日期类型建议定义为自定义类型
     *  若设置为poi所定义类型(format)
     *      请绕道这里：@See https://poi.apache.org/apidocs/dev/org/apache/poi/ss/usermodel/BuiltinFormats.html
     */

    /**
     *   自定义类型
     */
    //自定义日期全格式类型 new String[]{"Date","Timestamp","LocalDateTime"}
    CUSTOM_01("yyyy-MM-dd HH:mm:ss","yyyy/M/d HH:mm:ss",CellType.STRING),
    //自定义日期YMD类型
    CUSTOM_02("yyyy/M/d","yyyy/M/d",CellType.STRING),
    //自定义日期HMS类型
    CUSTOM_03("h:mm:ss","h:mm:ss",CellType.STRING),
    CUSTOM_04(null,"yyyy\"年\"mm\"月\"dd\"日\"",CellType.STRING),
    /**
     * 数值
     */
    //数值整数类型
    NUMERIC_01(null,"0",CellType.NUMERIC),
    //数值保留两位小数(含四舍五入)
    NUMERIC_02(null,"0.00",CellType.NUMERIC),
    //数值千分位类型
    NUMERIC_03(null,"#,##0",CellType.NUMERIC),
    //数值千分位保留两位小数(含四舍五入)
    NUMERIC_04(null,"#,##0.00",CellType.NUMERIC),

    /**
     * 文本
     */
    TEXT_01(null,"General",CellType.STRING),
    TEXT_02("%.2f","@",CellType.STRING),

    /**
     * 常规
     */
    GENERAL_01(null,"General",CellType.NUMERIC),
    GENERAL_02("%s 天","General",CellType.NUMERIC),
    GENERAL_03("%s 小时","General",CellType.NUMERIC),
    /**
     * 日期
     */
    DATE_01("yyyy/M/d","yyyy/M/d",CellType.NUMERIC);

    /**
     * 数据格式化(Excel格式化)，数据格式化样式(Date类型 to yyyy-MM-dd字符串样式)
     * 内部采用String.format(格式,数据值)/Date来格式化
     */
    private final String dataFmt;
    /**
     * 单元格格式化(java格式化),Excel渲染格式，也是预设格式
     * [
     *   "General",
     *   "0",
     *   "0.00",
     *   "#,##0",
     *   "#,##0.00",
     *   "\"$\"#,##0_);(\"$\"#,##0)",
     *   "\"$\"#,##0_);[Red](\"$\"#,##0)",
     *   "\"$\"#,##0.00_);(\"$\"#,##0.00)",
     *   "\"$\"#,##0.00_);[Red](\"$\"#,##0.00)",
     *   "0%",
     *   "0.00%",
     *   "0.00E+00",
     *   "# ?/?",
     *   "# ??/??",
     *   "m/d/yy",
     *   "d-mmm-yy",
     *   "d-mmm",
     *   "mmm-yy",
     *   "h:mm AM/PM",
     *   "h:mm:ss AM/PM",
     *   "h:mm",
     *   "h:mm:ss",
     *   "m/d/yy h:mm",
     *   "reserved-0x17",
     *   "reserved-0x18",
     *   "reserved-0x19",
     *   "reserved-0x1A",
     *   "reserved-0x1B",
     *   "reserved-0x1C",
     *   "reserved-0x1D",
     *   "reserved-0x1E",
     *   "reserved-0x1F",
     *   "reserved-0x20",
     *   "reserved-0x21",
     *   "reserved-0x22",
     *   "reserved-0x23",
     *   "reserved-0x24",
     *   "#,##0_);(#,##0)",
     *   "#,##0_);[Red](#,##0)",
     *   "#,##0.00_);(#,##0.00)",
     *   "#,##0.00_);[Red](#,##0.00)",
     *   "_(* #,##0_);_(* (#,##0);_(* \"-\"_);_(@_)",
     *   "_(\"$\"* #,##0_);_(\"$\"* (#,##0);_(\"$\"* \"-\"_);_(@_)",
     *   "_(* #,##0.00_);_(* (#,##0.00);_(* \"-\"??_);_(@_)",
     *   "_(\"$\"* #,##0.00_);_(\"$\"* (#,##0.00);_(\"$\"* \"-\"??_);_(@_)",
     *   "mm:ss",
     *   "[h]:mm:ss",
     *   "mm:ss.0",
     *   "##0.0E+0",
     *   "@" -- 字符串
     * ]
     */
    private final String cellFmt;
    /**
     * POI单元格类型，Excel渲染类型，也是预设类型
     */
    private final CellType cellType;

    CellFmt(String dataFmt, String cellFmt, CellType cellType){
        this.dataFmt=dataFmt;
        this.cellFmt=cellFmt;
        this.cellType=cellType;
    }
    public String getDataFmt() {
        return dataFmt;
    }


    public String getCellFmt() {
        return cellFmt;
    }


    public CellType getCellType() {
        return cellType;
    }


}