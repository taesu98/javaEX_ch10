package EX_ch10_Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.websocket.SendResult;

import java.util.*;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;

import EX_ch10.bills;
import EX_ch10.orders;
/**
 * Servlet implementation class restaurantControl
 */
@WebServlet("/restaurantControl")
public class restaurantControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private restaurantDAO restaurantDAO;
	private restaurantService restaurantService;
	private ServletContext ctx;
	private final String START_PAGE = "EX_ch10_Test/restaurant.jsp";
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		restaurantDAO = new restaurantDAO();
		restaurantService = new restaurantService();
		ctx = getServletContext();
	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = StringUtils.defaultIfEmpty(request.getParameter("action"), "bills");
		
		restaurantDAO = new restaurantDAO();
		
		String view = "";

		try {
  		switch(action) {
	  		case "bills":
	  			view = bills(request, response);
	  			break;
	  		case "addBills":
  				addBills(request, response);
  				response.sendRedirect("restaurantControl?active=billsList");
  			break;
	  		case "billsList":
	  			view = billsList(request, response);
	  			break;
	  	} // switch
		  if(StringUtils.isNotEmpty(view)) {
	  	getServletContext().getRequestDispatcher("/EX_ch10_Test/" + view).forward(request, response);
		  }
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private String billsList(HttpServletRequest request, HttpServletResponse response) {
		List<Object[]> billList = restaurantService.get();
		List<lineItems> lineItems;
		lineItems = restaurantDAO.getLineItemsByBillId();
		
        request.setAttribute("billList", billList);
        request.setAttribute("ordersList", ordersList);
		return "bills.jsp";
	}

	public String bills(HttpServletRequest request, HttpServletResponse response) {
		List<menus> menuList;
		List<creditCards> creditCardList;
		List<cardTypes> cardTypes;
		List<coupons> couponList;
		try {
			menuList = restaurantDAO.selectMenu();
			creditCardList = restaurantDAO.selectCards();
			cardTypes = restaurantDAO.selectTypes();
			couponList = restaurantDAO.selectCoupons();
			
			request.setAttribute("menuList", menuList);
			request.setAttribute("creditCardList", creditCardList);
			request.setAttribute("cardTypes", cardTypes);
			request.setAttribute("couponList", couponList);
			
//			List<String> memus = new ArrayList<>();
//			for(menus m : menuList) {
//				memus.add(m.toJsonString());
//			} // end for
//			String jsonArrayStringMenus = StringUtils.join(memus);
//			request.setAttribute("menus", jsonArrayStringMenus);
//			
//			List<String> coupons = new ArrayList<>();
//			for(coupons c : couponList) {
//				coupons.add(c.toJsonString());
//			} // end for
//			String jsonArrayStringCoupons = StringUtils.join(coupons);
//			request.setAttribute("coupons", jsonArrayStringCoupons);
//			
//			List<String> cardTypeList = new ArrayList<>();
//			for(cardTypes ct : cardTypes) {
//				cardTypeList.add(ct.toJsonString());
//			} // end for
//			String jsonArrayStringCardTypes = StringUtils.join(cardTypeList);
//			request.setAttribute("cardTypeList", jsonArrayStringCardTypes);
			
		} catch (Exception e) {
			e.printStackTrace();
			ctx.log("메뉴 목록 생성과정에서 문제 발생 !");
			request.setAttribute("error", "메뉴 목록이 정상적으로 처리되지 않았습니다.");
		}
		return "restaurant.jsp";
	}
	
	public void addBills(HttpServletRequest request, HttpServletResponse response) throws IOException {
		bill bill = new bill();
		try {
			BeanUtils.populate(bill, request.getParameterMap());
			String[] menuIds = request.getParameterValues("menuId");
			String[] menuQtys = request.getParameterValues("menuQty");
			lineItems[] lineItems = new lineItems[menuIds.length];
			for(int i = 0; i < lineItems.length; i++) {
				lineItems item = new lineItems();
				item.setMenuId(Long.parseLong(menuIds[i]));
				item.setMenuQty(Integer.parseInt(menuQtys[i]));
				lineItems[i] = item;
			}
			bill.setLineItems(lineItems);
			restaurantService.calc(bill);
			
			
			response.sendRedirect("restaurantControl?action=billsList");
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
