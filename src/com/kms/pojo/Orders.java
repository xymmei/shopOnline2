package com.kms.pojo;

import java.util.Date;

public class Orders extends Goods{
	private int orders_id;
	private int user_id;
	private String orders_date;
	private double orders_price;
	private int orders_status;
	private int orders_code;
	private int orders_goods_sum;
	
	
	public int getOrders_goods_sum() {
		return orders_goods_sum;
	}
	public void setOrders_goods_sum(int orders_goods_sum) {
		this.orders_goods_sum = orders_goods_sum;
	}
	public int getOrders_code() {
		return orders_code;
	}
	public void setOrders_code(int orders_code) {
		this.orders_code = orders_code;
	}
	public int getOrders_status() {
		return orders_status;
	}
	public void setOrders_status(int orders_status) {
		this.orders_status = orders_status;
	}
	public int getOrders_id() {
		return orders_id;
	}
	public void setOrders_id(int orders_id) {
		this.orders_id = orders_id;
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
	
}
