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
        for (int i = 0; i < headerNames.length; i++) {
            headerCell = row.createCell(i);
            headerCell.setCellStyle(headerCellStyle);
            headerCell.setCellValue(headerNames[i]);
            sheet.setColumnWidth(i,
                    null == headerNames[i] ? CELL_BASE_LENGTH
                            : (headerNames[i].contains("\r\n") ? CELL_CHARSET_LENGTH * headerNames[i].length()/2
                            : CELL_CHARSET_LENGTH * headerNames[i].length()));
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
            writeCellValueDefault2(bodyCell, value);
        }else{
            bodyCell.setCellType(cellFmt.getCellType());
            writeCellValueByFmt(bodyCell,value, cellFmt.getCellFmt());
        }
    }

    public static void writeCellValueByFmt(SXSSFCell bodyCell, Object value, String cellFmt){
        if (null == value) {
            bodyCell.setCellValue(EMPTY_VALUE_CONTENT);
            return;
        }
        if (value.getClass().getSimpleName().equals("String")) {
            if(null == cellFmt){
                bodyCell.setCellValue((String)value);
            }else{
                bodyCell.setCellValue(String.format(cellFmt,(String)value));
            }
            return;
        }
        if (value.getClass().getSimpleName().equals("BigDecimal")) {
            //bodyCell.setCellValue(((BigDecimal) value).stripTrailingZeros().toPlainString());
            if(null == cellFmt){
                bodyCell.setCellValue(((BigDecimal)value).doubleValue());
            }else {
                bodyCell.setCellValue(String.format(cellFmt, ((BigDecimal)value).doubleValue()));
            }
            return;
        }
        if (value.getClass().getSimpleName().equals("Timestamp")) {
            bodyCell.setCellValue(new Date(((Timestamp)value).getTime()));
            return;
        }
        if (value.getClass().getSimpleName().equals("Date")) {
            bodyCell.setCellValue((Date)value);
            //bodyCell.setCellValue(DateUtil.format((Date) value, cellFormat.getCellFmt()));
            return;
        }
        if (value.getClass().getSimpleName().equals("Integer")) {
            if(null == cellFmt){
                bodyCell.setCellValue((Integer)value);
            }else{
                bodyCell.setCellValue(String.format(cellFmt,value));
            }
            return;
        }
        if(value.getClass().getSimpleName().equals("Long")){
            if(null == cellFmt){
                bodyCell.setCellValue((Long)value);
            }else{
                bodyCell.setCellValue(String.format(cellFmt,value));
            }
            return;
        }
        if (value.getClass().getSimpleName().equals("Double")) {
            if(null == cellFmt){
                bodyCell.setCellValue((Double)value);
            }else{
                bodyCell.setCellValue(String.format(cellFmt,value));
            }
            return;
        }
        if(value.getClass().getSimpleName().equals("Float")){
            if(null == cellFmt){
                bodyCell.setCellValue((Float)value);
            }else{
                bodyCell.setCellValue(String.format(cellFmt,value));
            }
            return;
        }
        if (value.getClass().getSimpleName().equals("Byte")) {
            if(null == cellFmt){
                bodyCell.setCellValue((Byte)value);
            }else{
                bodyCell.setCellValue(String.format(cellFmt,value));
            }
            return;
        }
        if (value.getClass().getSimpleName().equals("Short")) {
            if(null == cellFmt){
                bodyCell.setCellValue((Short)value);
            }else{
                bodyCell.setCellValue(String.format(cellFmt,value));
            }
            return;
        }
        if (value.getClass().getSimpleName().equals("Boolean")) {
            if(null == cellFmt){
                bodyCell.setCellValue((Boolean) value);
            }else{
                bodyCell.setCellValue(String.format(cellFmt,value));
            }
            return;
        }
        if (value.getClass().getSimpleName().equals("Calendar")) {
            if(null == cellFmt){
                bodyCell.setCellValue((Calendar) value);
            }else{
                bodyCell.setCellValue(String.format(cellFmt,value));
            }
            return;
        }
        if (value.getClass().getSimpleName().equals("Character")) {
            if(null == cellFmt){
                bodyCell.setCellValue((Character) value);
            }else{
                bodyCell.setCellValue(String.format(cellFmt,value));
            }
            return;
        }
        if(value.getClass().getSimpleName().equals("LocalDate")){
            bodyCell.setCellValue(DateUtil.format((LocalDate)value,FORMAT_YYYY_MM_DD));
            return;
        }
        if(value.getClass().getSimpleName().equals("LocalDateTime")){
            bodyCell.setCellValue(Date.from(((LocalDateTime)value).atZone(CHINA_ZONE_ID).toInstant()));
            return;
        }
        /**
         * 文本添加删除线 RichTextString 单元格批注 Comment 单元格错误值 ErrorValue(byte) 文本公式设置
         * formula
         */
        // 什么都不是:设置为空值
        bodyCell.setCellValue(EMPTY_VALUE_CONTENT);
        log.error("\r\n>>>未知的数据类型{}", value.getClass().getName());
    }

    @Deprecated
    public static void writeCellValueDefault(SXSSFCell bodyCell, Object value) {
        bodyCell.setCellType(CellType.STRING);
        if (null == value) {
            bodyCell.setCellValue(EMPTY_VALUE_CONTENT);
            return;
        }
        if (value.getClass().getSimpleName().equals("String")) {
            bodyCell.setCellValue(String.valueOf(value));
            return;
        }
        if (value.getClass().getSimpleName().equals("BigDecimal")) {
            bodyCell.setCellValue(((BigDecimal) value).stripTrailingZeros().toPlainString());
            return;
        }
        if (value.getClass().getSimpleName().equals("Timestamp")) {
            //bodyCell.setCellValue(DATE_FORMAT_YMDHMS.format(((Timestamp) value).toLocalDateTime()));
            bodyCell.setCellValue(DateUtil.format(((Timestamp) value).toLocalDateTime(), FORMAT_YYYY_MM_DD_HMS));
            return;
        }
        if (value.getClass().getSimpleName().equals("Integer") || value.getClass().getSimpleName().equals("Long")) {
            bodyCell.setCellValue(String.valueOf(value));
            return;
        }
        if (value.getClass().getSimpleName().equals("Double") || value.getClass().getSimpleName().equals("Float")) {
            bodyCell.setCellValue(String.valueOf(value));
            return;
        }
        if (value.getClass().getSimpleName().equals("Byte")) {
            bodyCell.setCellValue((Byte) value);
            return;
        }

        if (value.getClass().getSimpleName().equals("Short")) {
            bodyCell.setCellValue((Short) value);
            return;
        }
        if (value.getClass().getSimpleName().equals("Boolean")) {
            bodyCell.setCellValue((boolean) value);
            return;
        }
        if (value.getClass().getSimpleName().equals("Calendar")) {
            bodyCell.setCellValue((Calendar) value);
            return;
        }
        if (value.getClass().getSimpleName().equals("Date")) {
            bodyCell.setCellValue(DateUtil.format((Date) value, FORMAT_YYYY_MM_DD_HMS));
            return;
        }
        if (value.getClass().getSimpleName().equals("Character")) {
            bodyCell.setCellValue(value.toString());
            return;
        }
        if(value.getClass().getSimpleName().equals("LocalDate")){
            bodyCell.setCellValue(DateUtil.format((LocalDate)value,FORMAT_YYYY_MM_DD));
            return;
        }
        if(value.getClass().getSimpleName().equals("LocalDateTime")){
            bodyCell.setCellValue(DateUtil.format((LocalDateTime)value,FORMAT_YYYY_MM_DD_HMS));
            return;
        }
        /**
         * 文本添加删除线 RichTextString 单元格批注 Comment 单元格错误值 ErrorValue(byte) 文本公式设置
         * formula
         */
        // 什么都不是:设置为空值
        bodyCell.setCellValue(EMPTY_VALUE_CONTENT);
        log.error("\r\n>>>未知的数据类型{}", value.getClass().getName());
    }

    public static void writeCellValueDefault2(SXSSFCell bodyCell, Object value) {
        bodyCell.setCellType(CellType.STRING);
        if (null == value) {
            bodyCell.setCellValue(EMPTY_VALUE_CONTENT);
            return;
        }
        switch (value.getClass().getSimpleName()){
            case "String":
                bodyCell.setCellValue(String.valueOf(value));
                break;
            case "BigDecimal":
                bodyCell.setCellValue(((BigDecimal) value).stripTrailingZeros().toPlainString());
                break;
            case "Timestamp":
                //bodyCell.setCellValue(DATE_FORMAT_YMDHMS.format(((Timestamp) value).toLocalDateTime()));
                bodyCell.setCellValue(DateUtil.format(((Timestamp) value).toLocalDateTime(), FORMAT_YYYY_MM_DD_HMS));
                break;
            case "Integer": case "Long":
                bodyCell.setCellValue(String.valueOf(value));
                break;
            case "Double":
            case "Float":
                bodyCell.setCellValue(String.valueOf(value));
                break;
            case "Byte":
               bodyCell.setCellValue((Byte) value);
                break;
            case "Short":
                bodyCell.setCellValue((Short) value);
                break;
            case "Boolean":
                bodyCell.setCellValue((boolean) value);
                break;
            case "Calendar":
                bodyCell.setCellValue((Calendar) value);
                break;
            case "Date":
                bodyCell.setCellValue(DateUtil.format((Date) value, FORMAT_YYYY_MM_DD_HMS));
                break;
            case "Character":
                bodyCell.setCellValue(value.toString());
                break;
            case "LocalDate":
                bodyCell.setCellValue(DateUtil.format((LocalDate)value,FORMAT_YYYY_MM_DD));
                break;
            case "LocalDateTime":
                bodyCell.setCellValue(DateUtil.format((LocalDateTime)value,FORMAT_YYYY_MM_DD_HMS));
                break;
            default:
                /**
                 * 文本添加删除线 RichTextString 单元格批注 Comment 单元格错误值 ErrorValue(byte) 文本公式设置
                 * formula
                 */
                // 什么都不是:设置为空值
                bodyCell.setCellValue(EMPTY_VALUE_CONTENT);
                log.error("\r\n>>>未知的数据类型{}", value.getClass().getName());
        }

    }

}