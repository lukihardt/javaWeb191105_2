<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>view.jsp</title>
<style>
.divcss5 img {
	max-width: 180px;
	_width: expression(this.width > 180 ? "180px" : this.width);
}
</style>
</head>
<body>
	<table>
		<c:forEach items="${ imagesReq}" var="image">
			<tr>
				<td>${image.id}</td>
				<td>${image.name}</td>
				<td>${image.size}</td>
				<td><img src="image/${ image.pic}" /></td>
				<td>${image.time}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>