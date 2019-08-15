package com.x.service;

import com.x.entity.Department;
import com.x.entity.PageBean;

import java.util.List;

public interface DepartmentService {

	public List<Department> departmentList(PageBean pageBean, Department s_department);
	public int departmentCount(Department s_department);
	public Department loadById(int deptId);
	public void add(Department department);
	public void update(Department department);
	public void delete(int deptId);
	
}
