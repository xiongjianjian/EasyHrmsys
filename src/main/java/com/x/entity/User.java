package com.x.entity;

public class User {

	private Integer userId;
	private String userName;
	private String password;
	private String trueName;
	private Integer role; //角色：-1超级管理员，0人资总监，1招聘专员，2培训专员，3人事专员
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public Integer getRole() {
		return role;
	}
	public void setRole(Integer role) {
		this.role = role;
	}

}
