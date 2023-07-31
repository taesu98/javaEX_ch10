package EX_ch09;

import org.apache.commons.lang3.StringUtils;

import EX_ch09.BookDAO;
import EX_ch09.CustomerDAO;
import EX_ch09.Ordering;
import EX_ch09.OrderingDAO;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

@WebServlet(name = "madang", urlPatterns = {"/madangControl"})
public class MadangController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    OrderingDAO orderingService;
    CustomerDAO customerService;
    BookDAO bookService;

    @Override
    public void init() throws ServletException {
        super.init();

        orderingService = new OrderingDAO();
        customerService = new CustomerDAO();
        bookService = new BookDAO();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = StringUtils.defaultIfEmpty(request.getParameter("action"), "orderings");

        String view = "";
        switch (action) {
            case "orderings":
                view = orderings(request, response);
                break;
            case "addOrdering":
                
                addOrdering(request, response);
                view = orderings(request, response);
                //view = list(request, response);
                break;
               
            case "customers":
                view = customers(request, response);
                break;
            case "customer":
                view = customer(request, response);
                break;
            case "setCustomer":
                setCustomer(request, response);
                //view = customers(request, response);
                break;
            case "removeCustomer":
                
                removeCustomer(request, response);
                view = customers(request, response);
                break;
                
            case "books":
            	view = books(request, response);
            	break;
            case "book":
                view = book(request, response);
                break;
            case "setBook":
                setBook(request, response);
                //view = customers(request, response);
                break;
            case "removeBook":
                
            	removeBook(request, response);
                view = books(request, response);
                break;
            default:
                break;
        }

        if (StringUtils.isNotBlank(view)) {
            getServletContext().getRequestDispatcher("/EX_ch09/" + view).forward(request, response);
        }
    }
    
    private String addOrdering(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Ordering ordering = new Ordering();
            BeanUtils.populate(ordering, request.getParameterMap());
            orderingService.add(ordering);
            //response.sendRedirect("madang?action=orderings");
            
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.getMessage());
        }
        return "orderings.jsp";
    }
    
    
    private String orderings(HttpServletRequest request, HttpServletResponse response) {
    	List<Customer> customerList = customerService.getCustomerList();
        List<Book> bookList = bookService.getBookList();
        List<Ordering> orderingList = orderingService.getOrderingList();

        request.setAttribute("customerList", customerList);
        request.setAttribute("bookList", bookList);
        request.setAttribute("orderingList", orderingList);

//        Map<Integer, Customer> customerMap = customerService.map();
//        Map<Integer, Book> bookMap = bookService.map();

//        request.setAttribute("customerMap", customerMap);
//        request.setAttribute("bookMap", bookMap);

        return "orderings.jsp";
    }
    
    private String books(HttpServletRequest request, HttpServletResponse response) {
        boolean hasOrdering = Boolean.parseBoolean(StringUtils.defaultIfEmpty(request.getParameter("hasOrdering"), "false"));
        List<Book> bookList = bookService.getBookList();

        request.setAttribute("hasOrdering", hasOrdering);
        request.setAttribute("bookList", bookList);

        return "books.jsp";
    }
    private String book(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(StringUtils.defaultIfEmpty(request.getParameter("id"), "-1"));
//        Book book = bookService.get(id);
//        request.setAttribute("book", book);

        return "book.jsp";
    }

    private String customers(HttpServletRequest request, HttpServletResponse response) {
        boolean hasOrdering = Boolean.parseBoolean(StringUtils.defaultIfEmpty(request.getParameter("hasOrdering"), "false"));
        List<Customer> customerList = customerService.getCustomerList();

        request.setAttribute("hasOrdering", hasOrdering);
        request.setAttribute("customerList", customerList);

        return "customers.jsp";
    }

    private String customer(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(StringUtils.defaultIfEmpty(request.getParameter("id"), "-1"));
//        Customer customer = customerService.get(id);
//        request.setAttribute("customer", customer);

        return "customer.jsp";
    }
    
    private String setBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Book book = new Book();
            BeanUtils.populate(book, request.getParameterMap());
            int id = Integer.parseInt(StringUtils.defaultIfEmpty(request.getParameter("id"), "-1"));
            book.setId(id);
            if (book.getId() == -1) {
            	bookService.add(book);
            } else {
//            	bookService.set(book);
            }
            response.sendRedirect("madangControl?action=books");
            
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.getMessage());
        }
        return "books.jsp";
    }
    
    private String removeBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(StringUtils.defaultIfEmpty(request.getParameter("id"), "-1"));
        
        boolean hasOrdering = false;
//        for (Ordering ordering : orderingService.get()) {
//            if (ordering.getBookId() == id) {
//                hasOrdering = true;
//                break;
//            }
//        }

        if (hasOrdering) {
            //response.sendRedirect("madang?action=customers&hasOrdering=true");
        	return "books.jsp";
        } else {
//            bookService.remove(id);
            //response.sendRedirect("madang?action=customers");
            return "books.jsp";
        }
    }

    private String setCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
        	int id = Integer.parseInt(StringUtils.defaultIfEmpty(request.getParameter("id"), "-1"));
            Customer customer = new Customer();
            BeanUtils.populate(customer, request.getParameterMap());
            customer.setId(id);
            
            if (customer.getId() == -1) {
                customerService.add(customer);
            } else {
//                customerService.set(customer);
            }
            response.sendRedirect("madangControl?action=customers");
            
        } catch (IllegalAccessException | InvocationTargetException e) {
            System.out.println(e.getMessage());
        }
        return "customers.jsp";
    }

    private String removeCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(StringUtils.defaultIfEmpty(request.getParameter("id"), "-1"));
        
        boolean hasOrdering = false;
//        for (Ordering ordering : orderingService.get()) {
//            if (ordering.getCustomerId() == id) {
//                hasOrdering = true;
//                break;
//            }
//        }

        if (hasOrdering) {
            //response.sendRedirect("madang?action=customers&hasOrdering=true");
        	return "customers.jsp";
        } else {
//            customerService.remove(id);
            //response.sendRedirect("madang?action=customers");
            return "customers.jsp";
        }
    }
}
