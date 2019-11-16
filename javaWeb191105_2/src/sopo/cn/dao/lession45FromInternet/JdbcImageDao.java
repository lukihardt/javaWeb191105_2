package sopo.cn.dao.lession45FromInternet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sopo.cn.dao.DBDao;
import sopo.cn.model.lession45FromInternet.Image;

public class JdbcImageDao implements ImageDao{

	@Override
	public void save(Image image) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO t_image(`p_name`, `p_size`, `p_pic`, `p_time`) VALUES(?, ?, ?, ?);";
		try {
			Connection connection = DBDao.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);	
			preparedStatement.setString(1, image.getName());
			preparedStatement.setLong(2, image.getSize());
			preparedStatement.setString(3, image.getPic());
			preparedStatement.setTimestamp(4, image.getTime());
			preparedStatement.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException("数据库访问异常", e);
		} finally {
			// DBUtil.closeConnection();
		}
	}

	@Override
	public List<Image> finaAll() {
		// TODO Auto-generated method stub
		
		String sql = "SELECT * FROM t_image ORDER BY p_time DESC;";
		
		try {
			Connection connection = DBDao.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			List<Image> list = new ArrayList<>();
			
			while (rs.next()) {
				Image image = new Image();
				image.setId(rs.getInt(1));
				image.setName(rs.getString(2));
				image.setSize(rs.getLong(3));
				image.setPic(rs.getString(4));
				image.setTime(rs.getTimestamp(5));
				list.add(image);
			}
			return list;
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException( "数据库访问异常", e);
		}
	}

}
