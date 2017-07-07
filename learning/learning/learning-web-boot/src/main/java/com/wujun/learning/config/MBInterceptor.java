package com.wujun.learning.config;

import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.util.Properties;

@Component
@Intercepts( {
        @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class,Integer.class }) })
public class MBInterceptor implements Interceptor{
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();
        System.out.println(statementHandler.getBoundSql().getSql());
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        System.out.println("-----------2 : " + o);
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
        if (properties != null) {
            properties.list(System.out);
        }
    }
}
