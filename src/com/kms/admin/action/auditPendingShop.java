package com.kms.admin.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.kms.admin.dao.AdminDao;
import com.kms.pojo.Shop;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

public class auditPendingShop extends ActionSupport{
	private int status;
	private int shop_id;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public String auditPend() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		AdminDao adminDao=new AdminDao();
		int i=adminDao.auditPendingShop(status,shop_id);
		String jsonString="{\"status\":"+i+"}";
		out.print(jsonString);
		return null;
	}
}
