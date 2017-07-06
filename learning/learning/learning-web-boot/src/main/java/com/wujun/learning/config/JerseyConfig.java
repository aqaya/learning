package com.wujun.learning.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.wujun.learning.api.JerseyCommonController;

@Component
@ApplicationPath("/jsy")
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
    	System.out.println("jssssssery!");
        register(JerseyCommonController.class);
    }

}