package com.kms.shop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.registry.infomodel.User;

import com.kms.shop.pojo.Goods;

public class GoodsDao extends BaseDao {

	/**
	 * 店铺商品信息
	 * 
	 * @param shop_id
	 * @return
	 */
	public List<Goods> lrs(int shop_id) {
		List<Goods> lrs = null;
		String sqlString = "SELECT goods_id,goods_name,goods_describe," + "goods_price,goods_sum,goods_type,goods_sale,"
				+ "goods_status from mall.goods " + "where shop_id = ? order by goods_status desc";
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sqlString);
			lrs = new ArrayList<Goods>();
			preparedStatement.setObject(1, shop_id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Goods Goods = new Goods();
				Goods.setGoods_id(resultSet.getInt("goods_id"));
				Goods.setGoods_describe(resultSet.getString("goods_describe"));
				Goods.setGoods_name(resultSet.getString("goods_name"));
				Goods.setGoods_price(resultSet.getDouble("goods_price"));
				Goods.setGoods_sum(resultSet.getInt("goods_sum"));
				// Goods.setGoods_img(resultSet.getString("goods_img"));
				Goods.setGoods_sale(resultSet.getInt("goods_sale"));
				Goods.setGoods_type(resultSet.getString("goods_type"));
				Goods.setGoods_status(resultSet.getInt("goods_status"));
				lrs.add(Goods);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			lrs = null;
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lrs;
	}

	/**
	 * 商品信息列表，返回的是goods对象
	 * 
	 * @param goods
	 * @return
	 */
	public Goods lrs(Goods goods) {
		Goods Goods = null;
		String sqlString = "SELECT goods_id,goods_name,goods_describe," + "goods_price,goods_sum,goods_type,goods_sale,"
				+ "goods_img,goods_status from mall.goods " + "where goods_id = ? ";
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setObject(1, goods.getGoods_id());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Goods = new Goods();
				Goods.setGoods_id(resultSet.getInt("goods_id"));
				Goods.setGoods_describe(resultSet.getString("goods_describe"));
				Goods.setGoods_name(resultSet.getString("goods_name"));
				Goods.setGoods_price(resultSet.getDouble("goods_price"));
				Goods.setGoods_sum(resultSet.getInt("goods_sum"));
				Goods.setGoods_img(resultSet.getString("goods_img"));
				Goods.setGoods_sale(resultSet.getInt("goods_sale"));
				Goods.setGoods_type(resultSet.getString("goods_type"));
				Goods.setGoods_status(resultSet.getInt("goods_status"));
				System.out.println("Dao-setGoods_id:" + Goods.getGoods_id());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			Goods = null;
		} finally {
			try {
				resultSet.close();
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Goods;
	}

	/**
	 * 更改商品的状态，不删除，即商品下架
	 * 
	 * @param goods
	 * @return
	 */
	public int Del(Goods goods) {
		int i = 0;
		String sqlString = "UPDATE   `mall`.`goods` SET  `goods_status` = ? WHERE `goods_id` = ? ";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		System.out.println("Goods_id::" + goods.getGoods_id());
		try {
			connection = super.getConnection();
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setObject(1, 0);
			preparedStatement.setObject(2, goods.getGoods_id());
			i = preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} finally {
			try {
				preparedStatement.close();
			} catch (Exception e) {
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

	/**
	 * 更新商品，还有需要更改的地方，这个id还没确定
	 * 
	 * @param goods
	 * @return
	 */
	public int Update(Goods goods) {
		int i = 0;
		String sqlString = "update mall.goods " + "set goods_name=?,goods_describe=?,goods_price=?,"
				+ "goods_sum=?,goods_type=? , goods_status=?," + "where goods_id= ? ";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setObject(1, goods.getGoods_name());
			preparedStatement.setObject(2, goods.getGoods_describe());
			preparedStatement.setObject(3, goods.getGoods_price());
			preparedStatement.setObject(4, goods.getGoods_sum());
			preparedStatement.setObject(5, goods.getGoods_type());
			// preparedStatement.setObject(6, goods.getGoods_img());
			preparedStatement.setObject(6, goods.getGoods_status());
			preparedStatement.setObject(7, goods.getGoods_id());
			i = preparedStatement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				preparedStatement.close();
			} catch (Exception e) {
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

	/**
	 * 添加商品
	 * 
	 * @param goods
	 * @return
	 */
	public int in(Goods goods) {
		int i = 0;
		String sql = "insert into mall.goods (goods_name , goods_describe , goods_price , goods_sum"
				+ " , goods_type , goods_sale , goods_status , shop_id ) values( ? , ? , ? , ? , ? , ? , ? , ? )";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setObject(1, goods.getGoods_name());
			preparedStatement.setObject(2, goods.getGoods_describe());
			preparedStatement.setObject(3, goods.getGoods_price());
			preparedStatement.setObject(4, goods.getGoods_sum());
			preparedStatement.setObject(5, goods.getGoods_type());
			preparedStatement.setObject(6, 0);
			// preparedStatement.setObject(7, goods.getGoods_img());
			preparedStatement.setObject(7, 0);
			preparedStatement.setObject(8, goods.getShop_id());
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

	public int Up(Goods goods) {
		int i = 0;
		String sqlString = "UPDATE   `mall`.`goods` SET  `goods_status` = ? WHERE `goods_id` = ? ";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		System.out.println("Goods_id::" + goods.getGoods_id());
		try {
			connection = super.getConnection();
			preparedStatement = connection.prepareStatement(sqlString);
			preparedStatement.setObject(1, 1);
			preparedStatement.setObject(2, goods.getGoods_id());
			i = preparedStatement.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		} finally {
			try {
				preparedStatement.close();
			} catch (Exception e) {
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

}
