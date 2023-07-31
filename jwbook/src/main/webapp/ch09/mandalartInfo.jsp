<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mandalart</title>
</head>
<body>
<h2>Mandalart</h2>
<hr>
<form action="">
    <table border="1">
    <c:forEach begin="0" end="2" var="i">
        <tr>
        <c:forEach begin="0" end="2" var="j">
            <c:choose>
                <c:when test="${3 * i + j == 4}">
                    <td>${firstList[3 * i + j]}</td>
                </c:when>
                <c:otherwise>
                    <td><input type="submit" name="goal" value="${firstList[3 * i + j]}"></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        </tr>
    </c:forEach>
    </table>
</form>
<hr>
    <c:if test="${goal!=null}">
    <table border="1">
    <c:forEach begin="0" end="2" var="i">
        <tr>
        <c:forEach begin="0" end="2" var="j">
            <c:choose>
                <c:when test="${(3 * i + j) == 4}">
                    <td>${secondList[4]}</td>
                </c:when>
                <c:otherwise>
                    <td>${secondList[3 * i + j]}</td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        </tr>
    </c:forEach>
    </table>
    </c:if>
</body>
</html>