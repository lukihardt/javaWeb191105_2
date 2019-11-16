<%@page import="java.io.OutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>download.jsp</title>
</head>
<body>
	<%
		out.clear();
		out = pageContext.pushBody();
	
		String realPath = this.getServletContext().getRealPath("/download/smlt.mp3");
		System.out.println(realPath);
		// 2.获取要下载的文件名
		String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
		String userAgent = request.getHeader("User-Agent");
		// 针对IE或者以IE为内核的浏览器
		if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
			fileName = URLEncoder.encode(fileName, "UTF-8");
		} else {
			//非IE浏览器的处理
			fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		}
		// 3.设置content-disposition响应头控制浏览器以下载的方式打开文件
		response.setHeader("content-disposition", "attachment;filename=" + fileName);
		// 4.获取要下载的文件输入流
		InputStream inputStream = new FileInputStream(realPath);

		int len = 0;
		// 5.创建缓冲区
		byte[] buffer = new byte[1024];
		// 6.通过response对象获取OutputStream输出流对象
		OutputStream outputStream = response.getOutputStream();
		// 7.将FileInputStream流对象写入到buffer缓冲区
		while ((len = inputStream.read(buffer)) > 0) {
			outputStream.write(buffer, 0, len);
		}
		// 8.关闭流
		inputStream.close();
		outputStream.close();
	%>
</body>
</html>