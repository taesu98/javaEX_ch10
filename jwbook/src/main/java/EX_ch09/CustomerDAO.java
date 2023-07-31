package EX_ch09;

import org.apache.commons.csv.*;

import ch09.Student;
import EX_ch09.Customer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CustomerDAO {
    
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
	
	public List<Customer> getCustomerList(){
		open();
		List<Customer> customerList = new ArrayList<>();
		
		try {
			pstmt = conn.prepareStatement("select * from customer");
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Customer c = new Customer();
				c.setId(rs.getInt("custid"));
				c.setName(rs.getString("name"));
				c.setAddress(rs.getString("address"));
				c.setPhone(rs.getString("phone"));
		
				customerList.add(c);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {close();}
		return customerList;
	}
	
	public void add(Customer c) {
		open();
		String sql = 
				"INSERT INTO customer(custid, name, address, phone) values(?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, c.getId());
			pstmt.setString(2, c.getName());
			pstmt.setString(3, c.getAddress());
			pstmt.setString(4, c.getPhone());
			
			pstmt.executeUpdate();
		} catch(Exception e) { e.printStackTrace();}
			finally {close();}
	}
}