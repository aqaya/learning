package com.wujun.learning;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wujun.learning.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testMoneyTransfer() {
    	Long idFrom = 1L;
    	Long idTo = 2L;
    	Double amount = 0.99;
    	
    	userService.transfer(idFrom, idTo, amount);
    }
}