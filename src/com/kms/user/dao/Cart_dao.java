

package com.kms.user.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;

import com.kms.dao.BaseDao;
import com.kms.pojo.Goods;
import com.kms.pojo.Orders;
import com.kms.pojo.User;
import com.kms.user.pojo.ShoppingCart;

public class Cart_dao extends BaseDao{
	public int AddCart_SQL(ShoppingCart shoppingCart) throws IOException {
		int i=0;
		//List<User> lrs = null;
		String sqlString="INSERT INTO shopping_cart(goods_id,user_id,shop_id,"
				+ " cart_sum,cart_money) values( ? , ? , ? , ? , ? ) ;";
		/**
		 * ServletContext servletContext=ServletActionContext.getServletContext();
		//System.out.println("servletContext:"+servletContext.toString());
		String path=servletContext.getRealPath("/images/"+user.getUser_imgFileFileName());//文件最终要上传到的路径
		 * 
		 * user.setUser_img(path);//文件最终要上传到的路径
			FileOutputStream out=new FileOutputStream(path);
			FileInputStream in=new FileInputStream(user.getUser_imgFile());
			System.out.println("Dao_imageFile:"+user.getUser_imgFile().toString());
			
			byte[]buffer=new byte[1024];
			int len=0;
			while((len=in.read(buffer))!=-1){
				System.out.println("len:"+len);
				out.write(buffer,0,len);
			}
		 */	
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		try {
			connection=super.getConnection();
			preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setObject(1,shoppingCart.getGoods_id());
			preparedStatement.setObject(2,shoppingCart.getUser_id());
			preparedStatement.setObject(3,shoppingCart.getShop_id());
			preparedStatement.setObject(4,shoppingCart.getCart_sum());
			preparedStatement.setObject(5,shoppingCart.getCart_sum()*shoppingCart.getGoods_price());
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
	
	public int DelCart_SQL(ShoppingCart shoppingCart) throws IOException {
		int i=0;
		//List<User> lrs = null;
		String sqlString="delete from shopping_cart where user_id = ? and "
				+ "goods_id = ? and shop_id = ? ";
		/**
		 * ServletContext servletContext=ServletActionContext.getServletContext();
		//System.out.println("servletContext:"+servletContext.toString());
		String path=servletContext.getRealPath("/images/"+user.getUser_imgFileFileName());//文件最终要上传到的路径
		 * 
		 * user.setUser_img(path);//文件最终要上传到的路径
			FileOutputStream out=new FileOutputStream(path);
			FileInputStream in=new FileInputStream(user.getUser_imgFile());
			System.out.println("Dao_imageFile:"+user.getUser_imgFile().toString());
			
			byte[]buffer=new byte[1024];
			int len=0;
			while((len=in.read(buffer))!=-1){
				System.out.println("len:"+len);
				out.write(buffer,0,len);
			}
		 */	
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		try {
			connection=super.getConnection();
			preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setObject(1,shoppingCart.getUser_id());
			preparedStatement.setObject(2,shoppingCart.getGoods_id());
			preparedStatement.setObject(3,shoppingCart.getShop_id());
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

	public List<ShoppingCart> QueryCart_SQL(ShoppingCart shoppingCart) {
		List<ShoppingCart> shoppingCartlist = null;
		String sqlString="select c.user_id,g.goods_name,g.goods_id,s.shop_name,s.shop_id,c.cart_sum,c.cart_money "
				+ " from shopping_cart c,goods g,shop s "
				+ " where g.goods_id=c.goods_id and s.shop_id=c.shop_id and c.user_id= ? ";
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try {
			connection=super.getConnection();
			preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setObject(1,shoppingCart.getUser_id());
			resultSet = preparedStatement.executeQuery();
			shoppingCartlist=new ArrayList<ShoppingCart>();
			while(resultSet.next()){
				ShoppingCart shoppingCart2=new ShoppingCart();
				shoppingCart2.setUser_id(resultSet.getInt("user_id"));;
				shoppingCart2.setGoods_name(resultSet.getString("goods_name"));
				shoppingCart2.setGoods_id(resultSet.getInt("goods_id"));;
				shoppingCart2.setShop_name(resultSet.getString("shop_name"));
				shoppingCart2.setShop_id(resultSet.getInt("shop_id"));
				shoppingCart2.setCart_sum(resultSet.getInt("cart_sum"));
				shoppingCart2.setCart_money(resultSet.getDouble("cart_money"));
				shoppingCartlist.add(shoppingCart2);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			shoppingCartlist=null;
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
		return shoppingCartlist;
	}
	
	
	public int UpdateCart_SQL(ShoppingCart shoppingCart) throws IOException {
		int i=0;
		//List<User> lrs = null;
		String sqlString="update shopping_cart set cart_sum =cart_sum + ? , cart_money = cart_money + ? "
				+ " where user_id = ? and goods_id = ? and shop_id = ? ";
		/**
		 * ServletContext servletContext=ServletActionContext.getServletContext();
		//System.out.println("servletContext:"+servletContext.toString());
		String path=servletContext.getRealPath("/images/"+user.getUser_imgFileFileName());//文件最终要上传到的路径
		 * 
		 * user.setUser_img(path);//文件最终要上传到的路径
			FileOutputStream out=new FileOutputStream(path);
			FileInputStream in=new FileInputStream(user.getUser_imgFile());
			System.out.println("Dao_imageFile:"+user.getUser_imgFile().toString());
			
			byte[]buffer=new byte[1024];
			int len=0;
			while((len=in.read(buffer))!=-1){
				System.out.println("len:"+len);
				out.write(buffer,0,len);
			}
		 */	
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		try {
			connection=super.getConnection();
			preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setObject(1,shoppingCart.getCart_sum());
			preparedStatement.setObject(2,shoppingCart.getCart_sum()*shoppingCart.getGoods_price());
			preparedStatement.setObject(3,shoppingCart.getUser_id());
			preparedStatement.setObject(4,shoppingCart.getGoods_id());
			preparedStatement.setObject(5,shoppingCart.getShop_id());
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
	
	public List<ShoppingCart> QueryCart_Cookie(Cookie[] cookies) throws Exception {
		 System.out.println("======================================================");
		 List<ShoppingCart> shoppingCartlist = null ;
		 ShoppingCart shoppingCart = new ShoppingCart();
		 if (cookies == null || cookies.length < 0) {
			System.out.println("there is no any cookie ..");
			// 没有cookie
		 } else {
			 for (Cookie c : cookies) {
				 System.out.println("haha there are many cookies :" + c.getName() + "    " + c.getValue());
				 shoppingCart.setGoods_id(Integer.parseInt(c.getName()));
				 shoppingCart.setCart_sum(Integer.parseInt(c.getValue()));
				 shoppingCart=Get_DetailMessages(shoppingCart);
				 shoppingCartlist.add(shoppingCart);
			 }
		 }
		return shoppingCartlist;
	}
	
	/**
	 * 查询货物详细信息
	 * @param user
	 * @return
	 */
	public ShoppingCart Get_DetailMessages(ShoppingCart shoppingCart){
		String sqlString="select s.shop_name,s.shop_id,g.goods_name,g.goods_price from shop s,goods g "
				+ " where g.shop_id=s.shop_id and g.goods_id = ? ";
		ShoppingCart shoppingCart_DetailMessage=null;
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet resultSet=null;
		try {
			connection=super.getConnection();
			preparedStatement=connection.prepareStatement(sqlString);
			preparedStatement.setObject(1,shoppingCart.getGoods_id());
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				shoppingCart_DetailMessage = new ShoppingCart();
				shoppingCart_DetailMessage.setShop_name(resultSet.getString("shop_name"));
				shoppingCart_DetailMessage.setShop_id(resultSet.getInt("shop_id"));
				shoppingCart_DetailMessage.setGoods_name(resultSet.getString("goods_name"));				
				shoppingCart_DetailMessage.setGoods_price(resultSet.getDouble("goods_price"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
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
		return shoppingCart_DetailMessage;
	}
	
	
	public void AddCart_Cookie(String name, String value) {
		Cookie cookie = new Cookie(name.trim(), value.trim());
		cookie.setMaxAge(2 * 60 * 60 * 1000);// 设置为2个钟
		ServletActionContext.getResponse().addCookie(cookie);
	}
	
	public void UpdateCart_Cookie(Cookie c, String value) {
		c.setValue(value.trim());
		c.setMaxAge(2 * 60 * 60 * 1000);// 设置为2个钟
		ServletActionContext.getResponse().addCookie(c);
	}
	
	public void DelCart_Cookie(Cookie c) {
		c.setMaxAge(0);// 设置为2个钟
		ServletActionContext.getResponse().addCookie(c);
	}
	 /**
     * 当用户登录的时候，持久化cookie中的购物车信息，更新为本用户的购物车信息
	 * @throws IOException 
     */
    public void Cookie_WriteTo_SQL(User user,Cookie [] cookies) throws IOException {
    	boolean GoodsCartExist = false;
        if (null != user) {
            if (cookies != null) {
            	List<ShoppingCart> shoppingCartlist = null;
            	ShoppingCart shoppingCart = new ShoppingCart();
            	//ShoppingCart shoppingCart2 = new ShoppingCart();
            	shoppingCart.setUser_id(user.getUser_id());
                for (Cookie c : cookies) {
                	shoppingCartlist=QueryCart_SQL(shoppingCart);
                	for(int k=0;k<shoppingCartlist.size();k++){
                		if(shoppingCartlist.get(k).getGoods_id()==Integer.parseInt(c.getName())){
                			shoppingCartlist.get(k).setCart_sum(Integer.parseInt(c.getValue()));
                			UpdateCart_SQL(shoppingCartlist.get(k));
                			System.out.println("cookie写入成功");
                			GoodsCartExist = true;
                			break;
                		}
                	}
                	if(GoodsCartExist==false){
                		shoppingCart.setGoods_id(Integer.parseInt(c.getName()));
                		shoppingCart.setCart_sum(Integer.parseInt(c.getValue()));
                		shoppingCart=Get_DetailMessages(shoppingCart);
                		AddCart_SQL(shoppingCart);
                	}
                }
                    /*if (c.getName().startsWith(Conf.IDUONA_CASHTICKET_COOKIE_STARTNAME)) {
                        // 获取cookie的名称："iduona_cashTicket_45" 和 cookie的值： "21"
                        String name = c.getName();
                        Integer amount = Integer.valueOf(Integer.valueOf(c.getValue())+Integer.valueOf(q));
                        Integer ct_id = Integer.valueOf(name.substring(name.lastIndexOf("_") + 1));
                        CashTicket temp = cashTicketService.get(ct_id);
                        ShoppingCart shoppingCartTemp = new ShoppingCart();
                        if (null != temp) {
                            if (shoppingCartService.isExistUserAndCashTicket(user, temp)) {
                                // 进行更新操作
                                ShoppingCart oldShoppingCart = shoppingCartService.getByUserAndCashTicket(user, temp);
                                oldShoppingCart.setAmount(amount);
                                shoppingCartService.update(oldShoppingCart);
                            } else {
                                // 否则进行保存记录
                                shoppingCartTemp.setAmount(amount);
                                shoppingCartTemp.setUser(user);
                                shoppingCartTemp.setCashTicket(temp);
                                shoppingCartTemp.setCreateTime(new Date());
                                shoppingCartTemp.setStatusType(StatusType.POSITIVE);
                                shoppingCartTemp.setUuid(UUID.randomUUID().toString());
                                shoppingCartService.save(shoppingCartTemp);
                            }
                        }
                    }*/
             // 移除所有的现金券cookies
                removeAllCookies();
            }
            System.out.println("压根没cookie");
        }
        System.out.println("明明未登录，为何能跑到这里？");
    }

    /**
     * 移除所有的现金券cookies操作
     */
    public void removeAllCookies() {
        Cookie cookies[] = ServletActionContext.getRequest().getCookies();
        if (cookies == null || cookies.length < 0) {
            // 没有cookie
            System.out.println("there is no any cookie ..");
        } else {
            System.out.println("开始删除cookies..");
            for (Cookie c : cookies) {
                c.setMaxAge(0);// 设置为0
                ServletActionContext.getResponse().addCookie(c);
            }
        }
    }
}
