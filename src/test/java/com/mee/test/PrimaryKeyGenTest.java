package com.mee.test;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class PrimaryKeyGenTest {


    // @Test
    public void process01(){
        /// 8+6+4+6
        /// 20191213 105230 8080 100000
        IntStream.range(0,10).forEach(i->{
            System.out.println(genPrimaryKey());
        });
    }



    //@Test
    public void process02(){
        IntStream.range(0,20).forEach(i->{
            System.out.println(genShortPrimaryKey());
        });
    }

    /**
     * @description 订单号生成(不重复), 十位(日期)+四位(商户号后四位)+四位(顺序编号)
     */
    private static final AtomicInteger it = new AtomicInteger(999000);
    private static final DateTimeFormatter DATE_SHORT_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private static final ZoneId zoneId = ZoneId.of("Asia/Shanghai");
    private static final String APPLICATION_PORT = "8080";
    public static String genPrimaryKey(){
        LocalDateTime dataTime = LocalDateTime.now(zoneId);
        if(it.intValue()>990000){
            it.getAndSet(100000);
        }
        if(APPLICATION_PORT.length()!=4){
            String port = "0000"+APPLICATION_PORT;
            return dataTime.format(DATE_SHORT_FORMAT)+port.substring(port.length()-4)+ it.getAndIncrement();
        }
        return dataTime.format(DATE_SHORT_FORMAT)+APPLICATION_PORT+ it.getAndIncrement();
    }

    /** 生成短主键:10位(时间戳)+4位(端口号)+4位(有序序列) **/
    private static final ZoneOffset ZONE_OFF_SET = ZoneOffset.of("+8");
    private static final AtomicInteger SHOT_IT = new AtomicInteger(9000);
    public static String genShortPrimaryKey(){
        LocalDateTime dataTime = LocalDateTime.now(zoneId);
        if(SHOT_IT.intValue()>9000){
            SHOT_IT.getAndSet(1000);
        }
        if(APPLICATION_PORT.length()!=4){
            String port = "0000"+APPLICATION_PORT;
            return ""+dataTime.toEpochSecond(ZONE_OFF_SET)+port.substring(port.length()-4)+SHOT_IT.getAndIncrement();
        }
        return ""+dataTime.toEpochSecond(ZONE_OFF_SET)+APPLICATION_PORT+SHOT_IT.getAndIncrement();
    }

}
