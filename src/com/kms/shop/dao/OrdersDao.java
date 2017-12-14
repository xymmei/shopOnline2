package com.kms.shop.dao;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import com.kms.shop.pojo.OdPrice;
import com.kms.shop.pojo.Orders;
import com.kms.shop.pojo.Orders_sum;

public class OrdersDao extends BaseDao {
	// 更改了查询语句
	public List<Orders> check(int orders_status,int shop_id) {
		List<Orders> lrs = null;
		String sql = "SELECT	g.goods_name,	g.goods_price, og.orders_goods_sum ,	u.user_name,	o.orders_id,	"
				+ "u.user_id,	o.orders_date,	o.orders_price,	o.orders_status,	o.orders_code FROM"
				+ "	mall.orders o,	mall.users u,	mall.orders_goods og,	mall.goods g WHERE	"
				+ "o.user_id = u.user_id AND o.orders_id = og.orders_id AND og.goods_id = g.goods_id and "
				+ "o.orders_status = ? and shop_id = ? ORDER BY	o.orders_date DESC; ";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			statement.setObject(1, orders_status);
			statement.setObject(2, shop_id);
			resultSet = statement.executeQuery();
			lrs = new ArrayList<Orders>();
			while (resultSet.next()) {
				Orders orders = new Orders();
				orders.setOrders_goods_sum(resultSet.getInt("orders_goods_sum"));
				orders.setGoods_name(resultSet.getString("goods_name"));
				orders.setGoods_price(resultSet.getDouble("goods_price"));
				orders.setUser_name(resultSet.getString("user_name"));
				orders.setOrders_id(resultSet.getInt("orders_id"));
				orders.setUser_id(resultSet.getInt("user_id"));
				orders.setOrders_date(resultSet.getDate("orders_date").toString());
				orders.setOrders_price(resultSet.getDouble("orders_price"));
				orders.setOrders_status(resultSet.getInt("orders_status"));
				orders.setOrders_code(resultSet.getInt("orders_code"));
				lrs.add(orders);
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

	public int edit(int orders_id) {
		int i = 0;
		String sql = "update mall.orders set orders_status = ? where orders_id=?";
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			statement.setObject(1, 1);
			statement.setObject(2, orders_id);
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

	public List<Orders_sum> Orders_sum(YearMonth sDate, YearMonth eDate) {
		List<Orders_sum> lrs = null;
		String sql = "SELECT YEAR(orders_date) y ,MONTH(orders_date) m, COUNT(*) sum FROM "
				+ "mall.`orders` WHERE orders_status=2 AND YEAR(orders_date)*12+MONTH(orders_date)" + "BETWEEN "
				+ (sDate.getYear() * 12 + sDate.getMonthValue()) + " and "
				+ (eDate.getYear() * 12 + eDate.getMonthValue()) + " group by y,m ";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			lrs = new ArrayList<Orders_sum>();
			while (resultSet.next()) {
				Orders_sum os = new Orders_sum();
				os.setYear(resultSet.getInt("y"));
				os.setMonth(resultSet.getInt("m"));
				os.setOrders_sum(resultSet.getInt("sum"));
				lrs.add(os);
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

	// 查询总的销售额;
	public List<OdPrice> sumMoney(int orders_status, int shop_id) {
		List<OdPrice> dlrs = null;
		String sql = "select SUM(orders_price) s,COUNT(*) c  from   mall.orders,  mall.shop,  mall.goods,"
				+ "  mall.orders_goods  WHERE  orders_status= " + orders_status
				+ "  and orders.orders_id = orders_goods.orders_id"
				+ "  and orders_goods.goods_id = goods.goods_id and goods.shop_id = shop.shop_id and shop.shop_id =  "
				+ shop_id;// 缺一个条件
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			dlrs = new ArrayList<OdPrice>();
			while (resultSet.next()) {
				OdPrice od = new OdPrice();
				od.setOrdersPrice(resultSet.getDouble("s"));
				od.setCountOrders(resultSet.getInt("c"));
				dlrs.add(od);
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
		return dlrs;
	}

	public List<Orders> checkAll(int shop_id) {
		List<Orders> lrs = null;
		String sql = "SELECT	g.goods_name,	g.goods_price,	u.user_name, og.orders_goods_sum ,	o.orders_id,	u.user_id,	"
				+ "o.orders_date,	o.orders_price,	o.orders_status,	o.orders_code FROM	mall.orders o,	"
				+ "mall.users u,	mall.orders_goods og,	mall.goods g WHERE	o.user_id = u.user_id AND"
				+ " o.orders_id = og.orders_id AND og.goods_id = g.goods_id and shop_id = "+shop_id+" ORDER BY"
						+ "	o.orders_date DESC;";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			resultSet = statement.executeQuery();
			lrs = new ArrayList<Orders>();
			while (resultSet.next()) {
				Orders orders = new Orders();
				orders.setOrders_goods_sum(resultSet.getInt("orders_goods_sum"));
				orders.setGoods_name(resultSet.getString("goods_name"));
				orders.setGoods_price(resultSet.getDouble("goods_price"));
				orders.setUser_name(resultSet.getString("user_name"));
				orders.setOrders_id(resultSet.getInt("orders_id"));
				orders.setUser_id(resultSet.getInt("user_id"));
				orders.setOrders_date(resultSet.getDate("orders_date").toString());
				orders.setOrders_price(resultSet.getDouble("orders_price"));
				orders.setOrders_status(resultSet.getInt("orders_status"));
				orders.setOrders_code(resultSet.getInt("orders_code"));
				lrs.add(orders);
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
}
