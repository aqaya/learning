package com.wujun.learning.commom.mthreads;

public class InterruptReset{
    public static void main(String[] args) {

        MyRunnable a = new MyRunnable("A");
        Thread t1 = new Thread(a);

        MyRunnable b = new MyRunnable("B");
        Thread t2 = new Thread(b);

        a.setFriend(t2);

        t1.start();
        t2.start();

    }

}

class MyRunnable implements Runnable{

    private Thread friend;

    private String name;

    MyRunnable(String name){
        this.name = name;
    }

    public MyRunnable setFriend(Thread t){
        this.friend = t;
        return this;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++){
            if (name.equals("A")){
                Thread.yield();
            }
//            if (friend != null && i == 5){
//                try {
//                    friend.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
            System.out.println(name + " : " + i);
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}