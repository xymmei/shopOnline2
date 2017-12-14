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
import com.kms.pojo.User;
import com.kms.user.dao.User_dao;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONArray;

public class TopGoodsAction extends ActionSupport implements SessionAware{
	private Map session;
	public String topGoods() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		HomeDao homeDao=new HomeDao();
		List<Goods>lrs=null;
		System.out.println("TOPGoods");
		lrs=homeDao.getTopGoods();
		System.out.println("TOPGoods");
		System.out.println(lrs.get(0).getGoods_name());
		JSONArray jsonArray=JSONArray.fromObject(lrs);
		out.print(jsonArray);
//		User user = new User();
//		User_dao user_dao=new User_dao();
//		user.setUser_id((int)session.get("SessionuserID"));
//		int HaveShop = user_dao.CheckShop(user);
//		System.out.println(HaveShop);
//		session.put("SessionUserHaveShop", HaveShop);
		return null;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
}
