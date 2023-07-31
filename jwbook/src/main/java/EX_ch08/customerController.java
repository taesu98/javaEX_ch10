package EX_ch08;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ccontrol")
public class customerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    customerService service; 
    bookService bservice;
    orderService oservice;
    
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	service = new customerService();
    	bservice = new bookService();
    	oservice = new orderService();
    }
    
    public customerController() {
        service = new customerService();
        bservice = new bookService();
        oservice = new orderService();
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		String action = request.getParameter("action");
		String view = "";
		
		if(request.getParameter("action") == null) {
			getServletContext().getRequestDispatcher("/ccontrol?action=list")
			.forward(request, response);
		} else {
			switch(action) {
			case "list": view = list(request, response); break;
			case "info": view = info(request, response); break;
			}
			getServletContext().getRequestDispatcher("/Ex_useBean/"+view)
			.forward(request, response);
		}
	}
//    private String addOrdering(HttpServletRequest request, HttpServletResponse response) throws IOException{
//    	try {
//    		Ordering ordering = new Ordering();
//    		
//    	} catch (IllegalAccessException | InvocationTargetException e) {
//    		System.out.println(e.getMessage());
//    	}
//    	return "orderList.jsp";
//    }

	private String info(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("c",  service.find(request.getParameter("id")));
//		request.setAttribute("c", service.findAll());
		request.setAttribute("customers", service.findAll());
		return "customerinfo.jsp";
	}
	private String list(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("customers", service.findAll());
		request.setAttribute("books", bservice.findAll());
		request.setAttribute("orders", oservice.findAll());
		request.setAttribute("bookMap", bservice.map());
		request.setAttribute("customerMap", service.map());
		request.setAttribute("orderMap", oservice.map());
		return "orderList.jsp";
	}
	
	

}
