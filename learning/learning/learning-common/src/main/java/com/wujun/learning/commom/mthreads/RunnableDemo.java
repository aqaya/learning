package com.wujun.learning.commom.mthreads;

class MyThread implements Runnable{
    private int ticket = 5;
    public void run(){  
        for (int i=0;i<10;i++)
        {  
            if(ticket > 0){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " ticket = " + --ticket);
            }
        }  
    }  
}  
  
public class RunnableDemo{  
    public static void main(String[] args){  
        MyThread my = new MyThread();
        new Thread(my).start();
        new Thread(my).start();
        new Thread(my).start();
    }
}  