package com.x.service.impl;

import com.x.dao.InterviewDao;
import com.x.entity.Interview;
import com.x.entity.PageBean;
import com.x.service.InterviewService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("interviewService")
public class InterviewServiceImpl implements InterviewService {
	
	@Resource
	private InterviewDao interviewDao;
	
	public List<Interview> interviewList(PageBean pageBean, Interview s_interview) {
		// TODO Auto-generated method stub
		return interviewDao.interviewList(pageBean, s_interview);
	}

	@Override
	public int interviewCount(Interview s_interview) {
		// TODO Auto-generated method stub
		return interviewDao.interviewCount(s_interview);
	}

	@Override
	public Interview loadById(int id) {
		// TODO Auto-generated method stub
		return interviewDao.loadById(id);
	}

	@Override
	public void add(Interview interview) {
		// TODO Auto-generated method stub
		interviewDao.add(interview);
	}

	@Override
	public void update(Interview interview) {
		// TODO Auto-generated method stub
		interviewDao.update(interview);
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		interviewDao.delete(id);
	}

}
