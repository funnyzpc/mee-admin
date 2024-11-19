package com.mee.common.util.excel;


import com.mee.common.util.JacksonUtil;
import com.mee.common.util.SeqGenUtil;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
* Excel写
* @className    ExcelWriteUtil
* @author       shadow
* @date         2024/5/28 9:11
* @version      1.0
*/
public class ExcelWriteUtil {
    private static final Logger log = LoggerFactory.getLogger(ExcelWriteUtil.class);

    //单sheet写入限制
    private static final int DATA_SPLIT_GROP_SIZE = 100000;

    //单元格最大宽度
    private static final int CELL_MAX_WIDTH= 10000;

    //导出文件类型
    private static final String EXPORT_FILE_END = ".xlsx";

    private static final String LINUX_FILE_SYSTEM = "/";
    /**
     * 内存刷新值大小
     */
    private static final int ROW_ACCESS_WINDOW_SIZE = 200;

    /**
     * 当前操作系统目录分隔符
     */
    private static final String SEPARATOR_KEY = "file.separator";

    private static final String HEADER_NAME = "\r\n headerNames's length it must be equal cellNames's Length,please Check!";

    private static final String CELL_LENGTH_ERROR_MSG = "\r\n cellFormat's length it must be equal headerNames's Length,please Check!";

    private static final String SYSTEM_TMP_DIR;
    static {
        if(LINUX_FILE_SYSTEM .equals(System.getProperty(SEPARATOR_KEY))){
            SYSTEM_TMP_DIR = "/tmp/export_dir";
        }else{
            SYSTEM_TMP_DIR = "D:/tmp/export_dir";
        }
    }

    /**
     *
     * @param dataList  数据
     * @param headerNames 表头名称
     * @param cellNames 单元格名称
     * @param <T> object
     * @return File
     */
    public static <T extends Object> File toXlsxByObj(List<T> dataList, String[] headerNames, String[] cellNames){
        return toXlsxByObj(dataList,headerNames,cellNames,null);
    }

    /**
     * 写入excel 文件，通过对象的方式
     * @param dataList 数据列表
     * @param headerNames 表头列表
     * @param cellNames 单元格名称
     * @param cellFmts 单元格长度配置
     * @param <T> object
     * @return
     */
    public static <T extends Object> File toXlsxByObj(
            List<T> dataList,
            String[] headerNames,
            String[] cellNames,
            CellFmt[] cellFmts
    ){
        /**
         * 基础值不可为空
         */
        if(null != headerNames && headerNames.length != cellNames.length){
            log.error(HEADER_NAME);
            return null;
        }
        if(null != cellFmts && cellFmts.length != cellNames.length){
            log.error(CELL_LENGTH_ERROR_MSG);
            return null;
        }
        /**
         * 构造文件夹
         */
        File fileObject = new File(SYSTEM_TMP_DIR);
        if(!fileObject.exists()){
            fileObject.mkdirs();
        }
        fileObject = new File(fileObject.getPath().concat(File.separator).concat(SeqGenUtil.genSeq()).concat(EXPORT_FILE_END));
        try(SXSSFWorkbook workbook = new SXSSFWorkbook(null,ROW_ACCESS_WINDOW_SIZE,true,true);  FileOutputStream outputStream = new FileOutputStream(fileObject);){
            workbook.setCompressTempFiles(true); // temp files will be gzipped
            CellStyle headerCellStyle = headerCellStyle(workbook);
            if(null == dataList || dataList.size()==0){
                SXSSFSheet sheet = workbook.createSheet("0~0");
                PoiCellProcess.writeHeaderCell(sheet,headerCellStyle,headerNames);
            }else{
                CellStyle[] bodyCellStyle = bodyCellStyle(workbook, cellFmts,cellNames.length);
                if(dataList.size()<= DATA_SPLIT_GROP_SIZE){
                    SXSSFSheet sheet = workbook.createSheet(String.format("0~%s",(null==dataList)?0:dataList.size()));
                    PoiCellProcess.writeHeaderCell(sheet,headerCellStyle,headerNames);
                    PoiCellProcess.writeBodyCellByObj(sheet,bodyCellStyle,cellNames,dataList, cellFmts);
                }else{
                    List<List<T>> mData = splitObjList(dataList);
                    for (List<T> mList : mData) {
                        //第一个sheet 参数(sheet名称,sheet的序号)
                        SXSSFSheet sheet = workbook.createSheet(String.format("%s~%s",
                                (dataList.size() > DATA_SPLIT_GROP_SIZE ?
                                        mData.indexOf(mList) * DATA_SPLIT_GROP_SIZE + 1
                                        : 0) + "",
                                (dataList.size() > DATA_SPLIT_GROP_SIZE ?
                                        (mData.size() == (mData.indexOf(mList) + 1) ? dataList.size() : DATA_SPLIT_GROP_SIZE * (mData.indexOf(mList) + 1))
                                        : dataList.size()) + "")
                        );
                        log.info(">>>sheet name : {}",sheet.getSheetName());
                        PoiCellProcess.writeHeaderCell(sheet,headerCellStyle,headerNames);
                        PoiCellProcess.writeBodyCellByObj(sheet,bodyCellStyle,cellNames,dataList, cellFmts);
                    }
                }
            }
            workbook.write(outputStream);
            workbook.dispose();
        }catch (IOException ioe){
            log.error("导出文件异常了 fileObject:{}",fileObject,ioe);
        }
        log.info("===>已生成导出文件：{}",fileObject.getPath());
        return fileObject;
    }

