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
 * �������ش���Ӧ�÷���jspҳ���У���
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
		// 1.��ȡҪ���ص��ļ��ľ���·��
		String realPath = this.getServletContext().getRealPath("/download/smlt.mp3");
		System.out.println(realPath);
		// 2.��ȡҪ���ص��ļ���
		String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
		String userAgent = request.getHeader("User-Agent");
		// ���IE������IEΪ�ں˵������
		if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
			fileName = URLEncoder.encode(fileName, "UTF-8");
		} else {
			//��IE������Ĵ���
			fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		}
		// 3.����content-disposition��Ӧͷ��������������صķ�ʽ���ļ�
		response.setHeader( "content-disposition", "attachment;filename=" + fileName);
		// 4.��ȡҪ���ص��ļ�������
		InputStream inputStream = new FileInputStream(realPath);
		
		int len = 0;
		// 5.����������
		byte[] buffer = new byte[1024];
		// 6.ͨ��response�����ȡOutputStream���������
		OutputStream outputStream = response.getOutputStream();
		// 7.��FileInputStream������д�뵽buffer������
		while ( (len = inputStream.read(buffer)) > 0) {
			outputStream.write(buffer, 0, len);
		}
		// 8.�ر���
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
