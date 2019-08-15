package com.x.dao;

import com.x.entity.DataDicType;
import com.x.entity.PageBean;

import java.util.List;

public interface DataDicTypeDao {

	public List<DataDicType> dataDicTypeList(PageBean pageBean, DataDicType s_dataDicType);
	public int dataDicTypeCount(DataDicType s_dataDicType);
	public DataDicType loadById(int ddTypeId);
	public void add(DataDicType dataDicType);
	public void update(DataDicType dataDicType);
	public void delete(int ddTypeId);
	
}
