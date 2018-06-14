package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.ThirdHBean;
import com.util.DBUtil;

public class ThirdHDao {
	
	public List<ThirdHBean> getThirdList(int parentId){
		ResultSet rs = null;
		Statement state = null;
		Connection conn = null;
		List<ThirdHBean> thirdHBeans = new ArrayList<ThirdHBean>();
		try {
		conn = DBUtil.getConn();
		state = conn.createStatement();
		rs = state.executeQuery("select * from ThirdH where parentId="+parentId);
		ThirdHBean bean;
		while(rs.next()) {
		bean = new ThirdHBean();
		bean.setId(rs.getInt("id"));
		bean.setName(rs.getString("name"));
		bean.setParentId(rs.getInt("parentId"));
		
		thirdHBeans.add(bean);
		}
		} catch (SQLException e) {
		e.printStackTrace();
		}finally{
		DBUtil.close(rs, state, conn);}
		return thirdHBeans;
	}
}
