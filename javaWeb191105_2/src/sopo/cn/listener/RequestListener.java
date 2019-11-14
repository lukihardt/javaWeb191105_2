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
		System.out.println("----发向" + request.getRequestURL() + ", 被销毁");
	}

	/**
	 * @see ServletRequestAttributeListener#attributeRemoved(ServletRequestAttributeEvent)
	 */
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
		ServletContext servletContext = srae.getServletContext();
		String name = srae.getName();
		Object value = srae.getValue();
		System.out.println(servletContext + "范围内移除了属性名为name=" + name + "\t" + "属性值为value=" + value);
	}

	/**
	 * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
	 */
	public void requestInitialized(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
		System.out.println("----发向" + request.getRequestURI() + ", 被初始化");
		HttpSession session = request.getSession();
		//获取sessionId
		String sessionId = session.getId();
		//获取IP地址
		String ip = request.getRemoteAddr();
		//获取正在访问的页面
		String page = request.getRequestURI();
		//获取用户名称
		String user = (String) session.getAttribute("user");

		user = user == null ? "游客" : user;
		
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
		System.out.println( request + "范围内新增了属性名为name=" + name + "\t" + "属性值为value=" + value);
	}

	/**
	 * @see ServletRequestAttributeListener#attributeReplaced(ServletRequestAttributeEvent)
	 */
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub	
		ServletContext servletContext = srae.getServletContext();
		String name = srae.getName();
		Object value = srae.getValue();
		System.out.println( servletContext + "范围内替换了属性,被替换属性名name=" + name + "\t" + "属性值为value=" + value);
	}

}
