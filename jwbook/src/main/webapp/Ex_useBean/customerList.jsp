<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객 목록</title>
</head>
<body>
	<h2>고객</h2>
	<hr>
	<table border="1">
	<tr> <th>고객번호</th> <th>고객명</th> <th>주소</th> <th>전화번호</th> </tr>
	<c:forEach var="c" varStatus="i" items="${customers}">
	<tr>
		<td>${i.count}</td>
		<td><a href="/jwbook/ccontrol?action=info&id=${c.id}">${c.name}</a></td>
		<td>${c.address}</td>
		<td>${c.phone}</td>
	</tr>	
	</c:forEach>
	</table>
</body>
</html>