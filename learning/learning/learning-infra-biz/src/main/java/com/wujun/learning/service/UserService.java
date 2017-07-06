package com.wujun.learning.service;

import com.wujun.learning.commom.factory.CheckUtils;
import com.wujun.learning.dao.hibernate.UserDAO;
import com.wujun.learning.dao.mybatis.UserMapper;
import com.wujun.learning.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class UserService {
	private final UserDAO userDAO;

	private final UserMapper userMapper;

	@Autowired
	public UserService(UserDAO userDAO, UserMapper userMapper) {
		this.userDAO = userDAO;
		this.userMapper = userMapper;
	}

	@Transactional
	public void transfer(Long idFrom, Long idTo, Double amount){
		CheckUtils.notNull("参数不能为空!", idFrom, idTo);
		CheckUtils.ge(amount, 0.0, "转账金额必须大于0!");

		User from = userDAO.selectById(idFrom);
		User to = userDAO.selectById(idTo);
		
		CheckUtils.notNull("用户不存在!", from, to);
		
		CheckUtils.ge(from.getBalance(), amount, "账户余额不足,不能转账!");

		from.setBalance(from.getBalance() - amount);

		to.setBalance(to.getBalance() + amount);
	}

	@Transactional
	public void modifyName(Long id, String newName){
		User user = userMapper.queryById(id);
		user.setName(newName);
		userMapper.updateById(user);
	}

	@Transactional
	public User queryById(Long id){
		return userMapper.queryById(id);
	}
}
