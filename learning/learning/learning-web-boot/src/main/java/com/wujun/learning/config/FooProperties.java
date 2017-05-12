package com.wujun.learning.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@ConfigurationProperties("foo")
@Component
public class FooProperties implements ApplicationRunner {

    private final List<MyPojo> list = new ArrayList<>();

    public List<MyPojo> getList() {
        return this.list;
    }

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println(this.toString());
	}

	@Override
	public String toString() {
		return "FooProperties [list=" + list + "]";
	}
    
    

}