package com.kms.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;


public class LogoutAction extends ActionSupport{
	 
	public String logout() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		request.getSession().removeAttribute("admin_name");
		request.getSession().removeAttribute("admin_password");
		response.sendRedirect("login.jsp");
		return null;
	}
	
	
	
}
