package com.mee.test.base;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * SetTest
 *
 * @author shaoow
 * @version 1.0
 * @className SetTest
 * @date 2023/10/11 10:37
 */
public class SetTest {

    @Test
    public void test01(){
        Set<String> st = new HashSet<>();
        st.add("aa");
        st.add("bb");
        st.add("99");
        Object o = st.toArray()[0];
        System.out.println(o);


    }



}
