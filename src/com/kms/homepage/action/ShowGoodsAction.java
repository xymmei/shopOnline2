package com.kms.homepage.action;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.kms.homepage.dao.HomeDao;
import com.kms.pojo.Goods;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

public class ShowGoodsAction extends ActionSupport implements SessionAware{
	private Map session;
	private int goods_id;
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public String showGoods() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		HomeDao homeDao=new HomeDao();
		List<Goods>lrs=null;
		lrs=homeDao.getGoodsMessage(goods_id);
		System.out.println(lrs.get(0).getGoods_name());
		JSONArray jsonArray=JSONArray.fromObject(lrs);
		out.print(jsonArray);
		return null;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
}
