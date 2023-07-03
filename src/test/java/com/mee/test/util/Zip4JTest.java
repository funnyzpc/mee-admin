package com.mee.test.util;

import cn.hutool.core.util.ZipUtil;
import com.mee.common.util.JacksonUtil;
import net.lingala.zip4j.ZipFile;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.Reader;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class Zip4JTest {


    @Test
    public void test01(){
        File zipFie = new File("D:\\tmp\\14909_917最后一轮.zip");
        File outputDir = new File("D:\\tmp\\unzipdir");
        File returnFile =  ZipUtil.unzip(zipFie,outputDir);
        System.out.printf("path:"+returnFile.getPath());
        System.out.printf("name:"+returnFile.getName());
    }

    @Test
    public void test02(){
        ZipFile zipFile = new ZipFile("D:\\tmp\\14909_917最后一轮.zip");
        File fileDir = new File("D:\\tmp\\unzipdir");
        try {
            zipFile.extractAll("D:\\tmp\\unzipdir");
        }catch (Exception e){
            e.printStackTrace();
            return;
        }
        List<File> fileList = listAll(fileDir);
        if(fileList.isEmpty()){
            System.out.printf("压缩包为空");
            return;
        }
        List<Map> dataResult = new ArrayList<Map>(80);
        for(File f:fileList){
            List<Map> fileDatas = listCsvData(f);
            if(null != fileDatas && !fileDatas.isEmpty()){
                dataResult.addAll(fileDatas);
            }
        }
        System.out.printf("文件数据内容大小: "+dataResult.size());
        // System.out.println("CSV文件内容为："+ JacksonUtil.toJsonString(dataResult));

        // 过滤 品牌一 or 品牌二  or 品牌一&品牌二
        System.out.println(JacksonUtil.toJsonString(dataResult.get(0)));
        System.out.println(JacksonUtil.toJsonString(dataResult.get(dataResult.size()-1)));

        List<Map> filterResult = dataResult.parallelStream().filter(i->
          i.get("宝贝名称").toString().contains("品牌二") || i.get("宝贝名称").toString().contains("品牌一")
        ).collect(Collectors.toCollection(ArrayList::new));

        System.out.println("过滤后大小"+filterResult.size()+"及数据:"+JacksonUtil.toJsonString(filterResult));

    }

    // 获取csv文件内容
    public static List<Map> listCsvData(File file){
        try {
            // Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\60003843\\Desktop\\report_20200811134735.csv"));
            Reader reader = Files.newBufferedReader(file.toPath());
            CSVParser csvParser = CSVFormat.DEFAULT.parse(reader);
            String[] dataHeader = null;
            List<CSVRecord> rst = csvParser.getRecords();
            List<Map> dataList = new ArrayList<Map>(rst.size());
            for (CSVRecord record : rst) {
                LinkedHashMap<String, Object> item = new LinkedHashMap<String, Object>(record.size(), 1.0F);
                for (int i = 0; i < record.size(); i++) {
                    if (record.getRecordNumber() == 1) {
                        if (0 == i) {
                            dataHeader = new String[record.size()];
                            dataHeader[i] = record.get(i).replaceAll("\uFEFF", "").trim();
                            continue;
                        }
                        dataHeader[i] = record.get(i).trim();
                        continue;
                    }
                    item.put(dataHeader[i], record.get(i).trim());
                    // System.out.print(record.get(i).trim() + " ");
                }
                if (!item.isEmpty()) {
                    dataList.add(item);
                }
            }
            return dataList;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    // 列出所有的文件(仅当前级及下一级)
    public static List<File> listAll(File dir) {
        List<File> fileList = new ArrayList<File>(8);
        for(File f:dir.listFiles()){
            if(f.isFile()){
                fileList.add(f);
                continue;
            }
            for(File innerFile:f.listFiles()){
                if(innerFile.isFile()){
                    fileList.add(innerFile);
                    continue;
                }
            }
        }
        return fileList;
    }

    /** 删除文件 **/
    @Test
    public void test03()throws Exception{
        FileUtils.forceDeleteOnExit(new File("D:\\tmp\\unzipdir\\14909_917╫ε║≤╥╗┬╓"));
    }
}
