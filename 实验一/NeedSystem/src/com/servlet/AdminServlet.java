package com.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.AdminBean;
import com.bean.CodeBean;
import com.bean.DemandBean;
import com.dao.AdminDao;
import com.dao.CodeDao;
import com.bean.ProvinceBean;
import com.dao.ProvinceDao;
import com.util.StringUtil;


/**
 * Servlet implementation class AdminServlet
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AdminServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String method=req.getParameter("method");
		if("login".equals(method)){
			login(req,resp);
		}else if(("register").equals(method)){
			register(req,resp);
		}else if("ProvinceView".equals(method)){
			ProvinceView(req,resp);
		}else if("updateP".equals(method)){
			updateP(req,resp);
		}else if("end".equals(method)){
			end(req,resp);
		}else if("updateBefore".equals(method)){
			updateBefore(req,resp);
		}else if("update".equals(method)){
			update(req,resp);
		}	
		/**
		 * Ȩ�޹���
		 */
		else if("fenpeiBefore".equals(method)){
			fenpeiBefore(req,resp);
		}else if("addSFSH".equals(method)){
			addSFSH(req,resp);
		}
		/**
		 * �û�����
		 */
		else if("userList".equals(method)){
			userList(req,resp);
		}else if("updatePassword".equals(method)){
			updatePassword(req,resp);
		}else if("delete".equals(method)){
			delete(req,resp);//�����ɵ�¼
		}
	}
	
	/**
	 * �û����� ֮�û��б�
	 */
	private void userList(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException{
		req.setCharacterEncoding("utf-8");
		AdminDao adminDao=new AdminDao();
		List<AdminBean> adminBeans = adminDao.getByStatusB();
		
		req.setAttribute("adminBeans", adminBeans);
		req.getRequestDispatcher("first/userList.jsp").forward(req, resp);
		
		
		
		
	}
	
	/**
	 * �û����� ֮��������
	 */
	private void updatePassword(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException{
		req.setCharacterEncoding("utf-8");
		AdminDao adminDao=new AdminDao();
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		adminDao.update(username,password);
		
		userList(req,resp);
		
	}
	
	
	
	
	/**
	 * �û����� ֮ע���û�
	 */
	private void delete(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException{
		req.setCharacterEncoding("utf-8");
		AdminDao adminDao=new AdminDao();
		String username=req.getParameter("username");
		adminDao.delete(username);
		userList(req,resp);
		
		
		
		
	}
	
	
	/**
	 * �����ɫ
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void addSFSH(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException{
		req.setCharacterEncoding("utf-8");
		String username=req.getParameter("username");
		int status=Integer.parseInt(req.getParameter("status"));
	System.out.println("username:"+username+"    status:"+status);
			AdminDao adminDao=new AdminDao();
		
		boolean f=adminDao.up(username,status);
		
		List<AdminBean> adminBeans = adminDao.get();
		
		
		
		fenpeiBefore(req,resp);
	
	}
	
	
	/**
	 * Ȩ�޹���֮ǰ���û��б�
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void fenpeiBefore(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
		req.setCharacterEncoding("utf-8");
		AdminDao adminDao=new AdminDao();
		List<AdminBean> adminBeans = adminDao.getByStatus();
		
		req.setAttribute("adminBeans", adminBeans);
		req.getRequestDispatcher("first/fenpei.jsp").forward(req, resp);
		
	}
	
	/**
	 * �˳���¼
	 */
	private void end(HttpServletRequest req,HttpServletResponse resp)throws
	ServletException,IOException{
		req.setCharacterEncoding("utf-8");
		//�û���¼֮��,servlet��request.getSession()����ʽ�������û��ĵ�¼��Ϣ
			//Ȼ��ʹ��request.getSession().invalidate()��ע���û���Ϣ
			req.getSession().invalidate();
			
			//���ˣ��û�����ȫ�˳���¼������Ϣ��ע���ˣ���ʱ������ת�ص���¼ҳ��
			resp.sendRedirect(req.getContextPath()+"/login.jsp");
		}
	/**
	 * ��ʾʡ�ݵ������˵��б�
	 * @param req
	 * @param resp
	 */
	private void ProvinceView(HttpServletRequest req, HttpServletResponse resp) {
		//��ʾʡ�ݵ�LIST
		ProvinceDao provinceDao=new ProvinceDao();
		List<ProvinceBean> provinceBeans=provinceDao.getProvinceList();
		//�ҵ��������������
		CodeDao codeDao=new CodeDao();
		List<CodeBean> codeBeans=codeDao.getCodeList();
			try {
		req.setAttribute("provinceBeans",provinceBeans);
		req.setAttribute("codeBeans",codeBeans);
		req.getRequestDispatcher("/add.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
		e.printStackTrace();
		}
	}
	
	/**
	 * �޸Ļ�����Ϣ֮ǰ
	 * @param req
	 * @param resp
	 */
	private void updateBefore(HttpServletRequest req, HttpServletResponse resp) {
		String username=req.getParameter("username");
		AdminDao adminDao=new AdminDao();
		AdminBean adminBean=adminDao.getByUsername(username);
		//��ʾʡ�ݵ�LIST
		ProvinceDao provinceDao=new ProvinceDao();
		List<ProvinceBean> provinceBeans=provinceDao.getProvinceList();
		//�ҵ��������������
		CodeDao codeDao=new CodeDao();
		List<CodeBean> codeBeans=codeDao.getCodeList();
			try {
		req.setAttribute("provinceBeans",provinceBeans);
		req.setAttribute("adminBean", adminBean);
		req.getRequestDispatcher("/my/myInfo.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
		e.printStackTrace();
		}
	}
	/**
	 * ע��
	 * @throws IOException 
	 */
	private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		req.setCharacterEncoding("utf-8");
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String code=req.getParameter("code");
		String name=req.getParameter("name");
		String id_number=req.getParameter("id_number");
		String sex=req.getParameter("sex");
		int provinceId=Integer.parseInt(req.getParameter("ProvinceId"));
		int cityId=Integer.parseInt(req.getParameter("CityId"));
		String unit=req.getParameter("unit");
		String direction=req.getParameter("direction");
		String industry=req.getParameter("industry");
		String levels=req.getParameter("levels");
		String title=req.getParameter("title");
		String address=req.getParameter("address");
		String postal=req.getParameter("postal");
		String phone=req.getParameter("phone");
		String telephone=req.getParameter("telephone");
		String email=req.getParameter("email");
		String qq=req.getParameter("qq");
		String msn=req.getParameter("msn");
		
		
		AdminBean adminBean=new AdminBean();
		AdminDao adminDao=new AdminDao();
		
		adminBean.setUsername(username);
		adminBean.setPassword(password);
		adminBean.setCode(code);
		adminBean.setName(name);
		adminBean.setId_number(id_number);
		adminBean.setSex(sex);
		adminBean.setProvinceId(provinceId);
		adminBean.setCityId(cityId);
		adminBean.setUnit(unit);
		adminBean.setDirection(direction);
		adminBean.setIndustry(industry);
		adminBean.setLevels(levels);
		adminBean.setTitle(title);
		adminBean.setAddress(address);
		adminBean.setPostal(postal);
		adminBean.setPhone(phone);
		adminBean.setTelephone(telephone);
		adminBean.setEmail(email);
		adminBean.setQq(qq);
		adminBean.setMsn(msn);
		
		boolean flag=adminDao.checkReg(username);
		if(flag){//�����ڣ�ע��ɹ�
			adminDao.save(adminBean);
			resp.sendRedirect("add.jsp?status=0");
		}else{//���ڣ�ע��ʧ��
			resp.sendRedirect("add.jsp?status=1");
		}
		
		
	}
	
	/**
	 * �޸Ļ�����Ϣ
	 * @throws IOException 
	 */
	private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		req.setCharacterEncoding("utf-8");
		String username=req.getParameter("username");
		String code=req.getParameter("code");
		String name=req.getParameter("name");
		String id_number=req.getParameter("id_number");
		String sex=req.getParameter("sex");
		int provinceId=Integer.parseInt(req.getParameter("ProvinceId"));
		int cityId=Integer.parseInt(req.getParameter("CityId"));
		String unit=req.getParameter("unit");
		String direction=req.getParameter("direction");
		String industry=req.getParameter("industry");
		String levels=req.getParameter("levels");
		String title=req.getParameter("title");
		String address=req.getParameter("address");
		String postal=req.getParameter("postal");
		String phone=req.getParameter("phone");
		String telephone=req.getParameter("telephone");
		String email=req.getParameter("email");
		String qq=req.getParameter("qq");
		String msn=req.getParameter("msn");
		
		
		AdminBean adminBean=new AdminBean();
		AdminDao adminDao=new AdminDao();
		
		adminBean.setUsername(username);
		adminBean.setCode(code);
		adminBean.setName(name);
		adminBean.setId_number(id_number);
		adminBean.setSex(sex);
		adminBean.setProvinceId(provinceId);
		adminBean.setCityId(cityId);
		adminBean.setUnit(unit);
		adminBean.setDirection(direction);
		adminBean.setIndustry(industry);
		adminBean.setLevels(levels);
		adminBean.setTitle(title);
		adminBean.setAddress(address);
		adminBean.setPostal(postal);
		adminBean.setPhone(phone);
		adminBean.setTelephone(telephone);
		adminBean.setEmail(email);
		adminBean.setQq(qq);
		adminBean.setMsn(msn);
		
		boolean flag=adminDao.updateX(adminBean);
		if(flag){//���³ɹ�
			resp.sendRedirect("my/myInfo.jsp?status=0");
		}else{//����ʧ��
			resp.sendRedirect("my/myInfojspstatus=1");
		}
		
		
	}
	
	
	
	/**
	 * ��¼
	 * @throws UnsupportedEncodingException 
	 */
	private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		req.setCharacterEncoding("utf-8");
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String codeCheck=req.getParameter("codeCheck");
		
		/*if(username==null || password==null || codeCheck==null){
				resp.sendRedirect(req.getContextPath()+"/login.jsp");	//������
		}else{
		*/
			System.out.println("�û�����"+username+"		���룺"+password);
			AdminBean adminBean=new AdminBean();
			AdminDao adminDao=new AdminDao();
			
			boolean flag=adminDao.checkReg(username);
			if(flag){//��¼ʧ�ܣ�username������
				resp.sendRedirect(req.getContextPath()+"/login.jsp?status=1");	//������
			}else{
				//username����
				adminBean=adminDao.check(username,password);
				if(adminBean!=null){//������ȷ
					System.out.println("������ȷ");
					if(adminBean.getStatus()==0)
					{
						req.getSession().setAttribute("adminBean",adminBean);	
						resp.sendRedirect(req.getContextPath()+"/first/index.jsp");
					}else{
						req.getSession().setAttribute("adminBean",adminBean);	
						resp.sendRedirect(req.getContextPath()+"/index.jsp");
					}
				
				}else{//���벻��ȷ
					System.out.println("�������");
					resp.sendRedirect(req.getContextPath()+"/login.jsp?status=2");
				}
			}
			
		//}
	}

	
	
	/*
/**
	 * �޸�����
	 
	private void updateP(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
		req.setCharacterEncoding("utf-8");
		String username=req.getParameter("username");
		String password=req.getParameter("password");//ԭ����
		String p1=req.getParameter("P1");
		String p2=req.getParameter("P2");
		
		if(!p1.equals(password)){
			resp.sendRedirect(req.getContextPath()+"/my/myPass.jsp?status=0");
		}else{
			AdminDao adminDao=new AdminDao();
			boolean flag=adminDao.update(username,p2);

			
			if(flag){
				//�޸ĳɹ�
				resp.sendRedirect(req.getContextPath()+"/my/myPass.jsp?status=1");
			}else{
				//�޸�ʧ��
				resp.sendRedirect(req.getContextPath()+"/my/myPass.jsp?status=2");
			}
			
		}
		
		
		
	}
	*/
	//�޸�����
		private void updateP(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
			// TODO Auto-generated method stub
			req.setCharacterEncoding("utf-8");
			resp.setContentType("text/html;charSet=utf-8");
			String username=req.getParameter("id");
			String oldPassword=req.getParameter("password");
			String password = req.getParameter("pwd2");
			System.out.println(username);//////username
			
			//int id=StringUtil.StringToInt(req.getParameter("id"));
			//UserBean updateBean=null;
			AdminDao userDao=new AdminDao();
			int flag=userDao.update1(username, password,oldPassword);
			//updateBean=userDao.getById(id);

			//req.setAttribute("updateBean", updateBean);
			//req.getRequestDispatcher("updatePassword.jsp?updateId=" + id).forward(req, resp);
			if(flag==1)//ԭʼ�������
			{
				resp.sendRedirect(req.getContextPath()+"/my/myPass.jsp?status=1");
			}
			else if(flag==2)//ת�����޸�����ҳ�沢��ʾ�޸ĳɹ�
			{
				resp.sendRedirect(req.getContextPath()+"/my/myPass.jsp?status=2");
			}
			else if(flag==3)//ת�����޸�����ҳ�沢��ʾ�޸�ʧ��
			{
				resp.sendRedirect(req.getContextPath()+"/my/myPass.jsp?status=3");
			}
		}
	
	
	
	
	
}
