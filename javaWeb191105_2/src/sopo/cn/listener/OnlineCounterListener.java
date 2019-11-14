package sopo.cn.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class OnlineCounterListener
 *
 */
public class OnlineCounterListener implements HttpSessionListener {

	@SuppressWarnings("rawtypes")
	private static Map sessionMap = new HashMap();

	/**
	 * Default constructor.
	 */
	public OnlineCounterListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
	 */
	@SuppressWarnings("unchecked")
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		boolean flag = true;
		for (Object key : sessionMap.keySet()) {
			if (key == se.getSession().getId()) {
				flag = false;
			}
		}
		if (flag) {
			sessionMap.put(se.getSession().getId(), se.getSession());
		}
		ServletContext ctx = se.getSession().getServletContext();
		ctx.setAttribute("numSessions", sessionMap.size());
	}

	/**
	 * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
	 */
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
	}

}
