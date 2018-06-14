package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.SecondXBean;
import com.util.DBUtil;

public class SecondXDao {
	
	public List<SecondXBean> getSecondList(int parentId){
		ResultSet rs = null;
		Statement state = null;
		Connection conn = null;
		List<SecondXBean> secondXBeans = new ArrayList<SecondXBean>();
		try {
		conn = DBUtil.getConn();
		state = conn.createStatement();
		rs = state.executeQuery("select * from SecondX where parentId="+parentId);
		SecondXBean bean;
		while(rs.next()) {
		bean = new SecondXBean();
		bean.setId(rs.getInt("id"));
		bean.setName(rs.getString("name"));
		bean.setParentId(rs.getInt("parentId"));
		
		secondXBeans.add(bean);
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}finally{
		DBUtil.close(rs, state, conn);}
		return secondXBeans;
	}
	
	
	
	
}
