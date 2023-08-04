package EX_ch10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ordersDAO {
	Connection conn = null;
	PreparedStatement pstmt;
	
	final String JDBC_DRIVER = "org.h2.Driver";
	final String JDBC_URL = "jdbc:h2:tcp://localhost/C:/JAVA/jwbookdb";
	
	public void open() {
		try {
			Class.forName(JDBC_DRIVER);
			conn = DriverManager.getConnection(JDBC_URL, "jwbook", "1234");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<orders> getOrdersList(){
		open();
		List<orders> ordersList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement("select o.bilId, o.menuId, o.menuQuantity from orders o join bills b on b.id = o.bilId join menus m on o.menuId = m.id ;");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				bills b = new bills();
				b.setId(rs.getInt("id"));
				b.setSellingprice(rs.getInt("sellingPrice"));
				b.setPrice(rs.getInt("price"));
				b.setOrderDate(rs.getDate("orderDate"));
				b.setCardId(rs.getInt("cardId"));
				b.setCouponId(rs.getInt("couponId"));
				menus m = new menus();
				m.setId(rs.getInt("id"));
				m.setMenuName(rs.getString("menuName"));
				orders o = new orders();
				o.setId(rs.getInt("id"));
				o.setBilId(rs.getInt("bilId"));
				o.setMenuId(rs.getInt("menuId"));
				o.setMenuQuantity(rs.getInt("menuQuantity"));	
				o.setBills(b);
				o.setMenus(m);
				
				ordersList.add(o);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {close();}
		return ordersList;
	}
	
//	public void add(orders o) {
//		open();
//		String sql = 
//				"INSERT INTO orders(bilId, menuId, menuquantity) values(?,?,?)";
//		try {
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, o.getBilId());
//			pstmt.setInt(2, o.getMenuId());
//			pstmt.setInt(3, o.getMenuquantity());
//			
//			pstmt.executeUpdate();
//		} catch(Exception e) { e.printStackTrace();}
//			finally {close();}
//	}
}
