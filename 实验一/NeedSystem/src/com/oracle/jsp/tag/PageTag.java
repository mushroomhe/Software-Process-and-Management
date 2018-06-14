package com.oracle.jsp.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.bean.PagingBean;


public class PageTag extends SimpleTagSupport {
	private PagingBean pagingBean;
	private HttpServletRequest request;

//	
//	JSP �Զ����ǩ
//	�Զ����ǩ���û������JSP����Ԫ�ء���JSPҳ�����һ���Զ����ǩʱ����ת��Ϊservlet����ǩת��Ϊ�Ա� ��Ϊtag handler�Ķ���Ĳ���������servletִ��ʱWeb container������Щ������
//	JSP��ǩ��չ�������㴴���µı�ǩ���ҿ���ֱ�Ӳ��뵽һ��JSPҳ�档 JSP 2.0�淶������Simple Tag Handlers����д��Щ�Զ����ǡ�
//	����Լ̳�SimpleTagSupport�ಢ��д��doTag()����������һ����򵥵��Զ����ǩ��
	@Override
	public void doTag() throws JspException,IOException{
		StringBuffer sb=new StringBuffer();
		if(pagingBean!=null){
//			���嵼�����ӵĲ����ء�������ĵ����С�ǰ�󡱰�ť����Ӧ�ð����ŵ� <nav> Ԫ���С�
			sb.append("<nav><ul class='pagination'>");
			//������һҳ����ҳ
			if(pagingBean.getCurrentPage()<=0){
				//��ǰҳΪ��һҳ
				//����ҳ��ť����һҳ ��ťΪ������״̬���������Ч
				sb.append("<li class='disabled'><a>��ҳ</a></li>");
				sb.append("<li class='disabled'><a href='#'>��һҳ</a></li>");
			}else{
				//��ǰҳ���ǵ�һҳ������ҳ����һҳΪ����״̬���������Ч
				sb.append("<li><a href='").append(pagingBean.getPrefixUrl())
				.append("' aria-label='Previous'><span aria-hidden='true'>��ҳ</span></a></li>");
				sb.append("<li><a href='").append(pagingBean.getPrefixUrl()).append(pagingBean.isAnd()?"&":"?")
				.append("currentPage=").append(pagingBean.getCurrentPage()-1).append("'>��һҳ</a></li>");
			}
				//������һҳ��βҳ	
				if(pagingBean.getCurrentPage()>=(pagingBean.getPageCount()-1)){
					//��ǰҳ�����һҳ����һҳ��βҳ��ť������״̬���������Ч
					sb.append("<li class='disabled'><a>��һҳ</a></li>");
					sb.append("<li class='disabled'><a>βҳ</a></li>");
				}else{
					//��ǰҳ�������һҳ
					sb.append("<li><a href='").append(pagingBean.getPrefixUrl()).append(pagingBean.isAnd() ? "&" : "?")
					.append("currentPage=").append(pagingBean.getCurrentPage() + 1).append("'>��һҳ</a></li>");
					
					sb.append("<li><a href='").append(pagingBean.getPrefixUrl()).append(pagingBean.isAnd() ? "&" : "?")
					.append("currentPage=").append(pagingBean.getPageCount() - 1)
					.append("' aria-label='Previous'><span aria-hidden='true'>βҳ</span></a></li>");
				}
				sb.append("<li><a href='#'");
				sb.append("<span>");
				sb.append(pagingBean.getCurrentPage() + 1);
				sb.append("/");
				sb.append(pagingBean.getPageCount());
				sb.append("</span>");
				sb.append("</a></li>");
				sb.append("</ul></nav>"); 
				getJspContext().getOut().write(sb.toString());
			
		}
	}

	public PagingBean getPagingBean(){
		return pagingBean;
	}
	
	public void setPagingBean(PagingBean pagingBean){
		this.pagingBean=pagingBean;
	}
	
	public HttpServletRequest getRequest(){
		return request;
	}

	public void setRequest(HttpServletRequest request)
	{
		this.request=request;
	}
}
