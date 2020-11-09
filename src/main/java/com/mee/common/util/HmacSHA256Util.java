package com.mee.common.util;

import cn.hutool.crypto.digest.HMac;
import cn.hutool.crypto.digest.HmacAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HmacSHA256Util {
    private static final Logger log = LoggerFactory.getLogger(HmacSHA256Util.class);

    /**
     *  签名生成工具类
     * @param data
     * @param key
     * @return
     */
    public static String enc(String data,String key){
        try {
            if (null == key || null == data) {
                return "";
            }
            // return new HMac(HmacAlgorithm.HmacSHA512, key.trim().getBytes("UTF-8")).digestHex(data);
            return new HMac(HmacAlgorithm.HmacSHA512, key.trim().getBytes("UTF-8")).digestHex(data);
        }catch (Exception e){
            log.error("加密异常 data:{}",data,e);
            return "";
        }
    }
}
