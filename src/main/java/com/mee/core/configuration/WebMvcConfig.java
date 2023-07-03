package com.mee.core.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
* 过滤器配置
* @className    WebMvcConfig
* @author       shadow
* @date         2023/7/3 13:19
* @version      1.0
*/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /** 重写资源拦截器: 将静态文件映射至classpath目录下**/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/public/**").addResourceLocations("classpath:/public/");
    }

}
