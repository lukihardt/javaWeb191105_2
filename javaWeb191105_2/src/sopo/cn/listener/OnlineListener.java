package sopo.cn.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import sopo.cn.utils.CastHash;

/**
 * Application Lifecycle Listener implementation class OnlineListener
 *
 */
// @WebListener // 为了现前的实例注释掉
public class OnlineListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public OnlineListener() {
        // TODO Auto-generated constructor stub
    }

	/**当用户与服务器开始session时触发该方法
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    	HttpSession session = se.getSession();
    	ServletContext application = session.getServletContext();
    	// 获取sessionId
    	String sessionId = session.getId();
    	session.setMaxInactiveInterval(15);
    	// 如果是新连接
    	if (session.isNew()) {
			Map< String, String> online = CastHash.castHash(application.getAttribute("online"), String.class, String.class);
			String user = (String) session.getAttribute("user");
			user = ( user == null ? "游客" : user);
			
			if (online == null) {
				online = new HashMap< String, String>();
			}
			
			online.put( sessionId, user);
			application.setAttribute( "online", online);
		}
    }

	/**当用户与服务器断开session时触发该方法
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    	HttpSession session = se.getSession();
    	ServletContext application = session.getServletContext();
    	String sessionId = session.getId();
    	System.out.println( "sessionId: " + sessionId);
    	HashMap< String, String> online = CastHash.castHash(application.getAttribute("online"), String.class, String.class);
    	
    	if (online != null) {
			online.remove(sessionId);
		}
    	
    	application.setAttribute( "online", online);
    }
	
}
