package com.kms.shop.pojo;

public class Shop {
	private int shop_id;//在个人信息页面中shop_id不能被更改，要设置
	private String shop_name;
	private int user_id;//在个人信息页面中shop_id不能被更改，要设置
	private String shop_type;
	private String shop_describe;
	private String shop_address;
	private String shop_phone;
	private String user_phone;
	private String shop_img;
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
	//这个user_id要从用户后台获取
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getShop_type() {
		return shop_type;
	}
	public void setShop_type(String shop_type) {
		this.shop_type = shop_type;
	}
	public String getShop_describe() {
		return shop_describe;
	}
	public void setShop_describe(String shop_describe) {
		this.shop_describe = shop_describe;
	}
	public String getShop_address() {
		return shop_address;
	}
	public void setShop_address(String shop_address) {
		this.shop_address = shop_address;
	}
	public String getShop_phone() {
		return shop_phone;
	}
	public void setShop_phone(String shop_phone) {
		this.shop_phone = shop_phone;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getShop_img() {
		return shop_img;
	}
	public void setShop_img(String shop_img) {
		this.shop_img = shop_img;
	}
	
}
