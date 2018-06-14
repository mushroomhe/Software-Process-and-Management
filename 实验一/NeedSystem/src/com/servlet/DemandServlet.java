package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.bean.AdminBean;
import com.bean.CodeBean;
import com.bean.DemandBean;
import com.bean.FirstHBean;
import com.bean.FirstXBean;
import com.bean.PagingBean;
import com.bean.SecondXBean;
import com.bean.Tongjibean;
import com.dao.AdminDao;
import com.dao.CodeDao;
import com.dao.DemandDao;
import com.dao.FirstHDao;
import com.dao.FirstXDao;
import com.util.Constants;
import com.util.StringUtil;

/**
 * Servlet implementation class DemandServlet
 */
@WebServlet("/DemandServlet")
public class DemandServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DemandServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
  
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String method = req.getParameter("method");
		if("info".equals(method)){
			info(req,resp);			//first/addInfo.jsp
		}else if ("addInfo".equals(method)) {
			addInfo(req, resp);
		} else if ("list".equals(method)) {
			list(req, resp);
		}else if("list_type".equals(method)){
			list_type(req,resp);
		}else if("listFen".equals(method)){
			listFen(req,resp);
		}else if ("selectList".equals(method)) {
			selectList(req, resp);
		} else if ("details".equals(method)) {
			details(req, resp);
		}
		/**
		 * 我的需求浏览
		 */
		else if("myList".equals(method)){
			myList(req,resp);//我的需求问卷
		}else if("myDetails".equals(method)){
			myDetails(req,resp);//我的需求问卷详情
		}
		/**
		 * 总需求
		 */
		else if("list".equals(method)){
			list(req,resp);
		}else if("details".equals(method)){
			details(req,resp);
		}
		
		/**
		 * 分类浏览功能
		 */
		else if("preType".equals(method)){
			preType(req,resp);
		}else if("getNextA".equals(method)){
			getNextA(req,resp);
		}else if("getNextBX".equals(method)){
			getNextBX(req,resp);
		}else if("getNextCX".equals(method)){
			getNextCX(req,resp);
		}else if("getNextBH".equals(method)){
			getNextBH(req,resp);
		}else if("getNextCH".equals(method)){
			getNextCH(req,resp);
		}else if("getList".equals(method)){
			getList(req,resp);
		}else if("getListAX".equals(method)){
			getListAX(req,resp);
		}else if("getListBX".equals(method)){
			getListBX(req,resp);
		}else if("getListCX".equals(method)){
			getListCX(req,resp);
		}else if("getListAH".equals(method)){
			getListAH(req,resp);
		}else if("getListBH".equals(method)){
			getListBH(req,resp);
		}else if("getListBH".equals(method)){
			getListCH(req,resp);
		}
		/**
		 * 图表统计
		 */
		else if("tongji".equals(method)){
			tongji(req,resp);
		}
		/**
		 * 审核
		 */
		else if("check".equals(method)){
			check(req,resp);//更改审核的状态
		}
		else if ("demandList".equals(method)) {
			demandList(req, resp);
		}else if ("selectDemandList".equals(method)) {
			selectDemandList(req, resp);
		}else if ("demandDetails".equals(method)) {
			demandDetails(req, resp);
		}
		else if("shenhechaxun".equals(method)){
			shenhechaxun(req,resp);
		}
	
	}
	
