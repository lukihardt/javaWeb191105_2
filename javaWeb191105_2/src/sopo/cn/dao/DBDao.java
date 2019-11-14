package sopo.cn.dao;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBDao {
	
	public static Connection connection = null;
	public static PreparedStatement preparedPreparedStatement = null;
	
	//C3p0
	private static DataSource dataSource = null;
	static {
		dataSource = new ComboPooledDataSource("mysql");
	}
	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
	//构造方法
	public DBDao( String driverClass, String url, String username, String password) throws ClassNotFoundException, SQLException {
		Class.forName(driverClass);
		connection = DriverManager.getConnection( url, username, password);
	}
	
	public ResultSet query( String sql, Boolean b, String ssid) throws SQLException {
		preparedPreparedStatement = connection.prepareStatement( sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		preparedPreparedStatement.setString(1, ssid);
		ResultSet rs = preparedPreparedStatement.executeQuery();
		return rs;
	}
	
	public void insert( String ssid, String user, String ip, String page, String timeMillis) throws SQLException {
		String sql = "insert into online_information values(?, ?, ?, ?, ?);";
		preparedPreparedStatement = connection.prepareStatement( sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		preparedPreparedStatement.setString( 1, ssid);
		preparedPreparedStatement.setString( 2, user);
		preparedPreparedStatement.setString( 3, ip);
		preparedPreparedStatement.setString( 4, page);
		preparedPreparedStatement.setString( 5, timeMillis);
		preparedPreparedStatement.executeUpdate();
	}

	public ResultSet query(String sql) throws SQLException {
		// TODO Auto-generated method stub
		preparedPreparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rs = preparedPreparedStatement.executeQuery();
		return rs;
	}

	public void delete(String sql) throws SQLException {
		// TODO Auto-generated method stub
		preparedPreparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		preparedPreparedStatement.execute();
	}
}
