package ch09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class MandalartDAO {
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
	
	public List<String> getFirstList(){
		open();
		List<String> firstList = new ArrayList<>();
		String goal = null;
		try {
			pstmt = conn.prepareStatement("select m1.name as name, m2.name as goal from FirstMandalart m2 join Mandalart m1 on m1.id = m2.mname");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				goal = rs.getString("name");
				String first_goal = rs.getString("goal");
				firstList.add(first_goal);
			}
			firstList.add(4, goal);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {close();}
		return firstList;
	}

	public List<String> getSecondList(String goal){
		open();
		List<String> secondList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement("select m1.name as first_name, m2.name as second_name from FirstMandalart m1 join SecondMandalart m2 on m1.id = m2.nname where m1.name = ?");
			pstmt.setString(1, goal);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String second_goal = rs.getString("second_name");
				secondList.add(second_goal);
			}
			secondList.add(4, goal);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {close();}
		return secondList;
	}
	
	
}
