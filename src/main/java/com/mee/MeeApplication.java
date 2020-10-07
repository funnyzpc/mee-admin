package com.mee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeeApplication.class, args);
	}

}
