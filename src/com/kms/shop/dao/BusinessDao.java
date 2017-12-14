package com.kms.shop.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.kms.shop.pojo.Business;

public class BusinessDao extends BaseDao{
	/**
	 * 检验登陆用户，与数据库比对。
	 * @param bs
	 * @return
	 */
	public int chlogin(Business bs){
		int i = 0;
		String sql = "SELECT 	  `user_name`,  `user_password` 	"
				+ "FROM `mall`.`users`  "
				+ "WHERE `user_name`= '"+bs.getUser_name()
				+"' AND `user_password`= '"+bs.getUser_password()+"'";	
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
				i++;
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return i;
	}
	/**
	 * 找到用户名对应的店铺id，若没有则返回-1。
	 * @param bs
	 * @return
	 */
	public int Idcheck(Business bs){
		int i=-1;
		String sql = "select shop_id from mall.shop where user_id="+
		"(select user_id from mall.users where user_name='"+bs.getUser_name()+"')";
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while(resultSet.next()){
				i=resultSet.getInt("shop_id");
			}
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return i;
	}
}
