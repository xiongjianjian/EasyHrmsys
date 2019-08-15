package com.x.dao.impl;

import com.x.dao.DepartmentDao;
import com.x.entity.Department;
import com.x.entity.PageBean;
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
public class DepartmentDaoImpl implements DepartmentDao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Department> departmentList(PageBean pageBean, Department s_department) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer("select dept_id,dept_name from department");
		if(s_department!=null){
			if(StringUtil.isNotEmpty(s_department.getDeptName())){
				sb.append(" and dept_name like '%"+s_department.getDeptName()+"%'");
			}
		}
		if(pageBean!=null){
			sb.append(" order by dept_id asc limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		final List<Department> departmentList=new ArrayList<Department>();
		jdbcTemplate.query(sb.toString().replaceFirst("and", "where"), new Object[]{}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				Department department=new Department();
				department.setDeptId(rs.getInt("dept_id"));
				department.setDeptName(rs.getString("dept_name"));
				departmentList.add(department);
			}
		});
		return departmentList;
	}

	@Override
	public int departmentCount(Department s_department) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer("select count(*) as total from department");
		if(s_department!=null){
			if(StringUtil.isNotEmpty(s_department.getDeptName())){
				sb.append(" and dept_name like '%"+s_department.getDeptName()+"%'");
			}
		}
		return jdbcTemplate.queryForObject(sb.toString().replaceFirst("and", "where"), Integer.class);
	}

	@Override
	public Department loadById(int deptId) {
		// TODO Auto-generated method stub
		String sql="select dept_id,dept_name from department where dept_id=?";
		final Department department=new Department();
		jdbcTemplate.query(sql, new Object[]{deptId}, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				department.setDeptId(rs.getInt("dept_id"));
				department.setDeptName(rs.getString("dept_name"));
			}
		});
		return department;
	}

	@Override
	public void add(Department department) {
		// TODO Auto-generated method stub
		String sql="insert into department values(NULL,?)";
		jdbcTemplate.update(sql, new Object[]{department.getDeptName()});
	}

	@Override
	public void update(Department department) {
		// TODO Auto-generated method stub
		String sql="update department set dept_name=? where dept_id=?";
		jdbcTemplate.update(sql, new Object[]{department.getDeptName(),department.getDeptId()});
	}

	@Override
	public void delete(int deptId) {
		// TODO Auto-generated method stub
		String sql="delete from department where dept_id=?";
		jdbcTemplate.update(sql, new Object[]{deptId});
	}

}
