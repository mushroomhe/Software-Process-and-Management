package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.SecondHBean;
import com.util.DBUtil;

public class SecondHDao {
	
	public List<SecondHBean> getSecondList(int parentId){
		ResultSet rs = null;
		Statement state = null;
		Connection conn = null;
		List<SecondHBean> secondHBeans = new ArrayList<SecondHBean>();
		try {
		conn = DBUtil.getConn();
		state = conn.createStatement();
		rs = state.executeQuery("select * from SecondH where parentId="+parentId);
		SecondHBean bean;
		while(rs.next()) {
		bean = new SecondHBean();
		bean.setId(rs.getInt("id"));
		bean.setName(rs.getString("name"));
		bean.setParentId(rs.getInt("parentId"));
		
		secondHBeans.add(bean);
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}finally{
		DBUtil.close(rs, state, conn);}
		return secondHBeans;
	}
	
	
	
	
}
