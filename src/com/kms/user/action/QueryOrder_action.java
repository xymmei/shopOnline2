package com.kms.user.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.New;
import javax.management.Query;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.objectweb.asm.tree.IntInsnNode;

import com.kms.pojo.Orders;
import com.kms.pojo.User;
import com.kms.user.dao.Order_dao;
import com.kms.user.dao.User_dao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;

public class QueryOrder_action extends ActionSupport implements ModelDriven<Orders>,SessionAware{
	
	
	public String All() throws Exception {
		int i = 0;
		JSONArray jsonArray;
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		if (session.get("SessionuserID")==null){
			i=10000;
			jsonArray = JSONArray.fromObject(i);
		}else{
			Order_dao order_dao = new Order_dao();
			user = (User) session.get("SessionUser");
			System.out.println("user:"+user.getUser_id());
			ordersList = order_dao.QueryOrder_All(user);
			jsonArray = JSONArray.fromObject(ordersList);
		}
		out.println(jsonArray);
		out.flush();
		out.close();
		return null;
	}
	
	public String NoExpress() throws Exception {
		int i = 0;
		JSONArray jsonArray;
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		if (session.get("SessionuserID")==null){
			i=10000;
			jsonArray = JSONArray.fromObject(i);
		}else{
			Order_dao order_dao = new Order_dao();
			user = (User) session.get("SessionUser");
			System.out.println("user:"+user.getUser_id());
			ordersList = new ArrayList<Orders>();
			ordersList=order_dao.QueryOrder_NoExpress(user);
			System.out.println("ordersList:"+ordersList.get(0).getOrders_id());
			System.out.println("ordersList.Size:"+ordersList.size());
			jsonArray = JSONArray.fromObject(ordersList);
		}
		System.out.println("jsonArray.size():");
		out.println(jsonArray);
		out.flush();
		out.close();
		return null;
	}


	

	public String NoSign() throws Exception {
		int i = 0;
		JSONArray jsonArray;
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		if (session.get("SessionuserID")==null){
			i=10000;
			jsonArray = JSONArray.fromObject(i);
		}else{
			Order_dao order_dao = new Order_dao();
			user = (User) session.get("SessionUser");
			System.out.println("user:"+user.getUser_id());
			ordersList = order_dao.QueryOrder_NoSign(user);
			jsonArray = JSONArray.fromObject(ordersList);
		}
		out.println(jsonArray);
		out.flush();
		out.close();
		return null;
	}
	
	public String Finish() throws Exception {
		int i = 0;
		JSONArray jsonArray;
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		if (session.get("SessionuserID")==null){
			i=10000;
			jsonArray = JSONArray.fromObject(i);
		}else{
			Order_dao order_dao = new Order_dao();
			user = (User) session.get("SessionUser");
			System.out.println("user:"+user.getUser_id());
			ordersList = order_dao.QueryOrder_Finish(user);
			jsonArray = JSONArray.fromObject(ordersList);
		}
		out.println(jsonArray);
		out.flush();
		out.close();
		return null;
	}
	/**
	 * 签收订单
	 * @return
	 * @throws Exception
	 */
	public String Signing() throws Exception {
		int i = 0;
		JSONArray jsonArray;
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		if (session.get("SessionuserID")==null){
			i=10000;
		}else{
			Order_dao order_dao = new Order_dao();
			i = order_dao.Order_Signing(orders);
		}
		jsonArray = JSONArray.fromObject(i);
		out.println(jsonArray);
		out.flush();
		out.close();
		return null;
	}
	/**
	 * 取消订单
	 * @return
	 * @throws Exception
	 */
	public String Cancel() throws Exception {
		int i = 0;
		JSONArray jsonArray;
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		if (session.get("SessionuserID")==null){
			i=10000;
		}else{
			Order_dao order_dao = new Order_dao();
			i = order_dao.Order_Cancel(orders);
		}
		jsonArray = JSONArray.fromObject(i);
		out.println(jsonArray);
		out.flush();
		out.close();
		return null;
	}
	/**
	 * 添加订单
	 */
	public String Create() throws Exception{
		int i = 0;
		JSONArray jsonArray;
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		if (session.get("SessionuserID")==null){
			i=10000;
		}else{
			Order_dao order_dao = new Order_dao();
			String time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
			orders.setOrders_date(time);
			orders.setUser_id((int)session.get("SessionuserID"));
			System.out.println("orders.getUser_id(create):"+orders.getUser_id());
			/**
			 * 查询货物单价
			 */
			orders.setOrders_price(orders.getOrders_goods_sum()*orders.getGoods_price());
			i = order_dao.Order_CreateOrders(orders);
			if (i==1){
				System.out.println("订单创建成功");
				i=order_dao.Order_CreateOrdersGoods(orders);
				i=order_dao.Order_UpdateGoods(orders);
			}else{
				System.out.println("订单创建失败");
			}
		}
		jsonArray = JSONArray.fromObject(i);
		out.println(jsonArray);
		out.flush();
		out.close();
		return null;
	}
	
	private User user = new User();
	private Map session = new HashMap();
	private Orders orders = new Orders();
	private List<Orders> ordersList;
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Orders getOrders() {
		return orders;
	}
	public void setOrders(Orders orders) {
		this.orders = orders;
	}
	public List<Orders> getOrdersList() {
		return ordersList;
	}
	public void setOrdersList(List<Orders> ordersList) {
		this.ordersList = ordersList;
	}
	@Override
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}
	@Override
	public Orders getModel() {
		// TODO Auto-generated method stub
		if(this.orders==null){
			this.orders=new Orders();
		}
		return this.orders;
	}
}
