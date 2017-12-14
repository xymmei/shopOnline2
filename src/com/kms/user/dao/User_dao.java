package com.kms.user.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import com.kms.dao.BaseDao;
import com.kms.pojo.Status;
import com.kms.pojo.User;

public class User_dao extends BaseDao{
	Status status = new Status();
	public int reg(User user) throws IOException {
		int i=0;
		//List<User> lrs = null;
		
		String sqlString="INSERT INTO users(user_name,user_phone,user_password,"
				+ "user_realname,user_address,user_sex,user_img,user_idcard) "
				+ "values ( ? , ? , ? , ? , ? , ? , ? , ? ) ;";
		
		/*String sqlString="INSERT INTO users(user_name,user_phone,user_password,"
				+ "user_realname,user_address,user_sex,user_idcard) "
				+ "values ( ? , ? , ? , ? , ? , ? , ? ) ;";*/
		
		//ServletContext servletContext=ServletActionContext.getServletContext();

		Connection connection=null;
		PreparedStatement preparedStatement=null;
		System.out.println("user.getUser_name():"+user.getUser_name());
		try {
			connection=super.getConnection();
			preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setObject(1,user.getUser_name());
			preparedStatement.setObject(2,user.getUser_phone());
			preparedStatement.setObject(3,user.getUser_password());
			preparedStatement.setObject(4,user.getUser_realname());
			preparedStatement.setObject(5,user.getUser_address());
			preparedStatement.setObject(6,user.getUser_sex());
			preparedStatement.setObject(7,user.getUser_img());
			preparedStatement.setObject(8,user.getUser_idcard());
			i = preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally {
			try {
				/*out.close();
				in.close();*/
				preparedStatement.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return i;
	}
	
	public int login(User user) {
		int i=0;
		//List<User> lrs = null;
		String sqlString="SELECT user_id,user_phone,user_password from users "
				+ "where user_phone = ? ";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		User checkuser =new User();
		try {
			connection=super.getConnection();
			preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setObject(1,user.getUser_phone());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()==false){
				i = -1;
			}else{
				checkuser.setUser_id(resultSet.getInt("user_id"));
				checkuser.setUser_phone(resultSet.getString("user_phone"));
				checkuser.setUser_password(resultSet.getString("user_password"));
				/**
				 * 璐﹀彿瀵嗙爜鍖归厤
				 */
				if (checkuser.getUser_phone().equals(user.getUser_phone())&&
						checkuser.getUser_password().equals(user.getUser_password())){
					i = checkuser.getUser_id();
				}else{
					i = 0; 
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("NULL:"+e.getMessage());
		}finally {
			try {
				resultSet.close();
				connection.close();
				preparedStatement.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return i;
	}
	
	public User QueryUser(User user) {
		int i=0;
		User queriedUser = null;
		//List<User> lrs = null;
		String sqlString="SELECT user_id,user_name,user_phone,user_password,"
				+ "user_realname,user_address,user_sex,user_img,user_idcard "
				+ " from users "
				+ " where user_id = ? ";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		//User checkuser =new User();
		try {
			connection=super.getConnection();
			preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setObject(1,user.getUser_id());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()==false){
				i = -1;
			}else{
				queriedUser = new User();
				queriedUser.setUser_id(resultSet.getInt("user_id"));
				queriedUser.setUser_name(resultSet.getString("user_name"));
				queriedUser.setUser_password(resultSet.getString("user_password"));
				queriedUser.setUser_phone(resultSet.getString("user_phone"));
				queriedUser.setUser_realname(resultSet.getString("user_realname"));
				queriedUser.setUser_idcard(resultSet.getString("user_idcard"));
				queriedUser.setUser_address(resultSet.getString("user_address"));
				queriedUser.setUser_sex(resultSet.getString("user_sex"));
				queriedUser.setUser_img(resultSet.getString("user_img"));
				/**
				 * 璐﹀彿瀵嗙爜鍖归厤
				 */
				/*if (checkuser.getUser_name().equals(user.getUser_name())&&
						checkuser.getUser_password().equals(user.getUser_password())){
					i = checkuser.getUser_id();
				}else{
					i = status.getNo_Pass(); 
				}*/
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("NULL:"+e.getMessage());
		}finally {
			try {
				resultSet.close();
				connection.close();
				preparedStatement.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return queriedUser;
	}
	
	public int update(User user){
		int i=0;
		String sqlString="update users set user_name= ? ,user_phone = ? , user_password=?,user_address=?,user_img = ?"
				+ " where user_id= ? ;";
		
		/*try {
			ServletContext servletContext=ServletActionContext.getServletContext();  
	        String path=servletContext.getRealPath("/image/"+user.getUser_imgFileFileName());
			user.setUser_imgFileFileName(((Integer)user.getUser_id()).toString()+user.getUser_imgFileFileName());
			System.out.println("setUser_imgFile"+user.getUser_imgFile());
			System.out.println("setUser_imgFileFileName"+user.getUser_imgFileFileName());
			//String path="C:\\images\\user\\"+user.getUser_imgFileFileName();
		    user.setUser_img(path);//鏂囦欢鏈�缁堣涓婁紶鍒扮殑璺緞
			FileOutputStream out=new FileOutputStream(path);
			FileInputStream in=new FileInputStream(user.getUser_imgFile());
			byte[]buffer=new byte[1024];
			int len=0 ;
			while((len=in.read(buffer))!=-1){
				System.out.println("len:"+len);
				out.write(buffer,0,len);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return i;
		}*/
		
		
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		try {
			connection=super.getConnection();
			preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setObject(1, user.getUser_name());
			preparedStatement.setObject(2, user.getUser_phone());
			preparedStatement.setObject(3, user.getUser_password());
			preparedStatement.setObject(4, user.getUser_address());
			preparedStatement.setObject(6, user.getUser_id());
			preparedStatement.setObject(5, user.getUser_img());
			i = preparedStatement.executeUpdate();
			if(i==0){
				System.out.println("鏇存柊澶辫触锛岀敤鎴峰悕宸茶娉ㄥ唽");
			}else{
				System.out.println("鏇存柊鎴愬姛");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally {
			try {
				preparedStatement.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return i;
	}
	
	public int CheckShop(User user) {
		int i=0;
		User CheckUser = null;
		//List<User> lrs = null;
		String sqlString="select shop_id,shop_status from shop where user_id = ? ";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		//User checkuser =new User();
		try {
			connection=super.getConnection();
			preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setObject(1,user.getUser_id());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()==false){
				i = -1;
			}else if(resultSet.getInt("shop_status")==0){
				i = resultSet.getInt("shop_id");
			}else if(resultSet.getInt("shop_status")==1){
				i = 1*10+resultSet.getInt("shop_id");
			}else{
				i = 2*10+resultSet.getInt("shop_id");
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("NULL:"+e.getMessage());
		}finally {
			try {
				resultSet.close();
				connection.close();
				preparedStatement.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return i;
	}
}
