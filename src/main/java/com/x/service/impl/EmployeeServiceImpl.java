package com.x.service.impl;

import com.x.dao.EmployeeDao;
import com.x.entity.Employee;
import com.x.entity.PageBean;
import com.x.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

	@Resource
	private EmployeeDao employeeDao;
	
	@Override
	public List<Employee> employeeList(PageBean pageBean, Employee s_employee) {
		// TODO Auto-generated method stub
		return employeeDao.employeeList(pageBean, s_employee);
	}

	@Override
	public int employeeCount(Employee s_employee) {
		// TODO Auto-generated method stub
		return employeeDao.employeeCount(s_employee);
	}

	@Override
	public Employee loadById(int id) {
		// TODO Auto-generated method stub
		return employeeDao.loadById(id);
	}

	@Override
	public void add(Employee employee) {
		// TODO Auto-generated method stub
		employeeDao.add(employee);
	}

	@Override
	public void update(Employee employee) {
		// TODO Auto-generated method stub
		employeeDao.update(employee);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		employeeDao.delete(id);
	}

	@Override
	public String findLastEmpNo() {
		// TODO Auto-generated method stub
		return employeeDao.findLastEmpNo();
	}

}
