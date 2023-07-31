<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>계산기-컨트롤러</title>
</head>
<body>
	<h2>계산기-컨트롤러</h2>
	<hr>
    <form method="post" action="<c:uri value="/calcControl"/>">
        <input type="text" name="n1" size="10"> <select name="op">
            <option>+</option>
            <option>-</option>
            <option>*</option>
            <option>/</option>
        </select><input type="text" name="n2" size="10">
        <input type="submit" value="실행">
    </form>
</body>