package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.DemandBean;
import com.bean.Tongjibean;
import com.util.DBUtil;
import com.util.StringUtil;

public class DemandDao {
	
	
	
//	获取数据表中数据总量
	public int getCount(){
		ResultSet rs=null;
		Statement state=null;
		Connection conn=null;
		int size=0;
		try{
			conn=DBUtil.getConn();
			state=conn.createStatement();
			rs=state.executeQuery("select count(*) count from T_DCWJXX");
			
			if(rs.next()){
				size=rs.getInt("count");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, state, conn);
		}
		return size;
	}
	
	
	
//	获取每一个分页的数据
	public List<DemandBean> getListByPage(int start,int size){
		String sql = "select top " + size + " * from T_DCWJXX where(WJID not in(select top " + start + " WJID from T_DCWJXX))";
		System.out.println(sql);
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet rs=null;
		List<DemandBean> demandBeans=new ArrayList<DemandBean>();
		try{
			statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			DemandBean demandBean;
			while(rs.next()){
				demandBean=new DemandBean();
				demandBean.setWJID(rs.getString("WJID"));
				demandBean.setUsername(rs.getString("username"));
				demandBean.setSZDY(rs.getString("SZDY"));
				demandBean.setSFSH(rs.getInt("SFSH"));
				demandBean.setJGMC(rs.getString("JGMC"));
				demandBean.setGLBM(rs.getString("GLBM"));
				demandBean.setTXDZ(rs.getString("TXDZ"));
				demandBean.setDWWZ(rs.getString("DWWZ"));
				demandBean.setDZYX(rs.getString("DZYX"));
				demandBean.setFRDB(rs.getString("FRDB"));
				demandBean.setYZBM(rs.getString("YZBM"));
				demandBean.setLXR(rs.getString("LXR"));
				demandBean.setGDDH(rs.getString("GDDH"));
				demandBean.setYDDH(rs.getString("YDDH"));
				demandBean.setCZ(rs.getString("CZ"));
				demandBean.setJGSX(rs.getString("JGSX"));
				demandBean.setJGJJ(rs.getString("JGJJ"));
				demandBean.setJSXQMC(rs.getString("JSXQMC"));
				demandBean.setQSXQNF(rs.getInt("QSXQNF"));
				demandBean.setJZXQNF(rs.getInt("JZXQNF"));
				demandBean.setZDKJXQGS(rs.getString("ZDKJXQGS"));
				demandBean.setGJZ(rs.getString("GJZ"));
				demandBean.setYJLX(rs.getString("YJLX"));
				demandBean.setXKFL1(rs.getString("XKFL1"));
				demandBean.setXKFL2(rs.getString("XKFL2"));
				demandBean.setXKFL3(rs.getString("XKFL3"));
				demandBean.setXQJSSSLY(rs.getString("XQJSSSLY"));
				demandBean.setQTJSMS(rs.getString("QTJSMS"));
				demandBean.setXQJSYYHY1(rs.getString("XQJSYYHY1"));
				demandBean.setXQJSYYHY2(rs.getString("XQJSYYHY2"));
				demandBean.setXQJSYYHY3(rs.getString("XQJSYYHY3"));
				demandBean.setJSXQHZMS(rs.getString("JSXQHZMS"));
				demandBean.setHZYXDW(rs.getString("HZYXDW"));
				demandBean.setNTR(rs.getString("NTR"));
				demandBean.setCreateDate(rs.getString("createDate"));
				
				demandBeans.add(demandBean);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs,statement, connection);
		}
		return demandBeans;
	}
	
