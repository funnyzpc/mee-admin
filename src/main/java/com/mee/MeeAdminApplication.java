package com.mee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync // 开启异步调用
@EnableScheduling // 开启定时任务
public class MeeAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeeAdminApplication.class, args);
	}

}
