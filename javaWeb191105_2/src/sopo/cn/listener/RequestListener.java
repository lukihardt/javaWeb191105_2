package sopo.cn.listener;

import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import sopo.cn.dao.DBDao;

/**
 * Application Lifecycle Listener implementation class RequestListener
 *
 */
public class RequestListener implements ServletRequestListener, ServletRequestAttributeListener {

	/**
	 * Default constructor.
	 */
	public RequestListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
	 */
	public void requestDestroyed(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
		System.out.println("----����" + request.getRequestURL() + ", ������");
	}

	/**
	 * @see ServletRequestAttributeListener#attributeRemoved(ServletRequestAttributeEvent)
	 */
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
		ServletContext servletContext = srae.getServletContext();
		String name = srae.getName();
		Object value = srae.getValue();
		System.out.println(servletContext + "��Χ���Ƴ���������Ϊname=" + name + "\t" + "����ֵΪvalue=" + value);
	}

	/**
	 * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
	 */
	public void requestInitialized(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
		System.out.println("----����" + request.getRequestURI() + ", ����ʼ��");
		HttpSession session = request.getSession();
		//��ȡsessionId
		String sessionId = session.getId();
		//��ȡIP��ַ
		String ip = request.getRemoteAddr();
		//��ȡ���ڷ��ʵ�ҳ��
		String page = request.getRequestURI();
		//��ȡ�û�����
		String user = (String) session.getAttribute("user");

		user = user == null ? "�ο�" : user;
		
		try {
			DBDao db = new DBDao( "com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/escshop?serverTimezone=Asia/Shanghai", "root", "F2NRnjVsKe");
			ResultSet rs = db.query("SELECT * FROM online_information WHERE sessionId=?", true, sessionId);
			
			if( rs.next()) {
				rs.updateString( 4, page);
				rs.updateString( 5, String.valueOf(System.currentTimeMillis()));
				rs.updateRow();
				rs.close();
			} else {
				db.insert(sessionId, user, ip, page, String.valueOf(System.currentTimeMillis()));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	/**
	 * @see ServletRequestAttributeListener#attributeAdded(ServletRequestAttributeEvent)
	 */
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
		ServletContext request = srae.getServletContext();
		String name = srae.getName();
		Object value = srae.getValue();
		System.out.println( request + "��Χ��������������Ϊname=" + name + "\t" + "����ֵΪvalue=" + value);
	}

	/**
	 * @see ServletRequestAttributeListener#attributeReplaced(ServletRequestAttributeEvent)
	 */
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub	
		ServletContext servletContext = srae.getServletContext();
		String name = srae.getName();
		Object value = srae.getValue();
		System.out.println( servletContext + "��Χ���滻������,���滻������name=" + name + "\t" + "����ֵΪvalue=" + value);
	}

}
