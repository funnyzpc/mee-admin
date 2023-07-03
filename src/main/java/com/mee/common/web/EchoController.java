package com.mee.common.web;


import com.mee.common.util.DateUtil;
import com.mee.common.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
* 公共接口测试用
* @className    EchoController
* @author       shadow
* @date         2023/7/3 14:04
* @version      1.0
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

    private static final LocalDateTime STARTUP_TIME = DateUtil.now();

    /**
     * 测试
     * @param reqData 请求数据
     * @param request 请求对象
     * @return 数据
     */
    @GetMapping("/echo")
    public Map<String,Object> echo(String reqData,HttpServletRequest request){
        try {
            String port = environment.getProperty("local.server.port");
            InetAddress inetAddress = Inet4Address.getLocalHost();
            return new HashMap<String,Object>(11,1){{
                put("status",0);
                put("reqData",reqData);
                put("port",port);
                put("host",inetAddress.getHostAddress());
                put("host_name",inetAddress.getHostName());
                put("context_path",ctxPath);
                put("application_name",applicationName);
                put("active_profile",active_profile);
                put("client_ip", HttpUtil.getRemoteAddr(request));
                put("timestamp",System.currentTimeMillis());
                put("startup_time",STARTUP_TIME.format(DateUtil.FORMAT_DAY_TIME));
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
