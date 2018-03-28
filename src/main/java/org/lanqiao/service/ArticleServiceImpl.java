package org.lanqiao.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.lanqiao.dao.ArticleMapper;
import org.lanqiao.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	ArticleMapper dao;
	@Override
	public List<Article> getAllArticle() {
		// TODO Auto-generated method stub
		return dao.getAllArticle();
	}
	@Override
	public List<Article> getSimpleArticleByPage(int page, int number) {
		// TODO Auto-generated method stub
		return dao.getSimpleArticleByPage((page-1)*number, number);
	}
	@Override
	public Article getArticleById(Integer id) {
		// TODO Auto-generated method stub
		return dao.getArticleById(id);
	}
	@Override
	public Article getArticleAndMsgById(Integer id) {
		// TODO Auto-generated method stub
		return dao.getArticleAndMsgById(id);
	}
	@Override
	public List<Article> getTopTenArticle() {
		// TODO Auto-generated method stub
		return dao.getTopTenArticle();
	}
	@Override
	public int getArticleNum() {
		// TODO Auto-generated method stub
		return dao.getArticleNum();
	}
	@Override
	public List<Article> searchSimpleArticleByPage(String title,int page,int size) {
		// TODO Auto-generated method stub
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("title", "%"+title+"%");
		map.put("size", size);
		map.put("start",(page-1)*size);
		return dao.searchSimpleArticleByPage(map);
	}
	@Override
	public int searchSimpleArticleNum(String title) {
		// TODO Auto-generated method stub
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("title", "%"+title+"%");
		return dao.searchSimpleArticleNum(map).size();
	}
	@Override
	public List<Article> getSimpleDiaryByPage(int sortid, int page, int size) {
		// TODO Auto-generated method stub
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("id", sortid);
		map.put("start", (page-1)*size);
		map.put("size", size);
		return dao.getSimpleDiaryByPage(map);
	}
	@Override
	public int getSimpleDiaryNum(int sortid) {
		// TODO Auto-generated method stub
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("id", sortid);
		return dao.getSimpleDiaryNum(map);
	}
	@Override
	public int insertSelective(Article record) {
		// TODO Auto-generated method stub
		record.setDate(new Date());
		record.setPage_view(0);
		return dao.insertSelective(record);
	}
	@Override
	public int updatePageViewAdd(int id) {
		// TODO Auto-generated method stub
		return dao.updatePageViewAdd(id);
	}
	

}
