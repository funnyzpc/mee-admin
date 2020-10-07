package com.mee.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class HttpUtil {
    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

    public static String getRemoteAddr(HttpServletRequest request) {
        String ip = null;
        try {
            ip = request.getHeader("X-Real-IP");
            if(StringUtils.isEmpty(ip)){
                ip =request.getRemoteAddr();
            }
            if(StringUtils.isEmpty(ip)){
                log.error("获得客户端真实IP为空");
                throw new Exception("获得客户端真实IP为空");
            }
            String forwardip = request.getHeader("x-forwarded-for");
            if(!ip.equalsIgnoreCase(forwardip)){
                log.error("获得客户端真实IP:{}和forwarded:{},信息不符合，有伪造嫌疑",ip,forwardip);
            }
            log.info("IP获取 REAL_IP:{},forwarded_IP:{}",ip,forwardip);
            return ip;
        } catch (Exception e) {
            log.error("获得客户端真实IP异常",e);
            return ip;
        }
    }

}
