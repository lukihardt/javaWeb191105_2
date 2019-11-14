<%@page import="java.sql.ResultSet"%>
<%@page import="sopo.cn.dao.DBDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%= basePath%>">
<meta charset="UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<title>online在线人数</title>
</head>
<body>
<h1>当前在线人员：</h1>
<table>
<%
	DBDao db = new DBDao( "com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/escshop?serverTimezone=Asia/Shanghai", "root", "F2NRnjVsKe");
	ResultSet rs = db.query("SELECT * FROM online_information");
	while( rs.next()){
%>
<tr>
	<td><%= rs.getString(1) %></td>
	<td><%= rs.getString(2) %></td>
	<td><%= rs.getString(3) %></td>
	<td><%= rs.getString(4) %></td>
	<td><%= rs.getString(5) %></td>
</tr>
<%
	}
%>
</table>
</body>
</html>