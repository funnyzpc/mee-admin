package com.mee.common.util.excel;


import com.mee.common.util.DateUtil;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author: funnyzpc
 * @Description Excel 单元格写入
 **/
public class PoiCellProcess {
    private static final Logger log = LoggerFactory.getLogger(PoiCellProcess.class);

    /**
     * 空字符串
     */
    private static final String EMPTY_VALUE_CONTENT = "";

    // 单字符长度设置
    private static final int CELL_CHARSET_LENGTH = 1000;

    // 单元格基础长度
    private static final int CELL_BASE_LENGTH = 5 * CELL_CHARSET_LENGTH;

    // 单元格格式化
    public final static String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
    public final static String FORMAT_YYYY_MM_DD_HMS = "yyyy-MM-dd HH:mm:ss";

    /* 设置日期时区常量 */
    public static final ZoneId CHINA_ZONE_ID = ZoneId.of("Asia/Shanghai");

    /**
     * 写表头
     */
    public static void writeHeaderCell(SXSSFSheet sheet, CellStyle headerCellStyle, String[] headerNames) {
        SXSSFRow row = sheet.createRow(0);
        row.setHeight((short) 30);
        row.setHeightInPoints((short) 30);
        SXSSFCell headerCell;
        String headerName;
        for (int i = 0; i < headerNames.length; i++) {
            headerCell = row.createCell(i);
            headerCell.setCellStyle(headerCellStyle);
            headerCell.setCellValue(headerName=headerNames[i]);
            sheet.setColumnWidth(i,
                    null == headerName ? CELL_BASE_LENGTH
                            : (headerName.contains("\n") ? CELL_CHARSET_LENGTH * (int) (headerName.length()/1.2)
                            : CELL_CHARSET_LENGTH * headerName.length()));
        }
    }

    /**
     * 写表体 by Obj
     */
    public static <T extends Object> void writeBodyCellByObj(SXSSFSheet sheet, CellStyle[] bodyCellStyles,
                                                             String[] cellNames, List<T> dataList, CellFmt[] cellFmts) {
        int countData = dataList.size();
        int countCell = cellNames.length;
        Object[] valueArr = new Object[countCell];
        SXSSFRow row;
        SXSSFCell bodyCell;
        for (int i = 0; i < countData; i++) {
            valueArr = ExcelDataReflectUtil.fieldValues(dataList.get(i), cellNames, valueArr);
            row = sheet.createRow(i + 1);
            row.setHeightInPoints((short) 15);
            for (int j = 0; j < countCell; j++) {
                bodyCell = row.createCell(j);
                bodyCell.setCellStyle(bodyCellStyles[j]);
                /**
                 * 设置单元格值
                 */
                writeCellValue(bodyCell, valueArr[j],null != cellFmts ? cellFmts[j]:null);
            }
        }
    }

    /**
     * 写表体 by Map
     */
    public static <V extends Object> void writeBodyCellByMap(SXSSFSheet sheet, CellStyle[] bodyCellStyles,
                                                             String[] cellNames, List<Map<String, V>> dataList, CellFmt[] cellFmts) {
        int countData = dataList.size();
        int countCell = cellNames.length;
        SXSSFRow row;
        SXSSFCell bodyCell;
        for (int i = 0; i < countData; i++) {
            row = sheet.createRow(i + 1);
            row.setHeightInPoints((short) 20);
            // SXSSFCell bodyCell;
            for (int j = 0; j < countCell; j++) {
                bodyCell = row.createCell(j);
                bodyCell.setCellStyle(bodyCellStyles[j]);
                /**
                 * 设置单元格值
                 */
                writeCellValue(bodyCell, (null == cellNames[j]) ? null : dataList.get(i).get(cellNames[j]),null == cellFmts ?null: cellFmts[j]);
            }
        }
    }

    /**
     * 单元格值写入
     */
    public static void writeCellValue(SXSSFCell bodyCell, Object value, CellFmt cellFmt) {
        if(null == cellFmt){
            bodyCell.setCellType(CellType.STRING);
            writeCellValueDefault2(bodyCell, value);
        }else{
            bodyCell.setCellType(cellFmt.getCellType());
            writeCellValueByFmt(bodyCell,value, cellFmt.getDataFmt());
        }
    }

