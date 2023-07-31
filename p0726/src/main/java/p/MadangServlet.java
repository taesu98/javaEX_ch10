package p;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;


@WebServlet(name = "Madang", urlPatterns = { "/madang" })
public class MadangServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	CustomerService customerService;
	BookService bookService;
	OrderingService orderingService;
       
    public MadangServlet() {
        super();
        
        customerService = new CustomerService();
        bookService = new BookService();
        orderingService = new OrderingService();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	String action = StringUtils.defaultIfEmpty(request.getParameter("action"), "orderings");
    	String view = "";
    	switch(action) {
    	case "apiCustomers":
    			apiCustomers(request, response);
    			break;
    	case "apiCustomer":
			apiCustomer(request, response);
			break;
    	case "orderings":
    			view = orderings(request, response);
    		break;
    	case "createOrdering":
    			createOrdering(request, response);
    		break;
    	case "customers":
    		view = customers(request, response);
    		break;
    	case "customer":
    		view = customer(request, response);
    		break;
    	case "updateCustomer":
    		updateCustomer(request, response);
    		break;
    	case "deleteCustomer":
    		deleteCustomer(request, response);
    		break;
    	case "books":
    		view = books(request, response);
    		break;
    	case "book":
    		view = book(request, response);
    		break;
    	case "updateBook":
    		updateBook(request, response);
    		break;
    	case "deleteBook":
    		deleteBook(request, response);
    	}
    	
    	if(StringUtils.isNotEmpty(view)) {
    		getServletContext().getRequestDispatcher(view).forward(request, response);
    		
    	}
    }
    
    void apiCustomers(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
    	List<Customer> customerList = customerService.get();
    	List<String> jsonStringList = new ArrayList<>();
		for(Customer customer : customerList) {
			jsonStringList.add(customer.toJsonString());
		}	
		String jsonArrayString = StringUtils.join(jsonStringList);
		System.out.println(jsonArrayString);
		response.setHeader("content-Type", "application/json; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(jsonArrayString);
		pw.flush();
		pw.close();
    }
    
    void apiCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
    	int id = Integer.parseInt(StringUtils.defaultIfEmpty(request.getParameter("id"), "-1"));
    	Customer customer = customerService.getOrBlack(id);
    	request.setAttribute("customer", customer);
    	
		response.setHeader("content-Type", "application/json; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(customer.toJsonString());
		pw.flush();
		pw.close();
    }
    
    void apiBooks(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
    	List<Book> customerList = bookService.get();
    	List<String> jsonStringList = new ArrayList<>();
		for(Book book : customerList) {
			jsonStringList.add(book.toJsonString());
		}	
		String jsonArrayString = StringUtils.join(jsonStringList);
		System.out.println(jsonArrayString);
		response.setHeader("content-Type", "application/json; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(jsonArrayString);
		pw.flush();
		pw.close();
    }
    
    void apiBook(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
    	int id = Integer.parseInt(StringUtils.defaultIfEmpty(request.getParameter("id"), "-1"));
    	Book book = bookService.getOrBlack(id);
    	request.setAttribute("customer", book);
    	
		response.setHeader("content-Type", "application/json; charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.print(book.toJsonString());
		pw.flush();
		pw.close();
    }
    
    String customers(HttpServletRequest request, HttpServletResponse response) {
    	boolean hasOrdering = Boolean.parseBoolean(StringUtils.defaultIfEmpty(request.getParameter("hasOrdering"), "false"));
    	List<Customer> customerList = customerService.get();
    	
    	request.setAttribute("hasOrdering", hasOrdering);
    	request.setAttribute("customerList", customerList);
    	
    	return "/customers.jsp";
    }
    
    String customer(HttpServletRequest request, HttpServletResponse response) {
    	int id = Integer.parseInt(StringUtils.defaultIfEmpty(request.getParameter("id"), "-1"));
    	Customer customer = customerService.getOrBlack(id);
    	request.setAttribute("customer", customer);
    	
    	return "/customer.jsp";
    }
    
    void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	Customer customer = new Customer();
    	try {
    		BeanUtils.populate(customer, request.getParameterMap());
    		
    		customerService.addOrSet(customer);
    		response.sendRedirect("madang?action=customers");
    	} catch(IllegalAccessException | InvocationTargetException e) {
    		e.printStackTrace();
    	}
    }
    
    void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	int id = Integer.parseInt(StringUtils.defaultIfEmpty(request.getParameter("id"), "-1"));
    	
    	try {
    		customerService.remove(id);
    		response.sendRedirect("madang?action=customers");
    	}catch(HasOrderingException hoe) {
    		response.sendRedirect("madang?action=customers&hasOrdering=true");
    	}
    }
    	
    	void updateBook(HttpServletRequest request, HttpServletResponse response) throws IOException{
        	Book book = new Book();
        	try {
        		BeanUtils.populate(book, request.getParameterMap());
        		
        		bookService.addOrSet(book);
        		response.sendRedirect("madang?action=books");
        	} catch(IllegalAccessException | InvocationTargetException e) {
        		e.printStackTrace();
        	}
        }
        
        void deleteBook(HttpServletRequest request, HttpServletResponse response) throws IOException{
        	int id = Integer.parseInt(StringUtils.defaultIfEmpty(request.getParameter("id"), "-1"));
        	
        	try {
        		bookService.remove(id);
        		response.sendRedirect("madang?action=books");
        	}catch(HasOrderingException hoe) {
        		response.sendRedirect("madang?action=books&hasOrdering=true");
        	}
        }
    	
    
    String books(HttpServletRequest request, HttpServletResponse response) {
		boolean hasOrdering = Boolean.parseBoolean(StringUtils.defaultIfEmpty(request.getParameter("hasOrdering"), "false"));
		List<Book> bookList = bookService.get();
		request.setAttribute("bookList", bookList);
		
		return "/books.jsp";
	}
    
    String book(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(StringUtils.defaultIfEmpty(request.getParameter("id"),"-1"));
		Book book = bookService.getOrBlack(id);
		request.setAttribute("book", book);
		
		return "/book.jsp";
	}
    
    String orderings(HttpServletRequest request, HttpServletResponse response) {
    	List<Customer> customerList = customerService.get();
    	List<Book> bookList = bookService.get();
    	List<Object[]> orderingList = orderingService.get();
    	
    	request.setAttribute("customerList", customerList);
    	request.setAttribute("bookList", bookList);
    	request.setAttribute("orderingList", orderingList);
    	
    	return "/orderings.jsp";
    }
    
    void createOrdering(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	Ordering ordering = new Ordering();
    	try {
    		BeanUtils.populate(ordering, request.getParameterMap());
    		orderingService.add(ordering);
    	} catch(IllegalAccessException | InvocationTargetException e) {
    		e.printStackTrace();
    	}
    	response.sendRedirect("madang?action=orderings");
    }
}
