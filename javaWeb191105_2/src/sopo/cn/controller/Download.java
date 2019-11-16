package sopo.cn.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Download
 * ！！下载代码应该放在jsp页面中！！
 */
@WebServlet("/Download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Download() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").println(request.getContextPath()); //////
		// 1.获取要下载的文件的绝对路径
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
		response.setHeader( "content-disposition", "attachment;filename=" + fileName);
		// 4.获取要下载的文件输入流
		InputStream inputStream = new FileInputStream(realPath);
		
		int len = 0;
		// 5.创建缓冲区
		byte[] buffer = new byte[1024];
		// 6.通过response对象获取OutputStream输出流对象
		OutputStream outputStream = response.getOutputStream();
		// 7.将FileInputStream流对象写入到buffer缓冲区
		while ( (len = inputStream.read(buffer)) > 0) {
			outputStream.write(buffer, 0, len);
		}
		// 8.关闭流
		inputStream.close();
		outputStream.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
