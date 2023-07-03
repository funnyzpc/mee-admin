package com.mee.common.util;


import java.security.MessageDigest;

/**
* @auther shadow
* @description MD5 工具类-建议添油加盐入参 str 改造一下
* @date 2022/8/31 17:51
*/
public class MD5Util {


    //工具类不允许被实例化
    private MD5Util() throws Exception {
        throw new Exception("异常");
    }
    public static String encode(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++){
            byteArray[i] = (byte) charArray[i];
        }

        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }

            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }


    public static void main(String[] args) {
        String encResult = encode("1");
        System.out.println(encResult);
    }

}