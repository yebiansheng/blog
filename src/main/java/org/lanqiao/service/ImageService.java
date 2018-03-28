package org.lanqiao.service;

import java.util.List;

import org.lanqiao.entity.Image;

public interface ImageService {

	List<Image> getImageByPage(int page,int size);
	
	int getImageNum();
}
