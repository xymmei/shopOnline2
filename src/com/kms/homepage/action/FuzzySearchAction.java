package com.kms.homepage.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.kms.homepage.dao.HomeDao;
import com.kms.pojo.Goods;
import com.kms.pojo.Shop;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

public class FuzzySearchAction extends ActionSupport{
	private String keywords;
	
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	private int status;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public String fuzzySearch() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		HomeDao homeDao=new HomeDao();
		System.out.println(keywords);
		System.out.println(status);
		String string ="%";
		for(int i=0;i<keywords.length();i++){
			string=string+keywords.charAt(i)+'%';
		}
		System.out.println(string);
		if(status==1){
		List<Goods>lrs=null;
		lrs=homeDao.getFuzzySearchGoods(string);
		System.out.println(lrs.get(0).getGoods_name());
		JSONArray jsonArray=JSONArray.fromObject(lrs);
		out.print(jsonArray);
		}else{
			List<Shop>lrsList=null;
			lrsList=homeDao.getFuzzySearchShop(string);
			System.out.println(lrsList.get(0).getShop_name());
			JSONArray jsonArray=JSONArray.fromObject(lrsList);
			out.print(jsonArray);
		}
		return null;
	}

}
