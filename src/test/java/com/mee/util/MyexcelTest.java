package com.mee.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.text.csv.CsvData;
import cn.hutool.core.text.csv.CsvReader;
import cn.hutool.core.text.csv.CsvRow;
import cn.hutool.core.text.csv.CsvUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import org.junit.jupiter.api.Test;

import java.util.List;

public class MyexcelTest {

/*    @Test
    public void test01()throws Exception{
        Path path = Paths.get("C:\\Users\\60003843\\Desktop\\C店业务\\店铺报表.csv");
        List<Map> result = SaxExcelReader.of(Map.class)
                // .rowFilter(row -> row.getRowNum() > 0) // 如无需过滤，可省略该操作，0代表第一行
                //.charset("GBK") // 目前仅.csv文件有效，设置当前文件的编码
                .read(path.toFile());// 可接收inputStream

        System.out.println(result);
    }*/

/*    @Test
    public void test02()throws Exception{
        // get html file
        File htmlFile = new File("/Users/liaochong/Downloads/example.html");

        Workbook workbook = HtmlToExcelFactory.readHtml(htmlFile).useDefaultStyle().build();

        FileExportUtil.export(workbook, new File("/Users/liaochong/Downloads/excel.xlsx"));
    }*/

    // csv
    @Test
    public void test03(){
        CsvReader reader = CsvUtil.getReader();
        CsvData data = reader.read(FileUtil.file("C:\\Users\\60003843\\Desktop\\C店业务\\店铺报表.csv"));
        List<CsvRow> rows = data.getRows();
        System.out.println(rows);
    }

    // excel
    @Test
    public void test04(){
        ExcelReader reader = ExcelUtil.getReader("C:\\Users\\60003843\\Desktop\\C店业务\\CC.xlsx");
        List<List<Object>> readAll = reader.read();
        System.out.println(readAll);
    }
}
