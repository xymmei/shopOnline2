package com.kms.homepage.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.kms.homepage.dao.HomeDao;
import com.kms.pojo.Goods;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

public class ClassifyAction extends ActionSupport{
	private String goods_type;

	public String getGoods_type() {
		return goods_type;
	}

	public void setGoods_type(String goods_type) {
		this.goods_type = goods_type;
	}
	
	public List<Goods> classify() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		HomeDao homeDao=new HomeDao();
		List<Goods>lrs=null;
		lrs=homeDao.getClassifyGoods(goods_type);
		System.out.println(lrs.get(0).getGoods_name());
		JSONArray jsonArray=JSONArray.fromObject(lrs);
		out.print(jsonArray);
		return null;
	}
}
