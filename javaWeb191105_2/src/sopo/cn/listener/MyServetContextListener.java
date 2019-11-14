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
		System.out.println("建立了会话, 会话id为" + sessionId); //☆☆chrome浏览器在实验时没有创建会话，ie浏览器创建了会话
	}

	/**
	 * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
	 */
	public void attributeRemoved(ServletContextAttributeEvent scae) {
		// TODO Auto-generated method stub
		ServletContext application = scae.getServletContext();
		// 获取移除的属性名与值
		String name = scae.getName();
		Object value = scae.getValue();
		System.out.println(application + "范围内移除了name值为=" + name + ", value值=" + value);
	}

	/**
	 * @see ServletRequestAttributeListener#attributeAdded(ServletRequestAttributeEvent)
	 */
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
		String name = srae.getName();
		Object value = srae.getValue();
		System.out.println("向request范围内添加了名为:" + name + ", 值为：" + value + "的属性");
	}

	/**
	 * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
	 */
	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		String name = se.getName();
		Object value = se.getValue();
		System.out.println("替换了session范围内的变量, 旧变量为: " + name + ", 值为:" + value);
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
		System.out.println("启动web应用的用户名字为：" + userName);
	}

	/**
	 * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
	 */
	public void attributeAdded(ServletContextAttributeEvent scae) {
		// TODO Auto-generated method stub
		ServletContext application = scae.getServletContext();
		String name = scae.getName();
		Object value = scae.getValue();
		System.out.println(application + "范围内新增了name值为=" + name + ", value值=" + value);
	}

	/**
	 * @see ServletRequestListener#requestDestroyed(ServletRequestEvent)
	 */
	public void requestDestroyed(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
		String ip = req.getRemoteAddr();
		System.out.println("ip为:" + ip + "的用户发送到" + req.getRequestURL() + "的请求结束");
	}

	/**
	 * @see ServletRequestAttributeListener#attributeRemoved(ServletRequestAttributeEvent)
	 */
	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
		String name = srae.getName();
		Object value = srae.getValue();
		System.out.println("移除了request范围名为:" + name + ", 值为：" + value + "的属性");
	}

	/**
	 * @see ServletRequestListener#requestInitialized(ServletRequestEvent)
	 */
	public void requestInitialized(ServletRequestEvent sre) {
		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
		String ip = req.getRemoteAddr();
		System.out.println("ip为:" + ip + "的用户发送到" + req.getRequestURI() + "的请求被初始化");
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSession session = se.getSession();
		String sessionId = session.getId();
		System.out.println("删除了会话, 会话id为" + sessionId);
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
		System.out.println("关闭web应用的用户名字为：" + userName);
	}

	/**
	 * @see ServletRequestAttributeListener#attributeReplaced(ServletRequestAttributeEvent)
	 */
	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		// TODO Auto-generated method stub
		String name = srae.getName();
		Object value = srae.getValue();
		System.out.println("替换了request范围内变量, 旧变量名为:" + name + ", 值为：" + value);
	}

	/**
	 * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
	 */
	public void attributeAdded(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		String name = se.getName();
		Object value = se.getValue();
		System.out.println("向session范围内添加了名为:" + name + ", 值为:" + value + ", 的变量");
	}

	/**
	 * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
	 */
	public void attributeRemoved(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		String name = se.getName();
		Object value = se.getValue();
		System.out.println("向session范围内移除了名为:" + name + ", 值为:" + value + ", 的变量");
	}

	/**
	 * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
	 */
	public void attributeReplaced(ServletContextAttributeEvent scae) {
		// TODO Auto-generated method stub
		ServletContext application = scae.getServletContext();
		String name = scae.getName();
		Object value = scae.getValue();
		System.out.println(application + "范围内替换了变量，旧变量名= " + name + ", 旧变量值= " + value);
	}

}
