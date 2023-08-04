package Test_ch10;

import fei1rain.BeanUtilsUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RestaurantServlet
 */
@WebServlet(description = "restaurantServlet", urlPatterns = { "/rControl" })
public class RestaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private restaurantService restaurantService;
	final private int REDIRECT_LENGTH = 10;
	
	
	public void init() throws ServletException {
		super.init();

		restaurantService = new restaurantService();

	}
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String action = StringUtils.defaultIfEmpty(request.getParameter("action"), "bill");
		
		String view = "";
		
		try {
  		switch(action) {
	  		case "bill":
	  			view = bill(request, response);
	  			break;
	  		case "addBill":
  				view = addBill(request, response);
//  				response.sendRedirect("rControl?active=bills");
  			break;
	  		case "bills":
	  			view = bills(request, response);
	  			break;
	  	} // switch
  		if(view.startsWith("redirect:")) {
  			response.sendRedirect(view.substring(REDIRECT_LENGTH));
  		} else {
  			getServletContext().getRequestDispatcher("/Test_ch10/" + view).forward(request, response);
  		}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private String bills(HttpServletRequest request, HttpServletResponse response) {
		List<Object[]> billList = restaurantService.getBills();
        request.setAttribute("billList", billList);
        
		return "bills.jsp";
	}

	public String bill(HttpServletRequest request, HttpServletResponse response) {
		 List<menus> menuList = restaurantService.getMenus();
	        /*
	        List<Card> creditCardList = restaurantService.getCardsCredit();
	        List<Card> telecomCardList = restaurantService.getCardsTelecom();
	        List<Card> okcashbagCardList = restaurantService.getCardsOkcashbag();
	        List<Card> pointCardList = restaurantService.getCardsPoint();
	         */
	        List<creditCards> creditCardList = restaurantService.getCardsByType("CREDIT");
	        List<creditCards> telecomCardList = restaurantService.getCardsByType("TELECOM");
	        List<creditCards> okcashbagCardList = restaurantService.getCardsByType("OKCASHBAG");
	        List<creditCards> pointCardList = restaurantService.getCardsByType("POINT");
	        List<coupons> couponList = restaurantService.getCoupons();
	        request.setAttribute("menuList", menuList);
	        request.setAttribute("creditCardList", creditCardList);
	        request.setAttribute("telecomCardList", telecomCardList);
	        request.setAttribute("okcashbagCardList", okcashbagCardList);
	        request.setAttribute("pointCardList", pointCardList);
	        request.setAttribute("couponList", couponList);

	        Stream<creditCards> cardStream = null;
	        cardStream = Stream.concat(creditCardList.stream(), telecomCardList.stream());
	        cardStream = Stream.concat(cardStream, okcashbagCardList.stream());
	        cardStream = Stream.concat(cardStream, pointCardList.stream());
	        List<String> cardJsonList = cardStream.map((it) -> {
	            return it.toJsonString();
	        }).collect(Collectors.toList());
	        String cardJsonArrayString = StringUtils.join(cardJsonList);
	        request.setAttribute("cardJsonArrayString", cardJsonArrayString);

	        Stream<coupons> couponStream = couponList.stream();
	        List<String> couponJsonList = couponStream.map((it) -> {
	            return it.toJsonString();
	        }).collect(Collectors.toList());
	        String couponJsonArrayString = StringUtils.join(couponJsonList);
	        request.setAttribute("couponJsonArrayString", couponJsonArrayString);

	        return "bill.jsp";
	}
	
	public String addBill(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
            Map<String, String[]> map = request.getParameterMap();

            /*
            List<Integer> lineItemsIndexes = new ArrayList<>();
            for (String key : map.keySet()) {
                if (key.startsWith("lineItems[")) {
                    lineItemsIndexes.add(Integer.parseInt(StringUtils.substringBetween(key, "lineItems[", "]")));
                }
            }
            Integer lineItemsMaxIndex = Collections.max(lineItemsIndexes);
            LineItem[] lineItems = new LineItem[lineItemsMaxIndex + 1];
            for (int i = 0; i < lineItems.length; i++) {
                lineItems[i] = new LineItem();
            }
             */
            lineItems[] lineItems = BeanUtilsUtil.createArrayProperties(lineItems.class, "lineItems", map);

            bill bill = new bill();
            bill.setLineItems(lineItems);
            BeanUtils.populate(bill, map);

            restaurantService.addBill(bill);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return "redirect:/rControl?action=bills";
    }
			
	}


