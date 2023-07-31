package p;

import java.io.IOException;
import java.sql.*;
import java.util.*;
import org.apache.commons.dbutils.QueryLoader;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class OrderingDAO extends MadangDAO{
	final static String QUERY_PATH = "/p/ordering_sql.properties";
	final static Map<String, String> QM;
	
	static {
		try {
			QM = QueryLoader.instance().load(QUERY_PATH);
		}catch(IOException ioe) {
			ioe.printStackTrace();
			
			throw new ExceptionInInitializerError(ioe);
		}
	}
	
	public List<Object[]> select(){
		List<Object[]> rtn = new ArrayList<>();
		
		try
//		(				Connection c = dataSource.getConnection(); 
//				PreparedStatement ps = c.prepareStatement(sqls.getProperty("select"));
//				ResultSet rs = ps.executeQuery();)
		{ 	
			QueryRunner qr = new QueryRunner(dataSource);
			ResultSetHandler<List<Object[]>> h = new ArrayListHandler();
			rtn = qr.query(QM.get("select"),h);
//			while(rs.next()) {
//				Ordering ordering = new Ordering();
////				ordering.setId(rs.getInt("id"));
//				ordering.setCustomerId(rs.getInt("customerId"));
//				ordering.setBookId(rs.getInt("bookId"));
//				ordering.setSellingPrice(rs.getInt("sellingPrice"));
//				ordering.setOrderingDate(rs.getDate("orderingDate"));
//				Customer customer = new Customer();
//				customer.setName(rs.getString("name"));
//				ordering.setCustomer(customer);
//				Book book = new Book();
//				book.setTitle(rs.getString("title"));
//				ordering.setBook(book);
//				rtn.add(ordering);
//			}
		} catch(SQLException sqle){
			sqle.printStackTrace();
		}
		
		return rtn;
	}
	
	public void insert(Ordering ordering) {
		
		try
//		(Connection c = dataSource.getConnection(); 
//				PreparedStatement ps = c.prepareStatement(QM.get("insert"));) 
		{
//			ps.setInt(1,  ordering.getCustomerId());
//			ps.setInt(2,  ordering.getBookId());
//			ps.setInt(3,  ordering.getSellingPrice());
//			ps.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
//			Object[] p = 
//			ps.executeUpdate();
			QueryRunner qr = new QueryRunner(dataSource);
			Object[] p = {ordering.getCustomerId(), ordering.getBookId(), ordering.getSellingPrice(),new java.sql. Date(new java.util.Date().getTime())};
			qr.execute( QM.get("insert"), p);
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public int selectCountByCustomerId(int customerId) {
		int rtn = -1;
	
		
//		(Connection c = dataSource.getConnection(); 
//				PreparedStatement ps = c.prepareStatement(QM.get("selectCountByCustomerId"));)
		
		try{
			QueryRunner qr = new QueryRunner(dataSource);
			ResultSetHandler<Long> h = new ScalarHandler<Long>();
			Object[] p = {customerId};
			qr.query(QM.get("selectCountByCustomerId"), h, p);
			
			
		} catch(SQLException sqle){
			sqle.printStackTrace();
		}
		
		return rtn;
	}

	public int selectCountByBookId(int bookId) {
		int rtn = -1;
		
		try
//		(Connection c = dataSource.getConnection(); 
//				PreparedStatement ps = c.prepareStatement(QM.get("selectCountByBookId"));)
		{
//			ps.setInt(1, bookId);
//			try (ResultSet rs = ps.executeQuery();) {
//				if(rs.next()) {
//					rtn = rs.getInt(1);
//				}
//			}
			QueryRunner qr = new QueryRunner(dataSource);
			ResultSetHandler<Long> h = new ScalarHandler<Long>();
			Object[] p = {bookId};
			qr.query(QM.get("selectCountByBookId"), h, p);
			
		} catch(SQLException sqle){
			sqle.printStackTrace();
		}
		
		return rtn;
		
	}

}

//SELECT o.id c.name, b.title, o.sellingPrice, o.orderingDate FROM ordering o JOIN customer c ON o.customerId = c.id JOIN book b ON o.bookId = b.id

//INSERT INTO ordering (customerId, bookId, sellingPrice, orderingDate) VALUES (?,?,?,?)