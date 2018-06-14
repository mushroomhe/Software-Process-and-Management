package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.ProvinceBean;
import com.util.DBUtil;

public class ProvinceDao {

	
//	通过province(id)获取provinceBean对象
	public ProvinceBean getProvinceById(int id){
		String sql="select * from province where id="+id;
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet resultSet=null;
		ProvinceBean provinceBean=null;
		try{
			statement=connection.createStatement();
			resultSet=statement.executeQuery(sql);
			while(resultSet.next()){
				provinceBean=new ProvinceBean();
				provinceBean.setId(resultSet.getInt("id"));
				provinceBean.setName(resultSet.getString("name"));
				provinceBean.setPostcode(resultSet.getString("postcode"));
				provinceBean.setCreateDate(resultSet.getString("create_date"));
				}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(resultSet,statement,connection);
		}
		return provinceBean;
	}
	
	
	
	/**
	 * 获取ProvinceBeans,LIST
	 */
	public List<ProvinceBean> getProvinceList(){
		String sql="select * from province";
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet resultSet=null;
		List<ProvinceBean> provinceBeans=new ArrayList<ProvinceBean>();
		try{
			statement=connection.createStatement();
			resultSet=statement.executeQuery(sql);
			ProvinceBean provinceBean;
			while(resultSet.next()){
				 provinceBean=new  ProvinceBean();
				 provinceBean.setId(resultSet.getInt("id"));
				 provinceBean.setName(resultSet.getString("name"));
				 provinceBean.setPostcode(resultSet.getString("postcode"));
				 provinceBean.setCreateDate(resultSet.getString("create_date"));
				 provinceBeans.add(provinceBean);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(resultSet, statement,connection);
		}
		return provinceBeans;
	}
	
}
