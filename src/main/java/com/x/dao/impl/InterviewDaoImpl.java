package com.x.dao.impl;

import com.x.dao.InterviewDao;
import com.x.entity.Interview;
import com.x.entity.PageBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InterviewDaoImpl implements InterviewDao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Interview> interviewList(PageBean pageBean, Interview s_interview) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer("select id,name,sex,idcard,tellphone,recruit_from,interviewer,is_ok,miss_reason"
				+ " from interview");
		if(s_interview!=null){
			if(s_interview.getName()!=null){
				sb.append(" and name like '%"+s_interview.getName()+"%'");
			}
			if(s_interview.getIdcard()!=null){
				sb.append(" and idcard like '%"+s_interview.getIdcard()+"%'");
			}
		}
		if(pageBean!=null){
			sb.append("  order by id asc limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		final List<Interview> interviewList=new ArrayList<Interview>();
		jdbcTemplate.query(sb.toString().replaceFirst("and", "where"), new Object[]{}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				Interview resultInterview=new Interview();	
				resultInterview.setId(rs.getInt("id"));
				resultInterview.setName(rs.getString("name"));
				resultInterview.setSex(rs.getString("sex"));
				resultInterview.setIdcard(rs.getString("idcard"));
				resultInterview.setTellphone(rs.getString("tellphone"));
				resultInterview.setRecruitFrom(rs.getString("recruit_from"));
				resultInterview.setInterviewer(rs.getString("interviewer"));
				resultInterview.setIsOk(rs.getInt("is_ok"));
				resultInterview.setMissReason(rs.getString("miss_reason"));
				interviewList.add(resultInterview);
			}
		});
		return interviewList;
	}

	@Override
	public int interviewCount(Interview s_interview) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer("select count(*) as total"
				+ " from interview");
		if(s_interview!=null){
			if(s_interview.getName()!=null){
				sb.append(" and name like '%"+s_interview.getName()+"%'");
			}
			if(s_interview.getIdcard()!=null){
				sb.append(" and idcard like '%"+s_interview.getIdcard()+"%'");
			}
		}
		return jdbcTemplate.queryForObject(sb.toString().replaceFirst("and", "where"), Integer.class);
	}

	@Override
	public Interview loadById(int id) {
		// TODO Auto-generated method stub
		String sql="select id,name,sex,idcard,tellphone,recruit_from,interviewer,is_ok,miss_reason"
				+ " from interview where id=?";
		final Interview resultInterview=new Interview();
		jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				resultInterview.setId(rs.getInt("id"));
				resultInterview.setName(rs.getString("name"));
				resultInterview.setSex(rs.getString("sex"));
				resultInterview.setIdcard(rs.getString("idcard"));
				resultInterview.setTellphone(rs.getString("tellphone"));
				resultInterview.setRecruitFrom(rs.getString("recruit_from"));
				resultInterview.setInterviewer(rs.getString("interviewer"));
				resultInterview.setIsOk(rs.getInt("is_ok"));
				resultInterview.setMissReason(rs.getString("miss_reason"));
			}
		});
		return resultInterview;
	}

	@Override
	public void add(Interview interview) {
		// TODO Auto-generated method stub
		String sql="insert into interview values(NULL,?,?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql,new Object[]{interview.getName(),interview.getSex(),interview.getIdcard(),
				interview.getTellphone(),interview.getRecruitFrom(),interview.getInterviewer(),
				interview.getIsOk(),interview.getMissReason()});
	}

	@Override
	public void update(Interview interview) {
		// TODO Auto-generated method stub
		String sql="update interview set name=?,sex=?,idcard=?,tellphone=?,recruit_from=?,interviewer=?,is_ok=?,miss_reason=?"
				+ " where id=?";
		jdbcTemplate.update(sql, new Object[]{interview.getName(),interview.getSex(),interview.getIdcard(),
				interview.getTellphone(),interview.getRecruitFrom(),interview.getInterviewer(),
				interview.getIsOk(),interview.getMissReason(),interview.getId()});
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql="delete from interview where id=?";
		jdbcTemplate.update(sql, new Object[]{id});
	}
	
}
