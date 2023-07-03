package com.mee.test.util;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.junit.jupiter.api.Test;

public class Sha512UtilTest {


    @Test
    public void test01(){
        // 166789588780011000
        final String env = "test";
        String user_name = "admin";
        String pwd = "admin";
        Sha512Hash sha512Hash = new Sha512Hash(pwd,user_name+env,3);
        System.out.println(sha512Hash.toString());
    }
    @Test
    public void test02(){
        String user_id = "1";
        String user_name = "admin";
        String pwd = "admin";
        Sha512Hash sha512Hash = new Sha512Hash(pwd,user_id,3);
        System.out.println(sha512Hash);

    }





}
