package com.wujun.learning.commom;

/**
 * Created by wujun on 2017/7/5.
 */
public class SubClass<D,Q> extends  BaseClass<D,Q>{

    private String a = "sub a";

    @Override
    protected String getNamespace() {
        return "wujun";
    }

    public void printA(){
        System.out.println("this.a = " + a);
        System.out.println("super.a = " + super.a);
    }

    public String getA(){
        return a;
    }

    protected D doQ(Q q){
        System.out.println("a = " + getA());
        return null;
    }
}