	public Tongjibean tongji()
	{
		Connection conn=DBUtil.getConn();
		Statement state=null;
		ResultSet rs=null;
		Tongjibean tongjibean=new Tongjibean();
		String sql="";
		try
		{
			state=conn.createStatement();
			sql="select count(*) as s from T_DCWJXX where SFSH=0";
			rs=state.executeQuery(sql);
			if(rs.next())
			{
			tongjibean.setWeishenhe(StringUtil.StringToInt(rs.getString("s")));
			}
			sql="select count(*) as s from T_DCWJXX where SFSH!=0";
			rs=state.executeQuery(sql);
			if(rs.next())
			{
			tongjibean.setShenhe(StringUtil.StringToInt(rs.getString("s")));
			}
			sql="select count(*) as s from T_DCWJXX where SFSH=1";
			rs=state.executeQuery(sql);
			if(rs.next())
			{
			tongjibean.setTongguo(StringUtil.StringToInt(rs.getString("s")));
			}
			sql="select count(*) as s from T_DCWJXX where SFSH=2";
			rs=state.executeQuery(sql);
			if(rs.next())
			{
			tongjibean.setTuihui(StringUtil.StringToInt(rs.getString("s")));
			}
			tongjibean.setZong();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			DBUtil.close(rs, state, conn);
		}
		return tongjibean;
	}
	
	
	
	
	//去掉分页
		public  ArrayList<DemandBean> xdshPolicy(
				String num,String sType0,String q0,
				String logic1,String sType1,String q1,
				String logic2,String sType2,String q2 )
		{
						ArrayList<DemandBean>  list = new ArrayList<DemandBean>();
						
						String sql = "select * from T_DCWJXX";
						Connection conn=DBUtil.getConn();
						Statement stmt=null;
						ResultSet rs=null;
						//拼的查询语句
						//因为只有一次查询多次显示所以不能提交到本方法~~~~~~会出现空指针
							if(num.equals("1")){
								//只有一个查询条件
								sql+=" where "+sType0+" like '%"+q0+"%'";
					            }
					            if(num.equals("2")){
					            	//2个查询条件
					              if(logic1.equals("AND")){
					            	sql+=" where "+sType0+" like '%"+q0+"%' "+logic1+" "+sType1+" like '%"+q1+"%'";
					              }
					              if(logic1.equals("OR")){
					            	  sql+=" where ("+sType0+" like '%"+q0+"%' "+logic1+" "+sType1+" like '%"+q1+"%')";  
					              }
					              if(logic1.equals("NOT")){
					            	 sql+=" where "+sType0+" like '%"+q0+"%' "+"and "+sType1+" not like '%"+q1+"%'";
					              }
					            }
					            if(num.equals("3")){
					            	//3个查询条件
				                	if(logic2.equals("AND")){
				                		if(logic1.equals("AND")){
				                        	sql+=" where "+sType0+" like '%"+q0+"%' "+logic1+" "+sType1+" like '%"+q1+"%' "+logic2+" "+sType2+" like '%"+q2+"%'"; 
				                          }
				                		if(logic1.equals("OR")){
				                      	  sql+=" where ("+sType0+" like '%"+q0+"%' "+logic1+" "+sType1+" like '%"+q1+"%' "+logic2+" "+sType2+" like '%"+q2+"%')";
				                        }
				                        if(logic1.equals("NOT")){
				                      	 sql+=" where "+sType0+" like '%"+q0+"%' "+"and "+sType1+" not like '%"+q1+"%'"+" and "+sType2+" not like '%"+q2+"%'";
				                        }
				                      }
				                	
				                       if(logic2.equals("OR")){
				                    	  if(logic1.equals("AND")){
				                          	sql+=" where "+sType0+" like '%"+q0+"%' "+logic1+" ("+sType1+" like '%"+q1+"%' "+logic2+" "+sType2+" like '%"+q2+"%' )";  
				                            }
				                  		if(logic1.equals("OR")){
				                        	  sql+=" where ("+sType0+" like '%"+q0+"%' "+logic1+" "+sType1+" like '%"+q1+"%' "+logic2+" "+sType2+" like '%"+q2+"%')";  
				                          }
				                          if(logic1.equals("NOT")){
				                        	 sql+=" where ("+sType0+" like '%"+q0+"%' "+"and "+sType1+" not like '%"+q1+"%') "+logic2+" "+sType2+" like '%"+q2+"%'";
				                          }
				                      }
				                       
				                      if(logic2.equals("NOT")){
				                    	  
				                    	  if(logic1.equals("AND")){
				                          	sql+=" where "+sType0+" like '%"+q0+"%' "+logic1+" "+sType1+" like '%"+q1+"%' "+" and "+sType2+" not like '%"+q2+"%'"; 
				                            }
				                  		if(logic1.equals("OR")){
				                        	  sql+=" where ("+sType0+" like '%"+q0+"%' "+logic1+" "+sType1+" like '%"+q1+"%') "+" and "+sType2+" not like '%"+q2+"%'";
				                          }
				                          if(logic1.equals("NOT")){
				                        	 sql+=" where "+sType0+" like '%"+q0+"%' "+"and "+sType1+" not like '%"+q1+"%'"+" and "+sType2+" not like '%"+q2+"%'";
				                          }
				                      }
				                      
				                }
					            System.out.println(sql);
						//SQL语句结束
						DemandBean xx=null;
						try{
							stmt=conn.createStatement();
							rs=stmt.executeQuery(sql);
							
							while(rs.next()){
								xx= new DemandBean();
								xx.setWJID(rs.getString("WJID"));
								xx.setUsername(rs.getString("username"));
								xx.setSZDY(rs.getString("SZDY"));
								xx.setSFSH(rs.getInt("SFSH"));
								xx.setJGMC(rs.getString("JGMC"));
								xx.setGLBM(rs.getString("GLBM"));
								xx.setTXDZ(rs.getString("TXDZ"));
								xx.setDWWZ(rs.getString("DWWZ"));
								xx.setDZYX(rs.getString("DZYX"));
								xx.setFRDB(rs.getString("FRDB"));
								xx.setYZBM(rs.getString("YZBM"));
								xx.setLXR(rs.getString("LXR"));
								xx.setGDDH(rs.getString("GDDH"));
								xx.setYDDH(rs.getString("YDDH"));
								xx.setCZ(rs.getString("CZ"));
								xx.setJGSX(rs.getString("JGSX"));
								xx.setJGJJ(rs.getString("JGJJ"));
								xx.setJSXQMC(rs.getString("JSXQMC"));
								xx.setQSXQNF(rs.getInt("QSXQNF"));
								xx.setJZXQNF(rs.getInt("JZXQNF"));
								xx.setZDKJXQGS(rs.getString("ZDKJXQGS"));
								xx.setGJZ(rs.getString("GJZ"));
								xx.setYJLX(rs.getString("YJLX"));
								xx.setXKFL1(rs.getString("XKFL1"));
								xx.setXKFL2(rs.getString("XKFL2"));
								xx.setXKFL3(rs.getString("XKFL3"));
								xx.setXQJSSSLY(rs.getString("XQJSSSLY"));
								xx.setQTJSMS(rs.getString("QTJSMS"));
								xx.setXQJSYYHY1(rs.getString("XQJSYYHY1"));
								xx.setXQJSYYHY2(rs.getString("XQJSYYHY2"));
								xx.setXQJSYYHY3(rs.getString("XQJSYYHY3"));
								xx.setJSXQHZMS(rs.getString("JSXQHZMS"));
								xx.setHZYXDW(rs.getString("HZYXDW"));
								xx.setNTR(rs.getString("NTR"));
								xx.setCreateDate(rs.getString("createDate"));
								
								list.add(xx);
							}
						} catch (Exception e) {
							e.printStackTrace();
						} finally {
						DBUtil.close(rs, stmt, conn);
						}
						return list;
					}
		
		
		
		
	
	public void save(DemandBean demandBean){
		//获取数据库连接
		String sql = "insert into T_DCWJXX(WJID,username,SZDY,SFSH,JGMC,GLBM,TXDZ,DWWZ,DZYX,FRDB,YZBM,LXR,GDDH,YDDH,CZ,JGSX,"
				+ "JGJJ,JSXQMC,QSXQNF,JZXQNF,ZDKJXQGS,GJZ,YJLX,XKFL1,XKFL2,XKFL3,XQJSSSLY,QTJSMS,XQJSYYHY1,XQJSYYHY2,XQJSYYHY3,JSXQHZMS,HZYXDW,NTR,createDate) values('"
						+demandBean.getWJID() + "','"+demandBean.getUsername()+"','"+demandBean.getSZDY()+"','"+demandBean.getSFSH()+"','"+demandBean.getJGMC()+"','"
		+demandBean.getGLBM()+"','"+demandBean.getTXDZ()+ "','" + demandBean.getDWWZ() + "','" + demandBean.getDZYX() +"','"+demandBean.getFRDB()+"','"+demandBean.getYZBM()+
		"','"+demandBean.getLXR()+"','"+demandBean.getGDDH()+"','"+demandBean.getYDDH()+"','"+demandBean.getCZ()+
		"','"+demandBean.getJGSX()+"','"+demandBean.getJGJJ()+"','"+demandBean.getJSXQMC()+"','"+demandBean.getQSXQNF()+"','"+demandBean.getJZXQNF()+
		"','"+demandBean.getZDKJXQGS()+"','"+demandBean.getGJZ()+"','"+demandBean.getYJLX()+"','"+demandBean.getXKFL1()+"','"+demandBean.getXKFL2()+"','"
		+demandBean.getXKFL3()
		+"','"+demandBean.getXQJSSSLY()+"','"+demandBean.getQTJSMS()+"','"+demandBean.getXQJSYYHY1()+"','"+demandBean.getXQJSYYHY2()
		+"','"+demandBean.getXQJSYYHY3()+"','"+demandBean.getJSXQHZMS()+"','"
		+demandBean.getHZYXDW()+"','"+demandBean.getNTR()+"','"+demandBean.getCreateDate()+"')";
		
		
		Connection conn=DBUtil.getConn();
		//创建数据库操作对象
		Statement state=null;
		try{
			state=conn.createStatement();
			state.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(state, conn);
		}
		
	}
	
	
	
