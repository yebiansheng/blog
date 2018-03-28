package org.lanqiao.service;

import java.util.List;

import org.lanqiao.entity.Article;

public interface ArticleService {
	public List<Article> getAllArticle();
	
	List<Article> getSimpleArticleByPage(int page,int number); 
	
	Article getArticleById(Integer id);
	
	Article getArticleAndMsgById(Integer id);
	
	List<Article> getTopTenArticle();
	
	int getArticleNum();

	List<Article> searchSimpleArticleByPage(String title, int page, int size); 
	
	int searchSimpleArticleNum(String title); 
	
	List<Article> getSimpleDiaryByPage(int id,int page,int size); 
    
    int getSimpleDiaryNum(int id);
    
    int insertSelective(Article record);
    
    int updatePageViewAdd(int id);
}
