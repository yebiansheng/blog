package org.lanqiao.entity;

public class Recommend {
    private Integer id;

    private Integer article_id;

    private Integer message_id;

    private Integer user_id;

    private Integer yesornot;

    private Article article;
    
    
    public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public Integer getMessage_id() {
        return message_id;
    }

    public void setMessage_id(Integer message_id) {
        this.message_id = message_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getYesornot() {
        return yesornot;
    }

    public void setYesornot(Integer yesornot) {
        this.yesornot = yesornot;
    }
}