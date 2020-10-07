package com.mee.core.configuration;


import com.jagregory.shiro.freemarker.ShiroTags;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.PostConstruct;

@Configuration
public class FreemarkerCustomConfiguration {

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Autowired
    private freemarker.template.Configuration configuration;

    @Value("${server.servlet.context-path}")
    private String ctxPath;

    @Value("${spring.application.name}")
    private String applicationName;

    /** 使freemarker支持shiro标签 **/
    @Bean
    public freemarker.template.Configuration getFreemarkerConfiguration(){
        freemarker.template.Configuration configuration = freeMarkerConfigurer.getConfiguration();
        configuration.setSharedVariable("shiro",new ShiroTags());
        return configuration;
    }


    /** 设置全局环境变量 **/
    @PostConstruct
    public void setConfigure() throws Exception {
        configuration.setSharedVariable("ctxPath", ctxPath);
        configuration.setSharedVariable("applicationName", applicationName);
    }
}
