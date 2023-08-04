package Test_ch10;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryLoader;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.BeanMapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import p.Book;
import pp.Bill;
import pp.BillsHandler;
import pp.Card;
import pp.Coupon;
import pp.LineItem;
import pp.Menu;

public class restaurantDAO extends dataSourceDAO{
	final static String QUERY_PATH = "/Test_ch10/restaurant_sql.properties";
	final static Map<String, String> QM;
	static {
		try {
			QM = QueryLoader.instance().load(QUERY_PATH);
		}catch(IOException ioe) {
			ioe.printStackTrace();
			throw new ExceptionInInitializerError(ioe);
		}
	} 
	
	public List<menus> getMenus() {
		List<menus> menuList = new ArrayList<>();
		try {
			QueryRunner qr = new QueryRunner(dataSource);
			ResultSetHandler<List<menus>> h = new BeanListHandler<>(menus.class);
			menuList = qr.query(QM.get("getMenus"), h);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return menuList;
	}
	
	public Map<Integer, menus> getMenusByIds(String ids) {
        Map<Integer, menus> rtn = new HashMap<>();

        try{
            QueryRunner qr = new QueryRunner(dataSource);
            ResultSetHandler<Map<Integer, menus>> h = new BeanMapHandler<>(menus.class, "id");
            rtn = qr.query(QM.get("getMenusByIds").replace(":ids:", ids), h);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return rtn;
    }
	
	public List<creditCards> getCardsCredit() {
        List<creditCards> rtn = new ArrayList<>();

        try {
            QueryRunner qr = new QueryRunner(dataSource);
            ResultSetHandler<List<creditCards>> h = new BeanListHandler<>(creditCards.class);
            rtn = qr.query(QM.get("getCardsCredit"), h);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return rtn;
    }

    public List<creditCards> getCardsTelecom() {
        List<creditCards> rtn = new ArrayList<>();

        try{
            QueryRunner qr = new QueryRunner(dataSource);
            ResultSetHandler<List<creditCards>> h = new BeanListHandler<>(creditCards.class);
            rtn = qr.query(QM.get("getCardsTelecom"), h);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return rtn;
    }

    public List<creditCards> getCardsOkcashbag() {
        List<creditCards> rtn = new ArrayList<>();

        try{
            QueryRunner qr = new QueryRunner(dataSource);
            ResultSetHandler<List<creditCards>> h = new BeanListHandler<>(creditCards.class);
            rtn = qr.query(QM.get("getCardsOkcashbag"), h);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return rtn;
    }

    public List<creditCards> getCardsPoint() {
        List<creditCards> rtn = new ArrayList<>();

        try{
            QueryRunner qr = new QueryRunner(dataSource);
            ResultSetHandler<List<creditCards>> h = new BeanListHandler<>(creditCards.class);
            rtn = qr.query(QM.get("getCardsPoint"), h);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return rtn;
    }

    public List<creditCards> getCardsByType(String cardType) {
        List<creditCards> rtn = new ArrayList<>();

        try {
            QueryRunner qr = new QueryRunner(dataSource);
            ResultSetHandler<List<creditCards>> h = new BeanListHandler<>(creditCards.class);
            Object[] p = {cardType};
            rtn = qr.query(QM.get("getCardsByType"), h, p);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return rtn;
    }

    public creditCards getCardById(int id) {
    	creditCards rtn = null;

        try ( Connection c = dataSource.getConnection();) {
            QueryRunner qr = new QueryRunner(dataSource);
            ResultSetHandler<creditCards> h = new BeanHandler<>(creditCards.class);
            Object[] p = {id};
            rtn = qr.query(c, QM.get("getCardById"), h, p);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return rtn;
    }

    public List<coupons> getCoupons() {
        List<coupons> rtn = new ArrayList<>();

        try ( Connection c = dataSource.getConnection();) {
            QueryRunner qr = new QueryRunner(dataSource);
            ResultSetHandler<List<coupons>> h = new BeanListHandler<>(coupons.class);
            rtn = qr.query(c, QM.get("getCoupons"), h);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return rtn;
    }

    public coupons getCouponById(int id) {
    	coupons rtn = null;

        try ( Connection c = dataSource.getConnection();) {
            QueryRunner qr = new QueryRunner(dataSource);
            ResultSetHandler<coupons> h = new BeanHandler<>(coupons.class);
            Object[] p = {id};
            rtn = qr.query(c, QM.get("getCouponById"), h, p);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return rtn;
    }

    public void addBill(bill bill) {
        try ( Connection c = dataSource.getConnection();) {
            c.setAutoCommit(false);
            try {
                QueryRunner qr = new QueryRunner(dataSource);
                ScalarHandler<Long> h = new ScalarHandler<>();
                Object[] p = {};
                p = new Object[]{
                    bill.getCardId() == -1 ? null : bill.getCardId(),
                    bill.getCouponId() == -1 ? null : bill.getCouponId(),
                    bill.getPrice(),
                    bill.getDiscountPrice(),
                    new java.util.Date()
                };
                long billId = qr.insert(c, QM.get("addBill"), h, p);
                for (lineItems lineItem : bill.getLineItems()) {
                    p = new Object[]{billId, lineItem.getMenuId(), lineItem.getMenuQty()};
                    qr.insert(c, QM.get("addLineItem"), h, p);
                }
                c.commit();
            } catch (SQLException sqle) {
                c.rollback();
                sqle.printStackTrace();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public List<Object[]> getBills() {
        List<Object[]> rtn = new ArrayList<>();

        try ( Connection c = dataSource.getConnection();) {
            QueryRunner qr = new QueryRunner(dataSource);
            ResultSetHandler<List<Object[]>> h = new BillsHandler(c, QM);
            rtn = qr.query(c, QM.get("getBills"), h);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return rtn;
    }
	
}
