package com.kms.pojo;

public class Code {
	private static final String url="http://gw.api.taobao.com/router/rest";
	private static final String appkey="24537795";
	private static final String secret="79298ddcaef19d3ad57f8a65d6e9cc8f";
	private static final String SmsType="normal";
	private static final String SmsFreeSignName="ali Â Â";
	private static final String SmsTemplateCode="SMS_77400012";
	private static final String RecNum="17805420352";
	private String Extend;
	private String name_sex;
	private String time;
	private String goods_list;
	private String yan;
	public String getRecNum() {
		return RecNum;
	}
	public String getExtend() {
		return Extend;
	}
	public void setExtend(String extend) {
		Extend = extend;
	}
	public String getName_sex() {
		return name_sex;
	}
	public void setName_sex(String name_sex) {
		this.name_sex = name_sex;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getGoods_list() {
		return goods_list;
	}
	public void setGoods_list(String goods_list) {
		this.goods_list = goods_list;
	}
	public String getYan() {
		return yan;
	}
	public void setYan(String yan) {
		this.yan = yan;
	}
	public static String getUrl() {
		return url;
	}
	public static String getAppkey() {
		return appkey;
	}
	public static String getSecret() {
		return secret;
	}
	public static String getSmstype() {
		return SmsType;
	}
	public static String getSmsfreesignname() {
		return SmsFreeSignName;
	}
	public static String getSmstemplatecode() {
		return SmsTemplateCode;
	}
	
}
