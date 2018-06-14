package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bean.AdminBean;
import com.util.DBUtil;

public class AdminDao {

	/**
	 * ����û������ݿ���
	 * 
	 * @param adminBean
	 */
	public void save(AdminBean adminBean) {
		// ��ȡ���ݿ�����
		String sql = "insert into admin(username,password,code,name,id_number,sex,provinceId,cityId,unit,direction,industry,"
				+ "levels,title,address,postal,phone,telephone,email,qq,msn) values('" + adminBean.getUsername() + "','"
				+ adminBean.getPassword() + "','" + adminBean.getCode() + "','" + adminBean.getName() + "','"
				+ adminBean.getId_number() + "','" + adminBean.getSex() + "','" +adminBean.getProvinceId()+"','"+ adminBean.getCityId() + "','"
				+ adminBean.getUnit() + "','" + adminBean.getDirection() + "','" + adminBean.getIndustry() + "','"
				+ adminBean.getLevels() + "','" + adminBean.getTitle() + "','" + adminBean.getAddress() + "','"
				+ adminBean.getPostal() + "','" + adminBean.getPhone() + "','" + adminBean.getTelephone() + "','"
				+ adminBean.getEmail() + "','" + adminBean.getQq() + "','" + adminBean.getMsn() + "')";
		Connection conn = DBUtil.getConn();
		// �������ݿ��������
		Statement state = null;
		try {
			state = conn.createStatement();
			state.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(state, conn);
		}

	}
	
	/**
	 * Ȩ�޹���
	 */
	public boolean up(String username,int status){
		 String sql="update admin set status="+status+" where username='"+username+"'";
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
		 if (a > 0) { f = true; }
		 return f;
		 
	}
	
