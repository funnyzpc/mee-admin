package com.mee.cron;

import com.mee.timed.Job;
import com.mee.timed.JobExecutionContext;
import com.mee.timed.annotation.MeeTimed;
import com.mee.timed.annotation.MeeTimeds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class Job02TestService implements Job {
    private static final Logger LOGGER = LoggerFactory.getLogger(Job02TestService.class);

    @MeeTimeds({
            @MeeTimed(fixedRate = 10000,lockName = "t10",lockAtLeastFor = "PT5S",lockAtMostFor ="PT5S" ),
//            @MeeTimed(fixedRate = 5000,lockName = "t11",lockAtLeastFor = "PT5S",lockAtMostFor ="PT5S" ),
            @MeeTimed(fixedDelay = 4000,lockName = "t11",lockAtLeastFor = "PT5S",lockAtMostFor ="PT5S" ),
    })
    public void exec01() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        LOGGER.info("=====> [Job01] Already Executed! <=====");
    }

    @MeeTimeds({
         @MeeTimed(cron = "10,20,30,40,50 * * * * ?",lockAtMostFor ="PT5S",lockName = "t1"),
         @MeeTimed(cron = "0/3 * * * * ?",lockAtMostFor ="PT1M",lockName = "t2"),
//         @MeeTimed(cron = "0/6 * * * * ?",lockAtMostFor ="PT1M",lockName = "t3")
    })
    @Override
    public void execute(JobExecutionContext context) {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        LOGGER.info("=====> proxy job exec! data:"+context.getJobDataJson()+"  <=====");
    }
}
