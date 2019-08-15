package com.x.dao;

import com.x.entity.PageBean;
import com.x.entity.User;

import java.util.List;

public interface UserDao {

	public User login(User user);
	public List<User> userList(PageBean pageBean, User s_user);
	public int userCount(User s_user);
	public User loadById(int id);
	public void add(User user);
	public void update(User user);
	public void delete(int id);
	
	public List<User> userListExceptSuperAdmin();
	
}
