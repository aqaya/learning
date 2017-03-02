package com.wujun.learning.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wujun.learning.commom.utils.BaseController;
import com.wujun.learning.commom.utils.ResultResponse;
import com.wujun.learning.config.Properties;
import com.wujun.learning.service.UserService;

@RestController
@RequestMapping("/common")
public class CommonController extends BaseController{

	Logger log = LoggerFactory.getLogger("DEBUG");

	@Autowired
	Properties properties;

	@Autowired
	UserService userService;

	@GetMapping("/greet")
	public String sayHello(String name){
		log.debug("Hello log!");
		log.error("Hello log!");
		log.debug("Hello log!");
		System.out.println("key:" + properties.getKey());
		System.out.println("homekey:" + properties.getHomeKey());
		System.out.println("profilekey:" + properties.getProfileKey());

		return "Hello " + name;
	}

	@PostMapping("/transfer")
	public ResultResponse transfer(Long idFrom, Long idTo, Double amount){
		return processSimple(new ResultResponse(), new Taker() {

			@Override
			public void process(ResultResponse rr) {
				userService.transfer(idFrom, idTo, amount);
			}
		});
	}

	private boolean get(){
	    return true;
    }

	
}
