package ch05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(name = "Hello", urlPatterns =  "/HelloServlet" )
public class HelloServlet extends HttpServlet {
	String name0;
	String name1;
	int i = 0;
	
	@Override
	public void init(ServletConfig sc) throws ServletException {
		super.init(sc);
		
		name0 = sc.getInitParameter("name0");
		name1 = sc.getInitParameter("name1");
		System.out.println("Hello inited");
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		i++;
		request.getSession().setAttribute("username", "홍길동");
		RequestDispatcher rd = request.getRequestDispatcher("requestheader");
		rd.forward(request, response);
//		response.sendRedirect("/jwbook/RquestHeaderServlet");
		
//		response.addHeader("Location", "hello"); 
//		response.setContentType("text/html;charset=UTF-8");
//		PrintWriter out = response.getWriter();
//		out.println("<!DOCTYPE html>");
//		out.println("<html>");
//		out.println("<head><title>Hello</title></head>");
//		out.println("<body>");
//		out.println("<h1>Hello 안녕</h1>");
//		out.println("<h1>" + i + "</h1>");
////		out.println("<h1>" + name1 + "</h1>");
//		out.println("</body>");
//		out.println("</html>");

	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}
	
	@Override
	public void destroy() {
		name0 = null;
		name1 = null;
		System.out.println("Hello destroyed");
	}


}
