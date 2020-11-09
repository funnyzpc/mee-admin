package com.mee.base;

import org.jasypt.util.text.AES256TextEncryptor;
import org.junit.jupiter.api.Test;

public class DBEncTest {

    // DB 密码加密
    @Test
    public void test01(){
        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        //加密所需的salt(盐)
        textEncryptor.setPassword("0989GoE101");
        //要加密的数据（数据库的用户名或密码）
        String password = textEncryptor.encrypt("4455");
        System.out.println("enc password:  "+password);
    }

    // DB 密码解密
    @Test
    public void test02(){
        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        //加密所需的salt(盐)
        textEncryptor.setPassword("KKli&867jhgg3");
        //要解密的数据
        String pwd = textEncryptor.decrypt("sssss");
        System.out.println("dec password:  "+pwd);

    }



}
