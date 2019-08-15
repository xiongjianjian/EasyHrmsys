package com.x.service;

import com.x.entity.PageBean;
import com.x.entity.Recruit;

import java.util.List;

public interface RecruitService {

	public List<Recruit> recruitList(PageBean pageBean, Recruit s_recruit);
	public int recruitCount(Recruit s_recruit);
	public Recruit loadById(int id);
	public void add(Recruit recruit);
	public void update(Recruit recruit);
	public void delete(int id);
	public void setHealth(int id);
	public void setIdcard(int id);
	
	public List<Recruit> recruitListByStateOk();
}