    public static void writeCellValueByFmt(SXSSFCell bodyCell,Object value,String dataFmt){
        if (null == value) {
            bodyCell.setCellValue(EMPTY_VALUE_CONTENT);
            return;
        }
        final String clazzName = value.getClass().getName();
        switch (clazzName){
            case "java.lang.String":
                if(null == dataFmt){
                    bodyCell.setCellValue((String)value);
                }else{
                    bodyCell.setCellValue(String.format(dataFmt,value));
                }
                return;
            case "java.math.BigDecimal":
                if(null == dataFmt){
                    bodyCell.setCellValue(((BigDecimal)value).doubleValue());
                }else {
                    bodyCell.setCellValue(String.format(dataFmt, ((BigDecimal)value).doubleValue()));
                }
                return;
            case "java.sql.Timestamp":
                if(null == dataFmt){
                    bodyCell.setCellValue(new Date(((Timestamp)value).getTime()));
                }else{
                    bodyCell.setCellValue(DateUtil.format((Timestamp)value,dataFmt));
                }
                return;
            case "java.util.Date":
                if(null==dataFmt){
                    bodyCell.setCellValue((Date)value);
                }else{
                    bodyCell.setCellValue(DateUtil.format((Date)value,dataFmt));
                }
                return;
            case "java.sql.Date":
                if(null==dataFmt){
                    bodyCell.setCellValue((java.sql.Date)value);
                }else{
                    bodyCell.setCellValue(DateUtil.format((java.sql.Date)value,dataFmt));
                }
                return;
            case "java.lang.Integer":
                if(null == dataFmt){
                    bodyCell.setCellValue((Integer)value);
                }else{
                    bodyCell.setCellValue(String.format(dataFmt,value));
                }
                return;
            case "java.lang.Long":
                if(null == dataFmt){
                    bodyCell.setCellValue((Long)value);
                }else{
                    bodyCell.setCellValue(String.format(dataFmt,value));
                }
                return;
            case "java.lang.Double":
                if(null == dataFmt){
                    bodyCell.setCellValue((Double)value);
                }else{
                    bodyCell.setCellValue(String.format(dataFmt,value));
                }
                return;
            case "java.lang.Float":
                if(null == dataFmt){
                    bodyCell.setCellValue((Float)value);
                }else{
                    bodyCell.setCellValue(String.format(dataFmt,value));
                }
                return;
            case "java.lang.Byte":
                if(null == dataFmt){
                    bodyCell.setCellValue((Byte)value);
                }else{
                    bodyCell.setCellValue(String.format(dataFmt,value));
                }
                return;
            case "java.lang.Short":
                if(null == dataFmt){
                    bodyCell.setCellValue((Short)value);
                }else{
                    bodyCell.setCellValue(String.format(dataFmt,value));
                }
                return;
            case "java.lang.Boolean":
                if(null == dataFmt){
                    bodyCell.setCellValue((Boolean) value);
                }else{
                    bodyCell.setCellValue(String.format(dataFmt,value));
                }
                return;
            case "java.util.Calendar":
                if(null == dataFmt){
                    bodyCell.setCellValue((Calendar) value);
                }else{
                    bodyCell.setCellValue(String.format(dataFmt,value));
                }
                return;
            case "java.lang.Character":
                if(null == dataFmt){
                    bodyCell.setCellValue((Character) value);
                }else{
                    bodyCell.setCellValue(String.format(dataFmt,value));
                }
                return;
            case "java.time.LocalDate":
                bodyCell.setCellValue(DateUtil.format((LocalDate)value,FORMAT_YYYY_MM_DD));
                return;
            case "java.time.LocalDateTime":
                bodyCell.setCellValue(Date.from(((LocalDateTime)value).atZone(CHINA_ZONE_ID).toInstant()));
                return;
            default:
                /**
                 * 文本添加删除线 RichTextString 单元格批注 Comment 单元格错误值 ErrorValue(byte) 文本公式设置
                 * formula
                 */
                // 什么都不是:设置为空值
//        bodyCell.setCellValue(EMPTY_VALUE_CONTENT);
                bodyCell.setCellValue(value.toString());
                log.error(">>>未知的数据类型:{},{}",value,clazzName);
                return ;
        }

    }

//    @Deprecated
//    public static void writeCellValueDefault(SXSSFCell bodyCell, Object value) {
//        bodyCell.setCellType(CellType.STRING);
//        if (null == value) {
//            bodyCell.setCellValue(EMPTY_VALUE_CONTENT);
//            return;
//        }
//        final String clazzName = value.getClass().getName();
//        if (value.getClass().getSimpleName().equals("String")) {
//            bodyCell.setCellValue(String.valueOf(value));
//            return;
//        }
//        if (value.getClass().getSimpleName().equals("BigDecimal")) {
//            bodyCell.setCellValue(((BigDecimal) value).stripTrailingZeros().toPlainString());
//            return;
//        }
//        if (value.getClass().getSimpleName().equals("Timestamp")) {
//            //bodyCell.setCellValue(DATE_FORMAT_YMDHMS.format(((Timestamp) value).toLocalDateTime()));
//            bodyCell.setCellValue(DateUtil.format(((Timestamp) value).toLocalDateTime(), FORMAT_YYYY_MM_DD_HMS));
//            return;
//        }
//        if (value.getClass().getSimpleName().equals("Integer") || value.getClass().getSimpleName().equals("Long")) {
//            bodyCell.setCellValue(String.valueOf(value));
//            return;
//        }
//        if (value.getClass().getSimpleName().equals("Double") || value.getClass().getSimpleName().equals("Float")) {
//            bodyCell.setCellValue(String.valueOf(value));
//            return;
//        }
//        if (value.getClass().getSimpleName().equals("Byte")) {
//            bodyCell.setCellValue((Byte) value);
//            return;
//        }
//
//        if (value.getClass().getSimpleName().equals("Short")) {
//            bodyCell.setCellValue((Short) value);
//            return;
//        }
//        if (value.getClass().getSimpleName().equals("Boolean")) {
//            bodyCell.setCellValue((boolean) value);
//            return;
//        }
//        if (value.getClass().getSimpleName().equals("Calendar")) {
//            bodyCell.setCellValue((Calendar) value);
//            return;
//        }
//        if (value.getClass().getSimpleName().equals("Date")) {
//            bodyCell.setCellValue(DateUtil.format((Date) value, FORMAT_YYYY_MM_DD_HMS));
//            return;
//        }
//        if (value.getClass().getSimpleName().equals("Character")) {
//            bodyCell.setCellValue(value.toString());
//            return;
//        }
//        if(value.getClass().getSimpleName().equals("LocalDate")){
//            bodyCell.setCellValue(DateUtil.format((LocalDate)value,FORMAT_YYYY_MM_DD));
//            return;
//        }
//        if(value.getClass().getSimpleName().equals("LocalDateTime")){
//            bodyCell.setCellValue(DateUtil.format((LocalDateTime)value,FORMAT_YYYY_MM_DD_HMS));
//            return;
//        }
//        /**
//         * 文本添加删除线 RichTextString 单元格批注 Comment 单元格错误值 ErrorValue(byte) 文本公式设置
//         * formula
//         */
//        // 什么都不是:设置为空值
//        bodyCell.setCellValue(value.toString());
//        log.error(">>>未知的数据类型:{},{}",value,clazzName);
//    }

