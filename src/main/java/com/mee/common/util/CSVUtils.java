package com.mee.common.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * @auther shadow
 * @description CSV读写工具类
 */
public class CSVUtils {
    private static final Logger log = LoggerFactory.getLogger(CSVUtils.class);

    // 获取csv文件内容 need import:commons-csv
    /*
    public static List<Map> listCsvData(File file){
        try {
            if(null == file || !file.exists()){
                log.error("传入文件为空或不存在");
                return null;
            }
            Reader reader = Files.newBufferedReader(file.toPath());
            CSVParser csvParser = CSVFormat.DEFAULT.parse(reader);
            // 首行内容作为key
            String[] dataHeader = null;
            List<CSVRecord> rst = csvParser.getRecords();
            List<Map> dataList = new ArrayList<Map>(rst.size());
            for (CSVRecord record : rst) {
                LinkedHashMap<String, Object> item = new LinkedHashMap<String, Object>(record.size(), 1.0F);
                for (int i = 0; i < record.size(); i++) {
                    if (record.getRecordNumber() == 1) {
                        // 首行特殊处理(获取key信息+替换特殊字符)
                        if (0 == i) {
                            dataHeader = new String[record.size()];
                            dataHeader[i] = record.get(i).replaceAll("\uFEFF", "").trim();
                            continue;
                        }
                        dataHeader[i] = record.get(i).trim();
                        continue;
                    }
                    item.put(dataHeader[i], record.get(i).trim());
                }
                if (!item.isEmpty()) {
                    dataList.add(item);
                }
            }
            return dataList;
        }catch (Exception e){
            log.error("获取CSV文件内容错误:",e);
            return null;
        }
    }
    */

    public static List<Map> listGigCsv(File file){
        if(null == file || file.isDirectory() || !file.exists()){
            log.error("传入文件为空");
            return null;
        }
        String[] headers = null;
        List<Map> dataList = new ArrayList<Map>(100);
        try (LineIterator it = FileUtils.lineIterator(file, "UTF-8")){
            while (it.hasNext()) {
                String line = it.nextLine().replaceAll("\uFEFF","");
                if(null == headers){
                    // 首行为空的禁止往下读
                    if(StringUtils.isEmpty(line)){
                        log.error("文件内容为空："+ file.getName());
                        return null;
                    }
                    headers = line.split(",");
                    continue;
                }
                String[] dataArr = line.substring(1,line.length()-1).split("\",\"");
                Map<String,Object> data = new HashMap<>(dataArr.length,1);
                if(headers.length>dataArr.length){
                    dataArr = (line+it.nextLine().replaceAll("\uFEFF","")).split("\",\"");
                    if(dataArr[0].startsWith("\"")){
                        dataArr[0] = dataArr[0].substring(1);
                    }
                    if(dataArr[dataArr.length-1].endsWith("\"")){
                        dataArr[dataArr.length-1] = dataArr[dataArr.length-1].substring(0,dataArr[dataArr.length-1].length()-1);
                    }
                }
                for(int i=0;i<headers.length;i++){
                    data.put(headers[i],dataArr[i]);
                }
                dataList.add(data);
            }
            return dataList;
        }catch (Exception e){
            log.error("异常了:",e);
            return null;
        }
    }

    // 读取标准CSV文件(office转换)
    public static List<Map> listGigCsv2(File file){
        if(null == file || file.isDirectory() || !file.exists()){
            log.error("传入文件为空");
            return null;
        }
        String[] headers = null;
        List<Map> dataList = new ArrayList<Map>(100);
        try (LineIterator it = FileUtils.lineIterator(file, "GBK")){
            while (it.hasNext()) {
                String line = it.nextLine().replaceAll("\uFEFF","");
                if(null == headers){
                    // 首行为空的禁止往下读
                    if(StringUtils.isEmpty(line)){
                        log.error("文件内容为空："+ file.getName());
                        return null;
                    }
                    headers = line.split(",");
                    continue;
                }
                String[] dataArr = line.split(",");
                Map<String,Object> data = new HashMap<>(dataArr.length,1);
                if(headers.length<dataArr.length){
                    // 分号单独处理
                    String[] data_arr= dataArr;
                    int ss = 0;
                    for(int i=0;i<data_arr.length;i++){
                        // 最后一个特殊处理
                        if(i+ss==data_arr.length){
                            break;
                        }
                        if(null!=data_arr[i] && data_arr[i].startsWith("\"")){
                            dataArr[i]=data_arr[i]+data_arr[i+1];
                            ss=ss+1;
                            continue;
                        }else{
                            dataArr[i]=data_arr[i+ss];
                        }
                    }
                }

                if(dataArr.length<(headers.length-1)){
                    line = (line+it.nextLine().replaceAll("\uFEFF",""));
                    dataArr = line.split(",");
                    while(dataArr.length<(headers.length-1)){
                        line = (line+it.nextLine().replaceAll("\uFEFF",""));
                        dataArr = line.split(",");
                    }
                }

                for(int i=0;i<headers.length;i++){

                    // 尾部为空补全
                    if((i+1)==headers.length && (headers.length-1)==dataArr.length){
                        data.put(headers[i],null);
                        continue;
                    }

                    data.put(headers[i],dataArr[i]);
                }
                dataList.add(data);
            }
            return dataList;
        }catch (Exception e){
            e.printStackTrace();
            log.error("异常了 position:{}",dataList.size(),e);
            return null;
        }
    }


    // 读取标准CSV文件(office转换)
    public static List<Map> listGigCsv3(File file){
        if(null == file || file.isDirectory() || !file.exists()){
            log.error("传入文件为空");
            return null;
        }
        String[] headers = null;
        List<Map> dataList = new ArrayList<Map>(100);
        try (LineIterator it = FileUtils.lineIterator(file, "GBK")){
            while (it.hasNext()) {
                String line = it.nextLine().replaceAll("\uFEFF","");
                if(null == headers){
                    // 首行为空的禁止往下读
                    if(StringUtils.isEmpty(line)){
                        log.error("文件内容为空："+ file.getName());
                        return null;
                    }
                    headers = line.split(",");
                    continue;
                }
                String[] dataArr = line.split(",");
                Map<String,Object> data = new HashMap<>(dataArr.length,1);
                if(headers.length<dataArr.length){
                    // 分号单独处理
                    String[] data_arr= dataArr;
                    int ss = 0;
                    for(int i=0;i<data_arr.length;i++){
                        // 最后一个特殊处理
                        if(i+ss==data_arr.length){
                            break;
                        }
                        if(null!=data_arr[i] && data_arr[i].startsWith("\"")){
                            dataArr[i]=data_arr[i]+data_arr[i+1];
                            ss=ss+1;
                            continue;
                        }else{
                            dataArr[i]=data_arr[i+ss];
                        }
                    }
                }

                // 对于记录行存在换行的需要循环处理
                if(dataArr.length<(headers.length-1)){
                    line = (line+it.nextLine().replaceAll("\uFEFF",""));
                    dataArr = line.split(",");
                    while(dataArr.length<(headers.length-1)){
                        line = (line+it.nextLine().replaceAll("\uFEFF",""));
                        dataArr = line.split(",");
                    }
                }

                for(int i=0;i<headers.length;i++){
                    // 尾部为空补全
                    if((i+1)==headers.length && (headers.length-1)==dataArr.length){
                        data.put(headers[i],null);
                        continue;
                    }
                    data.put(headers[i],dataArr[i]);
                }

                dataList.add(data);
            }
            return dataList;
        }catch (Exception e){
            log.error("异常了 position:{}",dataList.size(),e);
            return null;
        }
    }
}
