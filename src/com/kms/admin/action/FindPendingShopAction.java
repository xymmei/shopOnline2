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

public class FindPendingShopAction extends ActionSupport{
	private String status;
	
	

	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public String findPend() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		AdminDao adminDao=new AdminDao();
		System.out.println(status);
		List<Shop> lrs=new ArrayList<Shop>();
		lrs=adminDao.findPendingShop(Integer.parseInt(status));
		System.out.print(lrs.get(0).getShop_name());
		JSONArray jsonArray=JSONArray.fromObject(lrs);
		out.print(jsonArray);
		return null;
	}
}
