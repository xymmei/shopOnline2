package com.kms.dao;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Driver;

public class BaseDao {
	private static final String url = "jdbc:mysql://www.keyling.cn/mall?useUnicode=true&characterEncoding=utf-8";
	private static final String username = "root";
	private static final String passwd = "WANGqilin123.";
	private static final String name = "com.mysql.jdbc.Driver";
	static {
		try {
			Class.forName(name);
		} catch (ClassNotFoundException e) {
			System.err.println("¼ÓÔØÇý¶¯Ê§°Ü" + e.getMessage());
		}
	}
	public Connection getConnection(){
		try {
			Connection connection = (Connection) DriverManager.getConnection(url,username,passwd);
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
