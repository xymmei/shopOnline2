package com.kms.user.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.kms.dao.BaseDao;
import com.kms.pojo.User;

public class User_Check_dao extends BaseDao{
	public int Check_User_name(User user) {
		int i=0;
		//List<User> lrs = null;
		String Check_User_name_Sql="SELECT user_name from users "
				+ "where user_name = ? ";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		User checkuser =new User();
		try {
			connection=super.getConnection();
			preparedStatement=connection.prepareStatement(Check_User_name_Sql);
			preparedStatement.setObject(1,user.getUser_name());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()==false){
				i = 1;
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
	
	public int Check_User_phone(User user) {
		int i=0;
		//List<User> lrs = null;
		String Check_User_name_Sql="SELECT user_phone from users "
				+ "where user_phone = ? ";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		User checkuser =new User();
		try {
			connection=super.getConnection();
			preparedStatement=connection.prepareStatement(Check_User_name_Sql);
			preparedStatement.setObject(1,user.getUser_phone());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()==false){
				i = 1;
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
	
	public int Check_User_idcard(User user) {
		int i=0;
		//List<User> lrs = null;
		String Check_User_idcard_Sql="SELECT user_idcard from users "
				+ "where user_idcard = ? ";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		User checkuser =new User();
		try {
			connection=super.getConnection();
			preparedStatement=connection.prepareStatement(Check_User_idcard_Sql);
			preparedStatement.setObject(1,user.getUser_idcard());
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()==false){
				i = 1;
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
