package EX_ch10;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryLoader;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import ch10.News;

public class restaurantDAO {
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
	public restaurantDAO(){
		try {
			Context initialContext = new InitialContext();
			Context envContext = (Context) initialContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/jwbookdb");
		}catch(NamingException ne) {
			ne.printStackTrace();
		}
	}
	
	public void addOrder(News n) throws Exception {
		
		try{
			QueryRunner qr = new QueryRunner(dataSource);
			Object[] p = {n.getTitle(), n.getImg(), n.getContent()};
			qr.execute( QM.get("addOrder"), p);
		} catch(Exception e) {
			e.printStackTrace();
		} 

	}
	
	public List<bills> getAll() throws Exception {
		List<bills> billsList = new ArrayList<>();
		
		try{
			QueryRunner qr = new QueryRunner(dataSource);
			ResultSetHandler<List<bills>> h = new BeanListHandler<>(bills.class);
			billsList = qr.query(QM.get("getAll"), h);

		} catch(SQLException sqle){
			sqle.printStackTrace();
		}
		
		return billsList;
	}
	
	public bills getOrder(int id) throws SQLException {
		bills rtn = null;	
		try

		{
			QueryRunner qr = new QueryRunner(dataSource);
			ResultSetHandler<bills> h = new BeanHandler<>(bills.class);
			Object[] p = {id};
			rtn = qr.query(QM.get("getOrder"), h, p);
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
		return rtn;
	}

	
}