	/**
	 * ��ȡ�û���Ϣ�б�
	 */
	public List<AdminBean> getByStatus(){
		String sql="select * from admin where status='3'";
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet rs=null;
		List<AdminBean> adminBeans=new ArrayList<AdminBean>();
		try{
			statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			AdminBean adminBean;
			
			while(rs.next()){
				adminBean=new AdminBean();
				adminBean.setUsername(rs.getString("username"));
				adminBean.setPassword(rs.getString("password"));
				adminBean.setCode(rs.getString("code"));
				adminBean.setName(rs.getString("name"));
				adminBean.setId_number(rs.getString("id_number"));
				adminBean.setSex(rs.getString("sex"));
				adminBean.setProvinceId(rs.getInt("provinceId"));
				adminBean.setCityId(rs.getInt("cityId"));
				adminBean.setUnit(rs.getString("unit"));
				adminBean.setDirection(rs.getString("direction"));
				adminBean.setIndustry(rs.getString("industry"));
				adminBean.setLevels(rs.getString("levels"));
				adminBean.setTitle(rs.getString("title"));
				adminBean.setAddress(rs.getString("address"));
				adminBean.setPostal(rs.getString("postal"));
				adminBean.setPhone(rs.getString("phone"));
				adminBean.setTelephone(rs.getString("telephone"));
				adminBean.setEmail(rs.getString("email"));
				adminBean.setQq(rs.getString("qq"));
				adminBean.setMsn(rs.getString("msn"));
				adminBean.setStatus(rs.getInt("status"));
				
				adminBeans.add(adminBean);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, statement,connection);
		}
		return adminBeans;
	}
	/**
	 * ��ȡ�û���Ϣ�б�
	 */
	public List<AdminBean> getByStatusB(){
		String sql="select * from admin where status!='3'";
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet rs=null;
		List<AdminBean> adminBeans=new ArrayList<AdminBean>();
		try{
			statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			AdminBean adminBean;
			
			while(rs.next()){
				adminBean=new AdminBean();
				adminBean.setUsername(rs.getString("username"));
				adminBean.setPassword(rs.getString("password"));
				adminBean.setCode(rs.getString("code"));
				adminBean.setName(rs.getString("name"));
				adminBean.setId_number(rs.getString("id_number"));
				adminBean.setSex(rs.getString("sex"));
				adminBean.setProvinceId(rs.getInt("provinceId"));
				adminBean.setCityId(rs.getInt("cityId"));
				adminBean.setUnit(rs.getString("unit"));
				adminBean.setDirection(rs.getString("direction"));
				adminBean.setIndustry(rs.getString("industry"));
				adminBean.setLevels(rs.getString("levels"));
				adminBean.setTitle(rs.getString("title"));
				adminBean.setAddress(rs.getString("address"));
				adminBean.setPostal(rs.getString("postal"));
				adminBean.setPhone(rs.getString("phone"));
				adminBean.setTelephone(rs.getString("telephone"));
				adminBean.setEmail(rs.getString("email"));
				adminBean.setQq(rs.getString("qq"));
				adminBean.setMsn(rs.getString("msn"));
				adminBean.setStatus(rs.getInt("status"));
				
				adminBeans.add(adminBean);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, statement,connection);
		}
		return adminBeans;
	}
	/**
	 * ��ȡ�û���Ϣ�б�
	 */
	public List<AdminBean> get(){
		String sql="select * from admin";
		Connection connection=DBUtil.getConn();
		Statement statement=null;
		ResultSet rs=null;
		List<AdminBean> adminBeans=new ArrayList<AdminBean>();
		try{
			statement=connection.createStatement();
			rs=statement.executeQuery(sql);
			AdminBean adminBean;
			
			while(rs.next()){
				adminBean=new AdminBean();
				adminBean.setUsername(rs.getString("username"));
				adminBean.setPassword(rs.getString("password"));
				adminBean.setCode(rs.getString("code"));
				adminBean.setName(rs.getString("name"));
				adminBean.setId_number(rs.getString("id_number"));
				adminBean.setSex(rs.getString("sex"));
				adminBean.setProvinceId(rs.getInt("provinceId"));
				adminBean.setCityId(rs.getInt("cityId"));
				adminBean.setUnit(rs.getString("unit"));
				adminBean.setDirection(rs.getString("direction"));
				adminBean.setIndustry(rs.getString("industry"));
				adminBean.setLevels(rs.getString("levels"));
				adminBean.setTitle(rs.getString("title"));
				adminBean.setAddress(rs.getString("address"));
				adminBean.setPostal(rs.getString("postal"));
				adminBean.setPhone(rs.getString("phone"));
				adminBean.setTelephone(rs.getString("telephone"));
				adminBean.setEmail(rs.getString("email"));
				adminBean.setQq(rs.getString("qq"));
				adminBean.setMsn(rs.getString("msn"));
				adminBean.setStatus(rs.getInt("status"));
				
				adminBeans.add(adminBean);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.close(rs, statement,connection);
		}
		return adminBeans;
	}
	
	
	/**
	 * ���»�����Ϣ
	 * 
	 * @param id
	 * @return
	 */
	 public boolean updateX(AdminBean adminBean){ 
		 String sql="update admin set code='"+adminBean.getCode()+"',name='"+adminBean.getName()+"',id_number='"+
				 adminBean.getId_number()+"',sex='"+adminBean.getSex()+"',provinceId='"+adminBean.getProvinceId()+"',cityId='"+adminBean.getCityId()
	 +"',unit='"+adminBean.getUnit()+"',direction='"+adminBean.getDirection()+"',industry='"+adminBean.getIndustry()+"',levels='"+
	 adminBean.getLevels()+"',title='"+adminBean.getTitle()+"',address='"+adminBean.getAddress()+"',postal='"+adminBean.getPostal()
				 +"',phone='"+adminBean.getPhone()+"',telephone='"+adminBean.getTelephone()+"',email='"+adminBean.getEmail()+"',qq='"+
				 adminBean.getQq()+"',msn='"+adminBean.getMsn()+"' where username='"+adminBean.getUsername()+"'";
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
	 if (a > 0) { f = true; }
	 return f;
	 }
	 
	/**
	 * �ж��Ƿ����
	 * 
	 * @param name
	 * @return
	 */
	public boolean checkReg(String username) {
		// ����һ��ʼ����ע�ᣬ�����ֵ��Ϊtrue
		boolean flag = true;
		// ��ѯ�û��Ƿ��Ѵ���
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select username from admin");
			while (rs.next()) {
				if (username.equals(rs.getString("username"))) {
					// admin���ݱ����Ѵ��ڴ��˺���˵��������ע�ᣬ�����ֵ��Ϊfalse
					flag = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, statement, connection);
		}
		return flag;
	}

	/**
	 * �ж��Ƿ񱻷����ɫ
	 */
	public int checkF(String username){
		int f=0;
		Connection connection = DBUtil.getConn();
		Statement statement = null;
		ResultSet rs = null;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from admin where username='"+username+"'");
			while (rs.next()) {
					f=rs.getInt("status");
				}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(rs, statement, connection);
		}
		return f;
	}
	/**
	 * ��¼��֤
	 */
	public AdminBean check(String username, String password) {
		Connection conn = DBUtil.getConn();
		AdminBean adminBean = null;
		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery("select * from admin where username='" + username + "'");
			if (rs.next()) {
				if (password.equals(rs.getString("password"))) {
					adminBean = new AdminBean();
					adminBean.setUsername(username);
					adminBean.setPassword(password);
					adminBean.setCode(rs.getString("code"));
					adminBean.setName(rs.getString("name"));
					adminBean.setId_number(rs.getString("id_number"));
					adminBean.setSex(rs.getString("sex"));
					adminBean.setProvinceId(rs.getInt("provinceId"));
					adminBean.setCityId(rs.getInt("cityId"));
					adminBean.setUnit(rs.getString("unit"));
					adminBean.setDirection(rs.getString("direction"));
					adminBean.setIndustry(rs.getString("industry"));
					adminBean.setLevels(rs.getString("levels"));
					adminBean.setTitle(rs.getString("title"));
					adminBean.setAddress(rs.getString("address"));
					adminBean.setPostal(rs.getString("postal"));
					adminBean.setPhone(rs.getString("phone"));
					adminBean.setTelephone(rs.getString("telephone"));
					adminBean.setEmail(rs.getString("email"));
					adminBean.setQq(rs.getString("qq"));
					adminBean.setMsn(rs.getString("msn"));
					adminBean.setStatus(rs.getInt("status"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return adminBean;
	}

	/**
	 * ͨ��username�õ�Bean
	 */
	public AdminBean getByUsername(String username) {
		Connection conn = DBUtil.getConn();
		AdminBean adminBean = null;
		try {
			Statement state = conn.createStatement();
			ResultSet rs = state.executeQuery("select * from admin where username='" + username + "'");
			if (rs.next()) {
				adminBean = new AdminBean();
				adminBean.setUsername(username);
				adminBean.setPassword(rs.getString("password"));
				adminBean.setCode(rs.getString("code"));
				adminBean.setName(rs.getString("name"));
				adminBean.setId_number(rs.getString("id_number"));
				adminBean.setSex(rs.getString("sex"));
				adminBean.setProvinceId(rs.getInt("provinceId"));
				adminBean.setCityId(rs.getInt("cityId"));
				adminBean.setUnit(rs.getString("unit"));
				adminBean.setDirection(rs.getString("direction"));
				adminBean.setIndustry(rs.getString("industry"));
				adminBean.setLevels(rs.getString("levels"));
				adminBean.setTitle(rs.getString("title"));
				adminBean.setAddress(rs.getString("address"));
				adminBean.setPostal(rs.getString("postal"));
				adminBean.setPhone(rs.getString("phone"));
				adminBean.setTelephone(rs.getString("telephone"));
				adminBean.setEmail(rs.getString("email"));
				adminBean.setQq(rs.getString("qq"));
				adminBean.setMsn(rs.getString("msn"));
				adminBean.setStatus(rs.getInt("status"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return adminBean;
	}

	
	
	
	
			/**
			 * �޸�����
			 * 
			 * @param id
			 * @return
			 * */
			 
			 public boolean update(String username,String password){ 
				 String sql="update admin set password='"+password+"' where username='"+username+"'";
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
			 if (a > 0) { f = true; }
			 return f;
			 }
			 
	
	 //�޸�����
		public int update1(String username,String password,String oldPassword){ 
			String sql1="select password from admin where username='"+username+"'";
			String sql2="update admin set password='"+password+"' where username='"+username+"'";
			Connection connection=DBUtil.getConn(); Statement statement=null;
			int f=3;//f=1��ʾ�����������f=2��ʾ�޸ĳɹ���f=3��ʾ�����޸�ʧ��
			int a=0;
			String checkPassword=null;//���ݿ��е�����
			try {
				statement=connection.createStatement();
				ResultSet rs = statement.executeQuery(sql1);
				System.out.println(sql1);
				if(rs.next())
				{
					checkPassword=rs.getString("password");
					System.out.println(checkPassword);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(checkPassword);
			if(oldPassword.equals(checkPassword))
			{
				try{
					 a =statement.executeUpdate(sql2);
					 System.out.println(sql2);
					 
				 }catch(Exception e){
					 e.printStackTrace();}finally
				 {
					 DBUtil.close(statement,connection); 
				 } 
				 if(a>0)
				 {
					 f=2;
				 }
				 else
				 {
					 f=3;
				 }
			}
			else
			{
				f=1;
			}
			 return f;
			 }

			 
			 /**
			  * ע���û�
			  */
			 public boolean delete(String username){ 
				 String sql="delete from admin where username='"+username+"'";
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
			 if (a > 0) { f = true; }
			 return f;
			 }

}
