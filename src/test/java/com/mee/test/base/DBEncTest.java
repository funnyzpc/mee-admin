package com.mee.test.base;

import org.jasypt.util.text.AES256TextEncryptor;
import org.junit.jupiter.api.Test;

public class DBEncTest {

    // DB 密码加密
    @Test
    public void test01(){
        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        //加密所需的salt(盐)
        textEncryptor.setPassword("0989GoEncc}{||>.<||}0101");
        //要加密的数据（数据库的用户名或密码）
        String password = textEncryptor.encrypt("mee123qwe");
        System.out.println("enc password:  "+password);
    }

    // DB 密码解密
    @Test
    public void test02(){
        AES256TextEncryptor textEncryptor = new AES256TextEncryptor();
        //加密所需的salt(盐)
        textEncryptor.setPassword("KKli&86@$(*_*)d67jhggW132)i^tg3");
        //要解密的数据
        String pwd = textEncryptor.decrypt("4jVugdyx7ST6PatjxUePiV64DGK5grl3BHaSX3RuALWlbHkNgNX1dNMUQLT3JkDG");
        System.out.println("dec password:  "+pwd);

    }



}
