package com.x.dao.impl;

import com.x.dao.RecruitDao;
import com.x.entity.PageBean;
import com.x.entity.Recruit;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Repository
public class RecruitDaoImpl implements RecruitDao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Recruit> recruitList(PageBean pageBean, Recruit s_recruit) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer("select id,name,sex,idcard,tellphone,recruit_from,healthy_state,idcard_state"
				+ " from recruit");
		if(s_recruit!=null){
			if(s_recruit.getName()!=null){
				sb.append(" and name like '%"+s_recruit.getName()+"%'");
			}
			if(s_recruit.getIdcard()!=null){
				sb.append(" and idcard like '%"+s_recruit.getIdcard()+"%'");
			}
		}
		if(pageBean!=null){
			sb.append(" order by id asc limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		final List<Recruit> recruitList=new ArrayList<Recruit>();
		jdbcTemplate.query(sb.toString().replaceFirst("and", "where"), new Object[]{}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				Recruit resultRecruit=new Recruit();	
				resultRecruit.setId(rs.getInt("id"));
				resultRecruit.setName(rs.getString("name"));
				resultRecruit.setSex(rs.getString("sex"));
				resultRecruit.setIdcard(rs.getString("idcard"));
				resultRecruit.setTellphone(rs.getString("tellphone"));
				resultRecruit.setRecruitFrom(rs.getString("recruit_from"));
				resultRecruit.setHealthyState(rs.getInt("healthy_state"));
				resultRecruit.setIdcardState(rs.getInt("idcard_state"));
				recruitList.add(resultRecruit);
			}
		});
		return recruitList;
	}

	@Override
	public int recruitCount(Recruit s_recruit) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer("select count(*) as total"
				+ " from recruit");
		if(s_recruit!=null){
			if(s_recruit.getName()!=null){
				sb.append(" and name like '%"+s_recruit.getName()+"%'");
			}
			if(s_recruit.getIdcard()!=null){
				sb.append(" and idcard like '%"+s_recruit.getIdcard()+"%'");
			}
		}
		return jdbcTemplate.queryForObject(sb.toString().replaceFirst("and", "where"), Integer.class);
	}

	@Override
	public Recruit loadById(int id) {
		// TODO Auto-generated method stub
		String sql="select id,name,sex,idcard,tellphone,recruit_from,healthy_state,idcard_state"
				+ " from recruit where id=?";
		final Recruit resultRecruit=new Recruit();
		jdbcTemplate.query(sql, new Object[]{id}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				resultRecruit.setId(rs.getInt("id"));
				resultRecruit.setName(rs.getString("name"));
				resultRecruit.setSex(rs.getString("sex"));
				resultRecruit.setIdcard(rs.getString("idcard"));
				resultRecruit.setTellphone(rs.getString("tellphone"));
				resultRecruit.setRecruitFrom(rs.getString("recruit_from"));
				resultRecruit.setHealthyState(rs.getInt("healthy_state"));
				resultRecruit.setIdcardState(rs.getInt("idcard_state"));
			}
		});
		return resultRecruit;
	}

	@Override
	public void add(Recruit recruit) {
		// TODO Auto-generated method stub
		//健康状态 和 身份证核验默认为0
		String sql="insert into recruit values(NULL,?,?,?,?,?,0,0)";
		jdbcTemplate.update(sql,new Object[]{recruit.getName(),recruit.getSex(),recruit.getIdcard(),
				recruit.getTellphone(),recruit.getRecruitFrom()});
	}

	@Override
	public void update(Recruit recruit) {
		// TODO Auto-generated method stub
		String sql="update recruit set name=?,sex=?,idcard=?,tellphone=?,recruit_from=?,healthy_state=?,idcard_state=?"
				+ " where id=?";
		jdbcTemplate.update(sql, new Object[]{recruit.getName(),recruit.getSex(),recruit.getIdcard(),
				recruit.getTellphone(),recruit.getRecruitFrom(),recruit.getHealthyState(),
				recruit.getIdcardState(),recruit.getId()});
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql="delete from recruit where id=?";
		jdbcTemplate.update(sql, new Object[]{id});
	}

	@Override
	public void setHealth(int id) {
		// TODO Auto-generated method stub
		String sql="update recruit set healthy_state=?"
				+ " where id=?";
		jdbcTemplate.update(sql, new Object[]{1,id});
	}

	@Override
	public void setIdcard(int id) {
		// TODO Auto-generated method stub
		String sql="update recruit set idcard_state=?"
				+ " where id=?";
		jdbcTemplate.update(sql, new Object[]{1,id});
	}

	@Override
	public List<Recruit> recruitListByStateOk() {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer("select id,name,sex,idcard,tellphone,recruit_from,healthy_state,idcard_state"
				+ " from recruit where healthy_state=1 and idcard_state=1");
		final List<Recruit> recruitList=new ArrayList<Recruit>();
		jdbcTemplate.query(sb.toString(), new Object[]{}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				Recruit resultRecruit=new Recruit();	
				resultRecruit.setId(rs.getInt("id"));
				resultRecruit.setName(rs.getString("name"));
				resultRecruit.setSex(rs.getString("sex"));
				resultRecruit.setIdcard(rs.getString("idcard"));
				resultRecruit.setTellphone(rs.getString("tellphone"));
				resultRecruit.setRecruitFrom(rs.getString("recruit_from"));
				resultRecruit.setHealthyState(rs.getInt("healthy_state"));
				resultRecruit.setIdcardState(rs.getInt("idcard_state"));
				recruitList.add(resultRecruit);
			}
		});
		return recruitList;
	}

}
