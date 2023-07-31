<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<sql:query var="rs" dataSource="jdbc/jwbookdb">
		SELECT id, name, address, phone FROM customer
	</sql:query>
	
	<c:forEach var="customer" items="${rs.rows}">
		${customer.id} / ${customer.name} / ${customer.address} / ${customer.phone}
		<br/>
	</c:forEach>
</body>
</html>