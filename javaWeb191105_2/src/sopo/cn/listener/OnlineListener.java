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
// @WebListener // Ϊ����ǰ��ʵ��ע�͵�
public class OnlineListener implements HttpSessionListener {

    /**
     * Default constructor. 
     */
    public OnlineListener() {
        // TODO Auto-generated constructor stub
    }

	/**���û����������ʼsessionʱ�����÷���
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
         // TODO Auto-generated method stub
    	HttpSession session = se.getSession();
    	ServletContext application = session.getServletContext();
    	// ��ȡsessionId
    	String sessionId = session.getId();
    	session.setMaxInactiveInterval(15);
    	// �����������
    	if (session.isNew()) {
			Map< String, String> online = CastHash.castHash(application.getAttribute("online"), String.class, String.class);
			String user = (String) session.getAttribute("user");
			user = ( user == null ? "�ο�" : user);
			
			if (online == null) {
				online = new HashMap< String, String>();
			}
			
			online.put( sessionId, user);
			application.setAttribute( "online", online);
		}
    }

	/**���û���������Ͽ�sessionʱ�����÷���
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
