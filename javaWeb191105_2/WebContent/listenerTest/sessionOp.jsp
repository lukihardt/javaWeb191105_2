<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>sessionOp.jsp</title>
</head>
<body>
<c:remove var="934uj" scope="session" />
<%
	HttpSession httpSession = request.getSession();
	httpSession.setMaxInactiveInterval(9);
%>
</body>
</html>