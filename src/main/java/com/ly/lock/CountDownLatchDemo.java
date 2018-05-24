package com.ly.lock;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author tuoer
 * @description 定时器实验
 * @date 2018/5/9 20:40
 */
public class CountDownLatchDemo implements Runnable {
    static final CountDownLatch end = new CountDownLatch(10);
    static final CountDownLatchDemo demo = new CountDownLatchDemo();

    @Override
    public void run() {

        try {
            Thread.sleep(new Random().nextInt(10)*1000);
            System.out.println("check complete");
            end.countDown();
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) throws  InterruptedException{
        ExecutorService exe = Executors.newFixedThreadPool(10);
        
        for (int i= 0 ;i< 10 ;i++) {
            exe.submit(demo);
        }
        
        end.await();
        System.out.println("fire");
        exe.shutdown();
    }
}
