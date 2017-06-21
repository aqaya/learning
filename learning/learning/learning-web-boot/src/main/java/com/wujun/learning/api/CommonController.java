package com.wujun.learning.api;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.mockito.internal.util.collections.ListUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wujun.learning.commom.utils.BaseController;
import com.wujun.learning.commom.utils.ResultResponse;
import com.wujun.learning.config.ConfigInfo;
import com.wujun.learning.service.UserService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/common")
public class CommonController extends BaseController{

	Logger log = LoggerFactory.getLogger("DEBUG");

	@Autowired
	ConfigInfo properties;

	@Autowired
	UserService userService;

	@GetMapping("/test")
	public String test(String[] names, @RequestParam(required = false, value = "ages[]")ArrayList<String> ages){
		log.debug("Hello log!");
		log.error("Hello log!");
		log.debug("Hello log!");
		System.out.println("key:" + properties.getKey());
		System.out.println("homekey:" + properties.getHomeKey());
		System.out.println("profilekey:" + properties.getProfileKey());

		return "Hello " + Arrays.toString(names) + " 112 " + ages;
	}
	
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
		return processSimple(new ResultResponse(), (rr) -> {
			userService.transfer(idFrom, idTo, amount);
		});
	}
}
