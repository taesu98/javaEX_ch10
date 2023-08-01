package EX_ch10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import EX_ch10.bills;

public class billsDAO {
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
	
	public List<bills> getBillsList(){
		open();
		List<bills> billsList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement("select b.id, b.sellingPrice, b.price, b.orderDate, c.cardName, cc.CTITLE , m.menuName, o.MENUQUANTITY from bills b join orders o on b.id = o.bilId join menus m on o.menuId = m.id join CREDITCARDS  c on b.CARD  = c.ID join coupons cc on cc.id = b.COUPON ;");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				bills b = new bills();
				b.setId(rs.getInt("id"));
				b.setSellingprice(rs.getInt("sellingprice"));
				b.setPrice(rs.getInt("price"));
				b.setOrderDate(rs.getDate(0));
				b.setCard(rs.getString("card"));
				b.setCoupon(rs.getString("coupon"));
				coupons cc = new coupons();
				cc.setCtitle(rs.getString("ctitle"));
				creditCards cd = new creditCards();
				cd.setCardName(rs.getString("cardName"));
				menus m = new menus();
				m.setMenuName(rs.getString("menuName"));
				orders o = new orders();
				o.setMenuquantity(rs.getInt("menuquantity"));
				b.setOrders(o);
				b.setCoupons(cc);
				b.setCreditCards(cd);
				b.setMenus(m);
				
				billsList.add(b);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {close();}
		return billsList;
	}
	
	public void add(bills b) {
		open();
		String sql = 
				"INSERT INTO bills(sellingPrice, price, orderDate, card, coupon) values(?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, b.getId());
			pstmt.setInt(1, b.getSellingprice());
			pstmt.setInt(2, b.getPrice());
			pstmt.setDate(3, new java.sql.Date(new Date().getTime()));
			pstmt.setString(4, b.getCard());
			pstmt.setString(5, b.getCoupon());
			
			pstmt.executeUpdate();
		} catch(Exception e) { e.printStackTrace();}
			finally {close();}
	}

}
