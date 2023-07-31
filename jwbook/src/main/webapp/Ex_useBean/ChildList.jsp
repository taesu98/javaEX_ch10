<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@page import="java.util.*"%>
<%@page import="p.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	List<Integer> ageList = new ArrayList<>();
	List<Integer> heightList = new ArrayList<>();
	List<Boolean> parentList = new ArrayList<>();
	List<Boolean> heartDiseaseList = new ArrayList<>();
	
	for(int i = 0; i < 100; i++){
		int r = (int) Math.floor(Math.random() * 17 + 1);
		ageList.add(r);
	}
	for(int i = 0; i < 100; i++){
		int r = (int) Math.floor(Math.random() * 160 + 40);
		heightList.add(r);
	}
	for(int i = 0; i < 100; i++){
		boolean flag = false;
		int r = (int) Math.floor(Math.random() * 2);
		if(r == 1){
			flag = false;
		}else{
			flag = true;
		}
		parentList.add(flag);
	}
	for(int i = 0; i < 100; i++){
		boolean flag = false;
		int r = (int) Math.floor(Math.random() * 2);
		if(r == 1){
			flag = false;
		}else{
			flag = true;
		}
		heartDiseaseList.add(flag);
	}
	
	List<Child> childs = new ArrayList<>();
	pageContext.setAttribute("childs", childs);
	
	for(int i = 0; i < 16; i++){
		Child child = new Child();
		child.setAge(ageList.get(i));
		child.setHeight(heightList.get(i));
		child.setParent(parentList.get(i));
		child.setHeartDisease(heartDiseaseList.get(i));
		childs.add(child);
	}
	
	request.setAttribute("childs",childs );
%>

<jsp:forward page="child2.jsp"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Child</title>
</head>
<body>
</body>
</html>


