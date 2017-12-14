package com.kms.user.action;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.kms.pojo.User;
import com.kms.user.dao.Cart_dao;
import com.kms.user.dao.User_Check_dao;
import com.kms.user.dao.User_dao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class User_action extends ActionSupport implements ModelDriven<User>,SessionAware{
	private String code;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String reg() throws Exception {
		int i=0;
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		User_dao user_dao = new User_dao();
		System.out.println("user.getUser_imgFileFileName():"+user.getUser_imgFileFileName());
		String right=(String) session.get("yan");
		System.out.println(right);
		if(code.equals(right)){
			i=user_dao.reg(this.user);
			if(i!=1){
				System.out.println("璇ユ墜鏈哄凡琚敞鍐�");
			}else{
				System.out.println("regok"+user.getUser_phone());
			}
		}else{
			i=2;
		}	
		JSONArray jsonArray = JSONArray.fromObject(i);
		out.print(jsonArray);
		return null;
	}
	
	public String login() throws Exception {
		User_dao user_dao = new User_dao();
		Cart_dao cart_dao = new Cart_dao();
		int i=user_dao.login(this.user);
		int shopid = 0;
		if(i==-1){
			System.out.println("鐢ㄦ埛涓嶅瓨鍦�");
		}else if(i==0){
			System.out.println("瀵嗙爜閿欒");
		}else{
			user.setUser_id(i);
			user = user_dao.QueryUser(this.user);
			this.session.put("SessionuserID",user.getUser_id());
			this.session.put("SessionUser", user);
			int HaveShop = user_dao.CheckShop(user);
			if(HaveShop==-1){
				this.session.put("SessionUserHaveShop", HaveShop);
				this.session.put("ShopID" , shopid);
			}else{
				this.session.put("SessionUserHaveShop", HaveShop/10);
				shopid=HaveShop%10;
				System.out.println("ShopID:"+ shopid);
				this.session.put("ShopID" , shopid);
			}
			System.out.println("SeessionShopID:"+session.get("ShopID"));
			System.out.println("loginok"+user.getUser_name());
			/**
			 * 璇诲彇cookie,骞跺啓鍏ユ暟鎹簱
			 */
			/*Cookie cookies[] = ServletActionContext.getRequest().getCookies();
			cart_dao.Cookie_WriteTo_SQL(user, cookies);
			System.out.println("鍐欏叆鎴愬姛");*/
		}
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = JSONArray.fromObject(i);
		out.println(jsonArray);
		out.flush();
		out.close();
		return null;
	}
	
	public String logout() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		//request.getSession().invalidate();
		request.getSession().removeAttribute("SessionUser");
		request.getSession().removeAttribute("SessionuserID");
		request.getSession().removeAttribute("SessionUserHaveShop");
		response.sendRedirect("login.jsp");
		return null;
	}
	
	public String Query() throws Exception {
		int i = 0;
		JSONArray jsonArray;
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		if (session.get("SessionuserID")==null){
			i=10000;
			jsonArray = JSONArray.fromObject(i);
		}else{
			User_dao user_dao = new User_dao();
			user.setUser_id((int)this.session.get("SessionuserID"));
			user = user_dao.QueryUser(this.user);
			jsonArray = JSONArray.fromObject(user);
		}
		out.println(jsonArray);
		out.flush();
		out.close();
		return null;
	}
	public String update() throws Exception {
		System.out.println("setUser_imgFileFileName"+user.getUser_imgFileFileName());
		int i = 0;
		JSONArray jsonArray;
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		if (session.get("SessionuserID")==null){
			i=10000;
		}else{
			User_dao user_dao = new User_dao();
			/**
			 * 鍒ょ┖checkUser Session鍙栧��
			 */
			User checkUser = (User) session.get("SessionUser");
			if("".equals(user.getUser_name())||user.getUser_name()==null){
				user.setUser_name(checkUser.getUser_name());
			}
			if("".equals(user.getUser_address())||user.getUser_address()==null){
				user.setUser_address(checkUser.getUser_address());
			}
			if("".equals(user.getUser_password())||user.getUser_password()==null){
				user.setUser_password(checkUser.getUser_password());
			}
			if("".equals(user.getUser_phone())||user.getUser_phone()==null){
				user.setUser_phone(checkUser.getUser_phone());
			}	
			System.out.println("user.getUser_name()"+user.getUser_name());
			System.out.println("user.getUser_address()"+user.getUser_address());
			System.out.println("user.setUser_password()"+user.getUser_password());
			System.out.println("user.setUser_phone()"+user.getUser_phone());
			user.setUser_id((int)this.session.get("SessionuserID"));
			i = user_dao.update(this.user);
			user = user_dao.QueryUser(user);
			this.session.put("SessionUser", user);
		};
		jsonArray = JSONArray.fromObject(i);
		out.println(jsonArray);
		out.flush();
		out.close();
		return null;
	}

	public String Check_User() throws Exception{
		int i=0;
		User_Check_dao user_Check_dao = new User_Check_dao();
		if (user.getUser_name()!=null){
			i = user_Check_dao.Check_User_name(user);
		}
		if (user.getUser_phone()!=null){
			i = user_Check_dao.Check_User_phone(user);
		}
		if (user.getUser_idcard()!=null){
			i = user_Check_dao.Check_User_idcard(user);
		}
		HttpServletResponse response = (HttpServletResponse) ActionContext.getContext().get(ServletActionContext.HTTP_RESPONSE); 
		response.setCharacterEncoding("UTF-8"); 
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = JSONArray.fromObject(i);
		out.println(jsonArray);
		out.flush();
		out.close();
		return null;
	}
	
	
	
	private Map session;
	private User user = new User();
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public User getModel() {
		if(this.user==null){
			this.user=new User();
		}
		return this.user;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session=session;
	}
}
