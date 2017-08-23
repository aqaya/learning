package com.wujun.learning.commom.mthreads;

public class MissedNotify {
    private Object proceedLock;

    public MissedNotify() {
        print("in MissedNotify()");
        proceedLock = new Object();
    }

    public void waitToProceed() throws InterruptedException {
        print("in waitToProceed() - entered");

        synchronized (proceedLock) {
            print("in waitToProceed() - about to wait()");
            proceedLock.wait();
            print("in waitToProceed() - back from wait()");
        }

        print("in waitToProceed() - leaving");
    }

    public void proceed() {
        print("in proceed() - entered");

        synchronized (proceedLock) {
            print("in proceed() - about to notifyAll()");
            proceedLock.notifyAll();
            print("in proceed() - back from notifyAll()");
        }

        print("in proceed() - leaving");
    }

    private static void print(String msg) {
        System.out.println(Thread.currentThread().getName() + ": " + msg);
    }

    public static void main(String[] args) {
        final MissedNotify mn = new MissedNotify();

        new Thread(new Runnable() {
            public void run() {
                try {
                    //休眠1000ms，大于runB中的500ms，
                    //是为了后调用waitToProceed，从而先notifyAll，后wait，
                    //从而造成通知的遗漏
                    Thread.sleep(1000);
                    mn.waitToProceed();
                } catch (InterruptedException x1) {
                    x1.printStackTrace();
                }
            }
        }, "threadA").start();

        new Thread(new Runnable() {
            public void run() {
                try {
                    //休眠500ms，小于runA中的1000ms，
                    //是为了先调用proceed，从而先notifyAll，后wait，
                    //从而造成通知的遗漏
                    Thread.sleep(500);
                    mn.proceed();
                } catch (InterruptedException x1) {
                    x1.printStackTrace();
                }
            }
        }, "threadB").start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException x) {
        }

        //试图打断wait阻塞  
        print("about to invoke interrupt() on threadA");
        new Thread(new Runnable() {
            public void run() {
                try {
                    //休眠1000ms，大于runB中的500ms，
                    //是为了后调用waitToProceed，从而先notifyAll，后wait，
                    //从而造成通知的遗漏
                    Thread.sleep(1000);
                    mn.waitToProceed();
                } catch (InterruptedException x) {
                    x.printStackTrace();
                }
            }
        }, "threadA").interrupt();
    }
} 