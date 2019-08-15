package com.x.dao;

import com.x.entity.DataDic;
import com.x.entity.PageBean;

import java.util.List;

public interface DataDicDao {
	
	public List<DataDic> dataDicList(PageBean pageBean, DataDic s_dataDic);
	public int dataDicCount(DataDic s_dataDic);
	public DataDic loadById(int ddId);
	public void add(DataDic dataDic);
	public void update(DataDic dataDic);
	public void delete(int ddId);
	
}
