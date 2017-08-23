package com.wujun.learning.commom.mthreads;

public class SleepInterrupt implements Runnable {
    public void run() {
        System.out.println("in run() - about to sleep for 20 seconds");
        int l = Integer.MAX_VALUE / 1000;
        for (long i = 0; i < l; i++) {
            System.out.println("isInterrupted : " + Thread.currentThread().isInterrupted() + i);
        }
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("in run() - woke up");


        System.out.println("in run() - leaving normally");
    }


    public static void main(String[] args) {
        SleepInterrupt si = new SleepInterrupt();
        Thread t = new Thread(si);
        t.start();
        //主线程休眠2秒，从而确保刚才启动的线程有机会执行一段时间  
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("in main() - interrupting other thread");
        //中断线程t  
        t.interrupt();
        System.out.println("in main() - leaving");
    }
}  