package com.kms.shop.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.kms.shop.dao.ShopDao;
import com.kms.shop.pojo.Shop;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;

public class MsAction extends ActionSupport implements ModelDriven<Shop> {
	HttpServletRequest req = ServletActionContext.getRequest();
	HttpSession session = req.getSession();
	private Shop shop = new Shop();
	private List<Shop> lrs;

	public List<Shop> getLrs() {
		return lrs;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public String chms() throws Exception {
		int j = 0;
		System.out.println("ID:"+8);
		JSONArray jsonArray;
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext()
				.get(ServletActionContext.HTTP_RESPONSE);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		System.out.println("ID:"+8);
		System.out.println((int)session.getAttribute("SessionuserID"));
		if (session.getAttribute("SessionuserID") == null) {
			j = 10000;
			jsonArray = JSONArray.fromObject(j);
		} else {
			System.out.println("ID:"+18);
			ShopDao sd = new ShopDao();
//			System.out.println((int) session.getAttribute("SessionshopID"));
//			lrs = sd.chshop((int) session.getAttribute("SessionshopID"));// c传入一个用户ID
			lrs = sd.chshop(shop.getShop_id());
			System.out.println(lrs.get(0).getShop_address());
			for (int i = 0; i < lrs.size(); i++) {
				System.out.println(lrs.get(i).getShop_name());
			}

			jsonArray = JSONArray.fromObject(lrs);
		}
		out.println(jsonArray);
		out.flush();
		out.close();
		return null;
	}

	/*
	 * public String find() throws Exception { ShopDao sd = new ShopDao();
	 * System.out.println(shop.getShop_id());
	 * System.out.println(shop.getShop_name()); shop =
	 * sd.find(shop.getShop_id());
	 * System.out.println("shop_name:"+shop.getShop_name());
	 * System.out.println("shop_address:"+shop.getShop_address());
	 * System.out.println("shop_userphone:"+shop.getUser_phone());
	 * HttpServletResponse response = (HttpServletResponse)
	 * ActionContext.getContext() .get(ServletActionContext.HTTP_RESPONSE);
	 * response.setCharacterEncoding("UTF-8"); PrintWriter out =
	 * response.getWriter(); JSONArray jsonArray = JSONArray.fromObject(shop);
	 * out.println(jsonArray); out.flush(); out.close(); return null; }
	 */

	public String edit() throws Exception {
		ShopDao sd = new ShopDao();
		JSONArray jsonArray;
		System.out.println("id"+shop.getShop_id());
		System.out.println("name"+shop.getShop_name());
		System.out.println("address"+shop.getShop_address());
		System.out.println("uphone"+shop.getUser_phone());
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext()
				.get(ServletActionContext.HTTP_RESPONSE);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		Shop sp = new Shop();
		sp = sd.getShop(shop.getShop_id());
		System.out.println("sname"+sp.getShop_name());
		shop.setShop_phone(sp.getShop_phone());
		shop.setUser_phone(sp.getUser_phone());
		int i = sd.edit(shop);// 这里需要页面返回店铺id----shop_id
		System.out.println("z状态："+i);
		jsonArray = JSONArray.fromObject(i);
		out.println(jsonArray);
		out.flush();
		out.close();
		return null;
		/*
		 * System.out.println(i); if (i == 0) return ERROR; else { return
		 * this.chms(); }
		 */
	}

	public Shop getModel() {
		if (this.shop == null) {
			this.shop = new Shop();
		}
		return this.shop;
	}

}
