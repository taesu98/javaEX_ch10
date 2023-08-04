package EX_ch10;

import java.sql.Statement;
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
			pstmt = conn.prepareStatement("select b.id, b.sellingPrice, b.price, b.orderDate, c.cardName, cc.ctitle , m.menuName, o.menuQuantity from bills b join orders o on b.id = o.bilId join menus m on o.menuId = m.id join creditcards  c on b.cardId  = c.id join coupons cc on cc.id = b.couponId ;");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				bills b = new bills();
				b.setId(rs.getInt("id"));
				b.setSellingprice(rs.getInt("sellingprice"));
				b.setPrice(rs.getInt("price"));
				b.setOrderDate(rs.getDate("orderDate"));
				b.setCardId(rs.getInt("cardId"));
				b.setCouponId(rs.getInt("couponId"));
				coupons cc = new coupons();
				cc.setCtitle(rs.getString("ctitle"));
				creditCards cd = new creditCards();
				cd.setCardName(rs.getString("cardName"));
				menus m = new menus();
				m.setMenuName(rs.getString("menuName"));
				orders o = new orders();
				o.setMenuQuantity(rs.getInt("menuQuantity"));
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
	
	public Integer addBils(bills b) {
		open();
		String sql = 
				"INSERT INTO bills(sellingprice, price, orderDate, cardId, couponId) values(?,?,?,?,?)";
		Integer autoInsertedKey = null;
		try {
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
//			pstmt.setInt(1, rs.getInt(1));
			pstmt.setInt(1, b.getSellingprice());
			pstmt.setInt(2, b.getPrice());
			pstmt.setDate(3, new java.sql.Date(new Date().getTime()));
			pstmt.setInt(4, b.getCardId());
			pstmt.setInt(5, b.getCouponId());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			autoInsertedKey = (rs.next()) ? rs.getInt(1) : null;
			
		} catch(Exception e) { e.printStackTrace();}
			finally {close();}
		
		return autoInsertedKey;
		 
	}
	
	public void addOrders(orders o, int billId) {
		open();
		String sql = 
				"INSERT INTO orders(bilId, menuId, menuQuantity) values(?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, billId);
			pstmt.setInt(2, o.getMenuId());
			pstmt.setInt(3, o.getMenuQuantity());
			
			pstmt.executeUpdate();
		} catch(Exception e) { e.printStackTrace();}
			finally {close();}
	}

}
