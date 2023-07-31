package p;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import org.apache.commons.dbutils.QueryLoader;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;


public class BookDAO extends MadangDAO{
	final static String QUERY_PATH = "/p/book_sql.properties";
	final static Map<String, String> QM;
	
	static {
		try {
			QM = QueryLoader.instance().load(QUERY_PATH);
		}catch(IOException ioe) {
			ioe.printStackTrace();
			
			throw new ExceptionInInitializerError(ioe);
		}
	}
	
	public BookDAO() {
		super();
	}
	
	public List<Book> select(){
		List<Book> rtn = new ArrayList<>();
		
		try
//		(Connection c = dataSource.getConnection(); 
//				PreparedStatement ps = c.prepareStatement(sql);
//				ResultSet rs = ps.executeQuery();)
		{
			QueryRunner qr = new QueryRunner(dataSource);
			ResultSetHandler<List<Book>> h = new BeanListHandler<>(Book.class);
			rtn = qr.query(QM.get("select"), h);
//			while(rs.next()) {
//				Book book = new Book();
//				book.setId(rs.getInt("id"));
//				book.setTitle(rs.getString("title"));
//				book.setPublisher(rs.getString("publisher"));
//				book.setPrice(rs.getInt("price"));
//				rtn.add(book);
//			}
		} catch(SQLException sqle){
			sqle.printStackTrace();
		}
		
		return rtn;
	}
	
	public Book selectById(int id){
		Book rtn = null;
		
		try
//		(Connection c = dataSource.getConnection(); 
//				PreparedStatement ps = c.prepareStatement(sql);)
		{
			QueryRunner qr = new QueryRunner(dataSource);
			ResultSetHandler<Book> h = new BeanHandler<>(Book.class);
			Object[] p = {id};
			rtn = qr.query(QM.get("select_By_Id"), h, p);
//			ps.setInt(1, id);
//			try (ResultSet rs = ps.executeQuery();) {
//				if(rs.next()) {
//					rtn = new Book();
//					rtn.setId(rs.getInt("id"));
//					rtn.setTitle(rs.getString("title"));
//					rtn.setPublisher(rs.getString("publisher"));
//					rtn.setPrice(rs.getInt("price"));
//				}
//			}
			
		} catch(SQLException sqle){
			sqle.printStackTrace();
		}
		
		return rtn;
	}
	
	public void insert(Book book) {

		try
//		(Connection c = dataSource.getConnection(); 
//				PreparedStatement ps = c.prepareStatement(sql);)
		{
			QueryRunner qr = new QueryRunner(dataSource);
			Object[] p = {book.getTitle(), book.getPublisher(), book.getPrice()};
			qr.execute( QM.get("insert"), p);
//			ps.setString(1,  book.getTitle());
//			ps.setString(2,  book.getPublisher());
//			ps.setInt(3,  book.getPrice());
//			ps.executeUpdate();
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public void update(Book book){
		
		try
//		(Connection c = dataSource.getConnection(); 
//				PreparedStatement ps = c.prepareStatement(sql);) 
		{
			QueryRunner qr = new QueryRunner(dataSource);
			Object[] p = {book.getTitle(), book.getPublisher(), book.getPrice(), book.getId()};
			qr.execute( QM.get("update"), p);
//			ps.setString(1,  book.getTitle());
//			ps.setString(2,  book.getPublisher());
//			ps.setInt(3,  book.getPrice());
//			ps.setInt(4, book.getId());
//			ps.executeUpdate();
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public int selectCountByBookId(int bookId) {
		int rtn = -1;

		try(Connection c = dataSource.getConnection(); 
				PreparedStatement ps = c.prepareStatement(QM.get("selectCountByBookId"));){
			ps.setInt(1, bookId);
			try (ResultSet rs = ps.executeQuery();) {
				if(rs.next()) {
					rtn = rs.getInt(1);
				}
			}
			
		} catch(SQLException sqle){
			sqle.printStackTrace();
		}
		
		return rtn;
	}
	
	public void delete(int id) throws HasOrderingException{
		
		try
//		(Connection c = dataSource.getConnection(); 
//				PreparedStatement ps = c.prepareStatement(sql);)
		{
			QueryRunner qr = new QueryRunner(dataSource);
			Object[] p = {id};
			qr.execute(QM.get("delete"), p);
//			ps.setInt(1, id);
//			ps.executeUpdate();
		}catch(SQLIntegrityConstraintViolationException sqli) {
//			sqli.printStackTrace();
			System.out.println(sqli.getMessage());
			throw new HasOrderingException("Book has ordering.");
		}
		catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	
}

//SELECT id, title, publisher, price FROM book;
//
//SELECT id, title, publisher, price FROM book WHERE id = ?
