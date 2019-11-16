package sopo.cn.dao.lession45FromInternet;

import java.util.List;

import sopo.cn.model.lession45FromInternet.Image;

public interface ImageDao {
	public void save(Image image);
	public List<Image> finaAll();
}
