package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * 数据库工具类，用来连接数据库，执行SQL语句，关闭数据库连接等。
 */

public class DBUtil {
	//System数据库
	public static String db_url="jdbc:sqlserver://localhost:1433; DatabaseName=technology";//数据库地址
	public static String db_user="ZH";//数据库连接用户名
	public static String db_password="202862";//数据库连接密码
	private static ResultSet rs;
	private static PreparedStatement pstmt;
	private static Connection coon= null;
	/**
	 * 返回一个数据库的连接对象的应用
	 */
	public static Connection getConn(){
		Connection conn=null;
		try{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn=DriverManager.getConnection(db_url,db_user,db_password);
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
	//关闭数据库的连接对象，SQL语句对象
	public static void close(Statement state,Connection conn){
		if(state!=null){
			try{
				state.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try{
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	
	public static void close() {
		
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try{
					if(pstmt!=null)
						pstmt.close();
				}catch(SQLException e){
					e.printStackTrace();
				}finally{
					try{
						if(coon!=null)
							coon.close();
					}catch(SQLException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		
	}
	
	//关闭数据库的连接对象，SQL语句对象，查询结果集对象
	public static void close(ResultSet rs,Statement state,Connection conn){
		if(rs!=null){
			try{
				rs.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(state!=null){
			try{
				state.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try{
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}	
}









