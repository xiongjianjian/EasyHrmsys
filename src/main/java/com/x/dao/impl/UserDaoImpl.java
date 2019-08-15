package com.x.dao.impl;

import com.x.dao.UserDao;
import com.x.entity.PageBean;
import com.x.entity.User;
import com.x.util.StringUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public User login(User user) { //登录
		// TODO Auto-generated method stub
		String sql="select user_id,user_name,password,true_name,role"
				+ " from sys_user"
				+ " where user_name=? and password=?";
		final User resultUser=new User();
		jdbcTemplate.query(sql, new Object[]{user.getUserName(),user.getPassword()}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				resultUser.setUserId(rs.getInt("user_id"));
				resultUser.setUserName(rs.getString("user_name"));
				resultUser.setPassword(rs.getString("password"));
				resultUser.setTrueName(rs.getString("true_name"));
				resultUser.setRole(rs.getInt("role"));
			}
		});
		return resultUser;
	}

	@Override
	public List<User> userList(PageBean pageBean, User s_user) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer("select user_id,user_name,password,true_name,role"
				+ " from sys_user"
				+ " where role<>-1 "); //角色不是超级管理员的
		if(s_user!=null){
			if(StringUtil.isNotEmpty(s_user.getTrueName())){ //真是姓名模糊查询
				sb.append(" and true_name like '%"+s_user.getTrueName()+"%'");
			}
		}
		if(pageBean!=null){ //拼接分页sql（user_id升序）
			sb.append(" order by user_id asc limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		final List<User> userList=new ArrayList<User>();
		jdbcTemplate.query(sb.toString(), new Object[]{}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				User resultUser=new User();
				resultUser.setUserId(rs.getInt("user_id"));
				resultUser.setUserName(rs.getString("user_name"));
				resultUser.setPassword(rs.getString("password"));
				resultUser.setTrueName(rs.getString("true_name"));
				resultUser.setRole(rs.getInt("role"));
				userList.add(resultUser);
			}
		});
		return userList;
	}

	@Override
	public int userCount(User s_user) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer("select "
				+ " count(*) as total"
				+ " from sys_user"
				+ " where role<>-1 ");
		if(s_user!=null){
			if(StringUtil.isNotEmpty(s_user.getTrueName())){
				sb.append(" and true_name like '%"+s_user.getTrueName()+"%'");
			}
		}
		return jdbcTemplate.queryForObject(sb.toString(), Integer.class);
	}

	@Override
	public User loadById(int id) {
		// TODO Auto-generated method stub
		String sql="select user_id,user_name,password,true_name,role"
				+ " from sys_user where user_id=?";
		final User resultUser=new User();
		jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				resultUser.setUserId(rs.getInt("user_id"));
				resultUser.setUserName(rs.getString("user_name"));
				resultUser.setPassword(rs.getString("password"));
				resultUser.setTrueName(rs.getString("true_name"));
				resultUser.setRole(rs.getInt("role"));
			}
		});
		return resultUser;
	}

	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		String sql="insert into sys_user values(NULL,?,?,?,?)";
		jdbcTemplate.update(sql,new Object[]{user.getUserName(),user.getPassword(),
				user.getTrueName(),user.getRole()});
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		String sql="update sys_user set user_name=?,password=?,true_name=?,role=?"
				+ " where user_id=?";
		jdbcTemplate.update(sql, new Object[]{user.getUserName(),user.getPassword(),
				user.getTrueName(),user.getRole(),user.getUserId()});
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql="delete from sys_user where user_id=?";
		jdbcTemplate.update(sql, new Object[]{id});
	}

	@Override
	public List<User> userListExceptSuperAdmin() {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer("select user_id,user_name,password,true_name,role"
				+ " from sys_user"
				+ " where role<>-1");
		final List<User> userList=new ArrayList<User>();
		jdbcTemplate.query(sb.toString(), new Object[]{}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				User resultUser=new User();
				resultUser.setUserId(rs.getInt("user_id"));
				resultUser.setUserName(rs.getString("user_name"));
				resultUser.setPassword(rs.getString("password"));
				resultUser.setTrueName(rs.getString("true_name"));
				resultUser.setRole(rs.getInt("role"));
				userList.add(resultUser);
			}
		});
		return userList;
	}
	
}
