package com.mee.base;


import java.util.stream.IntStream;

public class Test4Main {

    // 防止指令重排
    private volatile static Test4Main target = null;

    private   Test4Main(){
        System.out.println("init default construct ...");
    }

    public  static Test4Main getInstance(){
        if(null == target){
            // 双端检锁机制 保证只实例化一次
            synchronized (Test4Main.class){
                if(null == target){
                    target = new Test4Main();
                }
            }
        }
        return target;
    }
    public static void main(String[] args)throws Exception {
        /*
        CompletableFuture asyncResult = CompletableFuture.runAsync(()->{
            test4Main.getInstance();
            test4Main.getInstance();
            test4Main.getInstance();
        });
        asyncResult.get();
        */

        IntStream.range(0,1000).parallel().forEach(i->{
            Test4Main.getInstance();
        });
        /*
        Test4Main.getInstance();
        Test4Main.getInstance();
        Test4Main.getInstance();
        */
        System.out.println("finished ...");
    }
}
