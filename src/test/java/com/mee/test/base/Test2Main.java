package com.mee.test.base;

public class Test2Main {
    public static void main(String[] args) {

        String str = new String("a");
        str.intern();
        String str2 = "a";
        System.out.println(str==str2);

        String s = new String("1")+new String("1");
        s.intern();
        String s2 = "11";
        System.out.println(s==s2);


        String k = new String("hello");
        k = k.intern();
        String k2 = "hello";
        System.out.println(k==k2);

    }
}
