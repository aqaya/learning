package com.wujun.learning.controller.test;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wujun.learning.controller.base.BaseController;
import com.wujun.learning.controller.base.ResultResponse;
import com.wujun.learning.model.User;
import com.wujun.learning.service.TestService;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

	@Autowired
	TestService testService;

	@RequestMapping(value = "/greet", method = { RequestMethod.POST,RequestMethod.GET })
	@ResponseBody
	public ResultResponse greet(@RequestParam("name") String name,
			@ApiParam(required = false, name = "age") @RequestParam("age") Integer age) {
		return processSimple(new ResultResponse(), rr -> {
			rr.addAttribute("greet", testService.formatNameAndAge(name, age));
		});
	}

	@RequestMapping(method=RequestMethod.POST)
	public ResultResponse addPerson(@RequestBody User user) {
		return processSimple(new ResultResponse(), rr -> {
			rr.addAttribute("greet", RequestMethod.POST.name() + user);
		});
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResultResponse getPerson(@CookieValue(name="x", required = false)String x,HttpServletResponse res) {
		System.out.println("Old cookie x: " + x);
		x = new Date().toLocaleString();
		System.out.println("New cookie x: " + x);
		res.addCookie(new Cookie("x", x));
		return processSimple(new ResultResponse(), rr -> {
			rr.addAttribute("greet", RequestMethod.GET);
		});
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public ResultResponse updatePerson(String[] strs,HttpServletRequest req) {
		return processSimple(new ResultResponse(), rr -> {
			rr.addAttribute("greet", RequestMethod.PUT.toString() + strs + ",content type:" + getBytesFromRequestReader(req));
		});
	}

	@RequestMapping(method=RequestMethod.DELETE)
	public ResultResponse deletePerson() {
		return processSimple(new ResultResponse(), rr -> {
			rr.addAttribute("greet", RequestMethod.DELETE);
		});
	}
	
	@RequestMapping(method=RequestMethod.HEAD)
	public ResultResponse headPerson() {
		return processSimple(new ResultResponse(), rr -> {
			rr.addAttribute("greet", RequestMethod.HEAD);
		});
	}
	
	@RequestMapping(method=RequestMethod.OPTIONS)
	public ResultResponse optionsPerson() {
		return processSimple(new ResultResponse(), rr -> {
			rr.addAttribute("greet", RequestMethod.OPTIONS);
		});
	}
	
	@RequestMapping(method=RequestMethod.PATCH)
	public ResultResponse patchPerson() {
		return processSimple(new ResultResponse(), rr -> {
			rr.addAttribute("greet", RequestMethod.PATCH);
		});
	}
	
	@RequestMapping(method=RequestMethod.TRACE)
	public ResultResponse tracePerson() {
		return processSimple(new ResultResponse(), rr -> {
			rr.addAttribute("greet", RequestMethod.TRACE);
		});
	}

}
