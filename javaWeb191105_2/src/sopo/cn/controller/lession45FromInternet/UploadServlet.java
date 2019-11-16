package sopo.cn.controller.lession45FromInternet;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import sopo.cn.dao.lession45FromInternet.ImageDao;
import sopo.cn.dao.lession45FromInternet.JdbcImageDao;
import sopo.cn.model.lession45FromInternet.Image;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").println(request.getContextPath());
		// 获取请求提交过来的文件信息 
		/*
		 * 获取请求提交的文件信息,使用(commons-fileupload.jar), commons-fileupload.jar是Apache旗下的工具包(是一款上传文件的解析器),我们使用它解析request的信息, 它可以将字节从request里面提取出来,并且封装成对象
		 */
		// 构建一个imageDao
		ImageDao imageDao = new JdbcImageDao();
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		servletFileUpload.setFileSizeMax(18 * 1024 * 1024);
		try {
			List<FileItem> list = servletFileUpload.parseRequest(request);
			for (FileItem item : list) {
				System.out.println( "文件名称:" + item.getName());
				System.out.println( "文件大小" + item.getSize());
				System.out.println( "文件类型" + item.getContentType());
				System.out.println( "上传的元素名" + item.getFieldName());
				// 将文件保存到目标位置 F盘下, 使用UUID生成唯一性存储型图片名,防止图片之间相互覆盖
				UUID uuid = UUID.randomUUID();
				String destName = uuid.toString().replaceAll( "-", "");
				// 获取图片的扩展名
				int index = item.getName().lastIndexOf( ".");
				String type = item.getName().substring(index);
				System.out.println(type);
				System.out.println("==" + item.getName());
				// String type = test1.substring(test1.lastIndexOf( "."));
				//将图片上传到tomcat/webapps/项目的images目录, 获取物理路径 
				String realPath = request.getServletContext().getRealPath("image");
				File pathFile = new File(realPath);
				
				if (!pathFile.exists()) {
					pathFile.mkdir();
				}
				
				File destFile = new File(realPath + "\\" + destName + type);
				System.out.println(destFile.getPath());
				
				item.write(destFile);
				// 将文件信息写入数据库
				Image image = new Image();
				image.setName(item.getName());
				image.setSize(item.getSize());
				// 存储的文件名
				image.setPic(destName + type);
				// 获取当前上传时间
				image.setTime( new Timestamp(System.currentTimeMillis()));
				imageDao.save(image);
			}
			
			request.getRequestDispatcher("/ViewServlet").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
