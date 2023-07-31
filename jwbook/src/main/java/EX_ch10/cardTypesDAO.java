package EX_ch10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class cardTypesDAO {
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
	
	public List<cardTypes> getCardTypesList(){
		open();
		List<cardTypes> cardTypeList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement("select * from cardTypes");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				cardTypes c = new cardTypes();
				c.setCardType(rs.getString("cardType"));
				c.setTitle(rs.getString("title"));
				
				cardTypeList.add(c);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {close();}
		return cardTypeList;
	}
}
