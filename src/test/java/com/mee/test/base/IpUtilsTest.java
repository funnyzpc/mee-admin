package com.mee.test.base;

import com.mee.common.util.IpUtils;
import org.junit.jupiter.api.Test;

/**
 * @author shaoow
 * @version 1.0
 * @className IpUtilsTest
 * @date 2024/5/28 9:59
 */
public class IpUtilsTest {

    @Test
    public void test01(){
        // todo ...
        String s = IpUtils.localIP();
        System.out.println(s);
    }
}
