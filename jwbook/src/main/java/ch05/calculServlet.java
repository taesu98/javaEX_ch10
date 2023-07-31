package ch05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/calcu")
public class calculServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int age = Integer.parseInt(request.getParameter("age"));
		int height = Integer.parseInt(request.getParameter("height"));
		boolean parent = Boolean.parseBoolean(request.getParameter("parent"));
		boolean heartDisease = Boolean.parseBoolean(request.getParameter("heartDisease"));
		
		boolean rtn = false;
		
		if (Boolean.parseBoolean(request.getParameter("heartDisease")) == true) {
			rtn = false;
			}
			if (age >= 6 && height >= 120) {
				rtn = true;
			} else {
				if (height >= 120 && parent == true) {
					rtn = true;
				}	
			}
			
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head><title>Hello</title></head>");
		out.println("<body>");
		out.println("<h1>탑승여부</h1>");
		out.println("<h1>" + rtn + "</h1>");
		out.println("</body>");
		out.println("</html>");
		
	}

}
