package com.kms.homepage.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.kms.homepage.dao.HomeDao;
import com.kms.pojo.Shop;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

public class TopShopAction extends ActionSupport{
	
	public String topShops() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		HomeDao homeDao=new HomeDao();
		List<Shop>lrs=null;
		lrs=homeDao.getTopShops();
		JSONArray jsonArray=JSONArray.fromObject(lrs);
		out.print(jsonArray);
		return null;
	}
}
