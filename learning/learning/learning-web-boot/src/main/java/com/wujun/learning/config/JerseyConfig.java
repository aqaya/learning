package com.wujun.learning.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.wujun.learning.api.CommonController2;

@Component
@ApplicationPath("/jsy")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
    	System.out.println("jssssssery!");
        register(CommonController2.class);
    }

}