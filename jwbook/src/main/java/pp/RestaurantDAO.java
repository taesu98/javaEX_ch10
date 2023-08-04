package pp;

import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.*;

import java.io.IOException;
import java.util.*;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;

public class RestaurantDAO {

    final static String QUERY_PATH = "/p/restaurant_queries.properties";
    final static Map<String, String> QM;

    private DataSource dataSource;

    static {
        try {
            QM = QueryLoader.instance().load(QUERY_PATH);
        } catch (IOException ioe) {
            ioe.printStackTrace();

            throw new ExceptionInInitializerError(ioe);
        }
    }

    public RestaurantDAO() {
        try {
            Context initialContext = new InitialContext();
            Context envContext = (Context) initialContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/jwbookdb");
        } catch (NamingException ne) {
            ne.printStackTrace();
        }
    }

    public List<Menu> getMenus() {
        List<Menu> rtn = new ArrayList<>();

        try ( Connection c = dataSource.getConnection();) {
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<List<Menu>> h = new BeanListHandler<>(Menu.class);
            rtn = qr.query(c, QM.get("getMenus"), h);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return rtn;
    }

    public Map<Long, Menu> getMenusByIds(String ids) {
        Map<Long, Menu> rtn = new HashMap<>();

        try ( Connection c = dataSource.getConnection();) {
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<Map<Long, Menu>> h = new BeanMapHandler<>(Menu.class, "id");
            rtn = qr.query(c, QM.get("getMenusByIds").replace(":ids:", ids), h);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return rtn;
    }

    public List<Card> getCardsCredit() {
        List<Card> rtn = new ArrayList<>();

        try ( Connection c = dataSource.getConnection();) {
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<List<Card>> h = new BeanListHandler<>(Card.class);
            rtn = qr.query(c, QM.get("getCardsCredit"), h);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return rtn;
    }

    public List<Card> getCardsTelecom() {
        List<Card> rtn = new ArrayList<>();

        try ( Connection c = dataSource.getConnection();) {
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<List<Card>> h = new BeanListHandler<>(Card.class);
            rtn = qr.query(c, QM.get("getCardsTelecom"), h);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return rtn;
    }

    public List<Card> getCardsOkcashbag() {
        List<Card> rtn = new ArrayList<>();

        try ( Connection c = dataSource.getConnection();) {
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<List<Card>> h = new BeanListHandler<>(Card.class);
            rtn = qr.query(c, QM.get("getCardsOkcashbag"), h);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return rtn;
    }

    public List<Card> getCardsPoint() {
        List<Card> rtn = new ArrayList<>();

        try ( Connection c = dataSource.getConnection();) {
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<List<Card>> h = new BeanListHandler<>(Card.class);
            rtn = qr.query(c, QM.get("getCardsPoint"), h);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return rtn;
    }

    public List<Card> getCardsByType(String type) {
        List<Card> rtn = new ArrayList<>();

        try ( Connection c = dataSource.getConnection();) {
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<List<Card>> h = new BeanListHandler<>(Card.class);
            Object[] p = {type};
            rtn = qr.query(c, QM.get("getCardsByType"), h, p);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return rtn;
    }

    public Card getCardById(long id) {
        Card rtn = null;

        try ( Connection c = dataSource.getConnection();) {
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<Card> h = new BeanHandler<>(Card.class);
            Object[] p = {id};
            rtn = qr.query(c, QM.get("getCardById"), h, p);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return rtn;
    }

    public List<Coupon> getCoupons() {
        List<Coupon> rtn = new ArrayList<>();

        try ( Connection c = dataSource.getConnection();) {
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<List<Coupon>> h = new BeanListHandler<>(Coupon.class);
            rtn = qr.query(c, QM.get("getCoupons"), h);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return rtn;
    }

    public Coupon getCouponById(long id) {
        Coupon rtn = null;

        try ( Connection c = dataSource.getConnection();) {
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<Coupon> h = new BeanHandler<>(Coupon.class);
            Object[] p = {id};
            rtn = qr.query(c, QM.get("getCouponById"), h, p);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return rtn;
    }

    public void addBill(Bill bill) {
        try ( Connection c = dataSource.getConnection();) {
            c.setAutoCommit(false);
            try {
                QueryRunner qr = new QueryRunner();
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
                for (LineItem lineItem : bill.getLineItems()) {
                    p = new Object[]{billId, lineItem.getMenuId(), lineItem.getMenuQuantity()};
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
            QueryRunner qr = new QueryRunner();
            ResultSetHandler<List<Object[]>> h = new BillsHandler(c, QM);
            rtn = qr.query(c, QM.get("getBills"), h);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return rtn;
    }
}
