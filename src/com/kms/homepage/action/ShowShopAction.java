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

public class ShowShopAction extends ActionSupport{
	private int shop_id;
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	
	public String showShop() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		HomeDao homeDao=new HomeDao();
		List<Shop>lrs=null;
		lrs=homeDao.getShopMessage(shop_id);
		System.out.println(lrs.get(0).getShop_name());
		JSONArray jsonArray=JSONArray.fromObject(lrs);
		out.print(jsonArray);
		return null;
	}
}