/**
 * 图表统计
 * @param req
 * @param resp
 * @throws ServletException
 * @throws IOException
 */
	protected void tongji(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException
	{
		req.setCharacterEncoding("utf-8");
		DemandDao xuqiudao=new DemandDao();
		Tongjibean tongjibean=xuqiudao.tongji();
		tongjibean.setWeishenhe1(tongjibean.jiussuan(tongjibean.getWeishenhe(), tongjibean.getZong()));
		tongjibean.setShenhe1(tongjibean.jiussuan(tongjibean.getShenhe(), tongjibean.getZong()));
		tongjibean.setTongguo1(tongjibean.jiussuan(tongjibean.getTongguo(), tongjibean.getZong()));
		tongjibean.setTuihui1(tongjibean.jiussuan(tongjibean.getTuihui(), tongjibean.getZong()));
		//System.out.println(tongjibean.getZong());
		req.setAttribute("tongjibean", tongjibean);
		req.getRequestDispatcher("my/index.jsp").forward(req, resp);
	}
	
	
	// 审核多条件查询第一页
		private void shenhechaxun(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			req.setCharacterEncoding("UTF-8");
			String num = req.getParameter("num");// 查询依据的个数

			// "logic" + strId; 与或非
			// "sType" + strId; 类型
			// "q" + strId; 输入框

			// 申明
			String q0 = null;
			String q1 = null;
			String q2 = null;
			String logic1 = null;
			String logic2 = null;
			String sType0 = null;
			String sType1 = null;
			String sType2 = null;
			if (req.getParameter("q0") != null) {
				q0 = req.getParameter("q0");
				sType0 = req.getParameter("sType0");
			}
			if (req.getParameter("q1") != null) {
				q1 = req.getParameter("q1");
				logic1 = req.getParameter("logic1");
				sType1 = req.getParameter("sType1");
			}
			if (req.getParameter("q2") != null) {
				q2 = req.getParameter("q2");
				logic2 = req.getParameter("logic2");
				sType2 = req.getParameter("sType2");
			}

			DemandDao cs = new DemandDao();
			
			List<DemandBean> xx = cs.xdshPolicy(num,sType0, q0, logic1, sType1, q1, logic2, sType2, q2);
			
			req.getSession().setAttribute("xxbeans", xx);
			if (xx.size() != 0) {
					req.getRequestDispatcher("/first/Type.jsp").forward(req, resp);
			} else {
				resp.sendRedirect(req.getContextPath() + "/first/Type.jsp?status=1");
			}

		}
		
	
	/**
	 * 分类浏览，从数据库中读出，科技活动类型的集合
	 */
	private void preType(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		System.out.println("转至preType方法！");
		DemandDao demandDao = new DemandDao();
		List<String> YJLXs = demandDao.getTypeList();
		
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSONString(YJLXs));
		out.flush();
		out.close();
		
	}
	
	
	/**
	 * 第二级：学科分类，国民经济行业分类的第一级结点显示
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void getNextA(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		//@RequestMapping(value="/abc",method=RequestMethod.POST,produces = {"application/json;charset=UTF-8"});
		System.out.println("转至getNext方法！");
		DemandDao demandDao = new DemandDao();
		String type=req.getParameter("id");
		
		System.out.println("类型为:"+type);
		List<String> NEXTs=new ArrayList<String>();
		
		if(type.equals("基础研究")){
			NEXTs = demandDao.getXKFLListA();//第一级
		}else {
			NEXTs = demandDao.getXQJSYYHYListA();//第一级
		}
			
		//System.out.println(NEXTs.toString());
		
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSONString(NEXTs));
		out.flush();
		out.close();
	}
	
	
	
	/**
	 * 第三级：学科分类的第二级结点显示
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void getNextBX(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		//@RequestMapping(value="/abc",method=RequestMethod.POST,produces = {"application/json;charset=UTF-8"});
		//System.out.println("转至getNext方法！");
		DemandDao demandDao = new DemandDao();
		String type=req.getParameter("id");
		
		System.out.println("类型为:"+type);
		List<String> NEXTs=new ArrayList<String>();
		
			NEXTs = demandDao.getXKFLListB(type);//第一级
			
		//System.out.println(NEXTs.toString());
		
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSONString(NEXTs));
		out.flush();
		out.close();
	}
	
	
	/**
	 * 第四级：学科分类的第三级结点显示
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void getNextCX(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		//@RequestMapping(value="/abc",method=RequestMethod.POST,produces = {"application/json;charset=UTF-8"});
		//System.out.println("转至getNext方法！");
		DemandDao demandDao = new DemandDao();
		String type=req.getParameter("id");
		
		System.out.println("类型为:"+type);
		List<String> NEXTs=new ArrayList<String>();
		
			NEXTs = demandDao.getXKFLListC(type);//第一级
			
		//System.out.println(NEXTs.toString());
		
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSONString(NEXTs));
		out.flush();
		out.close();
	}
	
	
	
	
	
	
	/**
	 * 第三级：国民经济行业分类的第二级结点显示
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void getNextBH(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		//@RequestMapping(value="/abc",method=RequestMethod.POST,produces = {"application/json;charset=UTF-8"});
		//System.out.println("转至getNext方法！");
		DemandDao demandDao = new DemandDao();
		String type=req.getParameter("id");
		
		System.out.println("类型为:"+type);
		List<String> NEXTs=new ArrayList<String>();
		
			NEXTs = demandDao.getXQJSYYHYListB(type);//第二级
			
		//System.out.println(NEXTs.toString());
		
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSONString(NEXTs));
		out.flush();
		out.close();
	}
	
	
	/**
	 * 第四级：国民经济行业分类的第三级结点显示
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	private void getNextCH(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		//@RequestMapping(value="/abc",method=RequestMethod.POST,produces = {"application/json;charset=UTF-8"});
		//System.out.println("转至getNext方法！");
		DemandDao demandDao = new DemandDao();
		String type=req.getParameter("id");
		
		System.out.println("类型为:"+type);
		List<String> NEXTs=new ArrayList<String>();
		
			NEXTs = demandDao.getXQJSYYHYListC(type);//第三级
			
		//System.out.println(NEXTs.toString());
		
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSONString(NEXTs));
		out.flush();
		out.close();
	}
	
	
	
	/**分类查询
	 * 以列表查看需求信息
	 */
	private void getList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String NEXT=req.getParameter("id");
		//NEXT = new String(NEXT.getBytes("ISO8859-1"),"UTF-8");
		//System.out.println("夏小暑选择了"+NEXT);
		DemandDao demandDao = new DemandDao();
		
		
		List<DemandBean> demandBeans = demandDao.getList(NEXT);

			
			req.setAttribute("demandBeans", demandBeans);
			req.getRequestDispatcher("type/demandList.jsp").forward(req, resp);
	}
	private void getListAX(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String NEXT=req.getParameter("id");
		//String parent=req.getParameter("parentId");
		//NEXT = new String(NEXT.getBytes("ISO8859-1"),"UTF-8");
		//System.out.println("夏小暑选择了"+NEXT);
		DemandDao demandDao = new DemandDao();
		
		
		List<DemandBean> demandBeans = demandDao.getListAX(NEXT);

			
			req.setAttribute("demandBeans", demandBeans);
			req.getRequestDispatcher("type/demandList.jsp").forward(req, resp);
	}
	private void getListBX(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String NEXT=req.getParameter("id");
		String parent=req.getParameter("parentId");
		//NEXT = new String(NEXT.getBytes("ISO8859-1"),"UTF-8");
		//parent=new String(parent.getBytes("ISO8859-1"),"UTF-8");
		//System.out.println("夏小暑选择了"+NEXT);
		System.out.println("next:"+NEXT+"   parent:"+parent);
		
		DemandDao demandDao = new DemandDao();
		
		
		List<DemandBean> demandBeans = demandDao.getListBX(NEXT,parent);

			
			req.setAttribute("demandBeans", demandBeans);
			req.getRequestDispatcher("type/demandList.jsp").forward(req, resp);
	}
	private void getListCX(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String NEXT=req.getParameter("id");
		String parent=req.getParameter("parentId");
		//NEXT = new String(NEXT.getBytes("ISO8859-1"),"UTF-8");
		//parent=new String(parent.getBytes("ISO8859-1"),"UTF-8");
		//System.out.println("夏小暑选择了"+NEXT);
		System.out.println("next:"+NEXT+"   parent:"+parent);
		
		DemandDao demandDao = new DemandDao();
		
		
		List<DemandBean> demandBeans = demandDao.getListCX(NEXT,parent);

			
			req.setAttribute("demandBeans", demandBeans);
			req.getRequestDispatcher("type/demandList.jsp").forward(req, resp);
	}
	
	
	
	
	
	/**
	 * 国民经济行业分类
	 */
	private void getListAH(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String NEXT=req.getParameter("id");
		//String parent=req.getParameter("parentId");
		//NEXT = new String(NEXT.getBytes("ISO8859-1"),"UTF-8");
		//System.out.println("夏小暑选择了"+NEXT);
		DemandDao demandDao = new DemandDao();
		
		
		List<DemandBean> demandBeans = demandDao.getListAH(NEXT);

			
			req.setAttribute("demandBeans", demandBeans);
			req.getRequestDispatcher("type/demandList.jsp").forward(req, resp);
	}
	private void getListBH(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String NEXT=req.getParameter("id");
		String parent=req.getParameter("parentId");
		//NEXT = new String(NEXT.getBytes("ISO8859-1"),"UTF-8");
		//parent=new String(parent.getBytes("ISO8859-1"),"UTF-8");
		//System.out.println("夏小暑选择了"+NEXT);
		DemandDao demandDao = new DemandDao();
		
		
		List<DemandBean> demandBeans = demandDao.getListBH(NEXT,parent);

			
			req.setAttribute("demandBeans", demandBeans);
			req.getRequestDispatcher("type/demandList.jsp").forward(req, resp);
	}
	private void getListCH(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String NEXT=req.getParameter("id");
		String parent=req.getParameter("parentId");
		//NEXT = new String(NEXT.getBytes("ISO8859-1"),"UTF-8");
		//parent=new String(parent.getBytes("ISO8859-1"),"UTF-8");
		//System.out.println("夏小暑选择了"+NEXT);
		DemandDao demandDao = new DemandDao();
		
		
		List<DemandBean> demandBeans = demandDao.getListCH(NEXT,parent);

			
			req.setAttribute("demandBeans", demandBeans);
			req.getRequestDispatcher("type/demandList.jsp").forward(req, resp);
	}
	
	
	
	/**
	 *	添加需求时的初始页面 
	 */
	private void info(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String username=req.getParameter("username");
		AdminBean adminBean=new AdminBean();
		AdminDao adminDao=new AdminDao();
		adminBean=adminDao.getByUsername(username);
		
		try{
		CodeBean codeBean=new CodeBean();
		CodeDao codeDao=new CodeDao();
		codeBean=codeDao.getByNum(adminBean.getCode());
		String code_name=codeBean.getCode_name();
		
		DemandBean demandBean=new DemandBean();
		demandBean.setJGMC(code_name);
		demandBean.setYZBM(adminBean.getPostal());
		demandBean.setTXDZ(adminBean.getAddress());
		//需求编号，自动生成
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		String WJID = formatter.format(currentTime);
		demandBean.setWJID(WJID);
		
		//搜出一级学科分类
		FirstXDao firstXDao=new FirstXDao();
		List<FirstXBean> firstXBeans = firstXDao.getFirstList();
		
		//搜出一级国民经济分类
		FirstHDao firstHDao=new FirstHDao();
		List<FirstHBean> firstHBeans = firstHDao.getFirstList();
		
		req.setAttribute("demandBean", demandBean);
		req.setAttribute("firstXBeans", firstXBeans);
		req.setAttribute("firstHBeans", firstHBeans);
		
		req.getRequestDispatcher("my/addInfo.jsp").forward(req, resp);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 提交技术需求表，0代表审核未通过，1代表已审核通过
	 */
	private void addInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");

		String username=req.getParameter("username");
		System.out.println("用户名username:"+username);
		String WJID=req.getParameter("WJID");
		//int SFSH=Integer.parseInt(req.getParameter("SFSH"));	//0表示未通过，1表示通过，缺省取值为0
		String JGMC=req.getParameter("JGMC");
		String GLBM=req.getParameter("GLBM");
		String TXDZ=req.getParameter("TXDZ");
		String SZDY=req.getParameter("SZDY");
		String DWWZ=req.getParameter("DWWZ");
		String DZYX=req.getParameter("DZYX");
		String FRDB=req.getParameter("FRDB");
		String YZBM=req.getParameter("YZBM");
		String LXR=req.getParameter("LXR");
		String GDDH=req.getParameter("GDDH");
		String YDDH=req.getParameter("YDDH");
		String CZ=req.getParameter("CZ");
		String JGSX=req.getParameter("JGSX");
	    String JGJJ=req.getParameter("JGJJ");
		String JSXQMC=req.getParameter("JSXQMC");
		int QSXQNF = Integer.parseInt(req.getParameter("QSXQNF"));
		int JZXQNF=Integer.parseInt(req.getParameter("JZXQNF"));
		String ZDKJXQGS1="1、主要问题：<p>"+req.getParameter("ZDKJXQGS1");
		String ZDKJXQGS2="<p>2、技术关键:<p>"+req.getParameter("ZDKJXQGS2");
		String ZDKJXQGS3="<p>3、预期目标:<p>"+req.getParameter("ZDKJXQGS3");
		String ZDKJXQGS=ZDKJXQGS1+ZDKJXQGS2+ZDKJXQGS3;
		/**
		 * 多个文本框
		 */
		String GJZ="";
		String GJZ1=req.getParameter("GJZ1");
		String GJZ2=req.getParameter("GJZ2");
		String GJZ3=req.getParameter("GJZ3");
		String GJZ4=req.getParameter("GJZ4");
		String GJZ5=req.getParameter("GJZ5");
		
		GJZ=GJZ1+GJZ2+GJZ3+GJZ4+GJZ5;
		
		String NTR=req.getParameter("NTR");
		
		/**
		 * 技术需求合作模式，单选框
		 */
		String JSXQHZMS=req.getParameter("JSXQHZMS");
		String HZYXDW=req.getParameter("HZYXDW");
		
		String YJLX=req.getParameter("YJLX");
		
		/**
		 * 学科分类
		 */
		String XKFL1=req.getParameter("FirstXId");
		String XKFL2=req.getParameter("SecondXId");
		String XKFL3=req.getParameter("ThirdXId");
		
		/**
		 *  需求技术所属领域，复选框
		 */
       String[] fiel=req.getParameterValues("XQJSSSLY");
		
		String XQJSSSLY="";
		if(fiel!=null){
		for(int j=0;j<fiel.length;j++){
			XQJSSSLY+=","+fiel[j];
			}
		}
		//System.out.println("需求技术所属领域XQJSSSLY:" + XQJSSSLY);

		String QTJSMS=req.getParameter("QTJSMS");
		
		/**
		 * 国民经济行业分类
		 */
		String XQJSYYHY="";
		String XQJSYYHY1=req.getParameter("FirstHId");
		String XQJSYYHY2=req.getParameter("SecondHId");
		String XQJSYYHY3=req.getParameter("ThirdHId");
	
		
		SimpleDateFormat createDate = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		DemandBean demandBean = new DemandBean();
		DemandDao demandDao = new DemandDao();

		
		demandBean.setWJID(WJID);
		demandBean.setUsername(username);
		demandBean.setJGMC(JGMC);
		demandBean.setGLBM(GLBM);
		demandBean.setTXDZ(TXDZ);
		demandBean.setSZDY(SZDY);
		demandBean.setDWWZ(DWWZ);
		demandBean.setDZYX(DZYX);
		demandBean.setFRDB(FRDB);
		demandBean.setYZBM(YZBM);
		demandBean.setLXR(LXR);
		demandBean.setGDDH(GDDH);
		demandBean.setYDDH(YDDH);
		demandBean.setCZ(CZ);
		demandBean.setJGSX(JGSX);
		demandBean.setJGJJ(JGJJ);
		demandBean.setJSXQMC(JSXQMC);
		demandBean.setQSXQNF(QSXQNF);
		demandBean.setJZXQNF(JZXQNF);
		demandBean.setZDKJXQGS(ZDKJXQGS);
		demandBean.setGJZ(GJZ);
		demandBean.setNTR(NTR);
		demandBean.setYJLX(YJLX);
		demandBean.setXKFL1(XKFL1);	
		demandBean.setXKFL2(XKFL2);	
		demandBean.setXKFL3(XKFL3);	
		demandBean.setXQJSSSLY(XQJSSSLY);
		demandBean.setQTJSMS(QTJSMS);
		
		demandBean.setXQJSYYHY1(XQJSYYHY1);	
		demandBean.setXQJSYYHY2(XQJSYYHY2);	
		demandBean.setXQJSYYHY3(XQJSYYHY3);
		demandBean.setJSXQHZMS(JSXQHZMS);		
		demandBean.setHZYXDW(HZYXDW);
		
		demandBean.setCreateDate(createDate.format(new Date()));

		boolean flag = demandDao.check(JSXQMC);

		if (flag) {// 不存在，添加成功
			demandDao.save(demandBean);
			resp.sendRedirect("my/addInfo.jsp?status=0");
		} else {// 存在，添加失败
			resp.sendRedirect("my/addInfo.jsp?status=1");
		}
	}

	/**
	 * 以列表查看需求信息
	 */
/*	private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		DemandDao demandDao = new DemandDao();
		List<DemandBean> demandBeans = demandDao.getList();

		req.setAttribute("demandBeans", demandBeans);
		req.getRequestDispatcher("first/list.jsp").forward(req, resp);
	}
	*/
	/**
	 * 分页的列表浏览
	 */
	private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		System.out.println("转至list方法中！");
		String status=req.getParameter("status");   ////？？？？status在这里的作用是什么
		DemandDao demandDao=new DemandDao();
		int currentPage=StringUtil.StringToInt(req.getParameter("currentPage"));
		//注意此处是调用方法，而不是获取属性值（而且这个属性是adminBean里面的）——方法：获取数据表中数据总量：public int getCount(){}
		int countSize=demandDao.getCount();
		//Constants.PAGE_SIZE_1: 该常量表示：	一页多少条数据,这里size1为一页一条数据
		PagingBean pagingBean=new PagingBean(currentPage,countSize,Constants.PAGE_SIZE_5);
		List<DemandBean> demandBeans=demandDao.getListByPage(currentPage * 
				Constants.PAGE_SIZE_5,countSize);
		pagingBean.setPrefixUrl(req.getContextPath()+"/DemandServlet?method=list");
		pagingBean.setAnd(true); //true的时候是&，否是？
		req.setAttribute("demandBeans", demandBeans); 
		req.setAttribute("pagingBean", pagingBean);

		if(status!=null){
			req.getRequestDispatcher("first/list.jsp?status="+status).forward(req, resp);
		}else{
			req.getRequestDispatcher("first/list.jsp").forward(req,resp);
		}
	}
	
	
	
	/**
	 * 以列表查看需求信息
	 */
	private void list_type(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		DemandDao demandDao = new DemandDao();
		List<DemandBean> demandBeans = demandDao.getList();

		req.setAttribute("demandBeans", demandBeans);
		req.getRequestDispatcher("first/list_type.jsp").forward(req, resp);
	}
	
	
	private void listFen(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");

		String num = req.getParameter("num");// 查询依据的个数

		// "logic" + strId; 与或非
		// "sType" + strId; 类型
		// "q" + strId; 输入框

		// 申明
		String q0 = null;
		String q1 = null;
		String q2 = null;
		String logic1 = null;
		String logic2 = null;
		String sType0 = null;
		String sType1 = null;
		String sType2 = null;
		if (req.getParameter("q0") != null) {
			q0 = req.getParameter("q0");
			sType0 = req.getParameter("sType0");
		}
		if (req.getParameter("q1") != null) {
			q1 = req.getParameter("q1");
			logic1 = req.getParameter("logic1");
			sType1 = req.getParameter("sType1");
		}
		if (req.getParameter("q2") != null) {
			q2 = req.getParameter("q2");
			logic2 = req.getParameter("logic2");
			sType2 = req.getParameter("sType2");
		}

		DemandDao cs = new DemandDao();
		
		List<DemandBean> xx = cs.xdshPolicy(num,sType0, q0, logic1, sType1, q1, logic2, sType2, q2);// sType0,q0,logic1,sType1,q1,logic2,sType2,q2);值
		
		req.getSession().setAttribute("xxbeans", xx);
		if (xx.size() != 0) {
				req.getRequestDispatcher("/first/list_type.jsp").forward(req, resp);
		} else {
			resp.sendRedirect(req.getContextPath() + "/first/list_type.jsp?status=1");
		}
	}
	
	
	
	
	
	/**
	 * 以列表查看用户自己已填的需求信息
	 */
	private void myList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String username=req.getParameter("username");
		//System.out.println("转到我的需求管理Servlet中");
		DemandDao demandDao = new DemandDao();
		List<DemandBean> demandBeans = demandDao.getMyList(username);

		req.setAttribute("demandBeans", demandBeans);
		req.getRequestDispatcher("my/myList.jsp").forward(req, resp);
	}
	

	/**
	 * 根据条件，搜索
	 */
	private void selectList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String JSXQMC = req.getParameter("JSXQMC");
		DemandDao demandDao = new DemandDao();

		List<DemandBean> demandBeans = demandDao.getByName(JSXQMC);

		if (demandBeans != null) {
			req.setAttribute("demandBeans", demandBeans);
			req.getRequestDispatcher("first/list.jsp?status=0").forward(req, resp);
		} else {
			req.getRequestDispatcher("first/list.jsp?status=1").forward(req, resp);
		}
	}

	
	/**
	 * 根据条件，搜索
	 */
	private void selectDemandList(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String name = req.getParameter("name");

		DemandDao demandDao = new DemandDao();

		List<DemandBean> demandBeans = demandDao.getByName(name);

		if (demandBeans != null) {
			req.setAttribute("demandBeans", demandBeans);
			req.getRequestDispatcher("check/demandList.jsp").forward(req, resp);
		} else {
			req.getRequestDispatcher("check/demandList.jsp?status=1").forward(req, resp);
		}
	}

	/**
	 * 详情信息
	 */
	private void details(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String WJID= req.getParameter("WJID");
		//Integer.valueOf("12")
		System.out.println("WJID="+WJID);
		DemandBean demandBean = new DemandBean();
		DemandDao demandDao = new DemandDao();

		demandBean = demandDao.getById(WJID);
		req.setAttribute("demandBean", demandBean);
		
		req.getRequestDispatcher("first/details.jsp").forward(req, resp);

		
		
		 
	}
	/**
	 * 用户自己的需求问卷详情信息
	 */
	private void myDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String WJID= req.getParameter("WJID");
		DemandBean demandBean = new DemandBean();
		DemandDao demandDao = new DemandDao();

		demandBean = demandDao.getById(WJID);
		req.setAttribute("demandBean", demandBean);
		
		req.getRequestDispatcher("my/myDetails.jsp").forward(req, resp);	 
	}
	
	
	/**
	 * 更改审核的状态
	 */
	private void check(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
	
		String WJID = req.getParameter("WJID");
		int SFSH=Integer.parseInt(req.getParameter("SFSH"));
		DemandDao demandDao = new DemandDao();
		if(SFSH==1){
			demandDao.updateSFSH(WJID,SFSH,"空");
		}else{
			String V=req.getParameter("V");
			demandDao.updateSFSH(WJID,SFSH,V);
		}//成功
		
		
			req.getRequestDispatcher("check/demandDetails.jsp?status=1").forward(req, resp);//审核成功
		
	

	}
	
	/**
	 * 以列表查看需求信息，加上审核
	 */
	private void demandList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		DemandDao demandDao = new DemandDao();
		List<DemandBean> demandBeans = demandDao.getListNo();

		req.setAttribute("demandBeans", demandBeans);
		req.getRequestDispatcher("check/demandList.jsp").forward(req, resp);
	}

	/**
	 * 详情信息
	 */
	private void demandDetails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String WJID = req.getParameter("WJID");

		DemandBean demandBean = new DemandBean();
		DemandDao demandDao = new DemandDao();

		demandBean = demandDao.getById(WJID);
		req.setAttribute("demandBean", demandBean);
		
		/*int len = demandBean.getType().length();
		System.out.println("类型的种类len=" + len);
		req.setAttribute("len", len);*/
		
		req.getRequestDispatcher("check/demandDetails.jsp").forward(req, resp);
	}


}
