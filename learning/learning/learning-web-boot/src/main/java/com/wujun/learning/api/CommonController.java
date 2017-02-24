package com.wujun.learning.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wujun.learning.config.Properties;

@RestController
@RequestMapping("/common")
public class CommonController {

	Logger log = LoggerFactory.getLogger("DEBUG");
	
	@Autowired
	Properties properties;
	
	@GetMapping("/greet")
	public String sayHello(String name){
		log.debug("Hello log!");
		log.error("Hello log!");
		log.debug("Hello log!");
		System.out.println("key:" + properties.getKey());
		System.out.println("key:" + properties.getHomeKey());
		System.out.println("profilekey:" + properties.getProfileKey());
		return "Hello " + name;
	}
	
}
