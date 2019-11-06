<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login.jsp</title>
</head>
<body>
	<form action="login.do" method="post">
		${ sessionScope.flag}<br>
		userName<input type="text" name="username" /><br> 
		password<input type="text" name="password" /><br> <input type="submit" />
	</form>
</body>
</html>