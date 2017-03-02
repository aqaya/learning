package com.wujun.learning.service;

import com.wujun.learning.commom.factory.CheckUtils;
import com.wujun.learning.dao.UserDAO;
import com.wujun.learning.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
@Service
public class UserService {
	@Autowired
	UserDAO userDAO;
	
	@SuppressWarnings("deprecation")
	@Transactional(isolation=Isolation.DEFAULT)
	public synchronized void transfer(Long idFrom, Long idTo, Double amount){
		Date date = new Date();
		System.out.println("thread id : " + Thread.currentThread().getId() + " \n" + date.toLocaleString() + "  begin");
		CheckUtils.notNull("参数不能为空!", idFrom, idTo);
		CheckUtils.ge(amount, 0.0, "转账金额必须大于0!");


		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		User from = userDAO.selectById(idFrom);
		User to = userDAO.selectById(idTo);
		
		System.out.println(String.format("转账前账户%s的余额是%s,账户%s的余额是%s", 
				from.getId(), from.getBalance(), to.getId(), to.getBalance()));
		
		CheckUtils.notNull("用户不存在!", from, to);
		
		CheckUtils.ge(from.getBalance(), amount, "账户余额不足,不能转账!");

		from.setBalance(from.getBalance() - amount);

		to.setBalance(to.getBalance() + amount);
		System.out.println(String.format("转账后账户%s的余额是%s,账户%s的余额是%s", 
				from.getId(), from.getBalance(), to.getId(), to.getBalance()));
		System.out.println(date.toLocaleString() + "  end \n\n\n\n\n");
	
	}
}
