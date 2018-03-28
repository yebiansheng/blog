package org.lanqiao.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Message {
    private Integer id;

    private Integer praise;

    private String words;

    private Integer article_id;

    private User user;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date date;
    
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
    public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words == null ? null : words.trim();
    }



    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }
}