package com.mee.base;

import java.util.ArrayList;
import java.util.List;

public class TestMain {


    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1,2,3};
        for (int i = 0; i <arr.length ; i++) {
            arr[i] = 5;
        }
        System.out.println(arr);


        List<Integer> lst =  new ArrayList<Integer>(3){{
           add(1);
           add(5);
           add(3);
        }};

        for (int i = 0; i <lst.size() ; i++) {
            lst.add(5);
        }
        System.out.println(lst);

    }
}
