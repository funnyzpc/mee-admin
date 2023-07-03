package com.mee.common.util.excel;


import com.mee.common.util.DateUtil;
import com.mee.common.util.SeqGenUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author: shadow
 * @Description 读取xlsx
 **/
public class ExcelReadUtil {
    private static final Logger log = LoggerFactory.getLogger(ExcelReadUtil.class);

    /**
     * 当前操作系统目录分隔符
     */
    private static final String FILE_SYSTEM_SEPARATOR_KEY = "file.separator";

    private static final String SYSTEM_TMP_DIR;
    static {
        if(System.getProperty(FILE_SYSTEM_SEPARATOR_KEY).equals("/")){
            SYSTEM_TMP_DIR = "/tmp/import_dir";
        }else{
            SYSTEM_TMP_DIR = "D:/tmp/import_dir";
        }
    }

    /**
     * 读取Excel并转换为Map数据（上传文件方式）
     * @param multipartFile 上传文件
     * @param colCount 读取最大列
     * @return
     */
    @Deprecated
    public static List<Map<String,String>> readXlsx2Map(MultipartFile multipartFile, int colCount){
        File tmpFile = new File(SYSTEM_TMP_DIR);
        if(!tmpFile.exists()){
            tmpFile.mkdirs();
        }
        tmpFile = new File(String.format("%s%s%s",SYSTEM_TMP_DIR,File.separator, SeqGenUtil.genSeq(),multipartFile.getOriginalFilename()));
        try {
            multipartFile.transferTo(tmpFile);
            return readXlsx2Map(tmpFile,colCount);
        }catch (IOException iOException){
            log.error("\r\n>>>文件导入异常:",iOException);
            // tmpFile.delete();
        }
        return null;
    }

    /***
     * 读取Excel并转换为Map数据（本地文件形式）
     * @param file 本地文件
     * @param colCount 读取最大列
     */
    @Deprecated
    public static List<Map<String,String>> readXlsx2Map(File file, int colCount) {
        List<Map<String,String>> varList = new ArrayList<>();
        try(FileInputStream fi = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(fi);
        ){
            //sheet 从0开始
            XSSFSheet sheet= wb.getSheetAt(0);
            //取得最后一行的行号
            int rowNum = sheet.getLastRowNum() + 1;
            //第二行循环开始 startrow:1
            for (int i = 1; i < rowNum; i++) {
                //行
                XSSFRow row = sheet.getRow(i);
                if(null == row ){
                    break;
                }
                Map<String,String> varpd = new Hashtable<>();
                //每行的最后一个单元格位置
                //int cellNum = row.getLastCellNum();
                //每行的第一列循环开始 startcol
                for (int j = 0; j < colCount; j++) {
                    XSSFCell cell = row.getCell(Short.parseShort(j + ""));
                    String cellValue = null;
                    String cellType = null;
                    if (null != cell && !"".equals(cell.getCellTypeEnum().name())) {
                        // 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
                        cellType = cell.getCellTypeEnum().name();
                        if("STRING".equals(cellType)){
                            cellValue=cell.getStringCellValue();
                        }else if("NUMERIC".equals(cellType)){
                            cellValue = String.valueOf(cell.getNumericCellValue());
                        }else if("BOOLEAN".equals(cellType)){
                            cellValue = String.valueOf(cell.getBooleanCellValue());
                        }else if("BLANK".equals(cellType)){
                            cellValue = "";
                            //当前行第一列为空或null的直接跳过
                            if(0 == j){
                                break;
                            }
                        }else{
                            log.error("导入>未知的数据类型!");
                        }
                    }else{
                        //当前行第一列为空或null的直接跳过
                        if(0 == j){
                            break;
                        }
                        continue;
                    }
                    varpd.put("var"+j, cellValue);
                }
                //若单账号为空
                if(!varpd.isEmpty()) {
                    varList.add(varpd);
                }
            }
        } catch (IOException iOException) {
            iOException.printStackTrace();
            log.error("错误：",iOException);
        }
        //删除传入文件
        // file.delete();
        return varList;
    }


    /**
     * 文件的方式读取
     * @param file 文件
     * @param colCount 读取最大列
     * @return
     */
    public static List<Map> xlsx2Map(File file, int colCount) {
        return xlsx2Map(file,colCount,0,null);
    }

    /**
     * 流的方式读取
     * @param fis    文件流
     * @param colCount 读取最大列
     * @return
     */
    public static List<Map> xlsx2Map(InputStream fis, int colCount) {
        return xlsx2Map(fis,colCount,0,null);
    }

    public static List<Map> xlsx2Map(File file, int colCount,Map<String,String> field_mapping) {
        return xlsx2Map(file,colCount,0,field_mapping);
    }

