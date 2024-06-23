package com.mee.cron;

import com.mee.common.util.DateUtil;
import com.mee.timed.annotation.MeeTimed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author funnyzpc
 * @description 集群分佈式定時任務測試(測試需要>=2個實例)
 */
@Service
public class DefaultTimerService {
    private static final Logger log = LoggerFactory.getLogger(DefaultTimerService.class);


    // 每5分钟执行一次
    @MeeTimed(cron = "0 */5 * * * *",lockName = "testTask1", lockAtMostFor = "PT1M", lockAtLeastFor = "PT1M")
    public void testTask1() {
        log.info("===>testTask1執行時間: {}", DateUtil.now().format(DateUtil.FORMAT_DAY_TIME));
    }

    // 每1小时执行一次
    @MeeTimed(cron = "0 0 */1 * * ?",lockName = "testTask2", lockAtMostFor = "PT5M", lockAtLeastFor = "PT5M")
    public void testTask2() {
        log.info("===>testTask2執行時間: {}", DateUtil.now().format(DateUtil.FORMAT_DAY_TIME));
    }

    // 每天9点0分触发
    @MeeTimed(cron = "0 0 9 ? * *",lockName = "testTask3", lockAtMostFor = "PT10M", lockAtLeastFor = "PT10M")
    public void testTask3(){
        log.info("===>testTask3執行時間: {}", DateUtil.now().format(DateUtil.FORMAT_DAY_TIME));
    }

}
