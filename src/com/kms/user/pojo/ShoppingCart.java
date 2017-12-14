package com.kms.user.pojo;

import com.kms.pojo.Goods;

public class ShoppingCart extends Goods{
	private int shop_id;
	private String shop_name;
	private int user_id;
	private int Cart_sum;
	private double cart_money;
	
	
	public double getCart_money() {
		return cart_money;
	}
	public void setCart_money(double cart_money) {
		this.cart_money = cart_money;
	}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getCart_sum() {
		return Cart_sum;
	}
	public void setCart_sum(int cart_sum) {
		Cart_sum = cart_sum;
	}
}
