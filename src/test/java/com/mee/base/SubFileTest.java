package com.mee.base;

import org.junit.jupiter.api.Test;

public class SubFileTest {


    @Test
    public void test01(){
        String file = "cc.pdf";
        System.out.println(file.substring(file.lastIndexOf(".")));
    }
}
