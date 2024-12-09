package com.mee.test.base;

import org.junit.jupiter.api.Test;

/**
 * StringTest
 *
 * @author shaoow
 * @version 1.0
 * @className StringTest
 * @date 2024/5/20 11:11
 */
public class StringTest {

    @Test
    public void test01(){
        String format = String.format("%.4f", 2233.12347654);
        System.out.println(format);
    }
}
