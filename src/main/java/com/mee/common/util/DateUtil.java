package com.mee.common.util;

import org.springframework.util.StringUtils;

import java.lang.management.ManagementFactory;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
* 日期时间工具类
* @className    DateUtil
* @author       shadow
* @date         2023/6/2 17:09
* @version      1.0
*/
public class DateUtil {

    // 格式化样式
    public static final DateTimeFormatter FORMAT_DAY = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter FORMAT_DAY2 = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static final DateTimeFormatter FORMAT_DAY_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final ZoneId zoneId = ZoneId.of("Asia/Shanghai");

    /**
     * 当前时间添加秒数
     * @param seconds
     * @return
     */
    public static LocalDateTime addSeconds(long seconds){
        return LocalDateTime.now(zoneId).plusSeconds(seconds);
    }

    /**
     * 当前时间加减天数
     * @param days
     * @return
     */
    public static String addDays(long days){
        return LocalDateTime.now(zoneId).plusDays(days).format(FORMAT_DAY);
    }

    public static LocalDateTime now(){
        return LocalDateTime.now(zoneId);
    }

    public static LocalDate nowDay(){
        return LocalDate.now(zoneId);
    }

    /**
     * 日期转换成时间字符串
     * @param date
     * @param formatStr
     */
    public static String format(Date date, String formatStr){
        if(date == null || StringUtils.isEmpty(formatStr)){
            return null;
        }
        return new SimpleDateFormat(formatStr).format(date);
    }

    /**
     * LocalDate、LocalDateTime 格式化字符串
     */
    public static String format(LocalDate localDate, String fmtPattern){
        if(localDate == null || StringUtils.isEmpty(fmtPattern)){
            return null;
        }
        return DateTimeFormatter.ofPattern(fmtPattern).format(localDate);
    }
    public static String format(LocalDateTime localDateTime, String fmtPattern){
        if(localDateTime == null || StringUtils.isEmpty(fmtPattern)){
            return null;
        }
        return DateTimeFormatter.ofPattern(fmtPattern).format(localDateTime);
    }

    public static String getServerStartDateStr() {
        long timestamp = ManagementFactory.getRuntimeMXBean().getStartTime();
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp),zoneId).format(FORMAT_DAY_TIME);
    }

    public static long betweenNanos(LocalDateTime start,LocalDateTime end){
        if( null== start || null==end){
            return 0L;
        }
        return Duration.between(start,end).toMillis()/1000;
    }

    /**
     * 获取服务器启动时间
     */
    public static LocalDateTime getServerStartDate() {
        long timestamp = ManagementFactory.getRuntimeMXBean().getStartTime();
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp),zoneId);
    }

}
