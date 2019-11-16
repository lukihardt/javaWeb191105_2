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
		System.out.println("文件存放位置:" + test);

		String realPath = "E:\\\\javaWebRealPath\\";
		// 设置临时目录。 上传文件大于缓冲区则先放于临时目录中
		String tempPath = "E:\\\\javaWebUpTempPath\\";
		// 判断存放上传文件的目录是否存在（不存在则创建）
		File file = new File(realPath);
		if (!file.exists() && !file.isDirectory()) {
			System.out.println("目录或文件不存在! 创建目标目录...");
			file.mkdir();
		}
		// 判断临时目录是否存在（不存在则创建）
		File file2 = new File(tempPath);
		if (!file2.exists() && !file2.isDirectory()) {
			System.out.println("临时文件目录不存在! 创建临时文件目录...");
			file2.mkdir();
		}
		/**
		 * 使用commons-fileupload-1.2.2.jar和commons-io-1.4.jar组件处理文件上传步骤：
		 */
		// 1、设置环境:创建一个DiskFileItemFactory工厂
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		// 设置上传文件的临时目录
		diskFileItemFactory.setRepository(file2);
		// 2、核心操作类:创建一个文件上传解析器。
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		servletFileUpload.setHeaderEncoding("UTF-8");

		// 3、判断enctype:判断提交上来的数据是否是上传表单的数据
		if (!ServletFileUpload.isMultipartContent(request)) {
			System.out.println("不是上传文件，终止!");
			return;
		}

		// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
		try {
			List<FileItem> items = servletFileUpload.parseRequest(request);
			for (FileItem fileItem : items) {
				if (fileItem.isFormField()) {
					String filedName = fileItem.getFieldName();
					String filedValue = fileItem.getString("UTF-8");
					System.out.println("普通字段: " + filedName + " = " + filedValue);
				} else {
					// 如果fileitem中封装的是上传文件，得到上传的文件名称，
					String fileName = fileItem.getName();
					if (fileName == null || "".equals(fileName.trim())) {
						continue;
					}

					// 处理上传文件的文件名的路径，截取字符串只保留文件名部分。//截取留最后一个"\"之后，+1截取向右移一位（"\a.txt"-->"a.txt"）
					fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
					// 拼接上传路径。存放路径+上传的文件名
					String filePath = realPath + fileName;
					// 构建输入输出流
					InputStream inputStream = fileItem.getInputStream();
					OutputStream outputStream = new FileOutputStream(filePath);
					// 创建一个缓冲区
					byte[] buffer = new byte[1024];
					// 判断输入流中的数据是否已经读完的标识
					int len;
					// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))！=-1就表示in里面还有数据
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
					System.out.println("文件上传成功");
				}

			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("服务器繁忙，文件上传失败");
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
