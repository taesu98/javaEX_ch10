package EX_ch10;

import org.apache.commons.csv.*;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import EX_ch10.creditCards;
public class creditCardsDAO {
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
	
	public List<creditCards> getCreditCardsList(){
		open();
		List<creditCards> creditCardList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement("select * from creditCards");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				creditCards c = new creditCards();
				c.setId(rs.getInt("id"));
				c.setCardType(rs.getString("cardType"));
				c.setCardName(rs.getString("cardName"));
				c.setDiscount(rs.getInt("discount"));
				c.setDiscountType(rs.getString("discountType"));
				
				creditCardList.add(c);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {close();}
		return creditCardList;
	}
}
