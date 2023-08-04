package EX_ch10;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import EX_ch09.Customer;
import EX_ch10.bills;

/**
 * Servlet implementation class rastaurantServlet
 */
@WebServlet("/restaurant")
public class restaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    menusDAO menusService;
    creditCardsDAO creditService;
    cardTypesDAO cardTypeService;
    couponsDAO couponsService;
    billsDAO billsService;
    ordersDAO ordersService;

    @Override
    public void init() throws ServletException {
        super.init();
        
        menusService = new menusDAO();
        creditService = new creditCardsDAO();
        cardTypeService = new cardTypesDAO();
        couponsService = new couponsDAO();
        billsService = new billsDAO();
        ordersService = new ordersDAO();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = StringUtils.defaultIfEmpty(request.getParameter("action"), "menus");

        String view = "";
        switch (action) {
            case "menus":
                view = menus(request, response);
                break;                       
            case "addBill":
            	view = addBill(request, response);
            	break;
            case "Bills":
            	view = Bills(request, response);
          
            default:
        	    break;
           
        }

        if (StringUtils.isNotBlank(view)) {
            getServletContext().getRequestDispatcher("/EX_ch10/" + view).forward(request, response);
        }
    }
//    private String setMenus(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        try {
//        	int id = Integer.parseInt(StringUtils.defaultIfEmpty(request.getParameter("id"),"-1"));
//            menus menus = new menus();
//            BeanUtils.populate(menus, request.getParameterMap());
//            menus.setId(id);
//            
//            if (menus.getId() == -1) {
//            	billsService.addBils(menus);
//            } else {
////                customerService.set(customer);
//            }
//            response.sendRedirect("madangControl?action=customers");
//            
//        } catch (IllegalAccessException | InvocationTargetException e) {
//            System.out.println(e.getMessage());
//        }
//        return "customers.jsp";
//    }
    
    

	private String Bills(HttpServletRequest request, HttpServletResponse response) {
    	List<menus> menusList = menusService.getMenusList();       
        List<creditCards> creditCardList = creditService.getCreditCardsList();
        List<cardTypes> cardTypeList = cardTypeService.getCardTypesList();
        List<coupons> couponList = couponsService.getCouponList();
        
        request.setAttribute("menusList", menusList);
        request.setAttribute("creditCardList", creditCardList);
        request.setAttribute("cardTypeList", cardTypeList);
        request.setAttribute("couponList", couponList);
        
		return "bills.jsp";
	}

	private String addBill(HttpServletRequest request, HttpServletResponse response) {
//		List<menus> menusList = menusService.getMenusList();       
//        List<creditCards> creditCardList = creditService.getCreditCardsList();
//        List<cardTypes> cardTypeList = cardTypeService.getCardTypesList();
//        List<coupons> couponList = couponsService.getCouponList();
//		Map<String, String[]> map = request.getParameterMap();
		
		
        List<bills> billsList = billsService.getBillsList();
        List<orders> ordersList = ordersService.getOrdersList();
        Integer billId = null;
        try {
            bills bills = new bills();
            BeanUtils.populate(bills, request.getParameterMap());
            billId = billsService.addBils(bills);
            
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.getMessage());
        }
        try {
            orders orders = new orders();
            BeanUtils.populate(orders, request.getParameterMap());
            billsService.addOrders(orders, billId);
            
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.getMessage());
        }
        
//        request.setAttribute("menusList", menusList);
//        request.setAttribute("creditCardList", creditCardList);
//        request.setAttribute("cardTypeList", cardTypeList);
//        request.setAttribute("couponList", couponList);
        request.setAttribute("billsList", billsList);
        request.setAttribute("ordersList", ordersList);
        
		return "bills.jsp";
	}


	private String menus(HttpServletRequest request, HttpServletResponse response) {
    	List<menus> menusList = menusService.getMenusList();       
        List<creditCards> creditCardList = creditService.getCreditCardsList();
        List<cardTypes> cardTypeList = cardTypeService.getCardTypesList();
        List<coupons> couponList = couponsService.getCouponList();
        
        request.setAttribute("menusList", menusList);
        request.setAttribute("creditCardList", creditCardList);
        request.setAttribute("cardTypeList", cardTypeList);
        request.setAttribute("couponList", couponList);
       
        return "restaurant.jsp";
    }
}
