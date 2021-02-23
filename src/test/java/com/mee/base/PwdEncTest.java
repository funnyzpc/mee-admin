package com.mee.base;

import com.mee.common.util.MD5Util;
import org.junit.jupiter.api.Test;

public class PwdEncTest {

    // 数据库密码加密
    @Test
    public void test01(){
        String enc_pwd = MD5Util.encode("你的密码");
        System.out.println(enc_pwd);
        // c4ca4238a0b923820dcc509a6f75849b
        // e034fb6b66aacc1d48f445ddfb08da98
    }
}