    public static void writeCellValueDefault2(SXSSFCell bodyCell, Object value) {
        if (null == value) {
            bodyCell.setCellValue(EMPTY_VALUE_CONTENT);
            return;
        }
        final String clazzName = value.getClass().getName();
        switch (clazzName){
            case "java.lang.String":
                bodyCell.setCellValue(String.valueOf(value));
                return;
            case "java.math.BigDecimal":
                bodyCell.setCellValue(((BigDecimal) value).stripTrailingZeros().toPlainString());
                return;
            case "java.sql.Timestamp":
                //bodyCell.setCellValue(DATE_FORMAT_YMDHMS.format(((Timestamp) value).toLocalDateTime()));
                bodyCell.setCellValue(DateUtil.format(((Timestamp) value).toLocalDateTime(), FORMAT_YYYY_MM_DD_HMS));
                return;
            case "java.lang.Integer": case "java.lang.Long":
                bodyCell.setCellValue(String.valueOf(value));
                return;
            case "java.lang.Double":
            case "java.lang.Float":
                bodyCell.setCellValue(String.valueOf(value));
                return;
            case "java.lang.Byte":
               bodyCell.setCellValue((Byte) value);
                return;
            case "java.lang.Short":
                bodyCell.setCellValue((Short) value);
                return;
            case "java.lang.Boolean":
                bodyCell.setCellValue((boolean) value);
                return;
            case "java.util.Calendar":
                bodyCell.setCellValue((Calendar) value);
                return;
            case "java.util.Date":
                bodyCell.setCellValue(DateUtil.format((Date) value, FORMAT_YYYY_MM_DD_HMS));
                return;
            case "java.sql.Date":
                bodyCell.setCellValue(DateUtil.format((java.sql.Date) value, FORMAT_YYYY_MM_DD_HMS));
                return;
            case "java.lang.Character":
                bodyCell.setCellValue(value.toString());
                return;
            case "java.time.LocalDate":
                bodyCell.setCellValue(DateUtil.format((LocalDate)value,FORMAT_YYYY_MM_DD));
                return;
            case "java.time.LocalDateTime":
                bodyCell.setCellValue(DateUtil.format((LocalDateTime)value,FORMAT_YYYY_MM_DD_HMS));
                return;
            default:
                /**
                 * 文本添加删除线 RichTextString 单元格批注 Comment 单元格错误值 ErrorValue(byte) 文本公式设置
                 * formula
                 */
                // 什么都不是:设置为空值
//                bodyCell.setCellValue(EMPTY_VALUE_CONTENT);
                bodyCell.setCellValue(value.toString());
                log.error(">>>未知的数据类型{},{}", value,clazzName);
        }

    }

}