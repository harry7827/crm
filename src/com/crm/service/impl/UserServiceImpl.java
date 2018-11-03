package com.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.crm.dao.UserDao;
import com.crm.domain.User;
import com.crm.service.UserService;
import com.crm.utils.MD5Utils;

public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	@Override
	public void regist(User user) {
		user.setUser_state("1");
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		userDao.save(user);
	}
	@Override
	public void findById(User user) {
		User u = userDao.findById(user.getUser_id());
	}
	@Override
	public User login(User user) {
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		return userDao.login(user);
	}
	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}
}
