<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import= "java.util.Calendar" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<%java.util.Calendar cal = java.util.Calendar.getInstance();
int year = cal.get(java.util.Calendar.YEAR);
int month = cal.get(java.util.Calendar.MONTH) + 1;
int day = 1;
cal.set(year, 6, 1); 

int startDayOfWeek = cal.get(java.util.Calendar.DAY_OF_WEEK);

%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>7월 달력</title>
<style>
table {
  border-collapse: collapse;
  width: 100%;
}

th, td {
  text-align: center;
  padding: 8px;
  border: 1px solid black;
}

th {
  background-color: lightgray;
}

td {
  height: 80px;
}

.today {
  background-color: lightblue;
}
</style>
</head>
<body>
    <h2>7월 달력</h2>
    <table>
        <tr>
            <th>일</th>
            <th>월</th>
            <th>화</th>
            <th>수</th>
            <th>목</th>
            <th>금</th>
            <th>토</th>
        </tr>
        
        <%
            for (int i = 1; i < startDayOfWeek; i++) {
                out.print("<td></td>"); 
            }
            while (cal.get(java.util.Calendar.MONTH) == 6) {
                if (cal.get(java.util.Calendar.DAY_OF_WEEK) == 1) {
                    out.print("<tr>");
                }
                
                if (cal.get(java.util.Calendar.MONTH) == month - 1 && 
                		cal.get(java.util.Calendar.DAY_OF_MONTH) 
                		== day) 
                {
                    out.print("<td class=\"today\">" + day + "</td>");
                } else {
                    out.print("<td>" + day + "</td>");
                }
                
                if (cal.get(java.util.Calendar.DAY_OF_WEEK) == 7) {
                    out.print("</tr>");
                }
                
                cal.add(java.util.Calendar.DAY_OF_MONTH, 1);
                day++;
            }
            while (cal.get(java.util.Calendar.DAY_OF_WEEK) != 1) {
                out.print("<td></td>");
                cal.add(java.util.Calendar.DAY_OF_MONTH, 1);
            }
        %>
        
    </table>
</body>
</html>
