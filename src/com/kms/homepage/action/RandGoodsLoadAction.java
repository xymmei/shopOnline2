package com.kms.homepage.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.kms.homepage.dao.HomeDao;
import com.kms.pojo.Goods;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;

public class RandGoodsLoadAction extends ActionSupport {
	
	public String randGoodsLoad() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		HomeDao homeDao=new HomeDao();
		List<Goods>lrs=null;
		lrs=homeDao.getRandGoods();
		JSONArray jsonArray=JSONArray.fromObject(lrs);
		out.print(jsonArray);
		return null;
	}
}
