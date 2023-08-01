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
			pstmt = conn.prepareStatement("select b.id, b.sellingprice, b.price, b.orderDate, c.cardName, cc.ctitle , m.menuName, o.menuquantity from bills b join orders o on b.id = o.bilId join menus m on o.menuId = m.id join creditcards  c on b.card  = c.id join coupons cc on cc.id = b.coupon ;");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				bills b = new bills();
				b.setId(rs.getInt("id"));
				b.setSellingprice(rs.getInt("sellingPrice"));
				b.setPrice(rs.getInt("price"));
				b.setOrderDate(rs.getDate(0));
				b.setCard(rs.getString("card"));
				b.setCoupon(rs.getString("coupon"));
				coupons cc = new coupons();
				cc.setCtitle(rs.getString("cTitle"));
				creditCards cd = new creditCards();
				cd.setCardName(rs.getString("cardName"));
				menus m = new menus();
				m.setMenuName(rs.getString("menuName"));
				orders o = new orders();
				o.setMenuquantity(rs.getInt("menuquantity"));
				o.setCoupons(cc);
				o.setCreditCards(cd);
				o.setMenus(m);
				
				ordersList.add(o);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {close();}
		return ordersList;
	}
	
	public void add(orders o) {
		open();
		String sql = 
				"INSERT INTO orders(menuquantity) values(?)";
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, o.getMenuquantity());
			
			pstmt.executeUpdate();
		} catch(Exception e) { e.printStackTrace();}
			finally {close();}
	}
}
