package com.x.dao;

import com.x.entity.Employee;
import com.x.entity.PageBean;

import java.util.List;

public interface EmployeeDao {

	public List<Employee> employeeList(PageBean pageBean, Employee s_employee);
	public int employeeCount(Employee s_employee);
	public Employee loadById(int id);
	public void add(Employee employee);
	public void update(Employee employee);
	public void delete(int id);
	
	public String findLastEmpNo();
}
