<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title>My JSP 'shop.jsp' starting page</title>
</head>
<body>
	<form action="buy.jsp">
		书籍：<input type="checkbox" name="item" value="book" /><br /> 电脑：<input
			type="checkbox" name="item" value="computer" /><br /> 汽车：<input
			type="checkbox" name="item" value="car" /><br /> <input
			type="submit" value="提交" />
	</form>

	<h1>在线用户:</h1>
	<table>
		<tr>
			<td>ID</td>
			<td>名称</td>
		</tr>
		<%
			@SuppressWarnings("unchecked")
			HashMap<String, String> online = (HashMap<String, String>) application.getAttribute("online");
			for (String sessionId : online.keySet()) {
		%>
		<tr>
			<td><%=sessionId%></td>
			<td><%=online.get(sessionId)%></td>
		</tr>
		<%
			}
		%>


	</table>
</body>
</html>