package com.x.service.impl;

import com.x.dao.UserDao;
import com.x.entity.PageBean;
import com.x.entity.User;
import com.x.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	
	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}

	@Override
	public List<User> userList(PageBean pageBean, User s_user) {
		// TODO Auto-generated method stub
		return userDao.userList(pageBean,s_user);
	}

	@Override
	public int userCount(User s_user) {
		// TODO Auto-generated method stub
		return userDao.userCount(s_user);
	}

	@Override
	public User loadById(int id) {
		// TODO Auto-generated method stub
		return userDao.loadById(id);
	}

	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		userDao.add(user);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		userDao.update(user);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		userDao.delete(id);
	}

	@Override
	public List<User> userListExceptSuperAdmin() {
		// TODO Auto-generated method stub
		return userDao.userListExceptSuperAdmin();
	}

}
