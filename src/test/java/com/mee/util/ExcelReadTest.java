package com.mee.util;

import com.mee.common.util.excel.ExcelReadUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.Map;

public class ExcelReadTest {

    private static final Logger log = LoggerFactory.getLogger(ExcelReadTest.class);

    /**
     * read data by xlsx
     */
    @Test
    public void readXlsx(){
        File file = new File("C:\\Users\\60003843\\Desktop\\doc\\ETL reflection.xlsx");
        List<Map<String,String>> results = ExcelReadUtil.readXlsx2Map(file,8);
        log.info("读取结果 ===> \r\n {}",results);

    }
}
