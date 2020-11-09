package com.mee.common.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author funnyzpc
 * @description 序列生成器
 */
@Service
public class SeqGenServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(SeqGenServiceImpl.class);

    private static final AtomicInteger it = new AtomicInteger(100000);
    private static final DateTimeFormatter DATE_SHORT_FORMAT = DateTimeFormatter.ofPattern("yyMMddHHmmss");
    private static final ZoneId zoneId = ZoneId.of("Asia/Shanghai");

    private static final ZoneOffset ZONE_OFF_SET = ZoneOffset.of("+8");
    private static final AtomicInteger SHOT_IT = new AtomicInteger(1000);


    @Autowired
    private Environment environment;

    /** 生成主键：14(日期时间)+4(端口)+6(有序序列) **/
    public synchronized String genPrimaryKey(){
        LocalDateTime dataTime = LocalDateTime.now(zoneId);
        String port =  environment.getProperty("local.server.port");
        if(it.intValue()>990000){
            it.getAndSet(100000);
        }
        if(null == port || port.length()!=4){
            port = "0000"+(null==port?"":port);
            return dataTime.format(DATE_SHORT_FORMAT)+port.substring(port.length()-4)+ it.getAndIncrement();
        }
        return dataTime.format(DATE_SHORT_FORMAT)+port+ it.getAndIncrement();
    }

    /** 生成短主键:10位(时间戳)+4位(端口号)+4位(有序序列) **/
    public synchronized String genShortPrimaryKey(){
        LocalDateTime dataTime = LocalDateTime.now(zoneId);
        String port =  environment.getProperty("local.server.port");
        if(SHOT_IT.intValue()>9000){
            log.info("序列重置 genShortPrimaryKey ");
            SHOT_IT.getAndSet(1000);
        }
        if(null == port || port.length()!=4){
            port = "0000"+(null==port?"":port);
            return ""+dataTime.toEpochSecond(ZONE_OFF_SET)+port.substring(port.length()-4)+SHOT_IT.getAndIncrement();
        }
        return ""+dataTime.toEpochSecond(ZONE_OFF_SET)+port+SHOT_IT.getAndIncrement();
    }



}
