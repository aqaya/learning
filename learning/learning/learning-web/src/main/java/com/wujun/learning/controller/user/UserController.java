package com.wujun.learning.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wujun.learning.controller.base.BaseController;
import com.wujun.learning.controller.base.ResultResponse;
import com.wujun.learning.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/transfer")
	public ResultResponse transfer(Long idFrom, Long idTo, Double amount){
		return processSimple(new ResultResponse(), new Taker() {
			
			@Override
			public void process(ResultResponse rr) {
				userService.transfer(idFrom, idTo, amount);
			}
		});
	}
	
}