    /**
     *
     * @param file XLSX文件
     * @param colCount 读取最大列(1~MAX)
     * @param startRow 开始读取行(0~MAX)
     * @return
     */
    public static List<Map> xlsx2Map(File file, int colCount,int startRow,Map<String,String> field_mapping) {
        try(FileInputStream fi = new FileInputStream(file);
            XSSFWorkbook wb = new XSSFWorkbook(fi);
        ){
            //Sheet1 下标0开始
            XSSFSheet sheet= wb.getSheetAt(0);
            //取得最后一行的行号
            int rowNum = sheet.getLastRowNum() + 1;
            List<Map> dataList = new ArrayList<Map>(rowNum);
            // header
            String[] header_arr = new String[colCount];
            //第二行循环开始 startrow:1
            for (int i = startRow; i < rowNum; i++) {
                //行
                XSSFRow row = sheet.getRow(i);
                if(null == row ){
                    break;
                }
                Map<String,String> dataItem = new HashMap<String,String>(colCount,1);
                //每行的最后一个单元格位置
                //int cellNum = row.getLastCellNum();
                //每行的第一列循环开始 startcol
                for (int j = 0; j < colCount; j++) {
                    // XSSFCell cell = row.getCell(Short.parseShort(j + ""));
                    XSSFCell cell = row.getCell( j );

                    if(startRow==i) {
                        // header row
                        if (null != cell && !"".equals(cell.getCellTypeEnum().name())) {
                            header_arr[j] = readCellValue(cell);
                            if(null!=field_mapping){
                                String mapping_value = field_mapping.get(header_arr[j]);
                                header_arr[j]= StringUtils.isEmpty(mapping_value)?header_arr[j]:mapping_value;
                                continue;
                            }
                            // 定义别名了使用英文别名作为字段名
                            if(header_arr[j].contains("\n")){
                                header_arr[j]=header_arr[j].split("\n")[1];
                                continue;
                            }
                        } else {
                            header_arr[j] = "NULL_CELL_" + i + "_" + j;
                        }
                    }else{
                        // data row
                        if (null != cell && !"".equals(cell.getCellTypeEnum().name())) {
                            dataItem.put(header_arr[j], readCellValue(cell));
                        } else {
                            dataItem.put(header_arr[j], null);
                        }
                    }
                }
                // 不为空则添加
                if(!dataItem.isEmpty()) {
                    dataList.add(dataItem);
                }
            }
            return dataList;
        } catch (IOException iOException) {
            log.error("错误 file:{}",file,iOException);
            return new ArrayList();
        }
        // 删除传入文件
        // file.delete();
    }

    public static List<Map> xlsx2Map(InputStream fi, int colCount, int startRow, Map<String,String> field_mapping) {
        try(fi;
            XSSFWorkbook wb = new XSSFWorkbook(fi);
        ){
            //Sheet1 下标0开始
            XSSFSheet sheet= wb.getSheetAt(0);
            //取得最后一行的行号
            int rowNum = sheet.getLastRowNum() + 1;
            List<Map> dataList = new ArrayList<Map>(rowNum);
            // header
            String[] header_arr = new String[colCount];
            //第二行循环开始 startrow:1
            for (int i = startRow; i < rowNum; i++) {
                //行
                XSSFRow row = sheet.getRow(i);
                if(null == row ){
                    break;
                }
                Map<String,String> dataItem = new HashMap<String,String>(colCount,1);
                //每行的最后一个单元格位置
                //int cellNum = row.getLastCellNum();
                //每行的第一列循环开始 startcol
                for (int j = 0; j < colCount; j++) {
                    // XSSFCell cell = row.getCell(Short.parseShort(j + ""));
                    XSSFCell cell = row.getCell( j );

                    if(startRow==i) {
                        // header row
                        if (null != cell && !"".equals(cell.getCellTypeEnum().name())) {
                            header_arr[j] = readCellValue(cell);
                            if(null!=field_mapping){
                                String mapping_value = field_mapping.get(header_arr[j]);
                                header_arr[j]= StringUtils.isEmpty(mapping_value)?header_arr[j]:mapping_value;
                                continue;
                            }
                            // 定义别名了使用英文别名作为字段名
                            if(header_arr[j].contains("\n")){
                                header_arr[j]=header_arr[j].split("\n")[1];
                                continue;
                            }
                        } else {
                            header_arr[j] = "NULL_CELL_" + i + "_" + j;
                        }
                    }else{
                        // data row
                        if (null != cell && !"".equals(cell.getCellTypeEnum().name())) {
                            dataItem.put(header_arr[j], readCellValue(cell));
                        } else {
                            dataItem.put(header_arr[j], null);
                        }
                    }
                }
                // 不为空则添加
                if(!dataItem.isEmpty()) {
                    dataList.add(dataItem);
                }
            }
            return dataList;
        } catch (IOException iOException) {
            log.error("错误 file:",iOException);
            return new ArrayList();
        }
        // 删除传入文件
        // file.delete();
    }

