<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	application.setAttribute("addAttrName", "addAttrValue");
	application.removeAttribute("addAttrName");
	application.setAttribute("newAttrName", "newAttrValue");
	application.setAttribute("newAttrName", "1234567");
	request.setAttribute("requestName1", "requestValue1");
	request.removeAttribute("requestName1");
	request.setAttribute("requestName2", "requestValue2");
	request.setAttribute("requestName2", "requestValue23");
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%= basePath %>">
<%= basePath %>
<!-- <meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache"> -->
<meta http-equiv="expires" content="360">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta charset="UTF-8">
<title>login.jsp</title>
</head>
<body>
${ tip}<br>
<form action="LoginServlet" method="post">
	名称：<input type="text" name="user"/><br/>
	密码: <input type="text" name="pass"/><br/>
	<input type="submit" value="登录"/><input type="reset" value="重置">
</form>
</body>
</html>















