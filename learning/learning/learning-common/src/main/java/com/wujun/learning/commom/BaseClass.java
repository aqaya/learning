package com.wujun.learning.commom;

import java.math.BigDecimal;

public abstract class BaseClass<D,Q> {

    public String a = "base a";

    protected abstract String getNamespace();

    protected D doQ(Q q){
        System.out.println("a = " + getA());
        return null;
    }

    public String getA(){
        return a;
    }

    public static void main(String[] args) {
        BigDecimal bd1 = new BigDecimal("12.4455");
        BigDecimal bd2 = new BigDecimal("12.4455");
        System.out.println(bd1);
        System.out.println(bd1.multiply(bd2).setScale(1, BigDecimal.ROUND_HALF_DOWN));

    }

}



