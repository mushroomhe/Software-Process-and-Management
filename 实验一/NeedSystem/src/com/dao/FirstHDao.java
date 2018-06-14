package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.FirstHBean;
import com.util.DBUtil;

public class FirstHDao {
	
	public List<FirstHBean> getFirstList(){
		ResultSet rs = null;
		Statement state = null;
		Connection conn = null;
		List<FirstHBean> firstHBeans = new ArrayList<FirstHBean>();
		try {
		conn = DBUtil.getConn();
		state = conn.createStatement();
		rs = state.executeQuery("select * from FirstH");
		FirstHBean bean;
		while(rs.next()) {
		bean = new FirstHBean();
		bean.setId(rs.getInt("id"));
		bean.setName(rs.getString("name"));
		
		firstHBeans.add(bean);
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}finally{
		DBUtil.close(rs, state, conn);}
		return firstHBeans;
	}
}
