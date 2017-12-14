package com.kms.shop.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.kms.shop.pojo.Shop;

import freemarker.core.ReturnInstruction.Return;

public class ShopDao extends BaseDao {
	/**
	 * 检测店铺用户名是否被使用，若被使用则返回大于0的数，未使用返回0
	 * 
	 * @param shop
	 * @return
	 */
	public int check_name(Shop shop) {
		int i = 0;
		String sql = "select shop_name from mall.shop";
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				if (shop.getShop_name().equals(resultSet.getString("shop_name")))
					i++;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
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
	 * 店铺注册
	 * 
	 * @param shop
	 * @return
	 * @throws IOException 
	 */
	//user_phone和id都是从session获取
	public int zhuce(Shop shop) throws IOException {
		int i = 0;
		String sql = "insert into mall.shop(shop_name , user_id , shop_type , shop_describe , shop_address"
				+ ", shop_phone , user_phone , shop_status , shop_date ) values  "
				+ " (? , ? , ? , ? , ? , ? , ? , ? , ?  )";
		/*String sql = "insert into mall.shop(shop_name , user_id  , shop_type , shop_describe , shop_address"
				+ ", shop_phone , user_phone , shop_status , shop_date , shop_img ) values  "
				+ " (? , ? , ? , ? , ? , ? , ? , ? ,  ? , ?  )";*/
		//ServletContext servletContext=ServletActionContext.getServletContext();
		//System.out.println("servletContext:"+servletContext.toString());
		/*String path= "C:\\images\\"+shop.getShop_id();//文件最终要上传到的路径
	    shop.setShop_img(path);//文件最终要上传到的路径
		FileOutputStream out=new FileOutputStream(path);
		FileInputStream in=new FileInputStream(shop.getShop_img());
		//System.out.println("Dao_imageFile:"+shop.getShop_img().toString());
	
		byte[]buffer=new byte[1024];
		int len=0;
		while((len=in.read(buffer))!=-1){
			System.out.println("len:"+len);
			out.write(buffer,0,len);
		}*/
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setObject(1, shop.getShop_name());
			preparedStatement.setObject(2, shop.getUser_id());
			preparedStatement.setObject(3, shop.getShop_type());
			preparedStatement.setObject(4, shop.getShop_describe());
			preparedStatement.setObject(5, shop.getShop_address());
			preparedStatement.setObject(6, shop.getShop_phone());
			preparedStatement.setObject(7, shop.getUser_phone());
			preparedStatement.setObject(8, 0);
			//preparedStatement.setObject(9, shop.getShop_img());
			String time=new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			preparedStatement.setObject(9, time);
			i = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				preparedStatement.close();
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
	 * 查询店铺信息。
	 * 
	 * @param id
	 * @return
	 */
	public List<Shop> chshop(int id) {
		List<Shop> lrs = null;
		String sql = "SELECT   `shop_id` , `shop_name` , " + " `shop_type`,  `shop_describe`, `shop_address`,  "
				+ "`shop_phone`, `user_phone`,  `shop_img`,  `shop_status`  `shop_date` "
				+ "FROM  `mall`.`shop` WHERE `shop_id` = " + id;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			lrs = new ArrayList<Shop>();
			while (resultSet.next()) {
				Shop shop = new Shop();
				// role.setNm(resultSet.getObject("nm") == null ? "" :
				// resultSet.getString("nm"));
				shop.setShop_id(resultSet.getInt("shop_id"));
				shop.setShop_address(resultSet.getString("shop_address"));
				shop.setShop_describe(resultSet.getString("shop_describe"));
				shop.setShop_img(resultSet.getString("shop_img"));
				shop.setShop_name(resultSet.getString("shop_name"));
				shop.setShop_phone(resultSet.getString("shop_phone"));
				shop.setShop_type(resultSet.getString("shop_type"));
				shop.setUser_phone(resultSet.getString("user_phone"));
				lrs.add(shop);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
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
		return lrs;
	}

	/**
	 * 对该shop变量进行编辑修改
	 * 
	 * @param shop
	 * @return shop
	 */
	public int edit(Shop shop) {
		int i = 0;
		String sql = "UPDATE   `mall`.`shop` SET  `shop_name` =  ? ,  `shop_type` =  ? ,"
				+ "  `shop_describe` =  ? ,  `shop_address` =  ? , "
				+ " `shop_phone` =  ? ,  `user_phone` =  ?   " + "  WHERE `shop_id` = ?  ";
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			statement.setObject(1, shop.getShop_name());
			statement.setObject(2, shop.getShop_type());
			statement.setObject(3, shop.getShop_describe());
			statement.setObject(4, shop.getShop_address());
			statement.setObject(5, shop.getShop_phone());
			statement.setObject(6, shop.getUser_phone());
			statement.setObject(7, shop.getShop_id());
			//statement.setObject(7, shop.getShop_img());
			i = statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
		}
		return i;
	}

	public Shop getShop(int shop_id) {
		String sqlString = "SELECT shop.shop_id, shop.shop_name, shop.shop_type, shop.shop_describe, "
				+ "shop.shop_address, shop.shop_phone, shop.user_phone FROM mall.shop where shop_id = "+shop_id;
		Shop shop = new Shop();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sqlString);
			while (resultSet.next()) {
				shop.setShop_id(resultSet.getInt("shop_id"));
				shop.setShop_address(resultSet.getString("shop_address"));
				shop.setShop_describe(resultSet.getString("shop_describe"));
				//shop.setShop_img(resultSet.getString("shop_img"));
				shop.setShop_name(resultSet.getString("shop_name"));
				shop.setShop_phone(resultSet.getString("shop_phone"));
				shop.setShop_type(resultSet.getString("shop_type"));
				shop.setUser_phone(resultSet.getString("user_phone"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
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
		return shop;

	}

/*	*//**
	 * 找到一个shop_id对应的变量
	 * 
	 * @param shop_id
	 * @return shop_id对应的一个shop变量
	 *//*
	public Shop find(int shop_id) {
		Shop shop = null;
		String sql = "select shop_id , shop_name, shop_type , shop_describe , shop_address, shop_phone , user_phone	"
				+ ",	shop_img from mall.shop where shop_id= ?";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			statement.setObject(1, shop_id);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				shop = new Shop();
				shop.setShop_id(resultSet.getInt("shop_id"));
				shop.setShop_address(resultSet.getString("shop_address"));
				shop.setShop_describe(resultSet.getString("shop_describe"));
				shop.setShop_img(resultSet.getString("shop_img"));
				shop.setShop_name(resultSet.getString("shop_name"));
				shop.setShop_phone(resultSet.getString("shop_phone"));
				shop.setShop_type(resultSet.getString("shop_type"));
				shop.setUser_phone(resultSet.getString("user_phone"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
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
		return shop;
	}*/
}
