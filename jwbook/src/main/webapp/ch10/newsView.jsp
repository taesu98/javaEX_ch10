<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<title>뉴스 관리 앱</title>
</head>
<body>
	<div class="container w-75 mt-5 mx-auto">
	<h2>${news.title}</h2>
	<hr>
	<div class="card w-75 mx-auto">
	<img class="card-img-top" src="${news.img}">
	<div class="card-body">
		<h4 class="card-title">Date: ${news.date}</h4>
		<p class="card-text">Content: ${news.content}</p>
	</div>
	</div>
	</div>
	<hr>
	<a href="javascript:history.back()" class="btn btn-primary"> << Back</a>
</body>
</html>