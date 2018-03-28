package org.lanqiao.service;

import java.util.List;

import org.lanqiao.dao.RecommendMapper;
import org.lanqiao.entity.Recommend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RecommendServiceImpl implements RecommendService {

	@Autowired
	RecommendMapper dao;
	@Override
	public List<Recommend> getLikeArticle(int articleId) {
		// TODO Auto-generated method stub
		Recommend record=new Recommend();
		record.setYesornot(1);
		record.setArticle_id(articleId);
		return dao.selectRcommendsTrim(record);
	}
	@Override
	public List<Recommend> getDislikeArticle(int articleId) {
		// TODO Auto-generated method stub
		Recommend record=new Recommend();
		record.setYesornot(0);
		record.setArticle_id(articleId);
		return dao.selectRcommendsTrim(record);
	}
	@Override
	public List<Recommend> getLikeArticleByUser(int articleId,int userId) {
		// TODO Auto-generated method stub
		Recommend record=new Recommend();
		record.setYesornot(1);
		record.setArticle_id(articleId);
		record.setUser_id(userId);
		return dao.selectRcommendsTrim(record);
	}
	@Override
	public List<Recommend> getDislikeArticleByUser(int articleId,int userId) {
		// TODO Auto-generated method stub
		Recommend record=new Recommend();
		record.setYesornot(0);
		record.setArticle_id(articleId);
		record.setUser_id(userId);
		return dao.selectRcommendsTrim(record);
	}
	@Override
	public int insertSelective(int artID, int userId, int recommend) {
		// TODO Auto-generated method stub
		Recommend record=new Recommend();
		record.setYesornot(recommend);
		record.setArticle_id(artID);
		record.setUser_id(userId);
		return dao.insertSelective(record);
	}
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return dao.deleteByPrimaryKey(id);
	}
	@Override
	public List<Recommend> getZanTopTen() {
		// TODO Auto-generated method stub
		return dao.getZanTopTen();
	}
	
}
