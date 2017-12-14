package com.kms.shop.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.kms.pojo.User;
import com.kms.shop.dao.ShopDao;
import com.kms.shop.pojo.Shop;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;

public class SqAction extends ActionSupport implements ModelDriven<Shop> {
	User u = new User();
	Shop shop = new Shop();
	HttpServletRequest request = ServletActionContext.getRequest();
	HttpSession session = request.getSession();
	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public String shenqing() throws Exception {
		ShopDao sd = new ShopDao();
		int i = sd.check_name(shop);
		System.out.println(i);
		if (i>0){
			JSONArray jsonArray = JSONArray.fromObject(-1); 
			HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
			response.setCharacterEncoding("UTF-8"); 
			response.getWriter().print(jsonArray); 
		}
		else {
			u = (User)session.getAttribute("SessionUser");
			shop.setUser_id(u.getUser_id());
			shop.setUser_phone(u.getUser_phone());
			int j = sd.zhuce(shop);
			JSONArray jsonArray = JSONArray.fromObject(j); 
			HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
			response.setCharacterEncoding("UTF-8"); 
			response.getWriter().print(jsonArray);
		}
		return null;
	}

	@Override
	public Shop getModel() {
		if (this.shop == null) {
			shop = new Shop();
		}
		return this.shop;
	}
}
