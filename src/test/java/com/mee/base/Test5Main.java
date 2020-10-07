package com.mee.base;

import java.util.concurrent.locks.ReentrantLock;

// 可重入锁(递归锁)
// 演示 1.使用synchronized
//      2.使用ReentrantLock
public class Test5Main {

    private static ReentrantLock lock = new ReentrantLock();

    public synchronized void callAA(){
        System.out.println(Thread.currentThread().getName()+"\t inter callAA()");
        callBB();
    }

    public synchronized void callBB(){
        System.out.println(Thread.currentThread().getName()+"\t inter callBB()");
    }

    public void callFirst(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"\t inter callFirst()");
            callSecond();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void callSecond(){
        try{
            lock.lock();
            System.out.println(Thread.currentThread().getName()+"\t inter callSecond()");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Test5Main test5Main = new Test5Main();
        new Thread(()->{test5Main.callAA();},"t001").start();
        new Thread(()->{test5Main.callAA();},"t002").start();

        try{
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{test5Main.callFirst();},"t003").start();
        new Thread(()->{test5Main.callFirst();},"t004").start();

    }
}
