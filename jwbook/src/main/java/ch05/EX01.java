package ch05;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "My second servlet", urlPatterns = { "/todo" })
public class EX01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EX01() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse 
	response)throws ServletException, IOException { 
	response.setContentType("text/html; charset = utf-8"); // 헤더정보
	String htmlTemplate ="";
	PrintWriter out = response.getWriter();
	String yyyy_MM_dd = java.time.LocalTime.now().toString();
	String html = htmlTemplate.replace("yyyy_MM_dd", yyyy_MM_dd);
	
	out.append("""
	<!DOCTYPE html>
	<html>
	<head>
	<meta charset="UTF-8">
	<title>TODO</title>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
	</head>

	<body>
	<div class="container bg-warning shadow mx-auto mt-5 p-5 w-75">
	<h2>My ToDo App :yyyy-MM-dd:</h2>
	<hr>
	<div class="input-group">
	<input id="item" class="form-control" type="text" placeholder="할일을 입력하세요..">
	<button type="button" class="btn btn-primary" onclick="addItem()">할일 추가</button>
	</div>
	<hr>
	<ul id="todolist" class="list-group"></ul>
	<button type="button" class="btn btn-danger mt-3" onclick="deleteCheckedItems()">선택한 항목 삭제</button>
	</div>
"""		
	);
	out.println(html);
	out.flush();
	out.close();
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
