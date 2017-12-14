package com.kms.user.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kms.dao.BaseDao;
import com.kms.pojo.Orders;
import com.kms.pojo.User;
import com.mysql.jdbc.Connection;

public class Order_dao extends BaseDao{
	public List<Orders> QueryOrder_All(User user){
		List<Orders> User_OrderList = null;
		String sqlString="select s.shop_name,o.*,og.orders_goods_sum,g.* from shop s,orders o,orders_goods og,goods g where o.user_id=? and o.orders_id=og.orders_id and og.goods_id=g.goods_id and g.shop_id=s.shop_id  ";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		System.out.println("user.getUser_name():"+user.getUser_name());
		try {
			connection=super.getConnection();
			preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setObject(1,user.getUser_id());
			resultSet = preparedStatement.executeQuery();
			User_OrderList=new ArrayList<Orders>();
			while(resultSet.next()){
				Orders orders =new Orders();
				orders.setOrders_id(resultSet.getInt("orders_id"));
				orders.setUser_id(resultSet.getInt("user_id"));
				orders.setOrders_date(resultSet.getDate("orders_date").toString());
				orders.setOrders_price(resultSet.getDouble("orders_price"));
				orders.setOrders_status(resultSet .getInt("orders_status"));
				orders.setOrders_code(resultSet.getInt("orders_code"));
				orders.setOrders_goods_sum(resultSet.getInt("orders_goods_sum"));
				orders.setGoods_id(resultSet.getInt("goods_id"));
				orders.setGoods_describe(resultSet.getString("goods_describe"));
				orders.setGoods_img(resultSet.getString("goods_img"));
				orders.setGoods_name(resultSet.getString("goods_name"));
				orders.setGoods_price(resultSet.getDouble("goods_price"));
				orders.setGoods_sale(resultSet.getInt("goods_sale"));
				orders.setGoods_status(resultSet.getInt("goods_status"));
				orders.setGoods_sum(resultSet.getInt("goods_sum"));
				orders.setGoods_type(resultSet.getString("goods_type"));
				orders.setShop_id(resultSet.getInt("shop_id"));
				orders.setShop_name(resultSet.getString("shop_name"));
				User_OrderList.add(orders);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			User_OrderList=null;
		}finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return User_OrderList;
	}
	
	public List<Orders> QueryOrder_NoExpress(User user){
		List<Orders> User_OrderList_NoEpress = null;
		String sqlString="select s.shop_name,o.*,og.orders_goods_sum,g.* from shop s,orders o,orders_goods og,goods g where o.orders_status=0 and o.user_id= ? and o.orders_id=og.orders_id and og.goods_id=g.goods_id and g.shop_id=s.shop_id ";
/*		SELECT orders_id,user_id,orders_date,orders_price,orders_status,orders_code
		FROM orders where user_id = 3 and orders_status=0 order by orders_date desc ;*/
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		System.out.println("user.getUser_name():"+user.getUser_name());
		try {
			connection=super.getConnection();
			preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setObject(1,user.getUser_id());
			resultSet = preparedStatement.executeQuery();
			User_OrderList_NoEpress=new ArrayList<Orders>();
			while(resultSet.next()){
				Orders orders =new Orders();
				orders.setOrders_id(resultSet.getInt("orders_id"));
				orders.setUser_id(resultSet.getInt("user_id"));
				orders.setOrders_date(resultSet.getDate("orders_date").toString());
				orders.setOrders_price(resultSet.getDouble("orders_price"));
				orders.setOrders_status(resultSet .getInt("orders_status"));
				orders.setOrders_code(resultSet.getInt("orders_code"));
				orders.setOrders_goods_sum(resultSet.getInt("orders_goods_sum"));
				orders.setGoods_id(resultSet.getInt("goods_id"));
				orders.setGoods_describe(resultSet.getString("goods_describe"));
				orders.setGoods_img(resultSet.getString("goods_img"));
				orders.setGoods_name(resultSet.getString("goods_name"));
				orders.setGoods_price(resultSet.getDouble("goods_price"));
				orders.setGoods_sale(resultSet.getInt("goods_sale"));
				orders.setGoods_status(resultSet.getInt("goods_status"));
				orders.setGoods_sum(resultSet.getInt("goods_sum"));
				orders.setGoods_type(resultSet.getString("goods_type"));
				orders.setShop_id(resultSet.getInt("shop_id"));
				orders.setShop_name(resultSet.getString("shop_name"));
				User_OrderList_NoEpress.add(orders);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			User_OrderList_NoEpress=null;
		}finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return User_OrderList_NoEpress;
	}
	
	public List<Orders> QueryOrder_NoSign(User user){
		List<Orders> User_OrderList_NoSign = null;
		String sqlString="select s.shop_name,o.*,og.orders_goods_sum,g.* from shop s,orders o,orders_goods og,goods g where o.orders_status=1 and o.user_id = ? and o.orders_id=og.orders_id and og.goods_id=g.goods_id and g.shop_id=s.shop_id ";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		System.out.println("user.getUser_name():"+user.getUser_name());
		try {
			connection=super.getConnection();
			preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setObject(1,user.getUser_id());
			resultSet = preparedStatement.executeQuery();
			User_OrderList_NoSign=new ArrayList<Orders>();
			while(resultSet.next()){
				Orders orders =new Orders();
				orders.setOrders_id(resultSet.getInt("orders_id"));
				orders.setUser_id(resultSet.getInt("user_id"));
				orders.setOrders_date(resultSet.getDate("orders_date").toString());
				orders.setOrders_price(resultSet.getDouble("orders_price"));
				orders.setOrders_status(resultSet .getInt("orders_status"));
				orders.setOrders_code(resultSet.getInt("orders_code"));
				orders.setOrders_goods_sum(resultSet.getInt("orders_goods_sum"));
				orders.setGoods_id(resultSet.getInt("goods_id"));
				orders.setGoods_describe(resultSet.getString("goods_describe"));
				orders.setGoods_img(resultSet.getString("goods_img"));
				orders.setGoods_name(resultSet.getString("goods_name"));
				orders.setGoods_price(resultSet.getDouble("goods_price"));
				orders.setGoods_sale(resultSet.getInt("goods_sale"));
				orders.setGoods_status(resultSet.getInt("goods_status"));
				orders.setGoods_sum(resultSet.getInt("goods_sum"));
				orders.setGoods_type(resultSet.getString("goods_type"));
				orders.setShop_id(resultSet.getInt("shop_id"));
				orders.setShop_name(resultSet.getString("shop_name"));
				User_OrderList_NoSign.add(orders);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			User_OrderList_NoSign=null;
		}finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return User_OrderList_NoSign;
	}
	
	public List<Orders> QueryOrder_Finish(User user){
		List<Orders> User_OrderList_Finish = null;
		String sqlString="select s.shop_name,o.*,og.orders_goods_sum,g.* from shop s,orders o,orders_goods og,goods g where o.orders_status=2 and o.user_id=? and o.orders_id=og.orders_id and og.goods_id=g.goods_id and g.shop_id=s.shop_id ";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		System.out.println("user.getUser_name():"+user.getUser_name());
		try {
			connection=super.getConnection();
			preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setObject(1,user.getUser_id());
			resultSet = preparedStatement.executeQuery();
			User_OrderList_Finish=new ArrayList<Orders>();
			while(resultSet.next()){
				Orders orders =new Orders();
				orders.setOrders_id(resultSet.getInt("orders_id"));
				orders.setUser_id(resultSet.getInt("user_id"));
				orders.setOrders_date(resultSet.getDate("orders_date").toString());
				orders.setOrders_price(resultSet.getDouble("orders_price"));
				orders.setOrders_status(resultSet .getInt("orders_status"));
				orders.setOrders_code(resultSet.getInt("orders_code"));
				orders.setOrders_goods_sum(resultSet.getInt("orders_goods_sum"));
				orders.setGoods_id(resultSet.getInt("goods_id"));
				orders.setGoods_describe(resultSet.getString("goods_describe"));
				orders.setGoods_img(resultSet.getString("goods_img"));
				orders.setGoods_name(resultSet.getString("goods_name"));
				orders.setGoods_price(resultSet.getDouble("goods_price"));
				orders.setGoods_sale(resultSet.getInt("goods_sale"));
				orders.setGoods_status(resultSet.getInt("goods_status"));
				orders.setGoods_sum(resultSet.getInt("goods_sum"));
				orders.setGoods_type(resultSet.getString("goods_type"));
				orders.setShop_id(resultSet.getInt("shop_id"));
				orders.setShop_name(resultSet.getString("shop_name"));
				User_OrderList_Finish.add(orders);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			User_OrderList_Finish=null;
		}finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return User_OrderList_Finish;
	}
	
	public int Order_Signing(Orders orders){
		int i=0;
		String sqlString="update orders set orders_status = 2 where orders_id = ?";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		//ResultSet resultSet=null;
		//System.out.println("user.getUser_name():"+user.getUser_name());
		try {
			connection=super.getConnection();
			preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setObject(1,orders.getOrders_id());
			i = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				preparedStatement.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return i;
	}
	
	public int Order_Cancel(Orders orders){
		int i=0;
		String sqlString="update orders set orders_status = -1 where orders_id = ?";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		//ResultSet resultSet=null;
		//System.out.println("user.getUser_name():"+user.getUser_name());
		try {
			connection=super.getConnection();
			preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setObject(1,orders.getOrders_id());
			i = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				preparedStatement.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return i;
	}
	
	public int Order_CreateOrders(Orders orders) {
		
		int i=0;
		String sqlString="Insert into orders(user_id,orders_date, "
				+ " orders_price,orders_status) values( ? , ? , ? , 0 ) ";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		//ResultSet resultSet=null;
		//System.out.println("user.getUser_name():"+user.getUser_name());
		try {
			connection=super.getConnection();
			preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setObject(1,orders.getUser_id());
			preparedStatement.setObject(2,orders.getOrders_date());
			preparedStatement.setObject(3,orders.getOrders_price());
			i = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				preparedStatement.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return i;
	}
	
	public int Order_CreateOrdersGoods(Orders orders) {

		int i=0;
		String sqlString="Insert into orders_goods(goods_id,orders_goods_sum) values ( ? , ? )";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		//ResultSet resultSet=null;
		//System.out.println("user.getUser_name():"+user.getUser_name());
		try {
			connection=super.getConnection();
			preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setObject(1,orders.getGoods_id());
			preparedStatement.setObject(2,orders.getOrders_goods_sum());
			i = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				preparedStatement.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return i;
	}
	
	public int Order_UpdateGoods(Orders orders){
		int i=0;
		String sqlString="update goods set goods_sum = goods_sum - ? , goods_sale = goods_sale + ?  "
				+ " where goods_id = ? ";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		//ResultSet resultSet=null;
		//System.out.println("user.getUser_name():"+user.getUser_name());
		try {
			connection=super.getConnection();
			preparedStatement=connection.prepareStatement(sqlString);
			System.out.println("orders.getOrders_goods_sum():"+orders.getOrders_goods_sum());
			preparedStatement.setObject(1,orders.getOrders_goods_sum());
			preparedStatement.setObject(2,orders.getOrders_goods_sum());
			preparedStatement.setObject(3,orders.getGoods_id());
			i = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				preparedStatement.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
			try {
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return i;
	}
}
