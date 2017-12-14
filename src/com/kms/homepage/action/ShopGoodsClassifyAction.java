package com.kms.homepage.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.kms.homepage.dao.HomeDao;
import com.kms.pojo.Goods;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

public class ShopGoodsClassifyAction extends ActionSupport{
	private int shop_id;
	private String goods_type;
	
	public int getShop_id() {
		return shop_id;
	}

	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}

	public String getGoods_type() {
		return goods_type;
	}

	public void setGoods_type(String goods_type) {
		this.goods_type = goods_type;
	}

	public String goodsClassify() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		HomeDao homeDao=new HomeDao();
		List<Goods>lrs=null;
		lrs=homeDao.getShopGoodsByType(shop_id, goods_type);
		System.out.println(lrs.get(0).getGoods_name());
		JSONArray jsonArray=JSONArray.fromObject(lrs);
		out.print(jsonArray);
		return null;
	}
}
