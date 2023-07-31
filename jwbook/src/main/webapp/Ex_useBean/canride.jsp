<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="p.*" %>
<%@ page import="org.apache.commons.lang3.*" %>
<%@ page import="org.apache.commons.beanutils.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%--<jsp:useBean id="Child" class="p.Child" scope="request"/>
<jsp:setProperty name="Child" property="*"/>
--%>

<%
java.util.Map<String, String[]> map = request.getParameterMap();
System.out.println(map);
for(Map.Entry<String, String[]> e : map.entrySet()){
	System.out.println(e.getKey());
	for(String s: e.getValue()){
		System.out.println(s);
	}
}

Child child = new Child();
BeanUtils.populate(child, map);
System.out.println(child);
pageContext.setAttribute("Child", child);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Can Ride</title>
</head>
<body>
</body>
</html>