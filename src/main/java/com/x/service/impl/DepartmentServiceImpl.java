package com.x.service.impl;

import com.x.dao.DepartmentDao;
import com.x.entity.Department;
import com.x.entity.PageBean;
import com.x.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

	@Resource
	private DepartmentDao departmentDao;
	
	@Override
	public List<Department> departmentList(PageBean pageBean, Department s_department) {
		// TODO Auto-generated method stub
		return departmentDao.departmentList(pageBean, s_department);
	}

	@Override
	public int departmentCount(Department s_department) {
		// TODO Auto-generated method stub
		return departmentDao.departmentCount(s_department);
	}

	@Override
	public Department loadById(int deptId) {
		// TODO Auto-generated method stub
		return departmentDao.loadById(deptId);
	}

	@Override
	public void add(Department department) {
		// TODO Auto-generated method stub
		departmentDao.add(department);
	}

	@Override
	public void update(Department department) {
		// TODO Auto-generated method stub
		departmentDao.update(department);
	}

	@Override
	public void delete(int deptId) {
		// TODO Auto-generated method stub
		departmentDao.delete(deptId);
	}

}