	/**
	 * 判断是否存在
	 * @param name
	 * @return
	 */
	public boolean check(String JSXQMC){
		//假设一开始允许注册，将标记值记为true
		boolean flag=true;
		//查询用户是否已存在
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet rs=null;
		try{
			statement=connection.createStatement();
			rs=statement.executeQuery("select JSXQMC from T_DCWJXX");
			while(rs.next()){
				if(JSXQMC.equals(rs.getString("JSXQMC"))){
				//数据表中已存在此账号则说明不允许添加，将标记值记为false
				flag=false;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, statement,connection);
		}
		return flag;
	}
	
	
	

	
	
	/**
	 * 显示基础研究，，，，集合
	 * @return
	 * SELECT * FROM dbo.Member WHERE ID  IN (SELECT MIN(ID) FROM dbo.Member GROUP BY Name)
	 */
	public List<String> getTypeList(){
		String sql="select distinct YJLX from T_DCWJXX";
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet rs=null;
		List YJLXs=new ArrayList<String>();
		try{
			statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			String YJLX;
			while(rs.next()){
				YJLX=rs.getString("YJLX");
				YJLXs.add(YJLX);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, statement,connection);
		}
		return YJLXs;
	}
	
	
	
	
	
	/*显示学科分类第一级*/
	public List<String> getXKFLListA(){
		String sql="select distinct XKFL1 from T_DCWJXX";
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet rs=null;
		List XKFLs=new ArrayList<String>();
		try{
			statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			String XKFL = null;
			while(rs.next()){
				XKFL=rs.getString("XKFL1");
				//System.out.println(XKFL);
				if(!XKFL.equals("0")){
					XKFLs.add(XKFL);
				}
				//XKFLs.add(XKFL);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, statement,connection);
		}
		
		return XKFLs;
	}
	

	/*显示学科分类第二级*/
	public List<String> getXKFLListB(String type){
		String sql="select distinct XKFL2 from T_DCWJXX where XKFL1='"+type+"'";
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet rs=null;
		List XKFLs=new ArrayList<String>();
		try{
			statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			String XKFL = null;
			while(rs.next()){
				XKFL=rs.getString("XKFL2");
				//System.out.println(XKFL);
				if(!XKFL.equals("0")){
					XKFLs.add(XKFL);
				}
				//XKFLs.add(XKFL);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, statement,connection);
		}
		
		return XKFLs;
	}
	
	/*显示学科分类第三级*/
	public List<String> getXKFLListC(String type){
		String sql="select distinct XKFL3 from T_DCWJXX where XKFL2='"+type+"'";
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet rs=null;
		List XKFLs=new ArrayList<String>();
		try{
			statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			String XKFL = null;
			while(rs.next()){
				XKFL=rs.getString("XKFL3");
				//System.out.println(XKFL);
				if(!XKFL.equals("0")){
					XKFLs.add(XKFL);
				}
				//XKFLs.add(XKFL);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, statement,connection);
		}
		
		return XKFLs;
	}
	
	
/*	检测字符串中第几个位置的字符
	public String getSP(String str,int n){
		String A="";
		String[] split=str.split("\\,");
		A=split[n];
		return A;
		   String address="上海:上海市:闵行区:吴中路";
		     String[] splitAddress=address.split("\\:");
		     System.out.println(splitAddress[0]+splitAddress[1]+splitAddress[2]+splitAddress[3]);
	}
	
	数组的个数
	public int getStr(String a){
		int num=0;
		String[] split=a.split("\\,");
		num=split.length;
		return num;
	}*/
	
	
	/*显示国民经济行业第一级*/
	public List<String> getXQJSYYHYListA(){
		String sql="select distinct XQJSYYHY1 from T_DCWJXX";
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet rs=null;
		List XQJSYYHYs=new ArrayList<String>();
		try{
			statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			String XQJSYYHY = null;
			while(rs.next()){
				XQJSYYHY=rs.getString("XQJSYYHY1");
				if(!XQJSYYHY.equals("0")){
					XQJSYYHYs.add(XQJSYYHY);
				}
			
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, statement,connection);
		}
		return XQJSYYHYs;
	}
	
	

	/*显示国民经济分类第二级*/
	public List<String> getXQJSYYHYListB(String type){
		String sql="select distinct XQJSYYHY2 from T_DCWJXX where XQJSYYHY1='"+type+"'";
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet rs=null;
		List XQJSYYHYs=new ArrayList<String>();
		try{
			statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			String XQJSYYHY = null;
			while(rs.next()){
				XQJSYYHY=rs.getString("XQJSYYHY2");
				//System.out.println(XKFL);
				if(!XQJSYYHY.equals("0")){
					XQJSYYHYs.add(XQJSYYHY);
				}
				//XKFLs.add(XKFL);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, statement,connection);
		}
		
		return XQJSYYHYs;
	}
	
	/*显示国民经济行业分类第三级*/
	public List<String> getXQJSYYHYListC(String type){
		String sql="select distinct XQJSYYHY3 from T_DCWJXX where XQJSYYHY2='"+type+"'";
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet rs=null;
		List XQJSYYHYs=new ArrayList<String>();
		try{
			statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			String XQJSYYHY = null;
			while(rs.next()){
				XQJSYYHY=rs.getString("XQJSYYHY3");
				//System.out.println(XKFL);
				if(!XQJSYYHY.equals("0")){
					XQJSYYHYs.add(XQJSYYHY);
				}
				//XKFLs.add(XKFL);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, statement,connection);
		}
		
		return XQJSYYHYs;
	}


	
	
	
	
	public List<DemandBean> getList(String next){
		String sql="select * from T_DCWJXX where YJLX='"+next+"'";
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet rs=null;
		List<DemandBean> demandBeans=new ArrayList<DemandBean>();
		try{
			statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			DemandBean demandBean;
			while(rs.next()){
				demandBean=new DemandBean();
				demandBean.setWJID(rs.getString("WJID"));
				demandBean.setUsername(rs.getString("username"));
				demandBean.setSZDY(rs.getString("SZDY"));
				demandBean.setSFSH(rs.getInt("SFSH"));
				demandBean.setJGMC(rs.getString("JGMC"));
				demandBean.setGLBM(rs.getString("GLBM"));
				demandBean.setTXDZ(rs.getString("TXDZ"));
				demandBean.setDWWZ(rs.getString("DWWZ"));
				demandBean.setDZYX(rs.getString("DZYX"));
				demandBean.setFRDB(rs.getString("FRDB"));
				demandBean.setYZBM(rs.getString("YZBM"));
				demandBean.setLXR(rs.getString("LXR"));
				demandBean.setGDDH(rs.getString("GDDH"));
				demandBean.setYDDH(rs.getString("YDDH"));
				demandBean.setCZ(rs.getString("CZ"));
				demandBean.setJGSX(rs.getString("JGSX"));
				demandBean.setJGJJ(rs.getString("JGJJ"));
				demandBean.setJSXQMC(rs.getString("JSXQMC"));
				demandBean.setQSXQNF(rs.getInt("QSXQNF"));
				demandBean.setJZXQNF(rs.getInt("JZXQNF"));
				demandBean.setZDKJXQGS(rs.getString("ZDKJXQGS"));
				demandBean.setGJZ(rs.getString("GJZ"));
				demandBean.setYJLX(rs.getString("YJLX"));
				demandBean.setXKFL1(rs.getString("XKFL1"));
				demandBean.setXKFL2(rs.getString("XKFL2"));
				demandBean.setXKFL3(rs.getString("XKFL3"));
				demandBean.setXQJSSSLY(rs.getString("XQJSSSLY"));
				demandBean.setQTJSMS(rs.getString("QTJSMS"));
				demandBean.setXQJSYYHY1(rs.getString("XQJSYYHY1"));
				demandBean.setXQJSYYHY2(rs.getString("XQJSYYHY2"));
				demandBean.setXQJSYYHY3(rs.getString("XQJSYYHY3"));
				demandBean.setJSXQHZMS(rs.getString("JSXQHZMS"));
				demandBean.setHZYXDW(rs.getString("HZYXDW"));
				demandBean.setNTR(rs.getString("NTR"));
				demandBean.setCreateDate(rs.getString("createDate"));
				
				demandBeans.add(demandBean);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, statement,connection);
		}
		return demandBeans;
	}
	
	
	public List<DemandBean> getListAX(String next){
		String sql="select * from T_DCWJXX where XKFL1='"+next+"'";
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet rs=null;
		List<DemandBean> demandBeans=new ArrayList<DemandBean>();
		try{
			statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			DemandBean demandBean;
			while(rs.next()){
				demandBean=new DemandBean();
				demandBean.setWJID(rs.getString("WJID"));
				demandBean.setUsername(rs.getString("username"));
				demandBean.setSZDY(rs.getString("SZDY"));
				demandBean.setSFSH(rs.getInt("SFSH"));
				demandBean.setJGMC(rs.getString("JGMC"));
				demandBean.setGLBM(rs.getString("GLBM"));
				demandBean.setTXDZ(rs.getString("TXDZ"));
				demandBean.setDWWZ(rs.getString("DWWZ"));
				demandBean.setDZYX(rs.getString("DZYX"));
				demandBean.setFRDB(rs.getString("FRDB"));
				demandBean.setYZBM(rs.getString("YZBM"));
				demandBean.setLXR(rs.getString("LXR"));
				demandBean.setGDDH(rs.getString("GDDH"));
				demandBean.setYDDH(rs.getString("YDDH"));
				demandBean.setCZ(rs.getString("CZ"));
				demandBean.setJGSX(rs.getString("JGSX"));
				demandBean.setJGJJ(rs.getString("JGJJ"));
				demandBean.setJSXQMC(rs.getString("JSXQMC"));
				demandBean.setQSXQNF(rs.getInt("QSXQNF"));
				demandBean.setJZXQNF(rs.getInt("JZXQNF"));
				demandBean.setZDKJXQGS(rs.getString("ZDKJXQGS"));
				demandBean.setGJZ(rs.getString("GJZ"));
				demandBean.setYJLX(rs.getString("YJLX"));
				demandBean.setXKFL1(rs.getString("XKFL1"));
				demandBean.setXKFL2(rs.getString("XKFL2"));
				demandBean.setXKFL3(rs.getString("XKFL3"));
				demandBean.setXQJSSSLY(rs.getString("XQJSSSLY"));
				demandBean.setQTJSMS(rs.getString("QTJSMS"));
				demandBean.setXQJSYYHY1(rs.getString("XQJSYYHY1"));
				demandBean.setXQJSYYHY2(rs.getString("XQJSYYHY2"));
				demandBean.setXQJSYYHY3(rs.getString("XQJSYYHY3"));
				demandBean.setJSXQHZMS(rs.getString("JSXQHZMS"));
				demandBean.setHZYXDW(rs.getString("HZYXDW"));
				demandBean.setNTR(rs.getString("NTR"));
				demandBean.setCreateDate(rs.getString("createDate"));
				
				demandBeans.add(demandBean);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, statement,connection);
		}
		return demandBeans;
	}
	
	public List<DemandBean> getListBX(String next,String parent){
		String sql="select * from T_DCWJXX where XKFL2='"+next+"' and XKFL1='"+parent+"'";
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet rs=null;
		List<DemandBean> demandBeans=new ArrayList<DemandBean>();
		try{
			statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			DemandBean demandBean;
			while(rs.next()){
				demandBean=new DemandBean();
				demandBean.setWJID(rs.getString("WJID"));
				demandBean.setUsername(rs.getString("username"));
				demandBean.setSZDY(rs.getString("SZDY"));
				demandBean.setSFSH(rs.getInt("SFSH"));
				demandBean.setJGMC(rs.getString("JGMC"));
				demandBean.setGLBM(rs.getString("GLBM"));
				demandBean.setTXDZ(rs.getString("TXDZ"));
				demandBean.setDWWZ(rs.getString("DWWZ"));
				demandBean.setDZYX(rs.getString("DZYX"));
				demandBean.setFRDB(rs.getString("FRDB"));
				demandBean.setYZBM(rs.getString("YZBM"));
				demandBean.setLXR(rs.getString("LXR"));
				demandBean.setGDDH(rs.getString("GDDH"));
				demandBean.setYDDH(rs.getString("YDDH"));
				demandBean.setCZ(rs.getString("CZ"));
				demandBean.setJGSX(rs.getString("JGSX"));
				demandBean.setJGJJ(rs.getString("JGJJ"));
				demandBean.setJSXQMC(rs.getString("JSXQMC"));
				demandBean.setQSXQNF(rs.getInt("QSXQNF"));
				demandBean.setJZXQNF(rs.getInt("JZXQNF"));
				demandBean.setZDKJXQGS(rs.getString("ZDKJXQGS"));
				demandBean.setGJZ(rs.getString("GJZ"));
				demandBean.setYJLX(rs.getString("YJLX"));
				demandBean.setXKFL1(rs.getString("XKFL1"));
				demandBean.setXKFL2(rs.getString("XKFL2"));
				demandBean.setXKFL3(rs.getString("XKFL3"));
				demandBean.setXQJSSSLY(rs.getString("XQJSSSLY"));
				demandBean.setQTJSMS(rs.getString("QTJSMS"));
				demandBean.setXQJSYYHY1(rs.getString("XQJSYYHY1"));
				demandBean.setXQJSYYHY2(rs.getString("XQJSYYHY2"));
				demandBean.setXQJSYYHY3(rs.getString("XQJSYYHY3"));
				demandBean.setJSXQHZMS(rs.getString("JSXQHZMS"));
				demandBean.setHZYXDW(rs.getString("HZYXDW"));
				demandBean.setNTR(rs.getString("NTR"));
				demandBean.setCreateDate(rs.getString("createDate"));
				
				demandBeans.add(demandBean);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, statement,connection);
		}
		return demandBeans;
	}
	
	public List<DemandBean> getListCX(String next,String parent){
		String sql="select * from T_DCWJXX where XKFL3='"+next+"' and XKFL2='"+parent+"'";
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet rs=null;
		List<DemandBean> demandBeans=new ArrayList<DemandBean>();
		try{
			statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			DemandBean demandBean;
			while(rs.next()){
				demandBean=new DemandBean();
				demandBean.setWJID(rs.getString("WJID"));
				demandBean.setUsername(rs.getString("username"));
				demandBean.setSZDY(rs.getString("SZDY"));
				demandBean.setSFSH(rs.getInt("SFSH"));
				demandBean.setJGMC(rs.getString("JGMC"));
				demandBean.setGLBM(rs.getString("GLBM"));
				demandBean.setTXDZ(rs.getString("TXDZ"));
				demandBean.setDWWZ(rs.getString("DWWZ"));
				demandBean.setDZYX(rs.getString("DZYX"));
				demandBean.setFRDB(rs.getString("FRDB"));
				demandBean.setYZBM(rs.getString("YZBM"));
				demandBean.setLXR(rs.getString("LXR"));
				demandBean.setGDDH(rs.getString("GDDH"));
				demandBean.setYDDH(rs.getString("YDDH"));
				demandBean.setCZ(rs.getString("CZ"));
				demandBean.setJGSX(rs.getString("JGSX"));
				demandBean.setJGJJ(rs.getString("JGJJ"));
				demandBean.setJSXQMC(rs.getString("JSXQMC"));
				demandBean.setQSXQNF(rs.getInt("QSXQNF"));
				demandBean.setJZXQNF(rs.getInt("JZXQNF"));
				demandBean.setZDKJXQGS(rs.getString("ZDKJXQGS"));
				demandBean.setGJZ(rs.getString("GJZ"));
				demandBean.setYJLX(rs.getString("YJLX"));
				demandBean.setXKFL1(rs.getString("XKFL1"));
				demandBean.setXKFL2(rs.getString("XKFL2"));
				demandBean.setXKFL3(rs.getString("XKFL3"));
				demandBean.setXQJSSSLY(rs.getString("XQJSSSLY"));
				demandBean.setQTJSMS(rs.getString("QTJSMS"));
				demandBean.setXQJSYYHY1(rs.getString("XQJSYYHY1"));
				demandBean.setXQJSYYHY2(rs.getString("XQJSYYHY2"));
				demandBean.setXQJSYYHY3(rs.getString("XQJSYYHY3"));
				demandBean.setJSXQHZMS(rs.getString("JSXQHZMS"));
				demandBean.setHZYXDW(rs.getString("HZYXDW"));
				demandBean.setNTR(rs.getString("NTR"));
				demandBean.setCreateDate(rs.getString("createDate"));
				
				demandBeans.add(demandBean);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, statement,connection);
		}
		return demandBeans;
	}
	
	public List<DemandBean> getListAH(String next){
		String sql="select * from T_DCWJXX where XQJSYYHY1='"+next+"'";
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet rs=null;
		List<DemandBean> demandBeans=new ArrayList<DemandBean>();
		try{
			statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			DemandBean demandBean;
			while(rs.next()){
				demandBean=new DemandBean();
				demandBean.setWJID(rs.getString("WJID"));
				demandBean.setUsername(rs.getString("username"));
				demandBean.setSZDY(rs.getString("SZDY"));
				demandBean.setSFSH(rs.getInt("SFSH"));
				demandBean.setJGMC(rs.getString("JGMC"));
				demandBean.setGLBM(rs.getString("GLBM"));
				demandBean.setTXDZ(rs.getString("TXDZ"));
				demandBean.setDWWZ(rs.getString("DWWZ"));
				demandBean.setDZYX(rs.getString("DZYX"));
				demandBean.setFRDB(rs.getString("FRDB"));
				demandBean.setYZBM(rs.getString("YZBM"));
				demandBean.setLXR(rs.getString("LXR"));
				demandBean.setGDDH(rs.getString("GDDH"));
				demandBean.setYDDH(rs.getString("YDDH"));
				demandBean.setCZ(rs.getString("CZ"));
				demandBean.setJGSX(rs.getString("JGSX"));
				demandBean.setJGJJ(rs.getString("JGJJ"));
				demandBean.setJSXQMC(rs.getString("JSXQMC"));
				demandBean.setQSXQNF(rs.getInt("QSXQNF"));
				demandBean.setJZXQNF(rs.getInt("JZXQNF"));
				demandBean.setZDKJXQGS(rs.getString("ZDKJXQGS"));
				demandBean.setGJZ(rs.getString("GJZ"));
				demandBean.setYJLX(rs.getString("YJLX"));
				demandBean.setXKFL1(rs.getString("XKFL1"));
				demandBean.setXKFL2(rs.getString("XKFL2"));
				demandBean.setXKFL3(rs.getString("XKFL3"));
				demandBean.setXQJSSSLY(rs.getString("XQJSSSLY"));
				demandBean.setQTJSMS(rs.getString("QTJSMS"));
				demandBean.setXQJSYYHY1(rs.getString("XQJSYYHY1"));
				demandBean.setXQJSYYHY2(rs.getString("XQJSYYHY2"));
				demandBean.setXQJSYYHY3(rs.getString("XQJSYYHY3"));
				demandBean.setJSXQHZMS(rs.getString("JSXQHZMS"));
				demandBean.setHZYXDW(rs.getString("HZYXDW"));
				demandBean.setNTR(rs.getString("NTR"));
				demandBean.setCreateDate(rs.getString("createDate"));
				
				demandBeans.add(demandBean);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, statement,connection);
		}
		return demandBeans;
	}
	
	public List<DemandBean> getListBH(String next,String parent){
		String sql="select * from T_DCWJXX where XQJSYYHY2='"+next+"' and XQJSYYHY1='"+parent+"'";
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet rs=null;
		List<DemandBean> demandBeans=new ArrayList<DemandBean>();
		try{
			statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			DemandBean demandBean;
			while(rs.next()){
				demandBean=new DemandBean();
				demandBean.setWJID(rs.getString("WJID"));
				demandBean.setUsername(rs.getString("username"));
				demandBean.setSZDY(rs.getString("SZDY"));
				demandBean.setSFSH(rs.getInt("SFSH"));
				demandBean.setJGMC(rs.getString("JGMC"));
				demandBean.setGLBM(rs.getString("GLBM"));
				demandBean.setTXDZ(rs.getString("TXDZ"));
				demandBean.setDWWZ(rs.getString("DWWZ"));
				demandBean.setDZYX(rs.getString("DZYX"));
				demandBean.setFRDB(rs.getString("FRDB"));
				demandBean.setYZBM(rs.getString("YZBM"));
				demandBean.setLXR(rs.getString("LXR"));
				demandBean.setGDDH(rs.getString("GDDH"));
				demandBean.setYDDH(rs.getString("YDDH"));
				demandBean.setCZ(rs.getString("CZ"));
				demandBean.setJGSX(rs.getString("JGSX"));
				demandBean.setJGJJ(rs.getString("JGJJ"));
				demandBean.setJSXQMC(rs.getString("JSXQMC"));
				demandBean.setQSXQNF(rs.getInt("QSXQNF"));
				demandBean.setJZXQNF(rs.getInt("JZXQNF"));
				demandBean.setZDKJXQGS(rs.getString("ZDKJXQGS"));
				demandBean.setGJZ(rs.getString("GJZ"));
				demandBean.setYJLX(rs.getString("YJLX"));
				demandBean.setXKFL1(rs.getString("XKFL1"));
				demandBean.setXKFL2(rs.getString("XKFL2"));
				demandBean.setXKFL3(rs.getString("XKFL3"));
				demandBean.setXQJSSSLY(rs.getString("XQJSSSLY"));
				demandBean.setQTJSMS(rs.getString("QTJSMS"));
				demandBean.setXQJSYYHY1(rs.getString("XQJSYYHY1"));
				demandBean.setXQJSYYHY2(rs.getString("XQJSYYHY2"));
				demandBean.setXQJSYYHY3(rs.getString("XQJSYYHY3"));
				demandBean.setJSXQHZMS(rs.getString("JSXQHZMS"));
				demandBean.setHZYXDW(rs.getString("HZYXDW"));
				demandBean.setNTR(rs.getString("NTR"));
				demandBean.setCreateDate(rs.getString("createDate"));
				
				demandBeans.add(demandBean);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, statement,connection);
		}
		return demandBeans;
	}
	
	public List<DemandBean> getListCH(String next,String parent){
		String sql="select * from T_DCWJXX where XQJSYYHY3='"+next+"' and XQJSYYHY2='"+parent+"'";
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet rs=null;
		List<DemandBean> demandBeans=new ArrayList<DemandBean>();
		try{
			statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			DemandBean demandBean;
			while(rs.next()){
				demandBean=new DemandBean();
				demandBean.setWJID(rs.getString("WJID"));
				demandBean.setUsername(rs.getString("username"));
				demandBean.setSZDY(rs.getString("SZDY"));
				demandBean.setSFSH(rs.getInt("SFSH"));
				demandBean.setJGMC(rs.getString("JGMC"));
				demandBean.setGLBM(rs.getString("GLBM"));
				demandBean.setTXDZ(rs.getString("TXDZ"));
				demandBean.setDWWZ(rs.getString("DWWZ"));
				demandBean.setDZYX(rs.getString("DZYX"));
				demandBean.setFRDB(rs.getString("FRDB"));
				demandBean.setYZBM(rs.getString("YZBM"));
				demandBean.setLXR(rs.getString("LXR"));
				demandBean.setGDDH(rs.getString("GDDH"));
				demandBean.setYDDH(rs.getString("YDDH"));
				demandBean.setCZ(rs.getString("CZ"));
				demandBean.setJGSX(rs.getString("JGSX"));
				demandBean.setJGJJ(rs.getString("JGJJ"));
				demandBean.setJSXQMC(rs.getString("JSXQMC"));
				demandBean.setQSXQNF(rs.getInt("QSXQNF"));
				demandBean.setJZXQNF(rs.getInt("JZXQNF"));
				demandBean.setZDKJXQGS(rs.getString("ZDKJXQGS"));
				demandBean.setGJZ(rs.getString("GJZ"));
				demandBean.setYJLX(rs.getString("YJLX"));
				demandBean.setXKFL1(rs.getString("XKFL1"));
				demandBean.setXKFL2(rs.getString("XKFL2"));
				demandBean.setXKFL3(rs.getString("XKFL3"));
				demandBean.setXQJSSSLY(rs.getString("XQJSSSLY"));
				demandBean.setQTJSMS(rs.getString("QTJSMS"));
				demandBean.setXQJSYYHY1(rs.getString("XQJSYYHY1"));
				demandBean.setXQJSYYHY2(rs.getString("XQJSYYHY2"));
				demandBean.setXQJSYYHY3(rs.getString("XQJSYYHY3"));
				demandBean.setJSXQHZMS(rs.getString("JSXQHZMS"));
				demandBean.setHZYXDW(rs.getString("HZYXDW"));
				demandBean.setNTR(rs.getString("NTR"));
				demandBean.setCreateDate(rs.getString("createDate"));
				
				demandBeans.add(demandBean);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, statement,connection);
		}
		return demandBeans;
	}
	
	
	/**
	 * 以列表形式显示
	 * @return
	 */
	public List<DemandBean> getList(){
		String sql="select * from T_DCWJXX";
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet rs=null;
		List<DemandBean> demandBeans=new ArrayList<DemandBean>();
		try{
			statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			DemandBean demandBean;
			while(rs.next()){
				demandBean=new DemandBean();
				demandBean.setWJID(rs.getString("WJID"));
				demandBean.setUsername(rs.getString("username"));
				demandBean.setSZDY(rs.getString("SZDY"));
				demandBean.setSFSH(rs.getInt("SFSH"));
				demandBean.setJGMC(rs.getString("JGMC"));
				demandBean.setGLBM(rs.getString("GLBM"));
				demandBean.setTXDZ(rs.getString("TXDZ"));
				demandBean.setDWWZ(rs.getString("DWWZ"));
				demandBean.setDZYX(rs.getString("DZYX"));
				demandBean.setFRDB(rs.getString("FRDB"));
				demandBean.setYZBM(rs.getString("YZBM"));
				demandBean.setLXR(rs.getString("LXR"));
				demandBean.setGDDH(rs.getString("GDDH"));
				demandBean.setYDDH(rs.getString("YDDH"));
				demandBean.setCZ(rs.getString("CZ"));
				demandBean.setJGSX(rs.getString("JGSX"));
				demandBean.setJGJJ(rs.getString("JGJJ"));
				demandBean.setJSXQMC(rs.getString("JSXQMC"));
				demandBean.setQSXQNF(rs.getInt("QSXQNF"));
				demandBean.setJZXQNF(rs.getInt("JZXQNF"));
				demandBean.setZDKJXQGS(rs.getString("ZDKJXQGS"));
				demandBean.setGJZ(rs.getString("GJZ"));
				demandBean.setYJLX(rs.getString("YJLX"));
				demandBean.setXKFL1(rs.getString("XKFL1"));
				demandBean.setXKFL2(rs.getString("XKFL2"));
				demandBean.setXKFL3(rs.getString("XKFL3"));
				demandBean.setXQJSSSLY(rs.getString("XQJSSSLY"));
				demandBean.setQTJSMS(rs.getString("QTJSMS"));
				demandBean.setXQJSYYHY1(rs.getString("XQJSYYHY1"));
				demandBean.setXQJSYYHY2(rs.getString("XQJSYYHY2"));
				demandBean.setXQJSYYHY3(rs.getString("XQJSYYHY3"));
				demandBean.setJSXQHZMS(rs.getString("JSXQHZMS"));
				demandBean.setHZYXDW(rs.getString("HZYXDW"));
				demandBean.setNTR(rs.getString("NTR"));
				demandBean.setCreateDate(rs.getString("createDate"));
				
				demandBeans.add(demandBean);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, statement,connection);
		}
		return demandBeans;
	}
	
	
	/**
	 * 以列表形式显示,用户已提交的问卷
	 * @return
	 */
	public List<DemandBean> getMyList(String username){
		String sql="select * from T_DCWJXX where username='"+username+"'";
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet rs=null;
		List<DemandBean> demandBeans=new ArrayList<DemandBean>();
		try{
			statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			DemandBean demandBean;
			while(rs.next()){
				demandBean=new DemandBean();
				demandBean.setWJID(rs.getString("WJID"));
				demandBean.setUsername(rs.getString("username"));
				demandBean.setSZDY(rs.getString("SZDY"));
				demandBean.setSFSH(rs.getInt("SFSH"));
				demandBean.setJGMC(rs.getString("JGMC"));
				demandBean.setGLBM(rs.getString("GLBM"));
				demandBean.setTXDZ(rs.getString("TXDZ"));
				demandBean.setDWWZ(rs.getString("DWWZ"));
				demandBean.setDZYX(rs.getString("DZYX"));
				demandBean.setFRDB(rs.getString("FRDB"));
				demandBean.setYZBM(rs.getString("YZBM"));
				demandBean.setLXR(rs.getString("LXR"));
				demandBean.setGDDH(rs.getString("GDDH"));
				demandBean.setYDDH(rs.getString("YDDH"));
				demandBean.setCZ(rs.getString("CZ"));
				demandBean.setJGSX(rs.getString("JGSX"));
				demandBean.setJGJJ(rs.getString("JGJJ"));
				demandBean.setJSXQMC(rs.getString("JSXQMC"));
				demandBean.setQSXQNF(rs.getInt("QSXQNF"));
				demandBean.setJZXQNF(rs.getInt("JZXQNF"));
				demandBean.setZDKJXQGS(rs.getString("ZDKJXQGS"));
				demandBean.setGJZ(rs.getString("GJZ"));
				demandBean.setYJLX(rs.getString("YJLX"));
				demandBean.setXKFL1(rs.getString("XKFL1"));
				demandBean.setXKFL2(rs.getString("XKFL2"));
				demandBean.setXKFL3(rs.getString("XKFL3"));
				demandBean.setXQJSSSLY(rs.getString("XQJSSSLY"));
				demandBean.setQTJSMS(rs.getString("QTJSMS"));
				demandBean.setXQJSYYHY1(rs.getString("XQJSYYHY1"));
				demandBean.setXQJSYYHY2(rs.getString("XQJSYYHY2"));
				demandBean.setXQJSYYHY3(rs.getString("XQJSYYHY3"));
				demandBean.setJSXQHZMS(rs.getString("JSXQHZMS"));
				demandBean.setHZYXDW(rs.getString("HZYXDW"));
				demandBean.setNTR(rs.getString("NTR"));
				demandBean.setCreateDate(rs.getString("createDate"));
				
				demandBeans.add(demandBean);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, statement,connection);
		}
		return demandBeans;
	}
	
	/**
	 * 带条件的搜索,通过name
	 */
	public List<DemandBean> getByName(String JSXQMC){
		String sql="select * from T_DCWJXX where JSXQMC like '%"+JSXQMC+"%'";
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet rs=null;
		List<DemandBean> demandBeans=new ArrayList<DemandBean>();
		try{
			statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			DemandBean demandBean;
			
			while(rs.next()){
				demandBean=new DemandBean();
				demandBean.setWJID(rs.getString("WJID"));
				demandBean.setUsername(rs.getString("username"));
				demandBean.setSZDY(rs.getString("SZDY"));
				demandBean.setSFSH(rs.getInt("SFSH"));
				demandBean.setJGMC(rs.getString("JGMC"));
				demandBean.setGLBM(rs.getString("GLBM"));
				demandBean.setTXDZ(rs.getString("TXDZ"));
				demandBean.setDWWZ(rs.getString("DWWZ"));
				demandBean.setDZYX(rs.getString("DZYX"));
				demandBean.setFRDB(rs.getString("FRDB"));
				demandBean.setYZBM(rs.getString("YZBM"));
				demandBean.setLXR(rs.getString("LXR"));
				demandBean.setGDDH(rs.getString("GDDH"));
				demandBean.setYDDH(rs.getString("YDDH"));
				demandBean.setCZ(rs.getString("CZ"));
				demandBean.setJGSX(rs.getString("JGSX"));
				demandBean.setJGJJ(rs.getString("JGJJ"));
				demandBean.setJSXQMC(rs.getString("JSXQMC"));
				demandBean.setQSXQNF(rs.getInt("QSXQNF"));
				demandBean.setJZXQNF(rs.getInt("JZXQNF"));
				demandBean.setZDKJXQGS(rs.getString("ZDKJXQGS"));
				demandBean.setGJZ(rs.getString("GJZ"));
				demandBean.setYJLX(rs.getString("YJLX"));
				demandBean.setXKFL1(rs.getString("XKFL1"));
				demandBean.setXKFL2(rs.getString("XKFL2"));
				demandBean.setXKFL3(rs.getString("XKFL3"));
				demandBean.setXQJSSSLY(rs.getString("XQJSSSLY"));
				demandBean.setQTJSMS(rs.getString("QTJSMS"));
				demandBean.setXQJSYYHY1(rs.getString("XQJSYYHY1"));
				demandBean.setXQJSYYHY2(rs.getString("XQJSYYHY2"));
				demandBean.setXQJSYYHY3(rs.getString("XQJSYYHY3"));
				demandBean.setJSXQHZMS(rs.getString("JSXQHZMS"));
				demandBean.setHZYXDW(rs.getString("HZYXDW"));
				demandBean.setNTR(rs.getString("NTR"));
				demandBean.setCreateDate(rs.getString("createDate"));
				
				demandBeans.add(demandBean);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, statement,connection);
		}
		return demandBeans;
	}

	
	
	/**
	 * 详细信息
	 */
	public DemandBean getById(String WJID){
		String sql="select * from T_DCWJXX where WJID='"+WJID+"'";
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet rs=null;
		DemandBean demandBean=null;
		try{
			statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			while(rs.next()){
				demandBean=new DemandBean();
				demandBean.setWJID(rs.getString("WJID"));
				demandBean.setUsername(rs.getString("username"));
				demandBean.setSZDY(rs.getString("SZDY"));
				demandBean.setSFSH(rs.getInt("SFSH"));
				demandBean.setJGMC(rs.getString("JGMC"));
				demandBean.setGLBM(rs.getString("GLBM"));
				demandBean.setTXDZ(rs.getString("TXDZ"));
				demandBean.setDWWZ(rs.getString("DWWZ"));
				demandBean.setDZYX(rs.getString("DZYX"));
				demandBean.setFRDB(rs.getString("FRDB"));
				demandBean.setYZBM(rs.getString("YZBM"));
				demandBean.setLXR(rs.getString("LXR"));
				demandBean.setGDDH(rs.getString("GDDH"));
				demandBean.setYDDH(rs.getString("YDDH"));
				demandBean.setCZ(rs.getString("CZ"));
				demandBean.setJGSX(rs.getString("JGSX"));
				demandBean.setJGJJ(rs.getString("JGJJ"));
				demandBean.setJSXQMC(rs.getString("JSXQMC"));
				demandBean.setQSXQNF(rs.getInt("QSXQNF"));
				demandBean.setJZXQNF(rs.getInt("JZXQNF"));
				demandBean.setZDKJXQGS(rs.getString("ZDKJXQGS"));
				demandBean.setGJZ(rs.getString("GJZ"));
				demandBean.setYJLX(rs.getString("YJLX"));
				demandBean.setXKFL1(rs.getString("XKFL1"));
				demandBean.setXKFL2(rs.getString("XKFL2"));
				demandBean.setXKFL3(rs.getString("XKFL3"));
				demandBean.setXQJSSSLY(rs.getString("XQJSSSLY"));
				demandBean.setQTJSMS(rs.getString("QTJSMS"));
				demandBean.setXQJSYYHY1(rs.getString("XQJSYYHY1"));
				demandBean.setXQJSYYHY2(rs.getString("XQJSYYHY2"));
				demandBean.setXQJSYYHY3(rs.getString("XQJSYYHY3"));
				demandBean.setJSXQHZMS(rs.getString("JSXQHZMS"));
				demandBean.setHZYXDW(rs.getString("HZYXDW"));
				demandBean.setNTR(rs.getString("NTR"));
				demandBean.setCreateDate(rs.getString("createDate"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, statement,connection);
		}
		return demandBean;
	}
	
	
	
	/**
	 * 以列表形式显示未审核的需求信息
	 * @return
	 */
	public List<DemandBean> getListNo(){
		String sql="select * from T_DCWJXX where SFSH='0'";
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet rs=null;
		List<DemandBean> demandBeans=new ArrayList<DemandBean>();
		try{
			statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			DemandBean demandBean;
			while(rs.next()){
				demandBean=new DemandBean();
				demandBean.setWJID(rs.getString("WJID"));
				demandBean.setUsername(rs.getString("username"));
				demandBean.setSZDY(rs.getString("SZDY"));
				demandBean.setSFSH(rs.getInt("SFSH"));
				demandBean.setJGMC(rs.getString("JGMC"));
				demandBean.setGLBM(rs.getString("GLBM"));
				demandBean.setTXDZ(rs.getString("TXDZ"));
				demandBean.setDWWZ(rs.getString("DWWZ"));
				demandBean.setDZYX(rs.getString("DZYX"));
				demandBean.setFRDB(rs.getString("FRDB"));
				demandBean.setYZBM(rs.getString("YZBM"));
				demandBean.setLXR(rs.getString("LXR"));
				demandBean.setGDDH(rs.getString("GDDH"));
				demandBean.setYDDH(rs.getString("YDDH"));
				demandBean.setCZ(rs.getString("CZ"));
				demandBean.setJGSX(rs.getString("JGSX"));
				demandBean.setJGJJ(rs.getString("JGJJ"));
				demandBean.setJSXQMC(rs.getString("JSXQMC"));
				demandBean.setQSXQNF(rs.getInt("QSXQNF"));
				demandBean.setJZXQNF(rs.getInt("JZXQNF"));
				demandBean.setZDKJXQGS(rs.getString("ZDKJXQGS"));
				demandBean.setGJZ(rs.getString("GJZ"));
				demandBean.setYJLX(rs.getString("YJLX"));
				demandBean.setXKFL1(rs.getString("XKFL1"));
				demandBean.setXKFL2(rs.getString("XKFL2"));
				demandBean.setXKFL3(rs.getString("XKFL3"));
				demandBean.setXQJSSSLY(rs.getString("XQJSSSLY"));
				demandBean.setQTJSMS(rs.getString("QTJSMS"));
				demandBean.setXQJSYYHY1(rs.getString("XQJSYYHY1"));
				demandBean.setXQJSYYHY2(rs.getString("XQJSYYHY2"));
				demandBean.setXQJSYYHY3(rs.getString("XQJSYYHY3"));
				demandBean.setJSXQHZMS(rs.getString("JSXQHZMS"));
				demandBean.setHZYXDW(rs.getString("HZYXDW"));
				demandBean.setNTR(rs.getString("NTR"));
				demandBean.setCreateDate(rs.getString("createDate"));
				demandBeans.add(demandBean);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, statement,connection);
		}
		return demandBeans;
	}
	
	
	
	/**
	 * 更新审核的状态
	 */
	public boolean updateSFSH(String WJID,int SFSH,String V){
		String sql="update T_DCWJXX set SFSH='"+SFSH+"',V='"+V+"' where WJID='"+WJID+"'";
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		boolean f = false;
		int a = 0;
		try{
			statement=connection.createStatement();
			a = statement.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(statement,connection);
		}
		if (a > 0) {
			f = true;
			}
		return f;
	}

	
}
