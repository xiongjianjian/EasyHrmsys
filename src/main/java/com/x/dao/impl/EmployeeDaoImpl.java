package com.x.dao.impl;

import com.x.dao.EmployeeDao;
import com.x.entity.Employee;
import com.x.entity.PageBean;
import com.x.util.DateUtil;
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
public class EmployeeDaoImpl implements EmployeeDao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Employee> employeeList(PageBean pageBean, Employee s_employee) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer("select emp_id,emp_no,emp_name,emp_sex,emp_picture,emp_idcard,"
				+ " emp_nation,emp_zzmm,emp_record,school,major,t1.dept_id,hiredate,emp_source,emp_form,"
				+ " emp_desc,is_leave,t2.dept_name"
				+ " from employee t1 left join department t2 on t1.dept_id=t2.dept_id");
		if(s_employee!=null){
			if(StringUtil.isNotEmpty(s_employee.getEmpNo())){
				sb.append(" and emp_no like '%"+s_employee.getEmpNo()+"%'");
			}
			if(StringUtil.isNotEmpty(s_employee.getEmpName())){
				sb.append(" and emp_name like '%"+s_employee.getEmpName()+"%'");
			}
			if(s_employee.getDeptId()!=null){
				sb.append(" and t1.dept_id ='"+s_employee.getDeptId()+"'");
			}
		}
		if(pageBean!=null){
			sb.append(" order by emp_id asc limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		final List<Employee> employeeList=new ArrayList<Employee>();
		jdbcTemplate.query(sb.toString().replaceFirst("and", "where"), new Object[]{}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				Employee employee=new Employee();
				employee.setEmpId(rs.getString("emp_id"));
				employee.setEmpNo(rs.getString("emp_no"));
				employee.setEmpName(rs.getString("emp_name"));
				employee.setEmpSex(rs.getString("emp_sex"));
				employee.setEmpPicture(rs.getString("emp_picture"));
				employee.setEmpIdcard(rs.getString("emp_idcard"));
				employee.setEmpNation(rs.getString("emp_nation"));
				employee.setEmpZzmm(rs.getString("emp_zzmm"));
				employee.setEmpRecord(rs.getString("emp_record"));
				employee.setSchool(rs.getString("school"));
				employee.setMajor(rs.getString("major"));
				employee.setDeptId(rs.getInt("dept_id"));
				employee.setHiredate(DateUtil.formatString(rs.getString("hiredate"), "yyyy-MM-dd")); //日期转换工具类
				employee.setEmpSource(rs.getString("emp_source"));
				employee.setEmpForm(rs.getString("emp_form"));
				employee.setEmpDesc(rs.getString("emp_desc"));
				employee.setIsLeave(rs.getInt("is_leave"));
				employee.setDeptName(rs.getString("dept_name"));
				employeeList.add(employee);
			}
		});
		return employeeList;
	}

	@Override
	public int employeeCount(Employee s_employee) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer("select count(*) as total from employee t1 left join department t2 on t1.dept_id=t2.dept_id");
		if(s_employee!=null){
			if(StringUtil.isNotEmpty(s_employee.getEmpNo())){
				sb.append(" and emp_no like '%"+s_employee.getEmpNo()+"%'");
			}
			if(StringUtil.isNotEmpty(s_employee.getEmpName())){
				sb.append(" and emp_name like '%"+s_employee.getEmpName()+"%'");
			}
			if(s_employee.getDeptId()!=null){
				sb.append(" and t1.dept_id ='"+s_employee.getDeptId()+"'");
			}
		}
		return jdbcTemplate.queryForObject(sb.toString().replaceFirst("and", "where"), Integer.class);
	}

	@Override
	public Employee loadById(int id) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer("select emp_id,emp_no,emp_name,emp_sex,emp_picture,emp_idcard,"
				+ " emp_nation,emp_zzmm,emp_record,school,major,t1.dept_id,hiredate,emp_source,emp_form,"
				+ " emp_desc,is_leave,t2.dept_name"
				+ " from employee t1 left join department t2 on t1.dept_id=t2.dept_id where emp_id=?");
		final Employee employee=new Employee();
		jdbcTemplate.query(sb.toString(), new Object[]{id}, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				employee.setEmpId(rs.getString("emp_id"));
				employee.setEmpNo(rs.getString("emp_no"));
				employee.setEmpName(rs.getString("emp_name"));
				employee.setEmpSex(rs.getString("emp_sex"));
				employee.setEmpPicture(rs.getString("emp_picture"));
				employee.setEmpIdcard(rs.getString("emp_idcard"));
				employee.setEmpNation(rs.getString("emp_nation"));
				employee.setEmpZzmm(rs.getString("emp_zzmm"));
				employee.setEmpRecord(rs.getString("emp_record"));
				employee.setSchool(rs.getString("school"));
				employee.setMajor(rs.getString("major"));
				employee.setDeptId(rs.getInt("dept_id"));
				employee.setHiredate(DateUtil.formatString(rs.getString("hiredate"), "yyyy-MM-dd")); //日期转换工具类
				employee.setEmpSource(rs.getString("emp_source"));
				employee.setEmpForm(rs.getString("emp_form"));
				employee.setEmpDesc(rs.getString("emp_desc"));
				employee.setIsLeave(rs.getInt("is_leave"));
				employee.setDeptName(rs.getString("dept_name"));
			}
		});
		return employee;
	}

	@Override
	public void add(Employee employee) {
		// TODO Auto-generated method stub
		String sql="insert into employee values(NULL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, new Object[]{employee.getEmpNo(),employee.getEmpName(),employee.getEmpSex(),
				employee.getEmpPicture(),employee.getEmpIdcard(),employee.getEmpNation(),
				employee.getEmpZzmm(),employee.getEmpRecord(),employee.getSchool(),employee.getMajor(),
				employee.getDeptId(),employee.getHiredate(),employee.getEmpSource(),employee.getEmpForm(),
				employee.getEmpDesc(),employee.getIsLeave()});
	}

	@Override
	public void update(Employee employee) {
		// TODO Auto-generated method stub
		String sql="update employee set emp_no=?,emp_name=?,emp_sex=?,emp_picture=?,emp_idcard=?,emp_nation=?,"
				+ " emp_zzmm=?,emp_record=?,school=?,major=?,dept_id=?,hiredate=?,emp_source=?,emp_form=?,"
				+ " emp_desc=?,is_leave=? where emp_id=?";
		jdbcTemplate.update(sql, new Object[]{employee.getEmpNo(),employee.getEmpName(),employee.getEmpSex(),
				employee.getEmpPicture(),employee.getEmpIdcard(),employee.getEmpNation(),
				employee.getEmpZzmm(),employee.getEmpRecord(),employee.getSchool(),employee.getMajor(),
				employee.getDeptId(),employee.getHiredate(),employee.getEmpSource(),employee.getEmpForm(),
				employee.getEmpDesc(),employee.getIsLeave(),employee.getEmpId()});
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql="delete from employee where emp_id=?";
		jdbcTemplate.update(sql, new Object[]{id});
	}

	@Override
	public String findLastEmpNo() {
		// TODO Auto-generated method stub
		String sql="SELECT MAX(emp_no) FROM employee";
		return jdbcTemplate.queryForObject(sql, String.class);
	}

}
