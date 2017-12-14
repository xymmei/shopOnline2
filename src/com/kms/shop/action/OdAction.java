package com.kms.shop.action;

import java.io.PrintWriter;
import java.time.YearMonth;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.kms.shop.dao.OrdersDao;
import com.kms.shop.pojo.Business;
import com.kms.shop.pojo.OdPrice;
import com.kms.shop.pojo.Orders;
import com.kms.shop.pojo.Orders_sum;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

public class OdAction extends ActionSupport {
	private Business bs = new Business();
	private Orders or = new Orders();
	private List<Orders> olrs;
	private List<Orders_sum> sumlrs;
	private String sMonth;
	private String eMonth;
	private int shop_id;
	private List<OdPrice> odlrs;
	private int orders_status;
	private int orders_id;

	public int getOrders_id() {
		return orders_id;
	}

	public void setOrders_id(int orders_id) {
		this.orders_id = orders_id;
	}

	public Business getBs() {
		return bs;
	}

	public void setBs(Business bs) {
		this.bs = bs;
	}

	public Orders getOr() {
		return or;
	}

	public void setOd(Orders or) {
		this.or = or;
	}

	public List<Orders> getOlrs() {
		return olrs;
	}

	public void setOlrs(List<Orders> olrs) {
		this.olrs = olrs;
	}

	public String getsMonth() {
		return sMonth;
	}

	public void setsMonth(String sMonth) {
		this.sMonth = sMonth;
	}

	public String geteMonth() {
		return eMonth;
	}

	public void seteMonth(String eMonth) {
		this.eMonth = eMonth;
	}

	public int getShop_id() {
		return shop_id;
	}

	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}

	public List<OdPrice> getOdlrs() {
		return odlrs;
	}

	public void setOdlrs(List<OdPrice> odlrs) {
		this.odlrs = odlrs;
	}

	public int getOrders_status() {
		return orders_status;
	}

	public void setOrders_status(int orders_status) {
		this.orders_status = orders_status;
	}

	// 查询某月到某月的成功的销售额
	public String ordersCount() throws Exception {
		int j = 0;
		JSONArray jsonArray;
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		if (session.getAttribute("SessionuserID") == null) {
			j = 10000;
			jsonArray = JSONArray.fromObject(j);
		} else {
			OrdersDao od = new OrdersDao();
			System.out.println(sMonth + eMonth);
			YearMonth y1 = null, y2 = null;
			sumlrs = od.Orders_sum(y1.parse(sMonth), y2.parse(eMonth));
			jsonArray = JSONArray.fromObject(sumlrs);
		}
		out.print(jsonArray);
		out.flush();
		out.close();
		return null;
	}

	// 查询总的销售额和订单数
	public String ordersPrice() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		OrdersDao od = new OrdersDao();
		odlrs = od.sumMoney(2, shop_id);
		System.out.println(odlrs.get(0).getOrdersPrice());
		JSONArray jsonArray = JSONArray.fromObject(odlrs);
		out.print(jsonArray);
		out.flush();
		out.close();
		return null;
	}

	// 订单详情订单内商品数量，发货状况
	public String chod() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		OrdersDao od = new OrdersDao();
		if (orders_status > 2) {
			olrs = od.checkAll(shop_id);
		} else {
			olrs = od.check(orders_status, shop_id);// 页面要传回订单号
		}
		JSONArray jsonArray = JSONArray.fromObject(olrs);
		out.print(jsonArray);
		out.flush();
		out.close();
		return null;
	}

	/**
	 * 订单状态更改,返回更改的status
	 * 
	 * @return
	 * @throws Exception
	 */
	public String upod() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		OrdersDao od = new OrdersDao();
		int status = od.edit(orders_id);// 订单id
		JSONArray jsonArray = JSONArray.fromObject(status);
		out.print(jsonArray);
		out.flush();
		out.close();
		return null;
	}
}
