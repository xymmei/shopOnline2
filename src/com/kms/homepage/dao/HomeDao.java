package com.kms.homepage.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kms.dao.BaseDao;
import com.kms.pojo.Goods;
import com.kms.pojo.Shop;


public class HomeDao extends BaseDao{
	/*
	 * 为首页获取所有商品，随机
	 */
	public List<Goods> getRandGoods(){
		List<Goods>lrsList=null;
		String sql="select g.*,s.shop_name from mall.goods g,mall.shop s where g.goods_status=1 and  g.shop_id=s.shop_id order by RAND() ;";
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet=null;
		connection=super.getConnection();
		try {
			 statement=connection.createStatement();
			 resultSet=statement.executeQuery(sql);
			 lrsList=new ArrayList<Goods>();
			 while(resultSet.next()){
				 Goods goods=new Goods();
				 goods.setGoods_id(resultSet.getInt("goods_id"));
				 goods.setGoods_name(resultSet.getString("goods_name"));
				 goods.setGoods_describe(resultSet.getString("goods_describe"));
				 goods.setGoods_price(resultSet.getDouble("goods_price"));
				 goods.setGoods_sale(resultSet.getInt("goods_sale"));
				 goods.setGoods_sum(resultSet.getInt("goods_sum"));
				 goods.setGoods_type(resultSet.getString("goods_type"));
				 goods.setGoods_status(resultSet.getInt("goods_status"));
				 goods.setGoods_img(resultSet.getString("goods_img"));
				 goods.setShop_id(resultSet.getInt("shop_id"));
				 goods.setShop_name(resultSet.getString("shop_name"));
				 lrsList.add(goods);
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
	 * 商品top 5
	 */
	public List<Goods> getTopGoods(){
		List<Goods>lrsList=null;
		String sql="select g.*,s.shop_name from mall.goods g,mall.shop s where g.goods_status=1 and g.shop_id=s.shop_id order by goods_sale desc limit 5 ;";
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet=null;
		connection=super.getConnection();
		try {
			 statement=connection.createStatement();
			 resultSet=statement.executeQuery(sql);
			 lrsList=new ArrayList<Goods>();
			 while(resultSet.next()){
				 Goods goods=new Goods();
				 goods.setGoods_id(resultSet.getInt("goods_id"));
				 goods.setGoods_name(resultSet.getString("goods_name"));
				 goods.setGoods_describe(resultSet.getString("goods_describe"));
				 goods.setGoods_price(resultSet.getDouble("goods_price"));
				 goods.setGoods_sale(resultSet.getInt("goods_sale"));
				 goods.setGoods_sum(resultSet.getInt("goods_sum"));
				 goods.setGoods_type(resultSet.getString("goods_type"));
				 goods.setGoods_status(resultSet.getInt("goods_status"));
				 goods.setGoods_img(resultSet.getString("goods_img"));
				 goods.setShop_id(resultSet.getInt("shop_id"));
				 goods.setShop_name(resultSet.getString("shop_name"));
				 lrsList.add(goods);
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
	 * 店铺Top 5
	 */
	public List<Shop> getTopShops(){
		List<Shop> lrsList;
		String sql="select s.*,SUM(g.goods_sale) shop_sale from mall.goods g,mall.shop s where s.shop_status=1 and g.shop_id=s.shop_id group by g.shop_id  order by shop_sale desc limit 5";
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
				 shop.setShop_sale(resultSet.getInt("shop_sale"));
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
	 * 根据商品类型查询随机3个
	 */
	public List<Goods> getClassifyGoods(String goods_type){
		List<Goods>lrsList=null;
		String sql="select g.*,s.shop_name from mall.goods g,mall.shop s where s.shop_status=1 and g.goods_status=1 and g.shop_id=s.shop_id and g.goods_type = '"+goods_type+"' order by RAND() limit 3;";
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet=null;
		connection=super.getConnection();
		try {
			 statement=connection.createStatement();
			 resultSet=statement.executeQuery(sql);
			 lrsList=new ArrayList<Goods>();
			 while(resultSet.next()){
				 Goods goods=new Goods();
				 goods.setGoods_id(resultSet.getInt("goods_id"));
				 goods.setGoods_name(resultSet.getString("goods_name"));
				 goods.setGoods_describe(resultSet.getString("goods_describe"));
				 goods.setGoods_price(resultSet.getDouble("goods_price"));
				 goods.setGoods_sale(resultSet.getInt("goods_sale"));
				 goods.setGoods_sum(resultSet.getInt("goods_sum"));
				 goods.setGoods_type(resultSet.getString("goods_type"));
				 goods.setGoods_status(resultSet.getInt("goods_status"));
				 goods.setGoods_img(resultSet.getString("goods_img"));
				 goods.setShop_id(resultSet.getInt("shop_id"));
				 goods.setShop_name(resultSet.getString("shop_name"));
				 lrsList.add(goods);
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
	 * 点击根据传来的商品id查询某一商品详情
	 */
	public List<Goods> getGoodsMessage(int goods_id){
		List<Goods>lrsList=null;
		String sql="select g.*,s.shop_name from mall.goods g,mall.shop s where g.goods_id="+goods_id+" and g.goods_status=1 and  g.shop_id=s.shop_id";
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet=null;
		connection=super.getConnection();
		try {
			 statement=connection.createStatement();
			 resultSet=statement.executeQuery(sql);
			 lrsList=new ArrayList<Goods>();
			 while(resultSet.next()){
				 Goods goods=new Goods();
				 goods.setGoods_id(resultSet.getInt("goods_id"));
				 goods.setGoods_name(resultSet.getString("goods_name"));
				 goods.setGoods_describe(resultSet.getString("goods_describe"));
				 goods.setGoods_price(resultSet.getDouble("goods_price"));
				 goods.setGoods_sale(resultSet.getInt("goods_sale"));
				 goods.setGoods_sum(resultSet.getInt("goods_sum"));
				 goods.setGoods_type(resultSet.getString("goods_type"));
				 goods.setGoods_status(resultSet.getInt("goods_status"));
				 goods.setGoods_img(resultSet.getString("goods_img"));
				 goods.setShop_id(resultSet.getInt("shop_id"));
				 goods.setShop_name(resultSet.getString("shop_name"));
				 lrsList.add(goods);
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
	 * 点击根据传来的店铺ID查询店铺详情
	 */
	public List<Shop> getShopMessage(int shop_id){
		List<Shop> lrsList;
		String sql="select s.*,SUM(g.goods_sale) shop_sale from mall.goods g,mall.shop s where s.shop_id="+shop_id+" and s.shop_status=1 and g.shop_id=s.shop_id group by g.shop_id ";
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
				 shop.setShop_sale(resultSet.getInt("shop_sale"));
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
	 * 显示店铺所有商品
	 */
	public List<Goods> getShopGoods(int shop_id){
		List<Goods>lrsList=null;
		String sql="select g.*,s.shop_name from mall.goods g,mall.shop s where g.shop_id="+shop_id+" and g.goods_status=1 and  g.shop_id=s.shop_id";
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet=null;
		connection=super.getConnection();
		try {
			 statement=connection.createStatement();
			 resultSet=statement.executeQuery(sql);
			 lrsList=new ArrayList<Goods>();
			 while(resultSet.next()){
				 Goods goods=new Goods();
				 goods.setGoods_id(resultSet.getInt("goods_id"));
				 goods.setGoods_name(resultSet.getString("goods_name"));
				 goods.setGoods_describe(resultSet.getString("goods_describe"));
				 goods.setGoods_price(resultSet.getDouble("goods_price"));
				 goods.setGoods_sale(resultSet.getInt("goods_sale"));
				 goods.setGoods_sum(resultSet.getInt("goods_sum"));
				 goods.setGoods_type(resultSet.getString("goods_type"));
				 goods.setGoods_status(resultSet.getInt("goods_status"));
				 goods.setGoods_img(resultSet.getString("goods_img"));
				 goods.setShop_id(resultSet.getInt("shop_id"));
				 goods.setShop_name(resultSet.getString("shop_name"));
				 lrsList.add(goods);
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
	 * 按照类型显示店铺商品
	 */
	public List<Goods> getShopGoodsByType(int shop_id,String goods_type){
		List<Goods>lrsList=null;
		String sql="select g.*,s.shop_name from mall.goods g,mall.shop s where g.shop_id="+shop_id+" and g.goods_type='"+goods_type+"' and g.goods_status=1 and  g.shop_id=s.shop_id order by g.shop_sale";
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet=null;
		connection=super.getConnection();
		try {
			 statement=connection.createStatement();
			 resultSet=statement.executeQuery(sql);
			 lrsList=new ArrayList<Goods>();
			 while(resultSet.next()){
				 Goods goods=new Goods();
				 goods.setGoods_id(resultSet.getInt("goods_id"));
				 goods.setGoods_name(resultSet.getString("goods_name"));
				 goods.setGoods_describe(resultSet.getString("goods_describe"));
				 goods.setGoods_price(resultSet.getDouble("goods_price"));
				 goods.setGoods_sale(resultSet.getInt("goods_sale"));
				 goods.setGoods_sum(resultSet.getInt("goods_sum"));
				 goods.setGoods_type(resultSet.getString("goods_type"));
				 goods.setGoods_status(resultSet.getInt("goods_status"));
				 goods.setGoods_img(resultSet.getString("goods_img"));
				 goods.setShop_id(resultSet.getInt("shop_id"));
				 goods.setShop_name(resultSet.getString("shop_name"));
				 lrsList.add(goods);
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
	 * 模糊搜索商品
	 */
	public List<Goods> getFuzzySearchGoods(String keywords){
		List<Goods>lrsList=null;
		String sql="select * from mall.goods where goods_status=1 and goods_name like '"+keywords+"' ";
		Connection connection=null;
		Statement statement=null;
		ResultSet resultSet=null;
		connection=super.getConnection();
		try {
			 statement=connection.createStatement();
			 resultSet=statement.executeQuery(sql);
			 lrsList=new ArrayList<Goods>();
			 while(resultSet.next()){
				 Goods goods=new Goods();
				 goods.setGoods_id(resultSet.getInt("goods_id"));
				 goods.setGoods_name(resultSet.getString("goods_name"));
				 goods.setGoods_describe(resultSet.getString("goods_describe"));
				 goods.setGoods_price(resultSet.getDouble("goods_price"));
				 goods.setGoods_sale(resultSet.getInt("goods_sale"));
				 goods.setGoods_sum(resultSet.getInt("goods_sum"));
				 goods.setGoods_type(resultSet.getString("goods_type"));
				 goods.setGoods_status(resultSet.getInt("goods_status"));
				 goods.setGoods_img(resultSet.getString("goods_img"));
				 goods.setShop_id(resultSet.getInt("shop_id"));
				 lrsList.add(goods);
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
	 * 模糊搜索店铺
	 */
	public List<Shop> getFuzzySearchShop(String keywords){
		List<Shop> lrsList;
		String sql="select * from mall.shop where shop_status=1 and shop_name like '"+keywords+"'";
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
}
