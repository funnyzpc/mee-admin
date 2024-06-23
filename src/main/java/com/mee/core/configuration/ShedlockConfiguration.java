//package com.mee.core.configuration;
//
//import com.mee.common.util.DateUtil;
//import net.javacrumbs.shedlock.core.LockProvider;
//import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
//import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jdbc.core.JdbcTemplate;
//
//import javax.sql.DataSource;
//import java.util.TimeZone;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ScheduledExecutorService;
//
///**
//* 定时任务锁配置
//* @className    ShedlockConfiguration
//* @author       shadow
//* @date         2023/7/3 13:14
//* @version      1.0
//*/
//@Configuration
//@EnableSchedulerLock(defaultLockAtMostFor = "PT10M",defaultLockAtLeastFor="PT2M")
//public class ShedlockConfiguration {
//    private static final int PROCESSOR = Runtime.getRuntime().availableProcessors();
//
//    /**
//     * 配置锁
//     * @param dataSource
//     * @return
//     */
//    @Bean
//    public LockProvider lockProvider(DataSource dataSource) {
//        return new JdbcTemplateLockProvider(
//                JdbcTemplateLockProvider.Configuration.builder()
//                        .withTableName("sys_shedlock") // 这里定义表名
//                        .withJdbcTemplate(new JdbcTemplate(dataSource))
//                        .usingDbTime() // DB time为UTC时区会有时差
////                        .withTimeZone(TimeZone.getTimeZone(DateUtil.zoneId))
//                        .build()
//        );
//    }
//
//    /**
//     * 设置执行线程数
//     *
//     * @return
//     */
//    @Bean
//    public ScheduledExecutorService setTaskExecutors(){
//        return Executors.newScheduledThreadPool(PROCESSOR*2);
//    }
//
//}