    /**
     * 写入excel 文件，通过对象的方式
     * @param dataList 数据列表
     * @param headerNames 表头列表
     * @param cellNames 单元格名称
     * @param <T> object
     * @return
     */
    public static <T extends Object> void toXlsxStreamByObj(
            List<T> dataList,
            String[] headerNames,
            String[] cellNames,
            OutputStream out
    ){
        toXlsxStreamByObj(dataList,headerNames,cellNames,null,out);
    }
    /**
     * 写入excel 文件，通过对象的方式
     * @param dataList 数据列表
     * @param headerNames 表头列表
     * @param cellNames 单元格名称
     * @param cellFmts 单元格长度配置
     * @param <T> object
     * @return
     */
    public static <T extends Object> void toXlsxStreamByObj(
            List<T> dataList,
            String[] headerNames,
            String[] cellNames,
            CellFmt[] cellFmts,
            OutputStream out
    ){
        /**
         * 基础值不可为空
         */
        if(null != headerNames && headerNames.length != cellNames.length){
            log.error(HEADER_NAME);
            return;
        }
        if(null != cellFmts && cellFmts.length != cellNames.length){
            log.error(CELL_LENGTH_ERROR_MSG);
            return;
        }
        try(SXSSFWorkbook workbook = new SXSSFWorkbook(null,ROW_ACCESS_WINDOW_SIZE,true,true); ){
            workbook.setCompressTempFiles(true); // temp files will be gzipped
            CellStyle headerCellStyle = headerCellStyle(workbook);
            if(null == dataList || dataList.size()==0){
                SXSSFSheet sheet = workbook.createSheet("0~0");
                PoiCellProcess.writeHeaderCell(sheet,headerCellStyle,headerNames);
            }else{
                CellStyle[] bodyCellStyle = bodyCellStyle(workbook, cellFmts,cellNames.length);
                if(dataList.size()<= DATA_SPLIT_GROP_SIZE){
                    SXSSFSheet sheet = workbook.createSheet(String.format("0~%s",(null==dataList)?0:dataList.size()));
                    PoiCellProcess.writeHeaderCell(sheet,headerCellStyle,headerNames);
                    PoiCellProcess.writeBodyCellByObj(sheet,bodyCellStyle,cellNames,dataList, cellFmts);
                }else{
                    List<List<T>> mData = splitObjList(dataList);
                    for (List<T> mList : mData) {
                        //第一个sheet 参数(sheet名称,sheet的序号)
                        SXSSFSheet sheet = workbook.createSheet(String.format("%s~%s",
                                (dataList.size() > DATA_SPLIT_GROP_SIZE ?
                                        mData.indexOf(mList) * DATA_SPLIT_GROP_SIZE + 1
                                        : 0) + "",
                                (dataList.size() > DATA_SPLIT_GROP_SIZE ?
                                        (mData.size() == (mData.indexOf(mList) + 1) ? dataList.size() : DATA_SPLIT_GROP_SIZE * (mData.indexOf(mList) + 1))
                                        : dataList.size()) + "")
                        );
                        log.info(">>>sheet name : {}",sheet.getSheetName());
                        PoiCellProcess.writeHeaderCell(sheet,headerCellStyle,headerNames);
                        PoiCellProcess.writeBodyCellByObj(sheet,bodyCellStyle,cellNames,dataList, cellFmts);
                    }
                }
            }
            workbook.write(out);
            // 写ServletResponse无需此句
//            workbook.dispose();
        }catch (IOException ioe){
            log.error("导出文件异常了 fileObject:{}",JacksonUtil.toJsonString(headerNames),ioe);
        }
    }

