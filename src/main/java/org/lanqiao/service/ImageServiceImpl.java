package org.lanqiao.service;

import java.util.List;

import org.lanqiao.dao.ImageMapper;
import org.lanqiao.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ImageServiceImpl implements ImageService {

	@Autowired
	ImageMapper dao;
	@Override
	public List<Image> getImageByPage(int page, int size) {
		// TODO Auto-generated method stub
		return dao.getImageByPage((page-1)*size, size);
	}
	@Override
	public int getImageNum() {
		// TODO Auto-generated method stub
		return dao.getImageNum();
	}

	
}
