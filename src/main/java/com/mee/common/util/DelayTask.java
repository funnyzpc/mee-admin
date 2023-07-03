//package com.mee.common.util;
//
//import io.netty.util.HashedWheelTimer;
//import io.netty.util.TimerTask;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.time.format.DateTimeFormatter;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author funnyzpc
// * @description 延迟任务执行类
// */
//public class DelayTask {
//    private static final Logger log  = LoggerFactory.getLogger(DelayTask.class);
//
//    private static HashedWheelTimer hashedWheelTimer = new HashedWheelTimer(1, TimeUnit.SECONDS,12);
//
//    public static final DateTimeFormatter FORMAT_HM = DateTimeFormatter.ofPattern("HHmm");
//
//    /**
//     * 基于 Netty 的延迟任务
//     * @param task 任务类
//     * @param delay 延迟时间
//     * @param tmu 延迟时间单位
//     */
//    public static void nettyTask(TimerTask task,long delay,TimeUnit tmu) {
//        // 当前的任务不可持续到晚上,防止任务池负载过大
//        long now = Long.valueOf(DateUtil.now().format(FORMAT_HM));
//        if(now>2355){
//            log.error("当前任务超时{}-{}-{}",now,delay,task.toString());
//            return;
//        }
//        // 将任务添加到延迟队列中
//        hashedWheelTimer.newTimeout(task, delay,tmu);
//    }
//
//}
