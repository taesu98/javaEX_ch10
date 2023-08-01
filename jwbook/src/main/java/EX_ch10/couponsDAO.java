package EX_ch10;

import org.apache.commons.csv.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import EX_ch10.coupons;

public class couponsDAO {
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
	
	public List<coupons> getCouponList(){
		open();
		List<coupons> couponList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement("select * from coupons");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				coupons c = new coupons();
				c.setId(rs.getInt("id"));
				c.setCtitle(rs.getString("ctitle"));
				c.setDiscount(rs.getInt("discount"));
				c.setDoubleDiscount(rs.getString("doubleDiscount"));
				c.setDiscountType(rs.getString("discountType"));
				
				couponList.add(c);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {close();}
		return couponList;
	}
}