    /**
     *
     * @param dataList 数据
     * @param headerNames 表头
     * @param cellNames 单元格
     * @param <V> 动态类型
     * @return
     */
    public static <V extends Object> File toXlsxByMap(List<Map<String,V>> dataList, String[] headerNames, String[] cellNames){
        return toXlsxByMap(dataList,headerNames,cellNames,null);
    }

    /**
     * 写入excel 文件，通过对象的方式
     * @param dataList  数据列表
     * @param headerNames 表头列表
     * @param cellNames 单元格名称
     * @param cellFmts 单元格格式化配置
     * @param <V> Object
     * @return File
     */
    public static <V extends Object> File toXlsxByMap(List<Map<String,V>> dataList,
                                                      String[] headerNames,
                                                      String[] cellNames,
                                                      CellFmt[] cellFmts){
        /**
         * 基础值不可为空
         */
        if(null != headerNames && headerNames.length != cellNames.length){
            log.error(HEADER_NAME);
            return null;
        }
        if(null != cellFmts && cellFmts.length!=cellNames.length){
            log.error(CELL_LENGTH_ERROR_MSG);
            return null;
        }
        /**
         * 构造文件夹
         */
        File fileObject = new File(SYSTEM_TMP_DIR);
        if(!fileObject.exists()){
            fileObject.mkdirs();
        }
        fileObject = new File(fileObject.getPath().concat(File.separator).concat(SeqGenUtil.genSeq()).concat(EXPORT_FILE_END));
        SXSSFSheet sheet ;
        CellStyle headerCellStyle;
        CellStyle[] bodyCellStyle;
        try(SXSSFWorkbook workbook = new SXSSFWorkbook(ROW_ACCESS_WINDOW_SIZE);
            FileOutputStream outputStream = new FileOutputStream(fileObject);
        ){
            headerCellStyle = headerCellStyle(workbook);
            //CellStyle cellStyle = workbook.createCellStyle();
            if(null == dataList || dataList.size()==0){
                sheet = workbook.createSheet("0~0");
                PoiCellProcess.writeHeaderCell(sheet,headerCellStyle,headerNames);
            }else{
                bodyCellStyle = bodyCellStyle(workbook, cellFmts,cellNames.length);
                if(dataList.size()<= DATA_SPLIT_GROP_SIZE){
                    sheet = workbook.createSheet(String.format("0~%s",(null==dataList)?0:dataList.size()));
                    PoiCellProcess.writeHeaderCell(sheet,headerCellStyle,headerNames);
                    PoiCellProcess.writeBodyCellByMap(sheet,bodyCellStyle,cellNames,dataList, cellFmts);
                }else{
                    List<List<Map<String, V>>> mData = splitMapList(dataList);
                    for (List<Map<String, V>> subList : mData) {
                        //第一个sheet 参数(sheet名称,sheet的序号)
                        sheet = workbook.createSheet(String.format("%s~%s",
                                (dataList.size() > DATA_SPLIT_GROP_SIZE ?
                                        mData.indexOf(subList) * DATA_SPLIT_GROP_SIZE + 1
                                        : 0) + "",
                                (dataList.size() > DATA_SPLIT_GROP_SIZE ?
                                        (mData.size() == (mData.indexOf(subList) + 1) ? dataList.size() : DATA_SPLIT_GROP_SIZE * (mData.indexOf(subList) + 1))
                                        : dataList.size()) + "")
                        );
                        log.info(">>>sheet name : {}",sheet.getSheetName());
                        PoiCellProcess.writeHeaderCell(sheet,headerCellStyle,headerNames);
                        PoiCellProcess.writeBodyCellByMap(sheet,bodyCellStyle,cellNames,subList, cellFmts);
                    }
                }
            }
            workbook.write(outputStream);
            // 如果只写ServletResponse则无需此句
            workbook.dispose();
        }catch (IOException ioe){
            log.error("导出文件异常了 fileObject:{}",fileObject,ioe);
        }
        log.info("===>已生成导出文件：{}",fileObject.getPath());
        return fileObject;
    }
    /**
     * 写入excel 文件，通过对象的方式
     * @param dataList  数据列表
     * @param headerNames 表头列表
     * @param cellNames 单元格名称
     * @param <V> Object
     * @return File
     */
    public static <V extends Object> void toXlsxStreamByMap(List<Map<String,V>> dataList,
                                                            String[] headerNames,
                                                            String[] cellNames,
                                                            OutputStream out){
        toXlsxStreamByMap(dataList,headerNames,cellNames,null,out);
    }

