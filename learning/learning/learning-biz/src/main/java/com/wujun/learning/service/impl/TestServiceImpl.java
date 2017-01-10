package com.wujun.learning.service.impl;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.wujun.learning.commom.factory.MyLogFactory;
import com.wujun.learning.service.TestService;

@Service
public class TestServiceImpl implements TestService {

	Logger logger = MyLogFactory.getBusiness();
	
	public String formatNameAndAge(String name, Integer age) {
		logger.debug(String.format("Debug Name : %s, Age : %s", name, age));
		logger.info(String.format("Info Name : %s, Age : %s", name, age));
		return String.format("Hello %s, you are %s years old!",	name, age);
	}

}
