<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Child</title>
</head>
<body>
<script>

 	function canRide(){
 		document.forms['canride'].action = document.forms['canride'].attractions.value + '.jsp';
 		document.forms['canride'].submit();
 	}
 	
 </script>
 <form name="canride" action="canride.jsp" method="post">
 	나이: <input type="text" name="age"><br>
 	키: <input type="text" name="height"><br>
 	부모 동반: <input type="checkbox" name="parent" value="true" checked><br>
 	심장병: 
 	유 <input type="radio" name="heartDisease" value="true" >
 	무 <input type="radio" name="heartDisease" value="false" checked><br>
 	어트랙션:<br>
 	<input type="checkbox" name="attractions" value="Rollercoaster"/>Rollercoaster
 	<input type="checkbox" name="attractions" value="Bumpercar"/>Bumpercar
 	<input type="checkbox" name="attractions" value="Merrygoround"/>Merrygoround
 	<br><input type="submit" name="제출" value="제출" />
 </form>
 
</body>
</html>