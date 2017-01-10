package com.wujun.learning.controller.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wujun.learning.controller.base.BaseController;
import com.wujun.learning.controller.base.ResultResponse;
import com.wujun.learning.service.TestService;

@RestController
@RequestMapping("/test")
public class TestController extends BaseController{

	@Autowired
	TestService testService;
	
	@RequestMapping(value="/greet",method={RequestMethod.GET,RequestMethod.POST})
	@ResponseBody
	public ResultResponse greet(@RequestParam("name") String name,
			@RequestParam("age") Integer age){
		return processSimple(new ResultResponse(), rr -> {
			rr.addAttribute("greet", testService.formatNameAndAge(name, age));
		});
	}
	
}
