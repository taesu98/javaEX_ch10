package ch10;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryLoader;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import p.Book;

public class NewsDAO {
	final static String QUERY_PATH = "/ch10/news_sql.properties";
	final static Map<String, String> QM;
	DataSource dataSource;
	
	static {
		try {
			QM = QueryLoader.instance().load(QUERY_PATH);
		}catch(IOException ioe) {
			ioe.printStackTrace();
			
			throw new ExceptionInInitializerError(ioe);
		}
	}
	public NewsDAO(){
		try {
			Context initialContext = new InitialContext();
			Context envContext = (Context) initialContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/jwbookdb");
		}catch(NamingException ne) {
			ne.printStackTrace();
		}
	}
//	
//	public Connection open() {
//		Connection conn = null;
//		try {
//			Class.forName(QUERY_PATH);
//			conn = DriverManager.getConnection(QUERY_PATH,"jwbook","1234");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return conn;
//	}
	
	public void addNews(News n) throws Exception {
//		Connection conn = open();
		
//		String sql = "insert into news(title, img, date, content) values(?,?,CURRENT_TIMESTAMP(),?)";
//		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		try{
			QueryRunner qr = new QueryRunner(dataSource);
			Object[] p = {n.getTitle(), n.getImg(), n.getContent()};
			qr.execute( QM.get("addNews"), p);
		} catch(Exception e) {
			e.printStackTrace();
		} 
//			finally {
////			pstmt.close();
//			conn.close();
//		}
	}
	
	public List<News> getAll() throws Exception {
//		Connection conn = open();
		List<News> newsList = new ArrayList<>();
		
//		String sql = "select aid, title, FORMATDATETIME(date,'yyyy-MM-dd hh:mm:ss') as cdate from news";
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		ResultSet rs = pstmt.executeQuery();
		
		try{
			QueryRunner qr = new QueryRunner(dataSource);
			ResultSetHandler<List<News>> h = new BeanListHandler<>(News.class);
			newsList = qr.query(QM.get("getAll"), h);
//			while(rs.next()) {
//				News n = new News();
//				n.setAid(rs.getInt("aid"));
//				n.setTitle(rs.getString("title"));
//				n.setDate(rs.getString("cdate"));
//				
//				newsList.add(n);
//			}
//			return newsList;
		} catch(SQLException sqle){
			sqle.printStackTrace();
		}
		
		return newsList;
	}
	
	public News getNews(int aid) throws SQLException {
//		Connection conn = open();
		News rtn = null;
		
//		News n = new News();
//		String sql = "select aid, title, img, FORMATDATETIME(date,'yyyy-MM-dd hh:mm:ss') as cdate, content from news where aid = ?";
//		
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		pstmt.setInt(1, aid);
//		ResultSet rs = pstmt.executeQuery();
//		
//		rs.next();
		
		try
//		(conn; pstmt; rs)
		{
//			n.setAid(rs.getInt("aid"));
//			n.setTitle(rs.getString("title"));
//			n.setImg(rs.getString("img"));
//			n.setDate(rs.getString("cdate"));
//			n.setContent(rs.getString("content"));
//			return n;
			
			QueryRunner qr = new QueryRunner(dataSource);
			ResultSetHandler<News> h = new BeanHandler<>(News.class);
			Object[] p = {aid};
			rtn = qr.query(QM.get("getNews"), h, p);
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return rtn;
	}
	
	public void delNews (int aid) throws SQLException{
//		Connection conn = open();
		
//		String sql = "delete from news where aid = ?";
//		PreparedStatement pstmt = conn.prepareStatement(sql);
//		
//		try
//		(conn; pstmt)
//		{
//			pstmt.setInt(1, aid);
//			//삭제된 뉴스 기사가 없을 경우
//			if(pstmt.executeUpdate() == 0) {
//				throw new SQLException("DB에러");
//			}
//		}
		try {
			QueryRunner qr = new QueryRunner(dataSource);
			Object[] p = {aid};
			qr.execute(QM.get("delNews"), p);
		} catch(SQLIntegrityConstraintViolationException sqli) {
			System.out.println(sqli.getMessage());
			throw new SQLException("DB에러");
		}
	}
}
