package com.mee.base;

import java.util.HashMap;

public class Test3Main {

    public static void main(String[] args) {
        double d1 = 11.11D;
        double d2 = 11.11;

        System.out.println(d1==d2);


        Integer d3 = 122;
        Integer d4 = Integer.valueOf(122);
        System.out.println(d3==d4);

        HashMap hm = new HashMap(){{
            put("k","v");
        }};
        HashMap hm2  = (HashMap)hm.clone();
        hm2.put("Y","M");
        hm2.put("k","kkk");
        System.out.println(hm);
        System.out.println(hm2);

    }
}
