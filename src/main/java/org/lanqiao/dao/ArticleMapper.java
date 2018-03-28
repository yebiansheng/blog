package org.lanqiao.dao;

import java.util.HashMap;
import java.util.List;

import org.lanqiao.entity.Article;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);
    
    List<Article> getAllArticle();
    
    List<Article> getSimpleArticleByPage(int begin,int end); 
    
    Article getArticleById(Integer id);
    
    Article getArticleAndMsgById(Integer id);
    
    List<Article> getTopTenArticle();
    
    int getArticleNum();
    
    List<Article> searchSimpleArticleByPage(HashMap<String, Object> map); 
    
    List<Article> searchSimpleArticleNum(HashMap<String, Object> map); 
    
    List<Article> getSimpleDiaryByPage(HashMap<String, Object> map); 
    
    int getSimpleDiaryNum(HashMap<String, Object> map);
    
    int updatePageViewAdd(int id);
}