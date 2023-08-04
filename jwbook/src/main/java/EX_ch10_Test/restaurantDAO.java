package EX_ch10_Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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

import p.Book;

public class restaurantDAO extends dataSourceDAO{
	final static String QUERY_PATH = "/EX_ch10_Test/restaurant_sql.properties";
	final static Map<String, String> QM;
	static {
		try {
			QM = QueryLoader.instance().load(QUERY_PATH);
		}catch(IOException ioe) {
			ioe.printStackTrace();
			throw new ExceptionInInitializerError(ioe);
		}
	} 
	
	public List<menus> selectMenu() {
		List<menus> menuList = new ArrayList<>();
		try {
			QueryRunner qr = new QueryRunner(dataSource);
			ResultSetHandler<List<menus>> h = new BeanListHandler<>(menus.class);
			menuList = qr.query(QM.get("selectMenu"), h);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return menuList;
	}
	
	public List<creditCards> selectCards(){	
		List<creditCards> creditList = new ArrayList<>();
		try {
			QueryRunner qr = new QueryRunner(dataSource);
			ResultSetHandler<List<creditCards>> h = new BeanListHandler<>(creditCards.class);
			creditList = qr.query(QM.get("selectCreditCards"), h);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return creditList;
	}
	
	public List<cardTypes> selectTypes() {
		List<cardTypes> cardTypes = new ArrayList<>();
		try {
			QueryRunner qr = new QueryRunner(dataSource);
			ResultSetHandler<List<cardTypes>> h = new BeanListHandler<>(cardTypes.class);
			cardTypes = qr.query(QM.get("selectTypes"), h);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return cardTypes;
	} 
	
	public List<coupons> selectCoupons(){
		List<coupons> couponList = new ArrayList<>();
		try {
			QueryRunner qr = new QueryRunner(dataSource);
			ResultSetHandler<List<coupons>> h = new BeanListHandler<>(coupons.class);
			couponList = qr.query(QM.get("selectCoupons"), h);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return couponList;
	} 
	
	public void insertBill(bill bill) {
		try {
			QueryRunner qr = new QueryRunner(dataSource);
			ResultSetHandler<List<bill>> h = new BeanListHandler<>(bill.class);
			Object[] p = {bill.getCardId(), bill.getCouponId(), bill.getPrice(), bill.getDiscountPrice(), new java.sql.Timestamp(System.currentTimeMillis())};
			qr.query(QM.get("insertBill"), h, p);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public void insertLineItem(lineItems lineItems) {
		try {
			QueryRunner qr = new QueryRunner(dataSource);
			ResultSetHandler<List<lineItems>> h = new BeanListHandler<>(lineItems.class);
			Object[] p = {lineItems.getBillId(), lineItems.getMenuId(), lineItems.getMenuQty(), new java.sql.Timestamp(System.currentTimeMillis())};
			qr.query(QM.get("insertLineItem"), h, p);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public List<creditCards> selectCreditCardById(int id){
		List<creditCards> creditCardList = new ArrayList<>();
		
		try
		{
			QueryRunner qr = new QueryRunner(dataSource);
			ResultSetHandler<List<creditCards>> h = new BeanListHandler<>(creditCards.class);
			Object[] p = {id};
			creditCardList = qr.query(QM.get("selectCreditCardById"), h, p);
			
		} catch(SQLException sqle){
			sqle.printStackTrace();
		}
		
		return creditCardList;
	}
	
	
	public List<coupons> selectCouponById(int id){
		List<coupons> couponList = new ArrayList<>();
		
		try
		{
			QueryRunner qr = new QueryRunner(dataSource);
			ResultSetHandler<List<coupons>> h = new BeanListHandler<>(coupons.class);
			Object[] p = {id};
			couponList = qr.query(QM.get("selectCouponById"), h, p);
			
		} catch(SQLException sqle){
			sqle.printStackTrace();
		}
		
		return couponList;
	}
	
	public List<lineItems> getLineItemsByBillId(int id){
		List<lineItems> lineItems = new ArrayList<>();
		
		try
		{
			QueryRunner qr = new QueryRunner(dataSource);
			ResultSetHandler<List<lineItems>> h = new BeanListHandler<>(lineItems.class);
			Object[] p = {id};
			lineItems = qr.query(QM.get("getLineItemsByBillId"), h, p);
			
		} catch(SQLException sqle){
			sqle.printStackTrace();
		}
		
		return lineItems;
	}
	
	public List<Object[]> selectBill(){
		List<Object[]> rtn = new ArrayList<>();
		try
		{ 	
			QueryRunner qr = new QueryRunner(dataSource);
			ResultSetHandler<List<Object[]>> h = new ArrayListHandler();
			rtn = qr.query(QM.get("selectBill"),h);

		} catch(SQLException sqle){
			sqle.printStackTrace();
		}
		
		return rtn;
	}
	
}
