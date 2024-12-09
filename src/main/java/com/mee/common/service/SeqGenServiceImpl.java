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
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author funnyzpc
 * @description 序列生成器
 */
@Service
public class SeqGenServiceImpl {
    private static final Logger LOG = LoggerFactory.getLogger(SeqGenServiceImpl.class);

    private static final AtomicInteger it = new AtomicInteger(100000);
    private static final DateTimeFormatter DATE_SHORT_FORMAT = DateTimeFormatter.ofPattern("yyMMddHHmmss");
    private static final ZoneId zoneId = ZoneId.of("Asia/Shanghai");

    private static final ZoneOffset ZONE_OFF_SET = ZoneOffset.of("+8");
    private static final AtomicInteger SHOT_IT = new AtomicInteger(1000);

    private static final AtomicInteger MEDIUM_IT = new AtomicInteger(1000);



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

//    /** 生成短主键:10位(时间戳)+4位(端口号)+4位(有序序列) **/
//    public synchronized String genShortPrimaryKey(){
//        LocalDateTime dataTime = LocalDateTime.now(zoneId);
//        String port =  environment.getProperty("local.server.port");
//        if(SHOT_IT.intValue()>9000){
//            log.info("序列重置 genShortPrimaryKey ");
//            SHOT_IT.getAndSet(1000);
//        }
//        if(null == port || port.length()!=4){
//            port = "0000"+(null==port?"":port);
//            return ""+dataTime.toEpochSecond(ZONE_OFF_SET)+port.substring(port.length()-4)+SHOT_IT.getAndIncrement();
//        }
//        return ""+dataTime.toEpochSecond(ZONE_OFF_SET)+port+SHOT_IT.getAndIncrement();
//    }

    /** 16位： 12(日期时间yyMMddHHmmss)+4(有序序列) **/
    public String genShortPrimaryKey(){
        LocalDateTime dataTime = LocalDateTime.now(zoneId);
        if(SHOT_IT.intValue()>9990){
            LOG.info("重置genSeq序列 1000");
            SHOT_IT.getAndSet(1000);
        }
        return dataTime.format(DATE_SHORT_FORMAT)+ SHOT_IT.getAndIncrement();
    }

    /** 生成主键(18位)：12(日期时间YYMMDDHHMISS)+2(端口)+4(有序序列) **/
    public String genMediumPrimaryKey(){
        LocalDateTime dataTime = LocalDateTime.now(zoneId);
        String port =  environment.getProperty("local.server.port");
        if(MEDIUM_IT.intValue()>9990){
            LOG.info("重置计数器:{}",MEDIUM_IT.intValue());
            MEDIUM_IT.getAndSet(1000);
        }
        if(null == port || port.length()!=2){
            port = "00"+(null==port?"":port);
            return dataTime.format(DATE_SHORT_FORMAT)+port.substring(port.length()-2)+ MEDIUM_IT.getAndIncrement();
        }
        return dataTime.format(DATE_SHORT_FORMAT)+port+ MEDIUM_IT.getAndIncrement();
    }

    /** 生成主键(18位)：12(日期时间YYMMDDHHMISS)+2(seq_key)+4(有序序列) **/
    private static final Map<String,AtomicInteger> TABLE_SEQ = new HashMap<>(16);
    private static volatile Long THREAD_ID = null;
    private static /*volatile*/ String PORT = null;
    public  String genPrimaryKey(String table_name){
        if(null==table_name || "".equals(table_name=table_name.toUpperCase().trim())){
            throw new RuntimeException("必要参数不可为空");
        }
        LocalDateTime dataTime = LocalDateTime.now(zoneId);
        AtomicInteger seq = TABLE_SEQ.get(table_name);
        if(null==seq){
            seq = this.createField(table_name);
        }
        if( seq.get()==9999 ){
            // 睡眠以防止并发不够
            // TODO~ 这个地方其实是有问题的，这个THREAD_NAME其实应该跟当前table绑定才是
            if( Thread.currentThread().getId()==THREAD_ID ){
                try {
                    LOG.info("开始休眠1s...");
                    TimeUnit.SECONDS.sleep(1);
                }catch (Exception e){
                    LOG.error("sleep异常...:{}",table_name,e);
                }
            }
            THREAD_ID = Thread.currentThread().getId();
            LOG.info("重置计数器:{}",seq);
            seq.getAndSet(1000);
        }
        return dataTime.format(DATE_SHORT_FORMAT)+PORT+ (seq.getAndIncrement());
    }

    private synchronized AtomicInteger createField(String table_name){
        if( null==PORT ){
            String port =  environment.getProperty("local.server.port");
            PORT = "00"+(null==port?"":port);
        }
        AtomicInteger seq ;
        if((seq=TABLE_SEQ.get(table_name))!=null){
            return seq;
        }
        THREAD_ID =Thread.currentThread().getId();
        TABLE_SEQ.put(table_name,seq = new AtomicInteger(1000));
        return seq;
    }


}
