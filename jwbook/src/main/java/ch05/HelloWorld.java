package ch05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "My first servlet", urlPatterns = { "/hello" })
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HelloWorld() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse 
	response)throws ServletException, IOException { 
	response.setContentType("text/html; charset = utf-8"); // 헤더정보
	PrintWriter out = response.getWriter();
	out.println("<!DOCTYPE html>");
	out.println("<html>");
	out.println("<body>");
	out.println(request.getParameter("boo"));
	out.println("<!DOCTYPE html>");
	out.println(request.getParameter("foo"));
	out.println("</body>");
	out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException {
		doGet(request, response);
	}

}
