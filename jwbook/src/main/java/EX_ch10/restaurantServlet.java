package EX_ch10;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

/**
 * Servlet implementation class rastaurantServlet
 */
@WebServlet("/restaurant")
public class restaurantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    menusDAO menusService;
    creditCardsDAO creditService;
    cardTypesDAO cardTypeService;

    @Override
    public void init() throws ServletException {
        super.init();
        
        menusService = new menusDAO();
        creditService = new creditCardsDAO();
        cardTypeService = new cardTypesDAO();
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
            case "creditCards":
                view = creditCards(request, response);
                break;
            case "cardTypes":
                view = cardTypes(request, response);
                break;
            default:
        	    break;
           
        }

        if (StringUtils.isNotBlank(view)) {
            getServletContext().getRequestDispatcher("/EX_ch10/" + view).forward(request, response);
        }
    }
    
    private String cardTypes(HttpServletRequest request, HttpServletResponse response) {
    	List<cardTypes> cardTypeList = cardTypeService.getCardTypesList();

        request.setAttribute("cardTypeList", cardTypeList);
        
        return "restaurant.jsp";
	}

	private String creditCards(HttpServletRequest request, HttpServletResponse response) {
    	List<creditCards> creditCardList = creditService.getCreditCardsList();

        request.setAttribute("creditCardList", creditCardList);
        
        return "restaurant.jsp";
	}

	private String menus(HttpServletRequest request, HttpServletResponse response) {
    	List<menus> menusList = menusService.getMenusList();       
        List<creditCards> creditCardList = creditService.getCreditCardsList();
        List<cardTypes> cardTypeList = cardTypeService.getCardTypesList();
        
        request.setAttribute("menusList", menusList);
        request.setAttribute("creditCardList", creditCardList);
        request.setAttribute("cardTypeList", cardTypeList);
       
        return "restaurant.jsp";
    }
}
