<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% 
	String n1String = request.getParameter("n1");
	String n2String = request.getParameter("n2");
	String opString = request.getParameter("op");
	
	int n1;
	int n2;
	String op;
	if(n1String == null){
		n1 = 0;
	} else {
		n1 = Integer.parseInt(n1String);
	}
	if(n2String == null){
		n2 = 0;
	} else {
		n2 = Integer.parseInt(n2String);
	}
	if(opString == null){
		op = "+";
	} else {
		op = opString;
	}
	
	long result = 0;
	
	switch(op){
	case "+":
		result = n1 + n2;
		break;
	case "-":
		result = n1 - n2;
		break;
	case "*":
		result = n1 * n2;
		break;
	case "/":
		result = n1 / n2;
		break;
	}
%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>계산기</h2>
<hr>
<form>
	<input type="text" name="n1"/>
	<select name="op">
		<option value="+">+</option>
		<option value="-">-</option>
		<option value="*">*</option>
		<option value="/">/</option>
	</select>
</form>
<hr>
</body>
</html>