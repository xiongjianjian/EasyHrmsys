package com.x.service;

import com.x.entity.Interview;
import com.x.entity.PageBean;

import java.util.List;

public interface InterviewService {
	
	public List<Interview> interviewList(PageBean pageBean, Interview s_interview);
	public int interviewCount(Interview s_interview);
	public Interview loadById(int id);
	public void add(Interview interview);
	public void update(Interview interview);
	public void delete(int id);
	
}
