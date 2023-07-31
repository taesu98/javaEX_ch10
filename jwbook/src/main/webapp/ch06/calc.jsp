<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%

	String paramNum = request.getParameter("display");
	String opers = "+-*/";
	int result = 0;
	String[] arrTemp = null;
	int temp = 0, count = 0;
	
	paramNum = paramNum.replace(" "," ");
	
	for(int i = 0; i < opers.length(); i++) {
		for(int j = 0; j < paramNum.length(); j++) {
			if(opers.charAt(i) == paramNum.charAt(j)) {
				temp = i; 
				count++; 
			}
		}
	}
	arrTemp = paramNum.split("\\"+opers.charAt(temp));
	
	int a = 0;
	a = Integer.parseInt(arrTemp[0]);
	
	int b = 0;
	b = Integer.parseInt(arrTemp[1]);
	
	if(count == 1) {
		switch(temp) {
		case  0 :
			result = a + b;
			break;
			
		case  1 :
			result = a - b;
			break;
			
		case  2 :
			result = a * b;
			break;
			
		case  3 :
			
			result = a / b;		
               
			break;
		}
	}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>계산 결과-JSP</h2>
	<hr>
	결과: <%=result %>
</body>
</html>