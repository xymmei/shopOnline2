package com.kms.admin.action;

import java.io.PrintWriter;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.kms.admin.dao.AdminDao;
import com.kms.admin.pojo.CountByDate;
import com.kms.admin.pojo.PriceByDate;
import com.kms.admin.pojo.Screen;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;

public class ScreenAction extends ActionSupport {
	private String single;
	private String startMonth;
	private String endMonth;
	
	public String getSingle() {
		return single;
	}

	public void setSingle(String single) {
		this.single = single;
	}

	public String getStartMonth() {
		return startMonth;
	}

	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}

	public String getEndMonth() {
		return endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

	public String screen() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		AdminDao adminDao=new AdminDao();
		System.out.println(single);
		System.out.println(startMonth);
		System.out.println(endMonth);
		YearMonth yearMonth1 = null,yearMonth2 = null;
		if(Integer.parseInt(single)==0){
			List<PriceByDate> lrs=new ArrayList<PriceByDate>();
			lrs=adminDao.countShopByDate(yearMonth1.parse(startMonth),yearMonth2.parse(endMonth));
			JSONArray jsonArray = JSONArray.fromObject(lrs); 
			out.print(jsonArray);
		}else{
			List<PriceByDate> lrs=new ArrayList<PriceByDate>();
			lrs=adminDao.countSalePriceByDate(yearMonth1.parse(startMonth),yearMonth2.parse(endMonth));
			JSONArray jsonArray = JSONArray.fromObject(lrs); 
			out.print(jsonArray);
		}
		return null;
	}
}
