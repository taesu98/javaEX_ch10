package EX_ch09;

import org.apache.commons.csv.*;
import EX_ch09.Ordering;
import ch09.Student;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class OrderingDAO {

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
	
	public List<Ordering> getOrderingList(){
		open();
		List<Ordering> orders = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement
					("select o.custid,c.name,b.bookid, b.bookname,saleprice, o.orderdate from orders o join book b on o.bookid = b.bookid join customer c on o.custid = c.custid;");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Ordering o = new Ordering();
				o.setId(rs.getInt("orderid"));
				o.setCustomerId(rs.getInt("custid"));
				o.setBookId(rs.getInt("bookid"));
				o.setSellingPrice(rs.getInt("saleprice"));
				o.setOrderingDate(rs.getDate("orderdate"));
				Customer c = new Customer();
				c.setName(rs.getString("name"));
				o.setCustomer(c);
				orders.add(o);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {close();}
		return orders;
	}
	
	public void add(Ordering o ) {
//		java.util.Date ud = new java.util.Date();
//		java.sql.Date sd = new java.sql.Date(ud.getTime());
		open();
		String sql = 
				"INSERT INTO orders(orderid, custid, bookid, saleprice, orderdate) values(?,?,?,?,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, o.getId());
			pstmt.setInt(2, o.getCustomerId());
			pstmt.setInt(3, o.getBookId());
			pstmt.setInt(4, o.getSellingPrice());
			pstmt.setDate(5, new java.sql.Date(new Date().getTime()));
			
			pstmt.executeUpdate();
		} catch(Exception e) { e.printStackTrace();}
			finally {close();}
	}
	
	

}