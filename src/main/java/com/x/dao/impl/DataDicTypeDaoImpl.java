package com.x.dao.impl;

import com.x.dao.DataDicTypeDao;
import com.x.entity.DataDicType;
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
public class DataDicTypeDaoImpl implements DataDicTypeDao {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<DataDicType> dataDicTypeList(PageBean pageBean, DataDicType s_dataDicType) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer("select dd_type_id,dd_type_name,dd_type_desc"
				+ " from sys_data_dic_type");
		if(s_dataDicType!=null){
			if(StringUtil.isNotEmpty(s_dataDicType.getDdTypeName())){
				sb.append(" and dd_type_name like '%"+s_dataDicType.getDdTypeName()+"%'");
			}
		}
		if(pageBean!=null){
			sb.append(" limit "+pageBean.getStart()+","+pageBean.getPageSize());
		}
		final List<DataDicType> dataDicTypeList=new ArrayList<DataDicType>();
		jdbcTemplate.query(sb.toString().replaceFirst("and", "where"), new Object[]{}, new RowCallbackHandler() {
			
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				DataDicType dataDicType=new DataDicType();
				dataDicType.setDdTypeId(rs.getInt("dd_type_id"));
				dataDicType.setDdTypeName(rs.getString("dd_type_name"));
				dataDicType.setDdTypeDesc(rs.getString("dd_type_desc"));
				dataDicTypeList.add(dataDicType);
			}
		});
		return dataDicTypeList;
	}

	@Override
	public int dataDicTypeCount(DataDicType s_dataDicType) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer("select count(*) as total from sys_data_dic_type");
		if(s_dataDicType!=null){
			if(StringUtil.isNotEmpty(s_dataDicType.getDdTypeName())){
				sb.append(" and dd_type_name like '%"+s_dataDicType.getDdTypeName()+"%'");
			}
		}
		return jdbcTemplate.queryForObject(sb.toString().replaceFirst("and", "where"), Integer.class);
	}

	@Override
	public DataDicType loadById(int ddTypeId) {
		// TODO Auto-generated method stub
		String sql="select dd_type_id,dd_type_name,dd_type_desc"
				+ " from sys_data_dic_type"
				+ " where dd_type_id=?";
		final DataDicType dataDicType=new DataDicType();
		jdbcTemplate.query(sql, new Object[]{ddTypeId}, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				// TODO Auto-generated method stub
				dataDicType.setDdTypeId(rs.getInt("dd_type_id"));
				dataDicType.setDdTypeName(rs.getString("dd_type_name"));
				dataDicType.setDdTypeDesc(rs.getString("dd_type_desc"));
			}
		});
		return dataDicType;
	}
	
	@Override
	public void add(DataDicType dataDicType) {
		// TODO Auto-generated method stub
		String sql="insert into sys_data_dic_type values(NULL,?,?)";
		jdbcTemplate.update(sql, new Object[]{dataDicType.getDdTypeName(),dataDicType.getDdTypeDesc()});
	}

	@Override
	public void update(DataDicType dataDicType) {
		// TODO Auto-generated method stub
		String sql="update sys_data_dic_type set dd_type_name=?,dd_type_desc=? where dd_type_id=?";
		jdbcTemplate.update(sql, new Object[]{dataDicType.getDdTypeName(),dataDicType.getDdTypeDesc(),dataDicType.getDdTypeId()});
	}

	@Override
	public void delete(int ddTypeId) {
		// TODO Auto-generated method stub
		String sql="delete from sys_data_dic_type where dd_type_id=?";
		jdbcTemplate.update(sql, new Object[]{ddTypeId});
	}
	
}
