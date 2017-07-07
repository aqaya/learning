package com.wujun.learning.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

//@RestController
//@RequestMapping("/common")
@Component
@Path("/hello")
public class JerseyCommonController {

	private Logger log = LoggerFactory.getLogger("DEBUG");

	@GET
	public String sayHello(){
		log.debug("Hello log!");
		log.error("Hello log!");
		return "Hello_ " + "";
	}
	
}
