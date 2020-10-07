package com.mee.common.util.excel;


import org.apache.poi.ss.usermodel.CellType;

/**
 * @author funnyzpc
 */
public enum CellFmt {
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
    CUSTOM_01(CellBaseType.CUSTOM,"yyyy/M/d HH:mm:ss","yyyy/M/d HH:mm:ss",CellType.STRING),
    //自定义日期YMD类型
    CUSTOM_02(CellBaseType.CUSTOM,"yyyy/M/d","yyyy/M/d",CellType.STRING),
    //自定义日期HMS类型
    CUSTOM_03(CellBaseType.CUSTOM,"h:mm:ss","h:mm:ss",CellType.STRING),
    /**
     * 数值
     */
    //数值整数类型
    NUMERIC_01(CellBaseType.NUMERIC,"0",null,CellType.NUMERIC),
    //数值保留两位小数(含四舍五入)
    NUMERIC_02(CellBaseType.NUMERIC,"0.00",null,CellType.NUMERIC),
    //数值千分位类型
    NUMERIC_03(CellBaseType.NUMERIC,"#,##0",null,CellType.NUMERIC),
    //数值千分位保留两位小数(含四舍五入)
    NUMERIC_04(CellBaseType.NUMERIC,"#,##0.00",null,CellType.NUMERIC),

    /**
     * 文本
     */
    TEXT_01(CellBaseType.TEXT,"General",null,CellType.STRING),

    /**
     * 常规
     */
    GENERAL_01(CellBaseType.GENERAL,"General",null,CellType.NUMERIC),
    GENERAL_02(CellBaseType.GENERAL,"General","%s 天",CellType.NUMERIC),
    GENERAL_03(CellBaseType.GENERAL,"General","%s 小时",CellType.NUMERIC),
    /**
     * 日期
     */
    DATE_01(CellBaseType.DATE,"yyyy/M/d","yyyy/M/d",CellType.NUMERIC);

    /**
     *  Excel基本类型
     */
    private Integer type;
    /**
     * 数据格式化(Excel格式化)
     */
    private String dataFmt;
    /**
     * 单元格格式化(java格式化)
     */
    private String cellFmt;
    /**
     * POI单元格类型
     */
    private CellType cellType;

    CellFmt(Integer type, String dataFmt, String cellFmt, CellType cellType){
        this.type= type;
        this.dataFmt=dataFmt;
        this.cellFmt=cellFmt;
        this.cellType=cellType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDataFmt() {
        return dataFmt;
    }

    public void setDataFmt(String dataFmt) {
        this.dataFmt = dataFmt;
    }

    public String getCellFmt() {
        return cellFmt;
    }

    public void setCellFmt(String cellFmt) {
        this.cellFmt = cellFmt;
    }

    public CellType getCellType() {
        return cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

}