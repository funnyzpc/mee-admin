package com.mee.cron;

import com.mee.timed.Job;
import com.mee.timed.JobExecutionContext;
import com.mee.timed.annotation.MeeTimed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * JobTimedService
 *
 * @author shaoow
 * @version 1.0
 * @className JobTimedService
 * @date 2024/6/17 17:45
 */
@Service
public class JobTimedService implements Job {
    private static final Logger LOGGER = LoggerFactory.getLogger(JobTimedService.class);

    @MeeTimed(cron = "0/20 * * * * ?",lockAtLeastFor = "PT5S",lockAtMostFor ="PT10S" )
    @Override
    public void execute(JobExecutionContext context) {
        LOGGER.info("=====> proxy job exec! data:"+context.getJobDataJson()+"  <=====");
    }
}
