package com.mee.base;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


// lock 读写分离锁
public class Test7Main {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();


        // ap<Integer,String> lst = new HashMap<Integer,String>(100000,1);
        Map<Integer,String> lst = new HashMap<Integer,String>();
        for (int i = 0; i < 100000; i++) {
            lst.put(i,UUID.randomUUID().toString());
        }

        long end = System.currentTimeMillis();

        // System.out.println(lst.get(10));
        System.out.println("耗时："+(end-start));

        for (int i = 0; i < lst.size(); i++) {
            lst.get(i);
        }

        long end2 = System.currentTimeMillis();
        System.out.println("耗时："+(end2-end));


    }
}
