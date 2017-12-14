package com.kms.pojo;

import java.io.File;

public class User {
	private int user_id;
	private String user_name;
	private String user_phone;
	private String user_password;
	private String user_realname;
	private String user_address;
	private String user_sex;
	private String user_idcard;
	private String user_img;
	
	
	
	
	private File   user_imgFile;              //文件
	private String user_imgFileFileName;      //名称
	private String user_imgFileContentType;  //文件类型
	
	
	
	public String getUser_imgFileContentType() {
		return user_imgFileContentType;
	}
	public void setUser_imgFileContentType(String user_imgFileContentType) {
		this.user_imgFileContentType = user_imgFileContentType;
	}
	public File getUser_imgFile() {
		return user_imgFile;
	}
	public void setUser_imgFile(File user_imgFile) {
		this.user_imgFile = user_imgFile;
	}
	public String getUser_imgFileFileName() {
		return user_imgFileFileName;
	}
	public void setUser_imgFileFileName(String user_imgFileFileName) {
		this.user_imgFileFileName = user_imgFileFileName;
	}
	
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_realname() {
		return user_realname;
	}
	public void setUser_realname(String user_realname) {
		this.user_realname = user_realname;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public String getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(String user_sex) {
		this.user_sex = user_sex;
	}
	public String getUser_img() {
		return user_img;
	}
	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}
	public String getUser_idcard() {
		return user_idcard;
	}
	public void setUser_idcard(String user_idcard) {
		this.user_idcard = user_idcard;
	}
	
}
