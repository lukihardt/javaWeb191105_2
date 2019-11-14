package sopo.cn.listener;

import javax.servlet.ServletContext; 
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class MyServetContextListener
 *
 */
public class MyServetContextListener implements ServletContextListener, ServletContextAttributeListener,
		HttpSessionListener, HttpSessionAttributeListener, HttpSessionActivationListener, ServletRequestListener,
		ServletRequestAttributeListener {

	/**
	 * Default constructor.
	 */
	public MyServetContextListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSession session = se.getSession();
		session.setMaxInactiveInterval(9);
		String sessionId = session.getId();
		System.out.println("�����˻Ự, �ỰidΪ" + sessionId); //���chrome�������ʵ��ʱû�д����Ự��ie����������˻Ự
	}

	/**
	 * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
	 */
	public void attributeRemoved(ServletContextAttributeEvent scae) {
		// TODO Auto-generated method stub
		ServletContext application = scae.getServletContext();
		// ��ȡ�Ƴ�����������ֵ
		String name = scae.getName();
		Object value = scae.getValue();
		System.out.println(application + "��Χ���Ƴ���nameֵΪ=" + name + ", valueֵ=" + value);
	}

	/**
	 * @see ServletRequestAttributeListener#attributeAdded(ServletRequestAttributeEvent)
	 */
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
		String name = srae.getName();
		Object value = srae.getValue();
		System.out.println("��request��Χ���������Ϊ:" + name + ", ֵΪ��" + value + "������");
	}

	/**
	 * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
	 */
	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		String name = se.getName();
		Object value = se.getValue();
		System.out.println("�滻��session��Χ�ڵı���, �ɱ���Ϊ: " + name + ", ֵΪ:" + value);
	}

	/**
	 * @see HttpSessionActivationListener#sessionWillPassivate(HttpSessionEvent)
	 */
	public void sessionWillPassivate(HttpSessionEvent se) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see OnlineRequestListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext application = sce.getServletContext();
		String userName = application.getInitParameter("userName");
		System.out.println("����webӦ�õ��û�����Ϊ��" + userName);
	}

	/**
	 * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
	 */
	public void attributeAdded(ServletContextAttributeEvent scae) {
		// TODO Auto-generated method stub
		ServletContext application = scae.getServletContext();
		String name = scae.getName();
		Object value = scae.getValue();
		System.out.println(application + "��Χ��������nameֵΪ=" + name + ", valueֵ=" + value);
	}

	/**
	 * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
	 */
	public void requestDestroyed(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
		String ip = req.getRemoteAddr();
		System.out.println("ipΪ:" + ip + "���û����͵�" + req.getRequestURL() + "���������");
	}

	/**
	 * @see ServletRequestAttributeListener#attributeRemoved(ServletRequestAttributeEvent)
	 */
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
		String name = srae.getName();
		Object value = srae.getValue();
		System.out.println("�Ƴ���request��Χ��Ϊ:" + name + ", ֵΪ��" + value + "������");
	}

	/**
	 * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
	 */
	public void requestInitialized(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
		String ip = req.getRemoteAddr();
		System.out.println("ipΪ:" + ip + "���û����͵�" + req.getRequestURI() + "�����󱻳�ʼ��");
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSession session = se.getSession();
		String sessionId = session.getId();
		System.out.println("ɾ���˻Ự, �ỰidΪ" + sessionId);
	}

	/**
	 * @see HttpSessionActivationListener#sessionDidActivate(HttpSessionEvent)
	 */
	public void sessionDidActivate(HttpSessionEvent se) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see OnlineRequestListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext application = sce.getServletContext();
		String userName = application.getInitParameter("userName");
		System.out.println("�ر�webӦ�õ��û�����Ϊ��" + userName);
	}

	/**
	 * @see ServletRequestAttributeListener#attributeReplaced(ServletRequestAttributeEvent)
	 */
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
		String name = srae.getName();
		Object value = srae.getValue();
		System.out.println("�滻��request��Χ�ڱ���, �ɱ�����Ϊ:" + name + ", ֵΪ��" + value);
	}

	/**
	 * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
	 */
	public void attributeAdded(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		String name = se.getName();
		Object value = se.getValue();
		System.out.println("��session��Χ���������Ϊ:" + name + ", ֵΪ:" + value + ", �ı���");
	}

	/**
	 * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
	 */
	public void attributeRemoved(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		String name = se.getName();
		Object value = se.getValue();
		System.out.println("��session��Χ���Ƴ�����Ϊ:" + name + ", ֵΪ:" + value + ", �ı���");
	}

	/**
	 * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
	 */
	public void attributeReplaced(ServletContextAttributeEvent scae) {
		// TODO Auto-generated method stub
		ServletContext application = scae.getServletContext();
		String name = scae.getName();
		Object value = scae.getValue();
		System.out.println(application + "��Χ���滻�˱������ɱ�����= " + name + ", �ɱ���ֵ= " + value);
	}

}
