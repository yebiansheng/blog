package org.lanqiao.service;

import java.util.List;

import org.lanqiao.entity.Recommend;

public interface RecommendService {

	public List<Recommend> getLikeArticle(int articleId);
	
	public List<Recommend> getDislikeArticle(int articleId);
	
	public List<Recommend> getLikeArticleByUser(int articleId,int userId);
	
	public List<Recommend> getDislikeArticleByUser(int articleId,int userId);
	
	int insertSelective(int artID,int userId,int recommend);
	
	int deleteByPrimaryKey(Integer id);
	
	List<Recommend> getZanTopTen();
}
