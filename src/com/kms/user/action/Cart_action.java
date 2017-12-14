package com.kms.user.action;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.kms.pojo.User;
import com.kms.user.dao.Cart_dao;
import com.kms.user.pojo.ShoppingCart;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;

public class Cart_action extends ActionSupport implements ModelDriven<ShoppingCart>,SessionAware{
	
	public String AddCart() throws Exception{
		int i = 0;
		Cart_dao cart_dao = new Cart_dao();
		JSONArray jsonArray;
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		if (session.get("SessionuserID")==null){
			i=10000;
			/**
			 * cookie
			 */
			Cookie cookies[] = ServletActionContext.getRequest().getCookies();
			if (cookies == null || cookies.length < 0) {
				 // 没有cookie
				 System.out.println("there is no any cookie ..");
				 cart_dao.AddCart_Cookie( "sg" + ((Integer)shoppingCart.getGoods_id()).toString().trim() , ((Integer)shoppingCart.getCart_sum()).toString().trim());
				 System.out.println("添加成功");
			} else {
				 for (Cookie c : cookies) {
					 if(c.getName().equals(((Integer)shoppingCart.getGoods_id()).toString().trim())){
						 GoodsCartExist = true;
						 cart_dao.UpdateCart_Cookie(c, c.getValue()+((Integer)shoppingCart.getCart_sum()).toString().trim());
						 System.out.println("添加成功");
					 }
				 }
				 if(GoodsCartExist==false){
					 cart_dao.AddCart_Cookie( ((Integer)shoppingCart.getGoods_id()).toString().trim() , ((Integer)shoppingCart.getCart_sum()).toString().trim());
					 System.out.println("添加成功");
				}
			}
		}else {
			/**
			 * 数据库
			 */
			shoppingCart.setUser_id((int)session.get("SessionuserID"));
			shoppingCartlist = cart_dao.QueryCart_SQL(shoppingCart);
			for(int k=0;k<shoppingCartlist.size();k++){
				if (shoppingCartlist.get(k).getGoods_id()==shoppingCart.getGoods_id()){
					GoodsCartExist = true;
				}
			}
			if(GoodsCartExist == false){
				i = cart_dao.AddCart_SQL(shoppingCart);
				System.out.println("添加成功");
			}else{
				i = cart_dao.UpdateCart_SQL(shoppingCart);
				System.out.println("更新成功");
			}
		}
		jsonArray = JSONArray.fromObject(i);
		out.println(jsonArray);
		out.flush();
		out.close();
		return null;
	}
	public String DelCart() throws Exception{
		int i = 0;
		Cart_dao cart_dao = new Cart_dao();
		JSONArray jsonArray;
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		if (session.get("SessionuserID")==null){
			i=10000;
			/**
			 * cookie
			 */
			Cookie cookies[] = ServletActionContext.getRequest().getCookies();
			for (Cookie c : cookies){
				if(shoppingCart.getGoods_id()==Integer.parseInt(c.getName())){
					GoodsCartExist = true;
					cart_dao.DelCart_Cookie(c);
					System.out.println("删除成功");
					i=1;
				}
			}
			if(GoodsCartExist == false){
				System.out.println("删除失败");
			}	
		}else {
			/**
			 * 数据库
			 */
			shoppingCart.setUser_id((int)session.get("SessionuserID"));
			i = cart_dao.DelCart_SQL(shoppingCart);
		}
		jsonArray = JSONArray.fromObject(i);
		out.println(jsonArray);
		out.flush();
		out.close();
		return null;
	}
	
	public String QueryCart() throws Exception{
		int i = 0;
		Cart_dao cart_dao = new Cart_dao();
		JSONArray jsonArray;
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		if (session.get("SessionuserID")==null){
			i=10000;
			/**
			 * cookie
			 */
			Cookie cookies[] = ServletActionContext.getRequest().getCookies();
			shoppingCartlist=cart_dao.QueryCart_Cookie(cookies);
			jsonArray = JSONArray.fromObject(shoppingCartlist);
			System.out.println("查询成功");
		}else {
			/**
			 * 数据库
			 */
			shoppingCart.setUser_id((int)session.get("SessionuserID"));
			shoppingCartlist = cart_dao.QueryCart_SQL(shoppingCart);
			jsonArray = JSONArray.fromObject(shoppingCartlist);
			System.out.println("查询成功");
		}
		out.println(jsonArray);
		out.flush();
		out.close();
		return null;
	}
	
	
	
	private Map session;
	private ShoppingCart shoppingCart=new ShoppingCart();
	private List<ShoppingCart> shoppingCartlist = null ;
	private User user = new User();
	boolean GoodsCartExist = false;
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	public List<ShoppingCart> getShoppingCartlist() {
		return shoppingCartlist;
	}
	public void setShoppingCartlist(List<ShoppingCart> shoppingCartlist) {
		this.shoppingCartlist = shoppingCartlist;
	}	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session=arg0;
	}
	@Override
	public ShoppingCart getModel() {
		if(this.shoppingCart==null){
			this.shoppingCart=new ShoppingCart();
		}
		return this.shoppingCart;
	}
	
}
