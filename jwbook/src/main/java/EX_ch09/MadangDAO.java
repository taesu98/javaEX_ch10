package EX_ch09;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import p.Book;
import p.Customer;
import p.Ordering;

public class MadangDAO {
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
	
	public List<Book> getBookList(){
		open();
		List<Book> books = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement("select * from book");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Book b = new Book();
				b.setId(rs.getInt("id"));
				b.setTitle(rs.getString("title"));
				b.setPublisher(rs.getString("publisher"));
				b.setPrice(rs.getInt("price"));
				
				books.add(b);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {close();}
		return books;
	}
	
	public List<Customer> getCustomerList(){
		open();
		List<Customer> customers = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement("select * from customer");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Customer c = new Customer();
				c.setId(rs.getInt("id"));
				c.setName(rs.getString("name"));
				c.setAddress(rs.getString("address"));
				c.setPhone(rs.getString("phone"));
				
				customers.add(c);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {close();}
		return customers;
	}
	
	public List<Ordering> getOrderingList(){
		open();
		List<Ordering> orders = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement("select * from orders");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Ordering o = new Ordering();
				o.setId(rs.getInt("id"));
				o.setCustomerId(rs.getInt("custid"));
				o.setBookId(rs.getInt("bookid"));
				o.setSellingPrice(rs.getInt("saleprice"));
				o.setOrderingDate(rs.getDate("orderdate"));
				
				orders.add(o);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {close();}
		return orders;
	}
}
