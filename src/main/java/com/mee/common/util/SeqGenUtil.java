package com.mee.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

public class SeqGenUtil {
    private static final Logger log = LoggerFactory.getLogger(SeqGenUtil.class);

    private static final ZoneId zoneId = ZoneId.of("Asia/Shanghai");
    private static final AtomicInteger SHOT_STATIC_IT = new AtomicInteger(9000);
    private static final DateTimeFormatter DATE_SHORT_FORMAT = DateTimeFormatter.ofPattern("yyMMddHHmmss");


    /** 生成主键：14(日期时间)+4(端口)+6(有序序列) **/
    public static synchronized String genSeq(){
        LocalDateTime dataTime = LocalDateTime.now(zoneId);
        if(SHOT_STATIC_IT.intValue()>9900){
            log.info("重置genSeq序列 1000");
            SHOT_STATIC_IT.getAndSet(1000);
        }
        return dataTime.format(DATE_SHORT_FORMAT)+ SHOT_STATIC_IT.getAndIncrement();
    }
}
