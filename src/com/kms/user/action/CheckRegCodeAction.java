package com.kms.user.action;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class CheckRegCodeAction extends ActionSupport implements SessionAware{
	private String code;
	private Map session;
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String checkRegCode() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		String right=(String) session.get("yan");
		System.out.println(right);
		if(code.equals(right)){
			out.print(1);
		}else{
			out.print(0);
		}
		return null;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
}
