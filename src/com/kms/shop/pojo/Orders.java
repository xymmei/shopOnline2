package com.kms.shop.pojo;

public class Orders {
	private int orders_id;
	private String user_name;
	//private int goods_id;
	private int orders_goods_sum;
	private int user_id;
	private String orders_date;
	private double orders_price;
	private int orders_status;
	private int orders_code;
	private double goods_price;
	private String goods_name;
	
	public int getOrders_id() {
		return orders_id;
	}

	public void setOrders_id(int orders_id) {
		this.orders_id = orders_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getOrders_goods_sum() {
		return orders_goods_sum;
	}

	public void setOrders_goods_sum(int orders_goods_sum) {
		this.orders_goods_sum = orders_goods_sum;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getOrders_date() {
		return orders_date;
	}

	public void setOrders_date(String orders_date) {
		this.orders_date = orders_date;
	}

	public double getOrders_price() {
		return orders_price;
	}

	public void setOrders_price(double orders_price) {
		this.orders_price = orders_price;
	}

	public int getOrders_status() {
		return orders_status;
	}

	public void setOrders_status(int orders_status) {
		this.orders_status = orders_status;
	}

	public int getOrders_code() {
		return orders_code;
	}

	public void setOrders_code(int orders_code) {
		this.orders_code = orders_code;
	}

	public double getGoods_price() {
		return goods_price;
	}

	public void setGoods_price(double goods_price) {
		this.goods_price = goods_price;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

}
