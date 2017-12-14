package com.kms.admin.action;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.kms.admin.dao.AdminDao;
import com.opensymphony.xwork2.ActionSupport;

public class LoadAction extends ActionSupport implements SessionAware{
	private Map session;
	public String load() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8"); 
		System.out.println("load");
		PrintWriter out=response.getWriter();
		String admin_name=(String) session.get("admin_name");
		System.out.println(admin_name);
		if(admin_name==null){
			System.out.println("1");
//			request.getSession().invalidate();
			out.print(10000);
			System.out.println(123);
			return null;
		}else{
		AdminDao adminDao=new AdminDao();
		int user_count=adminDao.countUser();
		int shop_count=adminDao.countShop();
		double saleprice_count=adminDao.countSalePrice();
		String jsonString="{\"user_count\":"+user_count+",\"shop_count\":"+shop_count+",\"saleprice_count\":"+saleprice_count+" }";		
		out.write(jsonString);
//		}
		return null;
		}
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
}
