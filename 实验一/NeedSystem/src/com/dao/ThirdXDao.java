package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.ThirdXBean;
import com.util.DBUtil;

public class ThirdXDao {
	
	public List<ThirdXBean> getThirdList(int parentId){
		ResultSet rs = null;
		Statement state = null;
		Connection conn = null;
		List<ThirdXBean> thirdXBeans = new ArrayList<ThirdXBean>();
		try {
		conn = DBUtil.getConn();
		state = conn.createStatement();
		rs = state.executeQuery("select * from ThirdX where parentId="+parentId);
		ThirdXBean bean;
		while(rs.next()) {
		bean = new ThirdXBean();
		bean.setId(rs.getInt("id"));
		bean.setName(rs.getString("name"));
		bean.setParentId(rs.getInt("parentId"));
		
		thirdXBeans.add(bean);
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}finally{
		DBUtil.close(rs, state, conn);}
		return thirdXBeans;
	}
}
