package com.mee.test.base;


import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CsvReaderTest {

/*
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("C:\\Users\\my33\\Desktop\\report_20200811134735.csv");

        Iterable <CSVRecord> records = CSVFormat.EXCEL.withTrim().parse(reader);
        for (CSVRecord csvRecord: records) {
            System.out.println(csvRecord);
            System.out.println(csvRecord.get("商品ID"));
        }
    }
*/
    public static void main(String[] args) throws Exception {
        Reader reader = Files.newBufferedReader(Paths.get("C:\\Users\\my33\\Desktop\\report_20200811134735.csv"));
        CSVParser csvParser = CSVFormat.DEFAULT.parse(reader);

        String[] dataHeader = null;
        List<CSVRecord> rst = csvParser.getRecords();
        List<Map> dataList = new ArrayList<Map>(rst.size());
        for(CSVRecord record:rst){
            LinkedHashMap<String,Object> item = new LinkedHashMap<String,Object>(record.size(),1.0F);
            for(int i=0;i<record.size();i++){
                        if(record.getRecordNumber()==1){
                            if(0==i) {
                                dataHeader = new String[record.size()];
                                dataHeader[i] = record.get(i).replaceAll("\uFEFF","").trim();
                                continue;
                            }
                            dataHeader[i] = record.get(i).trim();
                            continue;
                        }
                        item.put(dataHeader[i],record.get(i).trim());
                        System.out.print(record.get(i).trim()+" ");
            }
            if(!item.isEmpty()){
                dataList.add(item);
            }
            System.out.println();
        };
        System.out.println(dataList);
    }

}
