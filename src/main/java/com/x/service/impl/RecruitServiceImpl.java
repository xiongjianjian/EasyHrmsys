package com.x.service.impl;

import com.x.dao.RecruitDao;
import com.x.entity.PageBean;
import com.x.entity.Recruit;
import com.x.service.RecruitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("recruitService")
public class RecruitServiceImpl implements RecruitService {

	@Resource
	private RecruitDao recruitDao;
	
	@Override
	public List<Recruit> recruitList(PageBean pageBean, Recruit s_recruit) {
		// TODO Auto-generated method stub
		return recruitDao.recruitList(pageBean,s_recruit);
	}

	@Override
	public int recruitCount(Recruit s_recruit) {
		// TODO Auto-generated method stub
		return recruitDao.recruitCount(s_recruit);
	}

	@Override
	public Recruit loadById(int id) {
		// TODO Auto-generated method stub
		return recruitDao.loadById(id);
	}

	@Override
	public void add(Recruit recruit) {
		// TODO Auto-generated method stub
		recruitDao.add(recruit);
	}

	@Override
	public void update(Recruit recruit) {
		// TODO Auto-generated method stub
		recruitDao.update(recruit);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		recruitDao.delete(id);
	}

	@Override
	public void setHealth(int id) {
		// TODO Auto-generated method stub
		recruitDao.setHealth(id);
	}

	@Override
	public void setIdcard(int id) {
		// TODO Auto-generated method stub
		recruitDao.setIdcard(id);
	}

	@Override
	public List<Recruit> recruitListByStateOk() {
		// TODO Auto-generated method stub
		return recruitDao.recruitListByStateOk();
	}

}
