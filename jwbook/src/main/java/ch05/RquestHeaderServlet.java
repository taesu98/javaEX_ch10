package ch05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

@WebServlet("/RquestHeaderServlet")
public class RquestHeaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String htmlTemplate = """
			<!DOCTYPE html>
			<html>
			<head><title>RquestHeader</title></head>
			<body>
			<h3>:username:</h3>
			<h3>:result:</h3>
			</body>
			</html>
			""";

    public RquestHeaderServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		StringBuilder sb = new StringBuilder();
		Enumeration<String> headerNames = request.getHeaderNames();
		while(headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			String headerValue = request.getHeader(headerName);
			sb.append(headerName);
			sb.append(": ");
			sb.append(headerValue);
			sb.append("<br>");
		}
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String html = htmlTemplate.replace(":result:", sb.toString());
		
		Object o = (String) request.getSession().getAttribute("username");
		String s = null;
		
		if(o == null) {s = "null";
		}else {
			s = (String) o;
		}
		
		html = html.replace(":username:", s);
		out.println(html);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doGet(request, response);
	}

}

