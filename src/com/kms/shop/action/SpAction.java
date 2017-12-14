package com.kms.shop.action;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.kms.shop.dao.GoodsDao;
import com.kms.shop.pojo.Goods;
import com.kms.shop.pojo.Shop;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;

public class SpAction extends ActionSupport implements ModelDriven<Goods>, SessionAware {

	public String chsp() throws Exception {
		GoodsDao GD = new GoodsDao();
		System.out.println(goods.getShop_id());
		lrs = GD.lrs(goods.getShop_id());// id需要页面返回值，再来更改
		System.out.println(lrs.get(1).getGoods_id());
		JSONArray jsonArray = JSONArray.fromObject(lrs); 
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
		response.setCharacterEncoding("UTF-8"); 
		response.getWriter().print(jsonArray); 
		return null; 
	}

	public String Del() throws Exception {
		GoodsDao goodsDao = new GoodsDao();
		System.out.println("goods:" + goods.getGoods_describe());
		System.out.println("status"+goods.getGoods_status());
		System.out.println("id"+goods.getGoods_id());
		int status;
		if(this.goods.getGoods_status()==1){
			status = goodsDao.Del(this.goods);
		}
		else{
			status = goodsDao.Up(this.goods);
		}
		System.out.println("s::"+status);
		System.out.println("this.goods.getGoods_id():" + this.goods.getGoods_id());
		JSONArray jsonArray = JSONArray.fromObject(status); 
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
		response.setCharacterEncoding("UTF-8"); 
		System.out.println(jsonArray);
		PrintWriter out=response.getWriter();
		out.print(jsonArray);
		out.flush();
		out.close();
		/*if (i == 0) {
			System.out.println("error");
			return null;
		} else {
			System.out.println("goods.getGoods_id(delok):" + goods.getGoods_id());
			return this.chsp();
		}*/
		return null;
	}

	/*public String BeforeUpdate() throws Exception {
		Goods aGoods;
		GoodsDao rd = new GoodsDao();
		System.out.println("BeforeUpdate-Goods(action):" + goods.getGoods_id());
		Goods BeforeUpdateGoods = rd.lrs(this.goods);
		System.out.println("BeforeUpdate-goods:" + BeforeUpdateGoods.getGoods_id());
		this.session.put("GoodsDelete", BeforeUpdateGoods);// Test
		aGoods = (Goods) this.session.get("GoodsDelete");
		System.out.println(111111);
		System.out.println(aGoods.getGoods_name());
		
		JSONArray jsonArray = JSONArray.fromObject(lrs); 
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
		response.setCharacterEncoding("UTF-8"); 
		response.getWriter().print(jsonArray); 
		return "BeforeUpdate";
	}*/

	public String Update() throws Exception {
		GoodsDao rd = new GoodsDao();
		System.out.println("here");
		int i = rd.Update(goods);
		JSONArray jsonArray = JSONArray.fromObject(i); 
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
		response.setCharacterEncoding("UTF-8"); 
		response.getWriter().print(jsonArray);
		PrintWriter out=response.getWriter();
		out.print(jsonArray);
		out.flush();
		out.close();
		return null;
		/*if (i == 0) {
			System.out.println("error");
			return ERROR;
		} else {
			System.out.println("updateok:" + goods.getGoods_id());
			return this.chsp();
		}*/
	}
	
	
	public String insert() throws Exception {
		GoodsDao gd = new GoodsDao();
		int i = gd.in(goods);
		JSONArray jsonArray = JSONArray.fromObject(i); 
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out=response.getWriter();
		out.print(jsonArray);
		out.flush();
		out.close();
		return null;
	}
	
	private Map<String, Object> session;
	private Goods goods = new Goods();
	private List<Goods> lrs;

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public List<Goods> getLrs() {
		return lrs;
	}

	public void setLrs(List<Goods> lrs) {
		this.lrs = lrs;
	}

	@Override
	public Goods getModel() {
		if (this.goods == null) {
			this.goods = new Goods();
		}
		return this.goods;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}

}
