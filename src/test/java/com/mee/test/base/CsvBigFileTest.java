package com.mee.test.base;

import com.mee.common.util.CSVUtils;
import com.mee.common.util.excel.ExcelReadUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

public class CsvBigFileTest {

    @Test
    public void test01(){
        Instant time_start = Instant.now();

        File file = new File("D:\\tmp\\订单明细\\tmpp.csv");
        List<Map> data_list_01 = CSVUtils.listGigCsv2(file);

        System.out.println(data_list_01.size());
        Instant time_end = Instant.now();
        System.out.println("读取耗时===>"+(Duration.between(time_start, time_end).getSeconds())+"秒");

    }

    @Test
    public void test02(){
        Instant time_start = Instant.now();

        File file = new File("D:\\tmp\\订单明细\\叮当旗舰店_智能分析_订单信息_10月21日_164351206.xlsx");
        List<Map> data_list = ExcelReadUtil.xlsx2Map(file,23);
        Instant time_end = Instant.now();
        System.out.println("读取耗时===>"+(Duration.between(time_start, time_end).getSeconds())+"秒");
        System.out.println(data_list.get(0));
        System.out.println(data_list.get(1));
    }

    @Test
    public void test03(){
        Instant time_start = Instant.now();

        File file = new File("D:\\tmp\\订单明细\\002.xls");
        List<Map> data_list = ExcelReadUtil.xls2Map(file,23);
        Instant time_end = Instant.now();
        System.out.println("读取耗时===>"+(Duration.between(time_start, time_end).getSeconds())+"秒");
        System.out.println(data_list.size());
        System.out.println(data_list.get(0));
        System.out.println(data_list.get(1));
    }
}
