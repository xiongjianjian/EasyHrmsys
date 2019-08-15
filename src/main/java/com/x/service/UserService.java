package com.x.service;

import com.x.entity.PageBean;
import com.x.entity.User;

import java.util.List;

public interface UserService {

	/**
	 * @author 稚
	 * 用户登录，验证成功，返回User对象
	 */
	public User login(User user);
	
	/**
	 * @author 稚
	 * @param 分页Bean
	 * @param 搜索条件的User对象
	 */
	public List<User> userList(PageBean pageBean, User s_user);
	
	/**
	 * @author 稚
	 * @param 搜索条件的User对象
	 */
	public int userCount(User s_user);
	
	/**
	 * @author 稚
	 * 根据主键返回User对象
	 */
	public User loadById(int id);
	
	/**
	 * @author 稚
	 * 添加User对象
	 */
	public void add(User user);
	
	/**
	 * @author 稚
	 * 更新User对象
	 */
	public void update(User user);
	
	/**
	 * @author 稚
	 * 删除User对象
	 */
	public void delete(int id);
	
	/**
	 * @author 稚
	 * 选出所有的用户，超级管理员除外（角色是-1的为超级管理员）
	 */
	public List<User> userListExceptSuperAdmin();
	
}
