package com.x.service.impl;

import com.x.dao.DataDicTypeDao;
import com.x.entity.DataDicType;
import com.x.entity.PageBean;
import com.x.service.DataDicTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dataDicTypeDao")
public class DataDicTypeServiceImpl implements DataDicTypeService {

	@Autowired
	private DataDicTypeDao dataDicTypeDao;
	
	@Override
	public List<DataDicType> dataDicTypeList(PageBean pageBean, DataDicType s_dataDicType) {
		// TODO Auto-generated method stub
		return dataDicTypeDao.dataDicTypeList(pageBean,s_dataDicType);
	}

	@Override
	public int dataDicTypeCount(DataDicType s_dataDicType) {
		// TODO Auto-generated method stub
		return dataDicTypeDao.dataDicTypeCount(s_dataDicType);
	}
	
	@Override
	public DataDicType loadById(int id) {
		// TODO Auto-generated method stub
		return dataDicTypeDao.loadById(id);
	}

	@Override
	public void add(DataDicType dataDicType) {
		// TODO Auto-generated method stub
		dataDicTypeDao.add(dataDicType);
	}

	@Override
	public void update(DataDicType dataDicType) {
		// TODO Auto-generated method stub
		dataDicTypeDao.update(dataDicType);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		dataDicTypeDao.delete(id);
	}

}
