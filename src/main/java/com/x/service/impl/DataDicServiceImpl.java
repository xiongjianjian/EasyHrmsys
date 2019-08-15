package com.x.service.impl;

import com.x.dao.DataDicDao;
import com.x.entity.DataDic;
import com.x.entity.PageBean;
import com.x.service.DataDicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dataDicService")
public class DataDicServiceImpl implements DataDicService {

	@Autowired
	private DataDicDao dataDicDao;

	@Override
	public List<DataDic> dataDicList(PageBean pageBean, DataDic s_dataDic) {
		// TODO Auto-generated method stub
		return dataDicDao.dataDicList(pageBean, s_dataDic);
	}

	@Override
	public int dataDicCount(DataDic s_dataDic) {
		// TODO Auto-generated method stub
		return dataDicDao.dataDicCount(s_dataDic);
	}

	@Override
	public DataDic loadById(int id) {
		// TODO Auto-generated method stub
		return dataDicDao.loadById(id);
	}

	@Override
	public void add(DataDic dataDic) {
		// TODO Auto-generated method stub
		dataDicDao.add(dataDic);
	}

	@Override
	public void update(DataDic dataDic) {
		// TODO Auto-generated method stub
		dataDicDao.update(dataDic);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		dataDicDao.delete(id);
	}
	
}
