<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@page import="java.util.*"%>
<%@page import="p.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Child</title>

<style>
	td{
	width: 100px;
	height: 70px;
	text-align: center;
	}
	span{
		display: inline-block;
		width: 120px;
		text-align: center;
	}
	div{
		width: 100%;
		height: 100%;
		line-height: 70px;
		font-size: 1.3em;
		font-weight: bold;
	}
	div.true{
		background-color: #00ff00;
	}
	div.false{
		background-color: #ff0000;
	}
</style>
</head>
<body>
	<h1>Child List</h1>
	
	<table border="1">
		<tr>
			<td>No</td>
			<td>나이</td>
			<td>키</td>
			<td>부모 동반</td>
			<td>심장병</td>
			<td>탑승</td>
		</tr>
		
		<c:forEach var="child" varStatus="i" items="${childs}">
			<tr>
				<td><div>${i.index + 1}</div></td>

				<c:if test="${child.age >= 6 }">
					<td><div class="true">${child.age }</div></td>
				</c:if>
				<c:if test="${child.age < 6 }">
					<td><div class="false">${child.age }</div></td>
				</c:if>
				
				<c:if test="${child.height >= 120 }">
					<td><div class="true">${child.height }</div></td>
				</c:if>
				<c:if test="${child.height < 120 }">
					<td><div class="false">${child.height }</div></td>
				</c:if>
				
				<c:if test="${child.parent == true }">
					<td><div class="true">${child.parent }</div></td>
				</c:if>
				<c:if test="${child.parent != true }">
					<td><div class="false">${child.parent }</div></td>
				</c:if>
				
				<c:if test="${child.heartDisease == true }">
					<td><div class="true">${child.heartDisease }</div></td>
				</c:if>
				<c:if test="${child.heartDisease != true }">
					<td><div class="false">${child.heartDisease }</div></td>
				</c:if>
				
				<c:if test="${child.canRide == true }">
					<td><div class="true">${child.canRide }</div></td>
				</c:if>
				<c:if test="${child.canRide != true }">
					<td><div class="false">${child.canRide }</div></td>
				</c:if>
				
			</tr>
		</c:forEach>
	</table>

</body>