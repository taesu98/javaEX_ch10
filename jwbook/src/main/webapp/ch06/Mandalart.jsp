<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
    
<%
	Map<String, List<String>> firstMap = new HashMap<>();
	firstMap.put("8구단 드래프트 1순위", 
			Arrays.asList(
					"몸 만들기", "제구", "구위", "멘탈", "스피드 160km/h", "인간성", "운", "변화구"));

	Map<String, List<String>> secondMap = new HashMap<>();

	List<String> firstList = new ArrayList<>();
	firstList.addAll(firstMap.get("8구단 드래프트 1순위"));
	firstList.add(4,"8구단 드래프트 1순위");
	pageContext.setAttribute("firstList", firstList);

	Map<String, List<String>> scondMap = new HashMap<>();
	for(String s : firstMap.get("8구단 드래프트 1순위")) {
		secondMap.put(s, null);
	}

	secondMap.put("몸 만들기", 
			Arrays.asList("몸 관리", "영양제 먹기", "FSQ 90kg", "RSQ 130kg", "몸 만들기","식사 저녁7숱갈 아침3숱갈", "가통역", "스테미너", "유연성"));

	secondMap.put("제구", 
			Arrays.asList("몸통 강화", "인스텝 개선", "축 흔들지 않기", "불안정 없애기", "제구", "멘탈을 컨트롤", "몸을 열지 않기", "하체 강화", "릴리즈 포인트 안정"));

	secondMap.put("구위", 
			Arrays.asList("각도를 만든다", "위에서부터 공을 던진다", "손목강화", "하반신 주도", "구위", "가동력", "회전수 증가", "볼을 앞에서 릴리즈", "힘을 모으기"));

	secondMap.put("스피드 160km/h", 
			Arrays.asList("축을 돌리기", "하체강화", "체중증가", "어깨주변 강화", "스피드 160km/h", "피칭늘리기", "라이너캐치볼", "가동력", "몸통강화"));

	secondMap.put("변화구", 
			Arrays.asList("카운트볼 늘리기", "포크볼 완성", "슬라이더 구위", "좌타자 결정구", "변화구", "거리를 상상하기", "스트라이크 볼을 던질 때 제구", "직구와 같은 폼으로 던지기", "늦게 낙차가 있는 커브"));

	secondMap.put("운", 
			Arrays.asList("인사하기", "쓰레기 줍기", "부실 청소", "심판을 대하는 태도", "운", "책 읽기", "응원받는 사람", "긍정적 사고", "물건을 소중히 쓰자"));

	secondMap.put("인간성", 
			Arrays.asList("감성", "사랑받는 사람", "계획성", "감사", "인간성", "지속력", "신뢰받는 사람", "예의", "배려"));

	secondMap.put("멘탈", 
			Arrays.asList("뚜렷한 목표 목적", "일회일비 하지 않기", "머리는 차갑게 심장은 뜨겁게", "분위기에 휩쓸리지 않기", "멘탈", "동료를 배려하는 마음", "승리에 대한 집념", "마음의 파도를 안만들기", "펀치에 강하게"));

	String inputGoal = request.getParameter("goal");
	List<String> inputVal = null;


	if(inputGoal != null && secondMap.containsKey(inputGoal)){
		inputVal = secondMap.get(inputGoal);
	}
	
	pageContext.setAttribute("inputVal", inputVal);
%>
    
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
    <c:if test="${not empty inputVal}">
    <table border="1">
    <c:forEach begin="0" end="2" var="i">
        <tr>
        <c:forEach begin="0" end="2" var="j">
            <c:choose>
                <c:when test="${3 * i + j == 4}">
                    <td>${inputVal[3 * i + j]}</td>
                </c:when>
                <c:otherwise>
                    <td>${inputVal[3 * i + j]}</td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
        </tr>
    </c:forEach>
    </table>
    </c:if>
</body>
</html>