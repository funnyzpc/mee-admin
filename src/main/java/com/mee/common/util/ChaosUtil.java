package com.mee.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;

/**
 * @author shadow
 * @description 密码混淆加密
 */
public class ChaosUtil {
    private static final Logger log = LoggerFactory.getLogger(ChaosUtil.class);

    // 有效时间15分钟
    private static  final long DURATION = 15*60;

    // -- 简单加密处理
    // + 原始密码
    // + 密文1 = 随机字符5位+原始密码+随机字符5位置
    // + 密文2 = Base64(密文1)
    // + 密文3 = 反转(密文2)
    // + 密文4 = 时间戳10位+&&+密文3
    // + 密文5 = Base64(密文4)
    // + 密文6 = Base64 to hex

    public static String dec(String pwd){
        if(null == pwd || "".equals(pwd.trim())){
            log.error("密码为空:{}",pwd);
            return null;
        }
        String enc6 = new String(decodeHex(pwd.toCharArray()));
        //System.out.println(enc6);
        String enc5 = new String(Base64.getDecoder().decode(enc6));
        //System.out.println(enc5);
        String[] tmp = enc5.split("&&");
        // check time
        long t = Long.valueOf(tmp[0]);
        long now = System.currentTimeMillis()/1000;
        if(Math.abs(now-t)>DURATION){
            log.error("超时:{} t:{} now:{}",pwd,t,now);
            return null;
        }
        String enc4 = tmp[1];
        //System.out.println(enc4);
        String enc3 = new StringBuffer(enc4).reverse().toString();
        //System.out.println(enc3);
        String enc2 = new String(Base64.getDecoder().decode(enc3));
        //System.out.println(enc2);
        if(enc2.length()<=10){
            log.error("密码存在问题:{} t:{} now:{} enc2:{}",pwd,t,now,enc2);
            return null;
        }
        String enc1 = enc2.substring(5,enc2.length()-5);
        //System.out.println(enc1);
        return enc1;
    }

    private static byte[] decodeHex(char[] hexData) {
        int len = hexData.length;
        if ((len & 0x01) != 0) {
            throw new RuntimeException("Odd number of characters.");
        }
        byte[] out = new byte[len >> 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(hexData[j], j) << 4;
            j++;
            f = f | toDigit(hexData[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }
        return out;
    }

    private static int toDigit(char ch, int index) {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new RuntimeException("Illegal hexadecimal character " + ch + " at index " + index);
        }
        return digit;
    }

}
