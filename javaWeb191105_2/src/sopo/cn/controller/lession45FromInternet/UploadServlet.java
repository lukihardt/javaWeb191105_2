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
		// ��ȡ�����ύ�������ļ���Ϣ 
		/*
		 * ��ȡ�����ύ���ļ���Ϣ,ʹ��(commons-fileupload.jar), commons-fileupload.jar��Apache���µĹ��߰�(��һ���ϴ��ļ��Ľ�����),����ʹ��������request����Ϣ, �����Խ��ֽڴ�request������ȡ����,���ҷ�װ�ɶ���
		 */
		// ����һ��imageDao
		ImageDao imageDao = new JdbcImageDao();
		DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
		ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
		servletFileUpload.setFileSizeMax(18 * 1024 * 1024);
		try {
			List<FileItem> list = servletFileUpload.parseRequest(request);
			for (FileItem item : list) {
				System.out.println( "�ļ�����:" + item.getName());
				System.out.println( "�ļ���С" + item.getSize());
				System.out.println( "�ļ�����" + item.getContentType());
				System.out.println( "�ϴ���Ԫ����" + item.getFieldName());
				// ���ļ����浽Ŀ��λ�� F����, ʹ��UUID����Ψһ�Դ洢��ͼƬ��,��ֹͼƬ֮���໥����
				UUID uuid = UUID.randomUUID();
				String destName = uuid.toString().replaceAll( "-", "");
				// ��ȡͼƬ����չ��
				int index = item.getName().lastIndexOf( ".");
				String type = item.getName().substring(index);
				System.out.println(type);
				System.out.println("==" + item.getName());
				// String type = test1.substring(test1.lastIndexOf( "."));
				//��ͼƬ�ϴ���tomcat/webapps/��Ŀ��imagesĿ¼, ��ȡ����·�� 
				String realPath = request.getServletContext().getRealPath("image");
				File pathFile = new File(realPath);
				
				if (!pathFile.exists()) {
					pathFile.mkdir();
				}
				
				File destFile = new File(realPath + "\\" + destName + type);
				System.out.println(destFile.getPath());
				
				item.write(destFile);
				// ���ļ���Ϣд�����ݿ�
				Image image = new Image();
				image.setName(item.getName());
				image.setSize(item.getSize());
				// �洢���ļ���
				image.setPic(destName + type);
				// ��ȡ��ǰ�ϴ�ʱ��
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
