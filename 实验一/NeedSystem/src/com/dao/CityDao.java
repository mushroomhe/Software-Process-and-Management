package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.CityBean;
import com.util.DBUtil;

public class CityDao {

	
//	通过city(id)获取cityBean对象
	public CityBean getCityById(int id){
		String sql="select * from city where id="+id;
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet resultSet=null;
		CityBean cityBean=null;
		try{
			statement=connection.createStatement();
			resultSet=statement.executeQuery(sql);
			while(resultSet.next()){
				cityBean=new CityBean();
				cityBean.setId(resultSet.getInt("id"));
				cityBean.setName(resultSet.getString("name"));
				cityBean.setPostcode(resultSet.getString("postcode"));
				cityBean.setCreateDate(resultSet.getString("create_date"));
				cityBean.setProvinceId(resultSet.getInt("province_id"));
				}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(resultSet,statement,connection);
		}
		return cityBean;
	}
	
	
	
	public List<CityBean> getCityList(int provinceId){
		ResultSet rs = null;
		Statement state = null;
		Connection conn = null;
		List<CityBean> cityBeans = new ArrayList<CityBean>();
		try {
		conn = DBUtil.getConn();
		state = conn.createStatement();
		rs = state.executeQuery("select * from city where province_id="+provinceId);
		CityBean bean;
		while(rs.next()) {
		bean = new CityBean();
		bean.setId(rs.getInt("id"));
		bean.setName(rs.getString("name"));
		bean.setPostcode(rs.getString("postcode"));
		bean.setCreateDate(rs.getString("create_date"));
		bean.setProvinceId(rs.getInt("province_id"));
		cityBeans.add(bean);
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}finally{
		DBUtil.close(rs, state, conn);}
		return cityBeans;
	}
	
	
}
