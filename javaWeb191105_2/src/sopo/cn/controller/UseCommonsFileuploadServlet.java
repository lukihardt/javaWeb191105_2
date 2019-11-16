package sopo.cn.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UseCommonsFileuploadServlet
 */
@WebServlet("/UseCommonsFileuploadServlet")
public class UseCommonsFileuploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UseCommonsFileuploadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").println(request.getContextPath());

		String test = this.getServletContext().getRealPath("/upload");// /WEB-INF/files
		System.out.println("�ļ����λ��:" + test);

		String realPath = "E:\\\\javaWebRealPath\\";
		// ������ʱĿ¼�� �ϴ��ļ����ڻ��������ȷ�����ʱĿ¼��
		String tempPath = "E:\\\\javaWebUpTempPath\\";
		// �жϴ���ϴ��ļ���Ŀ¼�Ƿ���ڣ��������򴴽���
		File file = new File(realPath);
		if (!file.exists() && !file.isDirectory()) {
			System.out.println("Ŀ¼���ļ�������! ����Ŀ��Ŀ¼...");
			file.mkdir();
		}
		// �ж���ʱĿ¼�Ƿ���ڣ��������򴴽���
		File file2 = new File(tempPath);
		if (!file2.exists() && !file2.isDirectory()) {
			System.out.println("��ʱ�ļ�Ŀ¼������! ������ʱ�ļ�Ŀ¼...");
			file2.mkdir();
		}
		/**
		 * ʹ��commons-fileupload-1.2.2.jar��commons-io-1.4.jar��������ļ��ϴ����裺
		 */
		// 1�����û���:����һ��DiskFileItemFactory����
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// �����ϴ��ļ�����ʱĿ¼
		diskFileItemFactory.setRepository(file2);
		// 2�����Ĳ�����:����һ���ļ��ϴ���������
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		servletFileUpload.setHeaderEncoding("UTF-8");

		// 3���ж�enctype:�ж��ύ�����������Ƿ����ϴ���������
		if (!ServletFileUpload.isMultipartContent(request)) {
			System.out.println("�����ϴ��ļ�����ֹ!");
			return;
		}

		// 4��ʹ��ServletFileUpload�����������ϴ����ݣ�����������ص���һ��List<FileItem>���ϣ�ÿһ��FileItem��Ӧһ��Form����������
		try {
			List<FileItem> items = servletFileUpload.parseRequest(request);
			for (FileItem fileItem : items) {
				if (fileItem.isFormField()) {
					String filedName = fileItem.getFieldName();
					String filedValue = fileItem.getString("UTF-8");
					System.out.println("��ͨ�ֶ�: " + filedName + " = " + filedValue);
				} else {
					// ���fileitem�з�װ�����ϴ��ļ����õ��ϴ����ļ����ƣ�
					String fileName = fileItem.getName();
					if (fileName == null || "".equals(fileName.trim())) {
						continue;
					}

					// �����ϴ��ļ����ļ�����·������ȡ�ַ���ֻ�����ļ������֡�//��ȡ�����һ��"\"֮��+1��ȡ������һλ��"\a.txt"-->"a.txt"��
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
					// ƴ���ϴ�·�������·��+�ϴ����ļ���
					String filePath = realPath + fileName;
					// �������������
					InputStream inputStream = fileItem.getInputStream();
					OutputStream outputStream = new FileOutputStream(filePath);
					// ����һ��������
					byte[] buffer = new byte[1024];
					// �ж��������е������Ƿ��Ѿ�����ı�ʶ
					int len;
					// ѭ�������������뵽���������У�(len=in.read(buffer))��=-1�ͱ�ʾin���滹������
					while ((len = inputStream.read(buffer)) != -1) {
						outputStream.write(buffer, 0, len);
					}

					outputStream.close();
					inputStream.close();

					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					fileItem.delete();
					System.out.println("�ļ��ϴ��ɹ�");
				}

			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("��������æ���ļ��ϴ�ʧ��");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
