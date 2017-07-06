package com.wujun.learning.commom;

import ch.qos.logback.core.net.SyslogOutputStream;

public abstract class BaseClass<D,Q> {

    protected abstract String getNamespace();

    protected D doQ(Q q){
        System.out.println(getNamespace());
        System.out.println(q);
        return null;
    }

}



