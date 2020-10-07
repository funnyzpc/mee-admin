package com.mee.common.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * @author funnyzpc
 * @description 公共接口测试用
 */
@RestController
public class EchoController {

    @Value("${server.servlet.context-path}")
    private String ctxPath;

    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${spring.profiles.active}")
    private String active_profile;

    @Autowired
    private Environment environment;

    @GetMapping("/echo")
    public Map<String,Object> echo(String reqData){
        try {
            String port = environment.getProperty("local.server.port");
            InetAddress inetAddress = Inet4Address.getLocalHost();
            return new HashMap<String,Object>(8,1){{
                put("status",0);
                put("reqData",reqData);
                put("port",port);
                put("host",inetAddress.getHostAddress());
                put("host_name",inetAddress.getHostName());
                put("context_path",ctxPath);
                put("application_name",applicationName);
                put("active_profile",active_profile);
            }};
        }catch (Exception e){
            return new HashMap<String,Object>(3,1){{
                put("status",0);
                put("msg","fail to get appliction info");
                put("reqData",reqData);
            }};
        }
    }




}
