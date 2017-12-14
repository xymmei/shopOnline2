package com.kms.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kms.admin.pojo.Count;
import com.kms.admin.pojo.CountByDate;
import com.kms.admin.pojo.Price;
import com.kms.admin.pojo.PriceByDate;
import com.kms.dao.BaseDao;
import com.kms.pojo.Admin;
import com.kms.pojo.Orders;
import com.kms.pojo.Shop;
import com.kms.pojo.User;

public class AdminDao extends BaseDao{
	/*
	 * 添加管理员
	 */
	public int addAdmin(Admin admin){
		int i=0;
		String sql="insert into mall.admin(admin_name,admin_password) values ( ? , ? )";
		Connection connection=null;
		PreparedStatement praPreparedStatement=null;
		connection=super.getConnection();
		try {
			praPreparedStatement=connection.prepareStatement(sql);
			praPreparedStatement.setObject(1, admin.getAdmin_name());
			praPreparedStatement.setObject(2, admin.getAdmin_password());
			i=praPreparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				praPreparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return i;
	}
	/*
	 * 管理员修改个人信息
	 */
	public int updateAdmin(Admin admin){
		int i=0;
		String sql="update mall.admin set admin_name='"+admin.getAdmin_name()+"' where admin_password='"+admin.getAdmin_password()+"' ;";
		Connection connection=null;
		Statement statement=null;
		connection=super.getConnection();
		try {
			 statement=connection.createStatement();
			 i=statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return i;
	}
	/*
	 * 检查管理员登录信息
	 */
	public int checkAdmin(Admin admin){
		int i=0;
		String sqlString="select admin_name,admin_password from mall.admin where admin_name= '"+admin.getAdmin_name()+"' and admin_password = '"+admin.getAdmin_password()+"'; ";
		Connection connection=null;
		Statement statement=null;
		ResultSet rSet=null;
		connection=super.getConnection();
		try {
			
			statement=connection.createStatement();
			rSet=statement.executeQuery(sqlString);
			while(rSet.next()){
				i++;
			}
			System.out.println(i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}finally {
			try {
				rSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				System.out.println(e1.getMessage());
			}
		}
		return i;
	}
	/*
	 * 根据传来的参数和店铺ID审核店铺是否通过 status=1 通过 status=2 不通过
	 */
	public int auditPendingShop(int status,int shop_id){
		int i=0;
		String sql="update mall.shop set shop_status ="+status+" where shop_id="+shop_id+";";
		Connection connection=null;
		Statement statement=null;
		connection=super.getConnection();
		try {
			 statement=connection.createStatement();
			 i=statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			i=0;
		}finally{
			try {
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return i;
	}

	/*
	 * 根据传来的参数查询处于该状态的店铺 0--待审核 1--审核通过 2--审核不通过
	 */
	public List<Shop> findPendingShop(int status){
		List<Shop> lrsList;
		String sql="select s.*,user_name from mall.shop s,mall.users u where shop_status="+status+" and  s.user_id=u.user_id;";
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet=null;
		connection=super.getConnection();
		try {
			 statement=connection.createStatement();
			 resultSet=statement.executeQuery(sql);
			 lrsList=new ArrayList<Shop>();
			 while(resultSet.next()){
				 Shop shop=new Shop();
				 shop.setShop_id(resultSet.getInt("shop_id"));
				 shop.setShop_name(resultSet.getString("shop_name"));
				 shop.setUser_id(resultSet.getInt("user_id"));
				 shop.setShop_type(resultSet.getString("shop_type"));
				 shop.setShop_describe(resultSet.getString("shop_describe"));
				 shop.setShop_address(resultSet.getString("shop_address"));
				 shop.setShop_phone(resultSet.getString("shop_phone"));
				 shop.setUser_phone(resultSet.getString("user_phone"));
				 shop.setShop_img(resultSet.getString("shop_img"));
				 shop.setShop_status(resultSet.getInt("shop_status"));
				 shop.setShop_date(resultSet.getDate("shop_date").toString());
				 shop.setUser_name(resultSet.getString("user_name"));
				 lrsList.add(shop);				
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			lrsList=null;
		}finally{
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return lrsList;
	}
	/*
	 * 查询所有用户
	 */
	public List<User> findAllUsers(){
		List<User>lrsList=null;
		String sql="select * from mall.users;";
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet=null;
		connection=super.getConnection();
		try {
			 statement=connection.createStatement();
			 resultSet=statement.executeQuery(sql);
			 lrsList=new ArrayList<User>();
			 while(resultSet.next()){
				 User user=new User();
				 user.setUser_id(resultSet.getInt("user_id"));
				 user.setUser_name(resultSet.getString("user_name"));
				 user.setUser_address(resultSet.getString("user_address"));
				 user.setUser_idcard(resultSet.getString("user_idcard"));
				 user.setUser_img(resultSet.getString("user_img"));
				 user.setUser_password("");//��ȥ��ȡ����
				 user.setUser_sex(resultSet.getString("user.sex"));
				 user.setUser_realname(resultSet.getString("user_realname"));
				 user.setUser_phone(resultSet.getString("user_phone"));
				 lrsList.add(user);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			lrsList=null;
		}finally{
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return lrsList;
	}
	/*
	 * 查询所有订单
	 */
	public List<Orders> findAllOrders(){
		List<Orders>lrsList=null;
		String sql="select * from mall.orders;";
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet=null;
		connection=super.getConnection();
		try {
			 statement=connection.createStatement();
			 resultSet=statement.executeQuery(sql);
			 lrsList=new ArrayList<Orders>();
			 while(resultSet.next()){
				 Orders orders=new Orders();
				 orders.setOrders_id(resultSet.getInt("orders_id"));
				 orders.setUser_id(resultSet.getInt("user_id"));
				 orders.setOrders_date(resultSet.getDate("orders_date").toString());
				 orders.setOrders_price(resultSet.getDouble("orders_price"));
				 orders.setOrders_status(resultSet.getInt("orders_status"));
				 lrsList.add(orders);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			lrsList=null;
		}finally{
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return lrsList;
	}
	
	/*
	 * 查询注册用户数量
	 */
	public int countUser(){
		int count=0;
		String sql="select count(*) num from mall.users;";
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet=null;
		connection=super.getConnection();
		try {
			 statement=connection.createStatement();
			 resultSet=statement.executeQuery(sql);
			 while(resultSet.next()){
				 count=resultSet.getInt("num");
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			count=-1;
		}finally{
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return count;
	}
	/*
	 * 查询注册的店铺数量
	 */
	public int countShop(){
		int count=0;
		String sql="select count(*) num from mall.shop where shop_status=1;";
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet=null;
		connection=super.getConnection();
		try {
			 statement=connection.createStatement();
			 resultSet=statement.executeQuery(sql);
			 while(resultSet.next()){
				 count=resultSet.getInt("num");
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			count=-1;
		}finally{
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return count;
	}
	/*
	 * 查询网站总交易额
	 */
	public double countSalePrice(){
		String sql="select sum(orders_price) sum from mall.orders where orders_status=2 ;";
		double price=0;
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet=null;
		connection=super.getConnection();
		try {
			 statement=connection.createStatement();
			 resultSet=statement.executeQuery(sql);
			 while(resultSet.next()){
				 price=resultSet.getInt("sum");
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			price=-1;
		}finally{
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return price;
	}
	/*
	 * 根据开始日期和结束日期计算该时间段内的交易额，按年月分组
	 */
	public List<PriceByDate> countSalePriceByDate(YearMonth startDate,YearMonth endDate){
		List<PriceByDate>lrsList;
		String sql="select YEAR(orders_date) y,MONTH(orders_date) m,sum(orders_price) sum  from mall.orders where orders_status=2 and YEAR(orders_date)*12+MONTH(orders_date) "
				+"BETWEEN "+(startDate.getYear()*12+startDate.getMonthValue())+" and "+(endDate.getYear()*12+endDate.getMonthValue())+" group by y,m ";
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet=null;
		connection=super.getConnection();
		try {
			 statement=connection.createStatement();
			 resultSet=statement.executeQuery(sql);
			 lrsList=new ArrayList<PriceByDate>();
			 while(resultSet.next()){
				 PriceByDate priceByDate=new PriceByDate();
				 priceByDate.setYear(resultSet.getInt("y"));
				 priceByDate.setMonth(resultSet.getInt("m"));
				 priceByDate.setPrice(resultSet.getDouble("sum"));
				 lrsList.add(priceByDate);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			lrsList=null;
		}finally{
			try {
				resultSet.close();
				statement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return lrsList;
	}
	/*
	 * 根据开始日期和结束日期计算该时间段内成功注册的店铺数量，按年月分组
	 */
	public List<PriceByDate> countShopByDate(YearMonth startDate,YearMonth endDate){
		List<PriceByDate>lrsList;
		String sql="select YEAR(shop_date) y,MONTH(shop_date) m,count(shop_id) sum  from mall.shop where YEAR(shop_date)*12+ MONTH(shop_date) BETWEEN "
				+ (startDate.getYear()*12+startDate.getMonthValue())+" and "+(endDate.getYear()*12+endDate.getMonthValue() )+" and shop_status = 1"
				+ " group by y,m ";
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet=null;
		connection=super.getConnection();
		try {
			 statement=connection.createStatement();
			 resultSet=statement.executeQuery(sql);
			 lrsList=new ArrayList<PriceByDate>();
			 while(resultSet.next()){
				 PriceByDate priceByDate=new PriceByDate();
				 priceByDate.setYear(resultSet.getInt("y"));
				 priceByDate.setMonth(resultSet.getInt("m"));
				 priceByDate.setPrice(resultSet.getInt("sum"));
				 lrsList.add(priceByDate);
			 }
			} catch (SQLException e) {
					// TODO Auto-generated catch block
				e.printStackTrace();
				lrsList=null;
			}finally{
				try {
					resultSet.close();
					statement.close();
					connection.close();
				} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
				}
		return lrsList;
	}
}
	
