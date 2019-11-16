package sopo.cn.utils.lession45FromInternet;

import java.sql.Connection;

public class DBUtil {
	
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<>();
	
	public static void closeConnection() {
		Connection connection = threadLocal.get();
		if (connection != null) {
			threadLocal.set(null);
			try {
				connection.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
