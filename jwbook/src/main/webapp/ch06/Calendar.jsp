<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import= "java.util.Calendar" %>
<%
	Calendar cal = Calendar.getInstance();

	int nowYear = cal.get(Calendar.YEAR);
	int nowMonth = cal.get(Calendar.MONTH) + 1;
	int nowDay = cal.get(Calendar.DAY_OF_MONTH);
	
	String strYear = request.getParameter("year");
	String strMonth = request.getParameter("month");
	
	int year = nowYear;
	int month = nowMonth;
	
	if(strYear != null){
		year = Integer.parseInt(strYear);
		month = Integer.parseInt(strMonth);
	}
	int preYear = year;
	int preMonth = month-1;
	
	if(preMonth<1){
		preYear = year-1;
		preMonth = 12;
	}
	
	int nextYear = year;
	int nextMonth = month + 1;
	
	if(nextMonth>12){
		nextYear = year + 1;
		nextMonth = 1;
	}
	
	cal.set(year, month-1, 1);
	
	int startDay = 1;
	int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	
	int week = cal.get(Calendar.DAY_OF_WEEK);
		
	
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Calendar</title>
<style type="text/css">
    body{
    	font-size: 12pt;
    }
    td{
    	font-size: 12pt;
    }
</style>
</head>
<body>
<br>
<table align="center" width="210" cellpadding="2" cellspacing="1">
        <tr>
            <td align="center">
                <a href="Calendar.jsp?year=<%=nowYear %>&month=<%=nowMonth %>" style="text-decoration: none"></a>
                <a href="Calendar.jsp?year=<%=preYear %>&month=<%=preMonth %>" style="text-decoration: none"></a>
                <b>&nbsp;<%=year%>년 &nbsp;&nbsp; <%=month%>월</b>
                <a href="Calendar.jsp?year=<%=nextYear %>&month=<%=nextMonth %>" style="text-decoration: none"></a>
            </td>
        </tr>
    </table>
    
    <table align="center" width="210" cellpadding="0" cellspacing="1" bgcolor="#cccccc">
        <tr>
            <td bgcolor="#e6e4e6" align="center"><font color="red">일</font></td>
            <td bgcolor="#e6e4e6" align="center">월</td>
            <td bgcolor="#e6e4e6" align="center">화</td>
            <td bgcolor="#e6e4e6" align="center">수</td>
            <td bgcolor="#e6e4e6" align="center">목</td>
            <td bgcolor="#e6e4e6" align="center">금</td>
            <td bgcolor="#e6e4e6" align="center"><font color="blue">토</font></td>
        </tr>
<% 
        int newLine=0;
        out.print("<tr height=20>");
            for(int i = 1; i < week; i++){
                out.print("<td bgcolor='#ffffff'>&nbsp;</td>");
                newLine++;
            }

            for(int i = startDay; i <= endDay; i++){
            	
            	String fontColor = (newLine==0)?"red":(newLine==6)?"blue":"black";
            	
                String bgColor = ((newLine == 0)?"red":(newLine==6)&&(nowDay==i)?
                        "#e6e4e6": "#ffffff");
                out.print("<td bgcolor='"+ bgColor +"' align=center><font color='"+ fontColor +"'>"+ i +"</font></td>");
                newLine++;

                if(newLine ==7 && 1!=endDay){
                    out.print("</tr><tr height='20'>");
                        newLine=0;
                }
            }

            while(newLine > 0&& newLine < 7){
                out.print("<td bgcolor='#ffffff'>&nbsp;</td>");
                newLine++;
            }

            out.print("</tr>");
%>           
</table>
</body>
</html>