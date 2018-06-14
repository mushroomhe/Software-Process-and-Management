package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.FirstXBean;
import com.util.DBUtil;

public class FirstXDao {
	
	public List<FirstXBean> getFirstList(){
		ResultSet rs = null;
		Statement state = null;
		Connection conn = null;
		List<FirstXBean> firstXBeans = new ArrayList<FirstXBean>();
		try {
		conn = DBUtil.getConn();
		state = conn.createStatement();
		rs = state.executeQuery("select * from FirstX");
		FirstXBean bean;
		while(rs.next()) {
		bean = new FirstXBean();
		bean.setId(rs.getInt("id"));
		bean.setName(rs.getString("name"));
		
		firstXBeans.add(bean);
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}finally{
		DBUtil.close(rs, state, conn);}
		return firstXBeans;
	}
}
