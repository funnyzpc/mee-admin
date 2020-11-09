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
                log.error("client IP is empty");
                throw new Exception("Get client IP is empty");
            }
            String forwardip = request.getHeader("x-forwarded-for");
            if(!ip.equalsIgnoreCase(forwardip)){
                log.error("get client IP:{},forwarded:{} not equal",ip,forwardip);
            }
            log.info("IP获取 REAL_IP:{},forwarded_IP:{}",ip,forwardip);
            return ip;
        } catch (Exception e) {
            log.error("获得客户端真实IP异常",e);
            return ip;
        }
    }

}
