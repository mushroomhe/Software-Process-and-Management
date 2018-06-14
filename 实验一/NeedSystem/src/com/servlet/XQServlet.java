package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.bean.SecondHBean;
import com.bean.SecondXBean;
import com.bean.ThirdHBean;
import com.bean.ThirdXBean;
import com.dao.SecondHDao;
import com.dao.SecondXDao;
import com.dao.ThirdHDao;
import com.dao.ThirdXDao;
import com.util.StringUtil;

/**
 * Servlet implementation class XQServlet
 * 学科分类和国民经济分类的三级下拉菜单
 */
@WebServlet("/XQServlet")
public class XQServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest req,HttpServletResponse resp)
			throws ServletException,IOException{
				//设置字符集编码：避免获取的参数呈现乱码
				req.setCharacterEncoding("utf-8");	
				String method=req.getParameter("method");
				/**
				 * 学科分类
				 */
				if("getSecondX".equals(method)){
					getSecondX(req,resp);
				}else if("getThirdX".equals(method)){
					getThirdX(req,resp);
				}
				/**
				 * 国民经济行业分类
				 */
				else if("getSecondH".equals(method)){
					getSecondH(req,resp);
				}else if("getThirdH".equals(method)){
					getThirdH(req,resp);
				}
				
				
		}
	
	private void getSecondH(HttpServletRequest req, HttpServletResponse resp) throws IOException { 
		int parentId = StringUtil.StringToInt(req.getParameter("id"));
		SecondHDao secondHDao = new SecondHDao();
		System.out.println("转到getSecondH");
		List<SecondHBean> secondList = secondHDao.getSecondList(parentId);
		//System.out.println(cityList.get(0).getName());
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSONString(secondList));
		out.flush();
		out.close();

		}
	private void getThirdH(HttpServletRequest req, HttpServletResponse resp) throws IOException { 
		int parentId = StringUtil.StringToInt(req.getParameter("id"));
		ThirdHDao thirdHDao = new ThirdHDao();
		System.out.println("转到getThirdH");
		List<ThirdHBean> thirdList = thirdHDao.getThirdList(parentId);
		//System.out.println(cityList.get(0).getName());
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSONString(thirdList));
		out.flush();
		out.close();

		}
	
	
	
	private void getSecondX(HttpServletRequest req, HttpServletResponse resp) throws IOException { 
		int parentId = StringUtil.StringToInt(req.getParameter("id"));
		SecondXDao secondXDao = new SecondXDao();
		System.out.println("转到getSecondX");
		List<SecondXBean> secondList = secondXDao.getSecondList(parentId);
		//System.out.println(cityList.get(0).getName());
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSONString(secondList));
		out.flush();
		out.close();

		}
	private void getThirdX(HttpServletRequest req, HttpServletResponse resp) throws IOException { 
		int parentId = StringUtil.StringToInt(req.getParameter("id"));
		ThirdXDao thirdXDao = new ThirdXDao();
		System.out.println("转到getThirdX");
		List<ThirdXBean> thirdList = thirdXDao.getThirdList(parentId);
		//System.out.println(cityList.get(0).getName());
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSONString(thirdList));
		out.flush();
		out.close();

		}


}
