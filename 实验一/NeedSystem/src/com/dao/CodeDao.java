package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.CodeBean;
import com.util.DBUtil;

public class CodeDao {

	
	 /**
	  * 得到code的列表
	  * @return
	  */
	public List<CodeBean> getCodeList(){
		String sql="select * from code";
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet resultSet=null;
		List<CodeBean> codeBeans=new ArrayList<CodeBean>();
		try{
			statement=connection.createStatement();
			resultSet=statement.executeQuery(sql);
			CodeBean codeBean;
			while(resultSet.next()){
				 codeBean=new  CodeBean();
				 codeBean.setId(resultSet.getInt("id"));
				 codeBean.setCode_num(resultSet.getString("code_num"));
				 codeBean.setCode_name(resultSet.getString("code_name"));
				 codeBeans.add(codeBean);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(resultSet, statement,connection);
		}
		return codeBeans;
	}
	
	
	/**
	 * 通过num得到Bean
	 * @param code_num
	 * @return
	 */
	public CodeBean getByNum(String code_num){
		Connection conn = DBUtil.getConn();
		CodeBean codeBean = null;
		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery("select * from code where code_num='" + code_num + "'");
			if (rs.next()) {
				codeBean = new CodeBean();
				codeBean.setId(rs.getInt("id"));
				codeBean.setCode_name(rs.getString("code_name"));
				codeBean.setCode_num(rs.getString("code_num"));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return codeBean;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
