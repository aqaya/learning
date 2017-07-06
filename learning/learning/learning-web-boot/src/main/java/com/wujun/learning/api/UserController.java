package com.wujun.learning.api;

import com.wujun.learning.commom.utils.BaseController;
import com.wujun.learning.commom.utils.ResultResponse;
import com.wujun.learning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends BaseController{

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/transfer")
	public ResultResponse transfer(Long idFrom, Long idTo, Double amount){
		return processSimple(new ResultResponse(), rr -> userService.transfer(idFrom, idTo, amount));
	}
	
}
