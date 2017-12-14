package com.kms.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.New;
import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.kms.admin.dao.AdminDao;
import com.kms.pojo.Admin;
import com.kms.pojo.Status;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import org.apache.struts2.json.annotations.JSON;//��
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class LoginAction extends ActionSupport implements ModelDriven<Admin>,SessionAware{
	Admin admin = new Admin();
	private  Map session;
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	/*
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 * getModel ����������д
	 */
	@Override
		public Admin getModel() {
			if(this.admin==null){
				this.admin=new Admin();
			}
			return admin;
		}
	/*
	 * ��¼������Ӧ��action������null
	 */
	public String login() throws IOException {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		//���������̨�����
		System.out.println("loginUser:"+admin.getAdmin_name());
		System.out.println("loginPasswd:"+admin.getAdmin_password());
		AdminDao adminDao=new AdminDao();//�������ݿ⣬����AdminDao��checkAdmin������У��������û���
		
		int i=adminDao.checkAdmin(admin);//����0,1 0-������ 1-��ȷ��������session��
		
		if(i==1){
//			 ActionContext actionContext = ActionContext.getContext();
//			 Map session = actionContext.getSession();
//			 session.put("admin_name",admin.getAdmin_name());
//			 session.put("admin_password", admin.getAdmin_password());			
			session.put("admin_name",admin.getAdmin_name());
			session.put("admin_password", admin.getAdmin_password());
			System.out.println(admin.getAdmin_name());
			System.out.println(admin.getAdmin_password());
		}
		List<Status> lrs=new ArrayList<Status>();
		Status loginStatus=new Status();
		loginStatus.setLoginStatus(i);
		lrs.add(loginStatus);
		
		System.out.println(lrs.get(0).getLoginStatus());
		JSONArray jsonArray = JSONArray.fromObject(lrs); 
		out.println(jsonArray);
		out.flush();
		out.close();

		return null;		
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
	
	
}
