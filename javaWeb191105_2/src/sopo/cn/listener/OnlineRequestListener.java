package sopo.cn.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.swing.Timer;

import sopo.cn.dao.DBDao;

/**
 * Application Lifecycle Listener implementation class OnlineRequestListener
 *
 */
public class OnlineRequestListener implements ServletContextListener {

	public final int MAX_MILLITS = 9000;
	
    /**
     * Default constructor. 
     */
    public OnlineRequestListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see OnlineRequestListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see OnlineRequestListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	new Timer( 1000 * 5, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					DBDao db = new DBDao( "com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/escshop?serverTimezone=Asia/Shanghai", "root", "F2NRnjVsKe");
					ResultSet rs = db.query( "SELECT * FROM online_information");
					StringBuffer buffer = new StringBuffer("(");
					
					while (rs.next()) {
						//如果距离时间超过上次访问时间
						if ((System.currentTimeMillis() - Long.parseLong(rs.getString("timeMillis"))) > MAX_MILLITS) {
							buffer.append("'");
							buffer.append(rs.getString(1));
							buffer.append("',");
						}
					}
					
					System.out.println( "buffer: " + buffer + ")");
					
					// System.out.println("----" + buffer.length()); // 调试用
					//有删除的记录
					/////////★★★★★★★★★★
					if (buffer.length() > 3) {
						buffer.setLength(buffer.length() - 2);// ('------','------','------', 
						buffer.append("')");
					//删除所有在指定时间内未访问的用户信息
						String sql = "delete from online_information where sessionId in" + buffer.toString();
						System.out.println("sql: " + sql); //////////
						db.delete(sql);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}).start();;
    }
}
