<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>index.jsp</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/ReceiveImageServlet" enctype="multipart/form-data" method="post">
	上传文件1: <input type="file" name="file1"/><br/>
	<input type="submit" value="提交"/><br/>
</form>
</body>
</html>