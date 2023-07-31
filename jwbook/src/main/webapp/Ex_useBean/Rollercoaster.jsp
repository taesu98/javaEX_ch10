<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="p.*" %>
<%@ page import="org.apache.commons.lang3.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%!
boolean CanRide(Child c) {
	boolean rtn = false;

  if (!c.isHeartDisease()) { 
	  if (c.getAge() >= 6 && c.getHeight() >= 120) { 
		  rtn = true; 
		  } else { 
	if(c.getHeight() >= 120 && c.isParent()) { 
		rtn = true;
 	 	}
	}
}
return rtn;
}
%>

<%
		Child child = (Child) request.getAttribute("Child");
		String canRide = "";
		if(CanRide(child)){
			canRide = "탑승가능";
		} else {
			canRide = "탑승 불가능";
		}
	
%>


<h1>Rollercoaster</h1>
<%=canRide %>
</html>