package pp;

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

@WebServlet(name = "Restaurant", urlPatterns = {"/rest"})
public class RestaurantServlet extends HttpServlet {

    private RestaurantService restaurantService;
    final private int REDIRECT_LENGTH = 10;

    @Override
    public void init() throws ServletException {
        super.init();

        restaurantService = new RestaurantService();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = StringUtils.defaultIfEmpty(request.getParameter("action"), "bill");

        String view = "";
        switch (action) {
            case "bill":
                view = bill(request, response);
                break;
            case "addBill":
                view = addBill(request, response);
                break;
            case "bills":
                view = bills(request, response);
                break;
            default:
                break;
        }

        if (view.startsWith("redirect:")) {
            response.sendRedirect(view.substring(REDIRECT_LENGTH));
        } else {
            getServletContext().getRequestDispatcher(view).forward(request, response);
        }
    }

    private String bills(HttpServletRequest request, HttpServletResponse response) {
        List<Object[]> billList = restaurantService.getBills();
        request.setAttribute("billList", billList);

        return "/bills.jsp";
    }

    private String bill(HttpServletRequest request, HttpServletResponse response) {
        List<Menu> menuList = restaurantService.getMenus();
        /*
        List<Card> creditCardList = restaurantService.getCardsCredit();
        List<Card> telecomCardList = restaurantService.getCardsTelecom();
        List<Card> okcashbagCardList = restaurantService.getCardsOkcashbag();
        List<Card> pointCardList = restaurantService.getCardsPoint();
         */
        List<Card> creditCardList = restaurantService.getCardsByType("CREDIT");
        List<Card> telecomCardList = restaurantService.getCardsByType("TELECOM");
        List<Card> okcashbagCardList = restaurantService.getCardsByType("OKCASHBAG");
        List<Card> pointCardList = restaurantService.getCardsByType("POINT");
        List<Coupon> couponList = restaurantService.getCoupons();
        request.setAttribute("menuList", menuList);
        request.setAttribute("creditCardList", creditCardList);
        request.setAttribute("telecomCardList", telecomCardList);
        request.setAttribute("okcashbagCardList", okcashbagCardList);
        request.setAttribute("pointCardList", pointCardList);
        request.setAttribute("couponList", couponList);

        Stream<Card> cardStream = null;
        cardStream = Stream.concat(creditCardList.stream(), telecomCardList.stream());
        cardStream = Stream.concat(cardStream, okcashbagCardList.stream());
        cardStream = Stream.concat(cardStream, pointCardList.stream());
        List<String> cardJsonList = cardStream.map((it) -> {
            return it.toJsonString();
        }).collect(Collectors.toList());
        String cardJsonArrayString = StringUtils.join(cardJsonList);
        request.setAttribute("cardJsonArrayString", cardJsonArrayString);

        Stream<Coupon> couponStream = couponList.stream();
        List<String> couponJsonList = couponStream.map((it) -> {
            return it.toJsonString();
        }).collect(Collectors.toList());
        String couponJsonArrayString = StringUtils.join(couponJsonList);
        request.setAttribute("couponJsonArrayString", couponJsonArrayString);

        return "/bill.jsp";
    }

    private String addBill(HttpServletRequest request, HttpServletResponse response) {
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
            LineItem[] lineItems = BeanUtilsUtil.createArrayProperties(LineItem.class, "lineItems", map);

            Bill bill = new Bill();
            bill.setLineItems(lineItems);
            BeanUtils.populate(bill, map);

            restaurantService.addBill(bill);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return "redirect:/restaurant?action=bills";
    }
}
