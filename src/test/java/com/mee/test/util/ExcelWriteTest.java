package com.mee.test.util;

import com.mee.common.util.DateUtil;
import com.mee.common.util.excel.CellFmt;
import com.mee.common.util.excel.ExcelWriteUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

/**
 * @author: funnyzpc
 * @Description 导出工具类测试
 **/
public class ExcelWriteTest {
    private static final List<BookDTO> objList = new ArrayList<BookDTO>(){
        {
            BookDTO blog = new BookDTO();
            blog.setId(897978L);
            blog.setName("《树上的男爵》");
            blog.setLabel("推荐");
            blog.setDatePublication(new Date());
            blog.setStatusPublication(true);
            blog.setSerialPublication(new BigDecimal(77894588756L));
            blog.setCreateTime(new Timestamp(System.currentTimeMillis()));
            blog.setPrice(23.33);

            BookDTO blog2 = new BookDTO();
            blog2.setId(4566L);
            blog2.setName("《动物农庄》");
            blog2.setLabel("精选");
            blog2.setDatePublication(Date.from(LocalDate.of(2018,9,9).atStartOfDay(DateUtil.zoneId).toInstant()));
            blog2.setStatusPublication(false);
            blog2.setSerialPublication(new BigDecimal(66999410016L));
            blog2.setCreateTime(new Timestamp(new Date().getTime()));
            blog2.setPrice(23.456D);


            BookDTO blog3 = new BookDTO();
            blog3.setId(9123499L);
            blog3.setName("《小王子》");
            blog3.setLabel("儿童");
            blog3.setDatePublication(Date.from(LocalDate.of(2018,9,9).atStartOfDay(DateUtil.zoneId).toInstant()));
            blog3.setStatusPublication(false);
            blog3.setSerialPublication(new BigDecimal(66999410016L));
            blog3.setCreateTime(new Timestamp(new Date().getTime()));
            blog3.setPrice(158.46D);

            BookDTO blog4 = new BookDTO();
            blog4.setId(9999999999L);
            blog4.setName("《礼记》");
            blog4.setLabel("古典");
            blog4.setDatePublication(Date.from(LocalDate.of(2018,12,9).atStartOfDay(DateUtil.zoneId).toInstant()));
            blog4.setStatusPublication(false);
            blog4.setSerialPublication(new BigDecimal(9522112L));
            blog4.setCreateTime(new Timestamp(new Date().getTime()));
            blog4.setPrice(28.46D);

            add(blog);
            add(blog2);
            add(blog3);
            add(blog4);
        }
    };

    private static final List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>() {
        {
            Map<String,Object> map1 = new HashMap<>();
            map1.put("id",1234567L);
            map1.put("name","《小王子》");
            map1.put("tag","儿童");
            map1.put("price",158.463D);
            map1.put("datePublication",Date.from(LocalDate.of(2018,9,9).atStartOfDay(DateUtil.zoneId).toInstant()));
            map1.put("statusPublication",false);
            map1.put("createTime",new Date());
            map1.put("readTime",15);

            Map<String,Object> map2 = new HashMap<>();
            map2.put("id",7765434L);
            map2.put("name","《礼记》");
            map2.put("tag","古典");
            map2.put("price",28.469D);
            map2.put("datePublication",Date.from(LocalDate.of(2018,12,9).atStartOfDay(DateUtil.zoneId).toInstant()));
            map2.put("statusPublication",true);
            map2.put("createTime",new Timestamp(new Date().getTime()));
            map2.put("readTime",150);

            add(map1);
            add(map2);
        }
    };
    /**
     * export by object list
     */
    @Test
    public void expByObj(){
        //表头
        String[] headerNames = {"书籍编号 \r\n Book Number","书籍名称 \r\n Book Name","售价\r\nPrice","标签\r\nLabel",
                "出版日期 \r\n Publish Date","发行状态\r\nStatus","发行编号 \r\n Publish SerialNo","记录创建时间\r\nCreate Time"};
        //数据字段
        String[] cellNames = {"id","name","price","label","datePublication","statusPublication","serialPublication","createTime"};
        //数据格式
        CellFmt[] cellFmts= {null,null,CellFmt.NUMERIC_02,null,CellFmt.CUSTOM_02,CellFmt.TEXT_01,null,CellFmt.DATE_01};
        // File target = ExcelWriteUtil.toXlsxByObj(objList,headerNames,cellNames,cellFmts);
        //不设置单元格格式，所有所有导出数据均为字符串格式
        File target = ExcelWriteUtil.toXlsxByObj(objList,headerNames,cellNames);
        System.out.println(target.getPath());
    }


    /**
     * export by map list
     */
    @Test
    public void expByMap(){
        //表头
        String[] headerNames = {"书籍编号 \r\n Book Number","书籍名称 \r\n Book Name","售价\r\nPrice","标签\r\nTag",
                "阅读时长","出版日期 \r\n Publish Date","发行状态\r\nStatus","记录创建时间\r\nCreate Time"};
        //数据字段
        String[] cellNames = {"id","name","price","tag","readTime","datePublication","statusPublication","createTime"};
        //数据格式
        CellFmt[] cellFmts= {null,null,CellFmt.NUMERIC_02,null,CellFmt.GENERAL_02,CellFmt.CUSTOM_02,CellFmt.TEXT_01,CellFmt.CUSTOM_01};
        // File target = ExcelWriteUtil.toXlsxByMap(mapList,headerNames,cellNames,cellFmts);
        //不设置单元格格式，所有所有导出数据均为字符串格式
        File target =ExcelWriteUtil.toXlsxByMap(mapList,headerNames,cellNames,cellFmts);
        System.out.println(target.getPath());
    }
}