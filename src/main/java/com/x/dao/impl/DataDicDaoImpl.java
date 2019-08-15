package com.x.dao.impl;

import com.x.dao.DataDicDao;
import com.x.entity.DataDic;
import com.x.entity.PageBean;
import com.x.util.StringUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DataDicDaoImpl implements DataDicDao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<DataDic> dataDicList(PageBean pageBean, DataDic s_dataDic) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer("select t1.dd_id,t1.dd_type_id,t1.dd_value,t1.dd_desc,t2.dd_type_name"
				+ " from sys_data_dic t1,sys_data_dic_type t2"
				+ " where t1.dd_type_id=t2.dd_type_id");
		if(s_dataDic!=null){
			if(StringUtil.isNotEmpty(s_dataDic.getDdValue())){
				sb.append(" and t1.dd_value like '%"+s_dataDic.getDdValue()+"%'");
			}
			if(StringUtil.isNotEmpty(s_dataDic.getDdTypeName())){
				sb.append(" and t2.dd_type_name like '%"+s_dataDic.getDdTypeName()+"%'");
			}
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		final List<DataDic> dataDicList=new ArrayList<DataDic>();
		jdbcTemplate.query(sb.toString(), new Object[]{}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				DataDic resultDataDic=new DataDic();
				resultDataDic.setDdId(rs.getInt("dd_id"));
				resultDataDic.setDdTypeId(rs.getInt("dd_type_id"));
				resultDataDic.setDdValue(rs.getString("dd_value"));
				resultDataDic.setDdDesc(rs.getString("dd_desc"));
				resultDataDic.setDdTypeName(rs.getString("dd_type_name"));
				dataDicList.add(resultDataDic);
			}
		});
		return dataDicList;
	}

	@Override
	public int dataDicCount(DataDic s_dataDic) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer("select count(*) as total"
				+ " from sys_data_dic t1,sys_data_dic_type t2"
				+ " where t1.dd_type_id=t2.dd_type_id");
		if(s_dataDic!=null){
			if(StringUtil.isNotEmpty(s_dataDic.getDdValue())){
				sb.append(" and t1.dd_value like '%"+s_dataDic.getDdValue()+"%'");
			}
			if(StringUtil.isNotEmpty(s_dataDic.getDdTypeName())){
				sb.append(" and t2.dd_type_name like '%"+s_dataDic.getDdTypeName()+"%'");
			}
		}
		return jdbcTemplate.queryForObject(sb.toString(), Integer.class);
	}

	@Override
	public DataDic loadById(int ddId) {
		// TODO Auto-generated method stub
		String sql="select t1.dd_id,t1.dd_type_id,t1.dd_value,t1.dd_desc,t2.dd_type_name"
				+ " from sys_data_dic t1,sys_data_dic_type t2"
				+ " where t1.dd_type_id=t2.dd_type_id and t1.dd_id=?";
		final DataDic resultDataDic=new DataDic();
		jdbcTemplate.query(sql, new Object[]{ddId}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				resultDataDic.setDdId(rs.getInt("dd_id"));
				resultDataDic.setDdTypeId(rs.getInt("dd_type_id"));
				resultDataDic.setDdValue(rs.getString("dd_value"));
				resultDataDic.setDdDesc(rs.getString("dd_desc"));
				resultDataDic.setDdTypeName(rs.getString("dd_type_name"));
			}
		});
		return resultDataDic;
	}
	
	@Override
	public void add(DataDic dataDic) {
		// TODO Auto-generated method stub
		String sql="insert into sys_data_dic values(NULL,?,?,?)";
		jdbcTemplate.update(sql,new Object[]{dataDic.getDdTypeId(),dataDic.getDdValue(),
				dataDic.getDdDesc()});
	}

	@Override
	public void update(DataDic dataDic) {
		// TODO Auto-generated method stub
		String sql="update sys_data_dic set dd_type_id=?,dd_value=?,dd_desc=? "
				+ " where dd_id=?";
		jdbcTemplate.update(sql, new Object[]{dataDic.getDdTypeId(),dataDic.getDdValue(),
				dataDic.getDdDesc(),dataDic.getDdId()});
	}

	@Override
	public void delete(int ddId) {
		// TODO Auto-generated method stub
		String sql="delete from sys_data_dic where dd_id=?";
		jdbcTemplate.update(sql, new Object[]{ddId});
	}
	
}
