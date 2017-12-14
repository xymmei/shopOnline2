package com.kms.user.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;


import com.kms.pojo.Code;


public class CreateRegCodeAction extends ActionSupport implements SessionAware{
	private Map session;
	private String user_phone;
	
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String createRegCode() throws Exception {
		HttpServletRequest request=ServletActionContext.getRequest();
		HttpServletResponse response=ServletActionContext.getResponse();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		Code code =new Code();
		Random random=new Random(new Date().getTime());
		String yan="";
		yan=yan+(random.nextInt(8999)+1000);
		code.setYan(yan);
		String time=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		code.setTime(time);
		System.out.println(time);
		String json="{\"regCode\":\""+code.getYan()+"\"}";
		System.out.println(json);
		System.out.println(user_phone);
		TaobaoClient client = new DefaultTaobaoClient(code.getUrl(),code.getAppkey(), code.getSecret()); 
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest(); 
		req.setExtend(code.getExtend()); 
		req.setSmsType(code.getSmstype()); 
		req.setSmsFreeSignName(code.getSmsfreesignname()); 
		req.setSmsParamString(json); 
		req.setRecNum(user_phone);
		req.setSmsTemplateCode(code.getSmstemplatecode());
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req); 
		System.out.println(rsp.getBody()); 
		System.out.println(rsp.isSuccess());
		if (rsp.isSuccess()) {
			out.print(1);
			session.put("yan", yan);
		}else{
			out.print(0);
		}
		
		
		return null;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

}
