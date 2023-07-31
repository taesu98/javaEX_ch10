package EX_ch09;

import org.apache.commons.csv.*;

import EX_ch09.Book;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class BookDAO {

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
		List<Book> bookList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement("select * from book");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Book b = new Book();
				b.setId(rs.getInt("bookid"));
				b.setTitle(rs.getString("bookname"));
				b.setPublisher(rs.getString("publisher"));
				b.setPrice(rs.getInt("price"));
				
				bookList.add(b);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {close();}
		return bookList;
	}
	
	public void add(Book b) {
		open();
		String sql = 
				"INSERT INTO book(bookid, name, address, phone) values(?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, b.getId());
			pstmt.setString(2, b.getTitle());
			pstmt.setString(3, b.getPublisher());
			pstmt.setInt(4, b.getPrice());
			
			pstmt.executeUpdate();
		} catch(Exception e) { e.printStackTrace();}
			finally {close();}
	}
}