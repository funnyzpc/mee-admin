package com.mee.base;


import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantReadWriteLock;

// CAS 自旋转锁(实际使用则是 unSafe+CAS )
// for example : AtomicInteger or AtomicLong
public class Test6Main {

    private static  AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void setLock(){
        Thread thread = Thread.currentThread();
        System.out.println("to setLock thread is : "+thread.getName());
        while (!atomicReference.compareAndSet(null,thread)){
            // to do wait lock
        }
    }

    public void setUnLock(){
        Thread thread = Thread.currentThread();
        System.out.println("to setUnLock thread is : "+thread.getName());
        while (!atomicReference.compareAndSet(thread,null)){
            // to do wait unlock
        }
    }


    public static void main(String[] args) {
        Test6Main test6Main = new Test6Main();
        new Thread(()->{
            test6Main.setLock();
            try{
                Thread.sleep(1);
            }catch (Exception e){
                e.printStackTrace();
            }
            test6Main.setUnLock();
        },"TH001").start();

        new Thread(()->{
            test6Main.setLock();
            try{
                Thread.sleep(4);
            }catch (Exception e){
                e.printStackTrace();
            }
            test6Main.setUnLock();
        },"TH002").start();

        ReentrantReadWriteLock.ReadLock readLock = new ReentrantReadWriteLock().readLock();


    }
}