    /**
     * 写入excel 文件，通过对象的方式
     * @param dataList  数据列表
     * @param headerNames 表头列表
     * @param cellNames 单元格名称
     * @param cellFmts 单元格格式化配置
     * @param <V> Object
     * @return File
     */
    public static <V extends Object> void toXlsxStreamByMap(List<Map<String,V>> dataList,
                                                      String[] headerNames,
                                                      String[] cellNames,
                                                      CellFmt[] cellFmts,
                                                      OutputStream out){
        /**
         * 基础值不可为空
         */
        if(null != headerNames && headerNames.length != cellNames.length){
            log.error(HEADER_NAME);
            return;
        }
        if(null != cellFmts && cellFmts.length!=cellNames.length){
            log.error(CELL_LENGTH_ERROR_MSG);
            return;
        }
        SXSSFSheet sheet ;
        CellStyle headerCellStyle;
        CellStyle[] bodyCellStyle;
        try(SXSSFWorkbook workbook = new SXSSFWorkbook(ROW_ACCESS_WINDOW_SIZE);
        ){
            headerCellStyle = headerCellStyle(workbook);
            //CellStyle cellStyle = workbook.createCellStyle();
            if(null == dataList || dataList.size()==0){
                sheet = workbook.createSheet("0~0");
                PoiCellProcess.writeHeaderCell(sheet,headerCellStyle,headerNames);
            }else{
                bodyCellStyle = bodyCellStyle(workbook, cellFmts,cellNames.length);
                if(dataList.size()<= DATA_SPLIT_GROP_SIZE){
                    sheet = workbook.createSheet(String.format("0~%s",(null==dataList)?0:dataList.size()));
                    PoiCellProcess.writeHeaderCell(sheet,headerCellStyle,headerNames);
                    PoiCellProcess.writeBodyCellByMap(sheet,bodyCellStyle,cellNames,dataList, cellFmts);
                }else{
                    List<List<Map<String, V>>> mData = splitMapList(dataList);
                    for (List<Map<String, V>> subList : mData) {
                        //第一个sheet 参数(sheet名称,sheet的序号)
                        sheet = workbook.createSheet(String.format("%s~%s",
                                (dataList.size() > DATA_SPLIT_GROP_SIZE ?
                                        mData.indexOf(subList) * DATA_SPLIT_GROP_SIZE + 1
                                        : 0) + "",
                                (dataList.size() > DATA_SPLIT_GROP_SIZE ?
                                        (mData.size() == (mData.indexOf(subList) + 1) ? dataList.size() : DATA_SPLIT_GROP_SIZE * (mData.indexOf(subList) + 1))
                                        : dataList.size()) + "")
                        );
                        log.info(">>>sheet name : {}",sheet.getSheetName());
                        PoiCellProcess.writeHeaderCell(sheet,headerCellStyle,headerNames);
                        PoiCellProcess.writeBodyCellByMap(sheet,bodyCellStyle,cellNames,subList, cellFmts);
                    }
                }
            }
            workbook.write(out);
            // 如果只写ServletResponse则无需此句
//            workbook.dispose();
        }catch (IOException ioe){
            log.error("导出文件异常 fileObject:{}", JacksonUtil.toJsonString(headerNames),ioe);
        }
    }

