<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문</title>
</head>
<body>

	<form action="<c:url value="/madang?action=addOrdering"/>" method="post">
	<h2>고객</h2>
	<hr>
	<c:forEach var="c" varStatus="i" items="${customers}">
		<label><input type="radio" name="client" value="${c.id}">${c.name}</label>
	</c:forEach>
	
	<h2>도서</h2>
	<hr>
	<c:forEach var="b" varStatus="j" items="${books}">
		<label><input type="radio" name="bookli" value="${b.id}">${b.title} ${b.price} </label>
	</c:forEach>
	<input>
	
	</form>
	
	<h2>주문</h2>
	<hr>
	<table border="1">
	<tr> <th>고객번호</th> <th>고객명</th> <th>도서명</th> <th>판매가격</th> <th>주문일자</th> </tr>
	<c:forEach var="o" varStatus="k" items="${orders}">
	<tr>
        <td>${customerMap[o.customerid].id}</td>
        <td>${customerMap[o.customerid].name}</td>
        <td>${bookMap[o.bookid].title}</td>
        <td>${o.sellingprice}</td>
        <td>${o.orderdate}</td>
    </tr>
	</c:forEach>
	</table>

</body>
</html>