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
import com.bean.CityBean;
import com.dao.CityDao;
import com.util.StringUtil;

/**
 * Servlet implementation class CityServlet
 */
@WebServlet("/CityServlet")
public class CityServlet extends HttpServlet {
	protected void service(HttpServletRequest req,HttpServletResponse resp)
			throws ServletException,IOException{
				//设置字符集编码：避免获取的参数呈现乱码
				req.setCharacterEncoding("utf-8");	
				String method=req.getParameter("method");
				if("getCity".equals(method)){
					getCity(req,resp);
				}
		}
	
	
	
	
	private void getCity(HttpServletRequest req, HttpServletResponse resp) throws IOException { 
		int parentId = StringUtil.StringToInt(req.getParameter("id"));
		CityDao cityDao = new CityDao();
		System.out.println("转到getCity");
		List<CityBean> cityList = cityDao.getCityList(parentId);
		//System.out.println(cityList.get(0).getName());
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print(JSON.toJSONString(cityList));
		out.flush();
		out.close();

		}
	

}