    private static String readCellValue(XSSFCell cell){
        // 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
        String cellType = cell.getCellTypeEnum().name();
        switch (cellType){
            case "STRING":
                return cell.getStringCellValue();
            case "NUMERIC":
                if(org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)){
                    return DateUtil.format(cell.getDateCellValue(), "yyyy-MM-dd");
                }
                // 强制转
                cell.setCellType(CellType.STRING);
                String cellValue = cell.getStringCellValue();
                // return String.valueOf(cell.getNumericCellValue());
                // 包含科学计数法的格式化为小数四位
                if(cellValue.contains("E")){
                    return new BigDecimal(cellValue).setScale(4, BigDecimal.ROUND_HALF_UP).toPlainString();
                }
                return cellValue;
            case "BOOLEAN":
                return String.valueOf(cell.getBooleanCellValue());
            case "BLANK":
                return "";
            default:
                log.error("导入>未知数据类型!");
                return null;
        }
    }

    public static List<Map> xls2Map(File file, int colCount) {
        return xls2Map(file,colCount,0,null);
    }

    public static List<Map> xls2Map(File file, int colCount,Map<String,String> field_mapping) {
        return xls2Map(file,colCount,0,field_mapping);
    }

    /**
     * @ see this <a>https://poi.apache.org/components/spreadsheet/</a>
     * @param file 上传文件
     * @param colCount 最大列 1~max
     * @param startRow 开始行 0~max
     * @param field_mapping 字段映射
     * @return
     */
    public static List<Map> xls2Map(File file, int colCount,int startRow,Map<String,String> field_mapping) {
        if(!file.exists()){
            return new ArrayList<Map>(0);
        }
        try(FileInputStream fi = new FileInputStream(file);
            HSSFWorkbook wb = new HSSFWorkbook(fi);
        ){
            //Sheet1 下标0开始
            HSSFSheet sheet= wb.getSheetAt(0);
            //取得最后一行的行号
            int rowNum = sheet.getLastRowNum() + 1;
            List<Map> dataList = new ArrayList<Map>(rowNum);
            // header
            String[] header_arr = new String[colCount];
            //第二行循环开始 startrow:1
            for (int i = startRow; i < rowNum; i++) {
                //行
                HSSFRow row = sheet.getRow(i);
                if(null == row ){
                    break;
                }
                Map<String,String> dataItem = new HashMap<String,String>(colCount,1);
                //每行的最后一个单元格位置
                //int cellNum = row.getLastCellNum();
                //每行的第一列循环开始 startcol
                for (int j = 0; j < colCount; j++) {
                    // XSSFCell cell = row.getCell(Short.parseShort(j + ""));
                    HSSFCell cell = row.getCell( j );

                    if(startRow==i) {
                        // header row
                        if (null != cell && !"".equals(cell.getCellTypeEnum().name())) {
                            header_arr[j] = readXlsCellValue(cell);
                            if(null!=field_mapping){
                                String mapping_value = field_mapping.get(header_arr[j]);
                                header_arr[j]= StringUtils.isEmpty(mapping_value)?header_arr[j]:mapping_value;
                                continue;
                            }
                            // 定义别名了使用英文别名作为字段名
                            if(header_arr[j].contains("\n")){
                                header_arr[j]=header_arr[j].split("\n")[1];
                                continue;
                            }
                        } else {
                            header_arr[j] = "NULL_CELL_" + i + "_" + j;
                        }
                    }else{
                        // data row
                        if (null != cell && !"".equals(cell.getCellTypeEnum().name())) {
                            dataItem.put(header_arr[j], readXlsCellValue(cell));
                        } else {
                            dataItem.put(header_arr[j], null);
                        }
                    }
                }
                // 不为空则添加
                if(!dataItem.isEmpty()) {
                    dataList.add(dataItem);
                }
            }
            return dataList;
        } catch (IOException iOException) {
            log.error("错误 file:{}",file,iOException);
            return new ArrayList();
        }
    }

    private static String readXlsCellValue(HSSFCell cell){
        // 判断excel单元格内容的格式，并对其进行转换，以便插入数据库
        String cellType = cell.getCellTypeEnum().name();
        switch (cellType){
            case "STRING":
                return cell.getStringCellValue();
            case "NUMERIC":
                if(org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)){
                    return DateUtil.format(cell.getDateCellValue(), "yyyy-MM-dd");
                }
                // 强制转
                cell.setCellType(CellType.STRING);
                String cellValue = cell.getStringCellValue();
                // return String.valueOf(cell.getNumericCellValue());
                // 包含科学计数法的格式化为小数四位
                if(cellValue.contains("E")){
                    return new BigDecimal(cellValue).setScale(4, BigDecimal.ROUND_HALF_UP).toPlainString();
                }
                return cellValue;
            case "BOOLEAN":
                return String.valueOf(cell.getBooleanCellValue());
            case "BLANK":
                return "";
            default:
                log.error("导入>未知数据类型!");
                return null;
        }
    }

}