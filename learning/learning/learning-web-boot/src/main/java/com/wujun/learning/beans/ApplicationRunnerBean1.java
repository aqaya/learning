package com.wujun.learning.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.wujun.learning.config.ConfigInfo;

@Component
public class ApplicationRunnerBean1 implements ApplicationRunner {

	@Autowired
    ConfigInfo configInfo;
	
	@Value("${key1}")
	private String key;
	
	@Value("${profilekey}")
	private String profileKey;
	
	@Value("${homekey}")
	private String homeKey;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("ApplicationRunnerBean1");
	}

}
