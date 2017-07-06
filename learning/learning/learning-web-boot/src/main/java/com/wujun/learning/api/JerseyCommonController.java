package com.wujun.learning.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.wujun.learning.config.ConfigInfo;

//@RestController
//@RequestMapping("/common")
@Component
@Path("/hello")
public class JerseyCommonController {

	private Logger log = LoggerFactory.getLogger("DEBUG");

	private final ConfigInfo properties;

	@Autowired
	public JerseyCommonController(ConfigInfo properties) {
		this.properties = properties;
	}

	//@GetMapping("/greet")
	@GET
	public String sayHello(){
		log.debug("Hello log!");
		log.error("Hello log!");
		log.debug("Hello log!");
		System.out.println("key:" + properties.getKey());
		System.out.println("key:" + properties.getHomeKey());
		System.out.println("profilekey:" + properties.getProfileKey());
		return "Hello_ " + "";
	}
	
}
