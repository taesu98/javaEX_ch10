package Test_ch10;

import java.sql.*;
import java.util.*;
import javax.naming.*;
import javax.sql.DataSource;

public class dataSourceDAO {
protected DataSource dataSource;
	
	public dataSourceDAO() {
		try {
			Context initialContext = new InitialContext();
			Context envContext = (Context) initialContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/jwbookdb");
		}catch(NamingException ne) {
			ne.printStackTrace();
		}
	}
}
