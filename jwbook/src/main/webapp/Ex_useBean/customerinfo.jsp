<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객정보 조회</title>
</head>
<body>
	<h2>고객정보 조회</h2>
	<hr>
	<form>
		<label>고객번호: <input type="text" value="${c.id}"></label><br>
		<label>고객명: <input type="text" value="${c.name}"></label><br>
		<label>주소: <input type="text" value="${c.address}"></label><br>
		<label>전화번호: <input type="text" value="${c.phone}"></label>
	</form>
	<hr>
	<button>저장</button><button>삭제</button>
</body>
</html>