    /**
     * 表头样式
     * @param wb
     * @return
     */
    public static CellStyle headerCellStyle(SXSSFWorkbook wb){
        CellStyle headerStyle = wb.createCellStyle();
        DataFormat dataFormat = wb.createDataFormat();
        headerStyle.setDataFormat(dataFormat.getFormat("@"));
        //水平居中
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        //垂直居中
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //边线
        headerStyle.setBorderTop(BorderStyle.MEDIUM);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.HAIR);
        headerStyle.setBorderRight(BorderStyle.HAIR);
        //边线颜色
        headerStyle.setTopBorderColor(IndexedColors.DARK_RED.index);
        headerStyle.setBottomBorderColor(IndexedColors.GREY_80_PERCENT.index);
        headerStyle.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.index);
        headerStyle.setRightBorderColor(IndexedColors.GREY_50_PERCENT.index);
        //字体
        Font font = wb.createFont();
        font.setColor(IndexedColors.WHITE1.index);
        //font.setFontName("SimHei");
        font.setBold(true);
        font.setFontHeight((short)12);
        font.setFontHeightInPoints((short)12);
        headerStyle.setFont(font);
        //背景色
        //headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
        headerStyle.setFillForegroundColor(IndexedColors.DARK_BLUE.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //允许单元格内换行
        headerStyle.setWrapText(true);
        return  headerStyle;
    }

    public static CellStyle[] bodyCellStyle(SXSSFWorkbook workbook, CellFmt[] cellFmts,int cellLen){
        //LOGGER.info("\r\n==>开始创建表头样式~");
        CellStyle defaultStyle = bodyCellStyle(workbook,null);
        final CellStyle[] cellStyles = new CellStyle[cellLen];
//        if(null == cellFmts || cellFmts.length==0){
//            IntStream.range(0,cellStyles.length).parallel().forEach(i->cellStyles[i]=defaultStyle);
//            //Arrays.stream(cellStyles).parallel().forEach(styleItem->cellStyles=defaultStyle);
//            return cellStyles;
//        }
        // 预设样式
        if(null == cellFmts || cellFmts.length==0){
            for(int i =0;i<cellLen;i++){
                cellStyles[i]=defaultStyle;
            }
            return cellStyles;
        }
        // 自定义样式
        for(int i =0;i<cellLen;i++){
            if(null == cellFmts[i]){
                cellStyles[i]=defaultStyle;
            }else{
                cellStyles[i] = bodyCellStyle(workbook, cellFmts[i]);
            }
        }
        return cellStyles;
    }

    public static CellStyle bodyCellStyle(SXSSFWorkbook workbook, CellFmt cellFmt){
        CellStyle bodyStyle = workbook.createCellStyle();
        DataFormat dataFormat = workbook.createDataFormat();
        if(null == cellFmt || (null==cellFmt.getCellFmt() || "".equals(cellFmt.getCellFmt())) ) {
            bodyStyle.setDataFormat(dataFormat.getFormat("@"));
        }else{
//            bodyStyle.setDataFormat(dataFormat.getFormat(cellFmt.getDataFmt()));
            bodyStyle.setDataFormat(dataFormat.getFormat(cellFmt.getCellFmt()));
        }
        //水平居中
        bodyStyle.setAlignment(HorizontalAlignment.CENTER);
        //垂直居中
        bodyStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //边线
        bodyStyle.setBorderTop(BorderStyle.THIN);
        bodyStyle.setBorderBottom(BorderStyle.THIN);
        bodyStyle.setBorderLeft(BorderStyle.HAIR);
        bodyStyle.setBorderRight(BorderStyle.HAIR);
        //边线颜色
        bodyStyle.setTopBorderColor(IndexedColors.GREY_80_PERCENT.index);
        bodyStyle.setBottomBorderColor(IndexedColors.GREY_80_PERCENT.index);
        bodyStyle.setLeftBorderColor(IndexedColors.GREY_50_PERCENT.index);
        bodyStyle.setRightBorderColor(IndexedColors.GREY_50_PERCENT.index);
        //字体
        Font font = workbook.createFont();
        font.setColor(IndexedColors.BLACK.index);
        //font.setFontName("SimHei");
        //font.setBold(true);
        font.setFontHeight((short)12);
        font.setFontHeightInPoints((short)12);
        bodyStyle.setFont(font);
        //背景色
        bodyStyle.setFillForegroundColor(IndexedColors.WHITE.index);
        bodyStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return bodyStyle;
    }

    /**
     *  将Object数据切割成10W每组的List
     * @param dataList
     * @return
     */
    public static <T extends Object> List<List<T>> splitObjList(List<T> dataList){
        if(dataList.size()<= DATA_SPLIT_GROP_SIZE){
            List<List<T>> targetList  = new ArrayList<>();
            targetList.add(dataList);
            return targetList;
        }
        List<List<T>> mList=new ArrayList<List<T>>();
        //关键点！！！
        int groupCount=(dataList.size()% DATA_SPLIT_GROP_SIZE ==0)?dataList.size()/ DATA_SPLIT_GROP_SIZE :dataList.size()/ DATA_SPLIT_GROP_SIZE +1;
        List<T> group;
        for(int i=0;i<groupCount;i++){
            //不是最后一组数据
            if(i<groupCount-1){
                group=new ArrayList<T>();
                group.addAll(dataList.subList((i==0)?0:i* DATA_SPLIT_GROP_SIZE, (i+1)* DATA_SPLIT_GROP_SIZE));
                mList.add(group);
            }else{
                //最后一组数据这样处理
                group=new ArrayList<T>();
                group.addAll(dataList.subList(i* DATA_SPLIT_GROP_SIZE, dataList.size()));
                mList.add(group);
            }
        }
        return mList;
    }

    /**
     *  将Map数据切割成10W每组的List
     * @param dataList
     * @return
     */
    public static <V extends Object> List<List<Map<String,V>>> splitMapList(List<Map<String,V>> dataList){
        if(dataList.size()<= DATA_SPLIT_GROP_SIZE){
            List<List<Map<String,V>>> targetList  = new ArrayList<>();
            targetList.add(dataList);
            return targetList;
        }
        List<List<Map<String,V>>> mList=new ArrayList<List<Map<String,V>>>();
        //关键点！！！
        int groupCount=(dataList.size()% DATA_SPLIT_GROP_SIZE ==0)?dataList.size()/ DATA_SPLIT_GROP_SIZE :dataList.size()/ DATA_SPLIT_GROP_SIZE +1;
        List<Map<String,V>> group;
        for(int i=0;i<groupCount;i++){
            //不是最后一组数据
            if(i<groupCount-1){
                group=new ArrayList<Map<String,V>>();
                group.addAll(dataList.subList((i==0)?0:i* DATA_SPLIT_GROP_SIZE, (i+1)* DATA_SPLIT_GROP_SIZE));
                mList.add(group);
            }else{
                //最后一组数据这样处理
                group=new ArrayList<Map<String,V>>();
                group.addAll(dataList.subList(i*DATA_SPLIT_GROP_SIZE, dataList.size()));
                mList.add(group);
            }
        }
        return mList;
    }

}