package com.mee;

import com.mee.timed.annotation.EnableMeeTimed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.InetAddress;

/**
*   mee_admin启动类
*   @className  MeeAdminApplication
*   @author     shadow
*   @date       2023/4/18 8:34 PM
*   @version    v1.0
*/
@SpringBootApplication
@EnableAsync // 开启异步调用
//@EnableScheduling // 开启定时任务
@EnableMeeTimed
public class MeeAdminApplication {
	/**
	 * 日志
	 */
	private static final Logger LOG= LoggerFactory.getLogger(MeeAdminApplication.class);

	public static void main(String[] args)throws Exception {
		ConfigurableApplicationContext application = SpringApplication.run(MeeAdminApplication.class, args);
		Environment env = application.getEnvironment();
		String ip = InetAddress.getLocalHost().getHostAddress();
		String port = env.getProperty("server.port");
		String path = env.getProperty("server.servlet.context-path");
		LOG.info("\n\t----------------------------------------------------------\n\t" +
				"Application MeeAdminApplication is running!\n\t" +
				"Local: \t\thttp://localhost:" + port + path + "/\n\t" +
				"External: \thttp://" + ip + ":" + port + path + "/\n\t" +
				"----------------------------------------------------------");
	}

}
